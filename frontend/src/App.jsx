import { useEffect, useMemo, useState } from 'react'
import './App.css'
import RulesSetup from './components/RulesSetup'
import MatchPage from './components/MatchPage'

const DEFAULT_RULES = {
  homeTeam: 'Home Team',
  homeAlias: 'HOME',
  awayTeam: 'Away Team',
  awayAlias: 'AWAY',
  quarterMinutes: 12,
  maxTeamSize: 5,
  quarters: 4,
  homeRoster: '',
  awayRoster: '',
  gameRules: ''
}

const formatClock = (secondsLeft) => {
  const minutes = Math.floor(secondsLeft / 60)
  const seconds = secondsLeft % 60
  return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`
}

function App() {
  const [phase, setPhase] = useState('rules')
  const [rules, setRules] = useState(DEFAULT_RULES)
  const [currentQuarter, setCurrentQuarter] = useState(1)
  const [secondsLeft, setSecondsLeft] = useState(DEFAULT_RULES.quarterMinutes * 60)
  const [clockRunning, setClockRunning] = useState(false)
  const [clockPaused, setClockPaused] = useState(false)
  const [actionInput, setActionInput] = useState('')
  const [actions, setActions] = useState([])
  const [result, setResult] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)
  const [rulesError, setRulesError] = useState(null)
  const [rulesLoading, setRulesLoading] = useState(false)
  const [dslDraft, setDslDraft] = useState('')

  useEffect(() => {
    if (phase !== 'match' || !clockRunning || clockPaused) {
      return undefined
    }

    const totalQuarters = Math.max(1, Number(rules.quarters) || 4)

    const intervalId = window.setInterval(() => {
      setSecondsLeft((previous) => {
        if (previous > 0) {
          return previous - 1
        }

        setClockRunning(false)
        setClockPaused(false)
        return 0
      })
    }, 1000)

    return () => window.clearInterval(intervalId)
  }, [phase, clockRunning, clockPaused, rules.quarterMinutes, rules.quarters])

  const normalizeRoster = (rosterText) => {
    if (!rosterText.trim()) {
      return '#5, #7, #11'
    }

    return rosterText
      .split(',')
      .map((entry) => entry.trim())
      .filter(Boolean)
      .map((entry) => (entry.startsWith('#') ? entry : `#${entry}`))
      .join(', ')
  }

  const generatedCode = useMemo(() => {
    const maxQuarter = Math.max(1, Number(rules.quarters) || 4)

    const lines = [
      'RULES',
      `    max_team_size = ${Number(rules.maxTeamSize) || 5};`,
      `    quarters = ${maxQuarter};`,
      `    quarter_length = ${Number(rules.quarterMinutes) || 12};`,
      `    ROSTER ${rules.homeAlias || 'HOME'}: ${normalizeRoster(rules.homeRoster)};`,
      `    ROSTER ${rules.awayAlias || 'AWAY'}: ${normalizeRoster(rules.awayRoster)};`,
      'END;',
      '',
      `GAME ${rules.homeTeam} as ${rules.homeAlias || 'HOME'} vs ${rules.awayTeam} as ${rules.awayAlias || 'AWAY'};`,
      ''
    ]

    for (let quarter = 1; quarter <= maxQuarter; quarter += 1) {
      lines.push(`QUARTER ${quarter}`)
      actions
        .filter((item) => item.quarter === quarter)
        .forEach((item) => {
          lines.push(`    ${item.text}`)
        })
      lines.push('END;', '')
    }

    lines.push('BOXSCORE;')
    return lines.join('\n')
  }, [actions, rules.awayAlias, rules.awayTeam, rules.homeAlias, rules.homeRoster, rules.homeTeam, rules.maxTeamSize, rules.quarterMinutes, rules.quarters, rules.awayRoster])

  useEffect(() => {
    setDslDraft(generatedCode)
  }, [generatedCode])

  const handleStartMatch = async (configuredRules) => {
    setRulesLoading(true)
    setRulesError(null)
    try {
      const maxQuarter = Math.max(1, Number(configuredRules.quarters) || 4)
      const validateCode = [
        'RULES',
        `    max_team_size = ${Number(configuredRules.maxTeamSize) || 5};`,
        `    quarters = ${maxQuarter};`,
        `    quarter_length = ${Number(configuredRules.quarterMinutes) || 12};`,
        `    ROSTER ${configuredRules.homeAlias || 'HOME'}: ${normalizeRoster(configuredRules.homeRoster)};`,
        `    ROSTER ${configuredRules.awayAlias || 'AWAY'}: ${normalizeRoster(configuredRules.awayRoster)};`,
        'END;',
        '',
        `GAME ${configuredRules.homeTeam} as ${configuredRules.homeAlias || 'HOME'} vs ${configuredRules.awayTeam} as ${configuredRules.awayAlias || 'AWAY'};`,
        'QUARTER 1',
        'END;'
      ].join('\n')

      const response = await fetch('/api/validate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code: validateCode })
      })
      const data = await response.json()
      if (data.errors?.length) {
        setRulesError(data.errors.join(' '))
        return
      }

      setRules(configuredRules)
      setCurrentQuarter(1)
      setSecondsLeft(configuredRules.quarterMinutes * 60)
      setClockRunning(true)
      setClockPaused(false)
      setActions([])
      setActionInput('')
      setResult(null)
      setError(null)
      setPhase('match')
    } catch (err) {
      setRulesError('Failed to validate rules: ' + err.message)
    } finally {
      setRulesLoading(false)
    }
  }

  const handleBackToRules = () => {
    setClockRunning(false)
    setClockPaused(false)
    setPhase('rules')
  }

  const handleClockControl = () => {
    const totalQuarters = Math.max(1, Number(rules.quarters) || 4)

    if (!clockRunning) {
      if (secondsLeft === 0 && currentQuarter < totalQuarters) {
        setCurrentQuarter((quarter) => quarter + 1)
        setSecondsLeft(rules.quarterMinutes * 60)
      }
      setClockRunning(true)
      setClockPaused(false)
      return
    }
    setClockPaused((current) => !current)
  }

  const handleAddAction = () => {
    const trimmed = actionInput.trim()
    if (!trimmed) {
      return
    }

    const normalizedText = trimmed.endsWith(';') ? trimmed : `${trimmed};`

    const entry = {
      id: crypto.randomUUID(),
      quarter: currentQuarter,
      clock: formatClock(secondsLeft),
      text: normalizedText
    }

    setActions((previous) => [...previous, entry])
    setActionInput('')
  }

  const handleParse = async () => {
    setLoading(true)
    setError(null)
    try {
      const response = await fetch('/api/parse', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code: dslDraft || generatedCode })
      })
      const data = await response.json()
      setResult(data)
    } catch (err) {
      setError('Failed to connect to backend: ' + err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="app-shell">
      <h1>🏀 Basketball Boxscore DSL</h1>
      {phase === 'rules' ? (
        <RulesSetup onStart={handleStartMatch} error={rulesError} loading={rulesLoading} />
      ) : (
        <MatchPage
          rules={rules}
          currentQuarter={currentQuarter}
          secondsLeft={secondsLeft}
          clockText={formatClock(secondsLeft)}
          clockRunning={clockRunning}
          clockPaused={clockPaused}
          actionInput={actionInput}
          onActionChange={setActionInput}
          onActionSend={handleAddAction}
          onClockControl={handleClockControl}
          actions={actions}
          generatedCode={generatedCode}
          dslDraft={dslDraft}
          onDslDraftChange={setDslDraft}
          loading={loading}
          error={error}
          onParse={handleParse}
          onBackToRules={handleBackToRules}
          result={result}
        />
      )}
    </div>
  )
}

export default App