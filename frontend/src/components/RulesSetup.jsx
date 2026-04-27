import { useState } from 'react'
import './RulesSetup.css'

function RulesSetup({ onStart }) {
  const [homeTeam, setHomeTeam] = useState('HOME')
  const [awayTeam, setAwayTeam] = useState('AWAY')
  const [quarterMinutes, setQuarterMinutes] = useState(12)
  const [homeRoster, setHomeRoster] = useState('5, 7, 11, 23, 33')
  const [awayRoster, setAwayRoster] = useState('2, 3, 9, 10, 15')
  const [gameRules, setGameRules] = useState('Standard basketball rules')

  const handleSubmit = (event) => {
    event.preventDefault()
    onStart({
      homeTeam: homeTeam.trim() || 'HOME',
      awayTeam: awayTeam.trim() || 'AWAY',
      quarterMinutes: Number(quarterMinutes) || 12,
      homeRoster,
      awayRoster,
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
        <div className="rules-setup__row">
          <label>
            Home Team
            <input value={homeTeam} onChange={(e) => setHomeTeam(e.target.value)} />
          </label>
          <label>
            Away Team
            <input value={awayTeam} onChange={(e) => setAwayTeam(e.target.value)} />
          </label>
          <label>
            Quarter Time (minutes)
            <input
              type="number"
              min="1"
              max="20"
              value={quarterMinutes}
              onChange={(e) => setQuarterMinutes(e.target.value)}
            />
          </label>
        </div>

        <div className="rules-setup__row rules-setup__row--two">
          <label>
            Home Roster (frontend-only)
            <textarea
              value={homeRoster}
              onChange={(e) => setHomeRoster(e.target.value)}
              placeholder="e.g. 5, 7, 11, 23, 33"
            />
          </label>
          <label>
            Away Roster (frontend-only)
            <textarea
              value={awayRoster}
              onChange={(e) => setAwayRoster(e.target.value)}
              placeholder="e.g. 2, 3, 9, 10, 15"
            />
          </label>
        </div>

        <label>
          Game-specific Rules (frontend-only)
          <textarea
            value={gameRules}
            onChange={(e) => setGameRules(e.target.value)}
            placeholder="Describe any special rules for this game"
          />
        </label>

        <button type="submit" className="rules-setup__start-btn">
          Start Match Page
        </button>
      </form>
    </section>
  )
}

export default RulesSetup
