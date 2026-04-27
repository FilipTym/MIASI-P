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

      {teams.map((team) => (
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
              {Object.entries(result.stats?.[team] || {}).map(([num, player]) => (
                <tr key={num}>
                  <td>{num}</td>
                  <td>{player.pts}</td>
                  <td>
                    {player.fgm}/{player.fga}
                  </td>
                  <td>
                    {player.tpm}/{player.tpa}
                  </td>
                  <td>
                    {player.ftm}/{player.fta}
                  </td>
                  <td>{player.rebOff + player.rebDef}</td>
                  <td>{player.ast}</td>
                  <td>{player.stl}</td>
                  <td>{player.blk}</td>
                  <td>{player.to}</td>
                  <td>{player.foulsPersonal}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </section>
  )
}

export default BoxscoreResult
