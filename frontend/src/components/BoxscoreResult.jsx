import { useMemo, useState } from 'react'
import './BoxscoreResult.css'

function BoxscoreResult({ result }) {
  if (!result) {
    return null
  }

  if (result.errors?.length > 0) {
    return (
      <section className="boxscore-result boxscore-result--error">
        <h3>Errors</h3>
        {result.errors.map((item, index) => (
          <div key={index}>{item}</div>
        ))}
      </section>
    )
  }

  const teams = [result.homeTeam, result.awayTeam]
  const [warningsOpen, setWarningsOpen] = useState(false)
  const warnings = useMemo(() => {
    if (!result.events?.length) {
      return []
    }
    return result.events
      .filter((item) => item.includes('Ignored action:'))
      .map((item) => {
        const match = item.match(/^\[(.+?)\]\s*(.*)$/)
        if (match) {
          return { timestamp: match[1], message: match[2] }
        }
        return { timestamp: 'Q?', message: item }
      })
  }, [result.events])

  return (
    <section className="boxscore-result">
      <h2>
        📊 Boxscore: {result.homeTeam} vs {result.awayTeam}
      </h2>

      <table className="boxscore-result__table boxscore-result__table--quarters">
        <thead>
          <tr>
            <th>Team</th>
            {result.quarterScores?.[result.homeTeam]?.map((_, index) => (
              <th key={index}>{index < 4 ? `Q${index + 1}` : 'OT'}</th>
            ))}
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {teams.map((team) => (
            <tr key={team}>
              <td>
                <strong>{team}</strong>
              </td>
              {result.quarterScores?.[team]?.map((score, index) => (
                <td key={index}>{score}</td>
              ))}
              <td>
                <strong>{result.quarterScores?.[team]?.reduce((a, b) => a + b, 0)}</strong>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {teams.map((team) => {
        const rosterNumbers = (result.rosters?.[team] || []).map((player) => String(player.number))
        const maxTeamSize = Number(result.maxTeamSize) || rosterNumbers.length
        const paddedNumbers = [...rosterNumbers]
        while (paddedNumbers.length < maxTeamSize) {
          paddedNumbers.push(null)
        }

        return (
          <div key={team} className="boxscore-result__team-block">
            <h3>{team}</h3>
            <table className="boxscore-result__table">
              <thead>
                <tr>
                  {['#', 'PTS', 'FG', '3P', 'FT', 'REB', 'AST', 'STL', 'BLK', 'TO', 'PF'].map((header) => (
                    <th key={header}>{header}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {paddedNumbers.map((num, index) => {
                  const player = num ? result.stats?.[team]?.[num] : null
                  return (
                    <tr key={num ?? `empty-${index}`}>
                      <td>{num ?? ''}</td>
                      <td>{player ? player.pts : ''}</td>
                      <td>{player ? `${player.fgm}/${player.fga}` : ''}</td>
                      <td>{player ? `${player.tpm}/${player.tpa}` : ''}</td>
                      <td>{player ? `${player.ftm}/${player.fta}` : ''}</td>
                      <td>{player ? player.rebOff + player.rebDef : ''}</td>
                      <td>{player ? player.ast : ''}</td>
                      <td>{player ? player.stl : ''}</td>
                      <td>{player ? player.blk : ''}</td>
                      <td>{player ? player.to : ''}</td>
                      <td>{player ? player.foulsPersonal : ''}</td>
                    </tr>
                  )
                })}
              </tbody>
            </table>
          </div>
        )
      })}

      {warnings.length > 0 && (
        <div className="boxscore-result__warnings">
          <button
            type="button"
            className="boxscore-result__warnings-toggle"
            onClick={() => setWarningsOpen((current) => !current)}
            aria-expanded={warningsOpen}
          >
            <span className="boxscore-result__warnings-arrow" aria-hidden="true">
              {warningsOpen ? '▼' : '▶'}
            </span>
            <span>Warnings</span>
          </button>
          {warningsOpen && (
            <div className="boxscore-result__warnings-list">
              {warnings.map((item, index) => (
                <div key={index}>
                  <span className="boxscore-result__warnings-time">[{item.timestamp}]</span> {item.message}
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </section>
  )
}

export default BoxscoreResult
