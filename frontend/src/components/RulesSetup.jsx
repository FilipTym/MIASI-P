import { useState } from 'react'
import './RulesSetup.css'

function RulesSetup({ onStart, error, loading }) {
  const [homeTeam, setHomeTeam] = useState('TeamA')
  const [homeAlias, setHomeAlias] = useState('H')
  const [awayTeam, setAwayTeam] = useState('TeamB')
  const [awayAlias, setAwayAlias] = useState('A')
  const [quarterMinutes, setQuarterMinutes] = useState(12)
  const [maxTeamSize, setMaxTeamSize] = useState(5)
  const [quarters, setQuarters] = useState(4)
  const [homeRoster, setHomeRoster] = useState('#4, #5, #11, #23, #33')
  const [awayRoster, setAwayRoster] = useState('#1, #2, #3, #4, #5')
  const [gameRules, setGameRules] = useState('game_style = standard;')

  const extraRuleLines = gameRules
    .split('\n')
    .map((line) => line.trim())
    .filter(Boolean)
    .map((line) => `    ${line}`)
    .join('\n')

  const rulesPreview = `RULES
    max_team_size = ${maxTeamSize};
    quarters = ${quarters};
    quarter_length = ${quarterMinutes};
${extraRuleLines ? `${extraRuleLines}\n` : ''}    ROSTER ${homeAlias || 'HOME'}: ${homeRoster || '#5, #7, #11'};
    ROSTER ${awayAlias || 'AWAY'}: ${awayRoster || '#2, #3, #9'};
END;

GAME ${homeTeam || 'Home Team'} as ${homeAlias || 'HOME'} vs ${awayTeam || 'Away Team'} as ${awayAlias || 'AWAY'};`

  const handleSubmit = (event) => {
    event.preventDefault()
    onStart({
      homeTeam: homeTeam.trim() || 'TeamA',
      homeAlias: homeAlias.trim() || 'HOME',
      awayTeam: awayTeam.trim() || 'TeamB',
      awayAlias: awayAlias.trim() || 'AWAY',
      quarterMinutes: Number(quarterMinutes) || 12,
      maxTeamSize: Number(maxTeamSize) || 5,
      quarters: Number(quarters) || 4,
      homeRoster: homeRoster.trim(),
      awayRoster: awayRoster.trim(),
      gameRules
    })
  }

  return (
    <section className="rules-setup">
      <h2>Game Rules Setup</h2>
      <p className="rules-setup__subtitle">
        Set game rules first, then continue to the live match page.
      </p>

      <form className="rules-setup__form" onSubmit={handleSubmit}>
        <div className="rules-setup__section">
          <h3>RULES block</h3>
          <div className="rules-setup__row rules-setup__row--four">
            <label>
              max_team_size
              <input
                type="number"
                min="1"
                max="10"
                value={maxTeamSize}
                onChange={(e) => setMaxTeamSize(e.target.value)}
              />
            </label>
            <label>
              quarters
              <input
                type="number"
                min="1"
                max="8"
                value={quarters}
                onChange={(e) => setQuarters(e.target.value)}
              />
            </label>
            <label>
              quarter_length
              <input
                type="number"
                min="1"
                max="20"
                value={quarterMinutes}
                onChange={(e) => setQuarterMinutes(e.target.value)}
              />
            </label>
          </div>

          <label className="rules-setup__full-width">
            Game-specific rules
            <textarea
              value={gameRules}
              onChange={(e) => setGameRules(e.target.value)}
              placeholder={'game_style = standard;\ntimeout_limit = 2;'}
            />
          </label>
        </div>

        <div className="rules-setup__section">
          <h3>Team definitions</h3>
          <div className="rules-setup__row rules-setup__row--two">
            <label>
              Home team name
              <input value={homeTeam} onChange={(e) => setHomeTeam(e.target.value)} />
            </label>
            <label>
              Home alias used in DSL
              <input value={homeAlias} onChange={(e) => setHomeAlias(e.target.value)} />
            </label>
            <label>
              Away team name
              <input value={awayTeam} onChange={(e) => setAwayTeam(e.target.value)} />
            </label>
            <label>
              Away alias used in DSL
              <input value={awayAlias} onChange={(e) => setAwayAlias(e.target.value)} />
            </label>
          </div>

          <div className="rules-setup__row rules-setup__row--two">
            <label>
              Home roster
              <textarea
                value={homeRoster}
                onChange={(e) => setHomeRoster(e.target.value)}
                placeholder="#5, #7, #11, #23, #33"
              />
            </label>
            <label>
              Away roster
              <textarea
                value={awayRoster}
                onChange={(e) => setAwayRoster(e.target.value)}
                placeholder="#2, #3, #9, #10, #15"
              />
            </label>
          </div>
        </div>

        <div className="rules-setup__section">
          <h3>Live DSL preview</h3>
          <textarea readOnly rows={6} value={rulesPreview} className="rules-setup__preview" />
        </div>

        {error && <div className="rules-setup__error">{error}</div>}
        <button type="submit" className="rules-setup__start-btn" disabled={loading}>
          {loading ? 'Validating...' : 'Start Match Page'}
        </button>
      </form>
    </section>
  )
}

export default RulesSetup
