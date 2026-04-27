import { useEffect, useMemo, useState } from 'react'
import './App.css'
import RulesSetup from './components/RulesSetup'
import MatchPage from './components/MatchPage'

const DEFAULT_RULES = {
  homeTeam: 'HOME',
  awayTeam: 'AWAY',
  quarterMinutes: 12,
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
  const [actionInput, setActionInput] = useState('')
  const [actions, setActions] = useState([])
  const [result, setResult] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  useEffect(() => {
    if (phase !== 'match' || !clockRunning) {
      return undefined
    }

    const intervalId = window.setInterval(() => {
      setSecondsLeft((previous) => {
        if (previous > 0) {
          return previous - 1
        }

        setCurrentQuarter((quarter) => {
          if (quarter >= 4) {
            setClockRunning(false)
            return quarter
          }
          return quarter + 1
        })

        return rules.quarterMinutes * 60
      })
    }, 1000)

    return () => window.clearInterval(intervalId)
  }, [phase, clockRunning, rules.quarterMinutes])

  const generatedCode = useMemo(() => {
    const quarterSet = new Set(actions.map((item) => item.quarter))
    const quarterNumbers = Array.from(quarterSet).sort((a, b) => a - b)
    const maxQuarter = Math.max(1, ...quarterNumbers)

    const lines = [`GAME ${rules.homeTeam} vs ${rules.awayTeam};`, '']

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
  }, [actions, rules.homeTeam, rules.awayTeam])

  const handleStartMatch = (configuredRules) => {
    setRules(configuredRules)
    setCurrentQuarter(1)
    setSecondsLeft(configuredRules.quarterMinutes * 60)
    setClockRunning(true)
    setActions([])
    setActionInput('')
    setResult(null)
    setError(null)
    setPhase('match')
  }

  const handleBackToRules = () => {
    setClockRunning(false)
    setPhase('rules')
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
        body: JSON.stringify({ code: generatedCode })
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
        <RulesSetup onStart={handleStartMatch} />
      ) : (
        <MatchPage
          rules={rules}
          currentQuarter={currentQuarter}
          clockText={formatClock(secondsLeft)}
          clockRunning={clockRunning}
          actionInput={actionInput}
          onActionChange={setActionInput}
          onActionSend={handleAddAction}
          actions={actions}
          generatedCode={generatedCode}
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