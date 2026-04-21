import { useState } from 'react'
import './App.css'

const SAMPLE_CODE = `GAME HOME vs AWAY;

QUARTER 1
    HOME #5 2pt;
    AWAY #23 3pt;
    HOME #11 reb_def;
    AWAY #7 foul_personal;
    HOME #5 ft;
    HOME #5 ft;
END;

QUARTER 2
    HOME #33 ast;
    AWAY #10 stl;
    AWAY #10 2pt;
    HOME #5 3pt;
END;

BOXSCORE;
`

function App() {
  const [code, setCode] = useState(SAMPLE_CODE)
  const [result, setResult] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const handleParse = async () => {
    setLoading(true)
    setError(null)
    try {
      const response = await fetch('/api/parse', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code })
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
    <div style={{ padding: '20px', maxWidth: '1200px', margin: '0 auto' }}>
      <h1>🏀 Basketball Boxscore DSL</h1>

      <div style={{ display: 'flex', gap: '20px', marginBottom: '20px' }}>
        <div style={{ flex: 1 }}>
          <h3>DSL Input</h3>
          <textarea
            value={code}
            onChange={(e) => setCode(e.target.value)}
            style={{
              width: '100%',
              height: '400px',
              fontFamily: 'monospace',
              fontSize: '14px',
              padding: '10px'
            }}
          />
          <button
            onClick={handleParse}
            disabled={loading}
            style={{ marginTop: '10px', padding: '10px 20px', fontSize: '16px' }}
          >
            {loading ? 'Parsing...' : 'Parse & Generate Boxscore'}
          </button>
        </div>

        <div style={{ flex: 1 }}>
          <h3>Events Log</h3>
          <div style={{
            height: '400px',
            overflow: 'auto',
            background: '#1a1a1a',
            color: '#fff',
            padding: '10px',
            fontFamily: 'monospace',
            fontSize: '13px'
          }}>
            {result?.events?.map((event, i) => (
              <div key={i}>{event}</div>
            ))}
          </div>
        </div>
      </div>

      {error && <div style={{ color: 'red', marginBottom: '20px' }}>{error}</div>}

      {result && !result.errors?.length && (
        <div>
          <h2>📊 Boxscore: {result.homeTeam} vs {result.awayTeam}</h2>

          {/* Quarter Scores */}
          <table style={{ borderCollapse: 'collapse', marginBottom: '20px' }}>
            <thead>
              <tr>
                <th style={thStyle}>Team</th>
                {result.quarterScores?.[result.homeTeam]?.map((_, i) => (
                  <th key={i} style={thStyle}>{i < 4 ? `Q${i+1}` : 'OT'}</th>
                ))}
                <th style={thStyle}>Total</th>
              </tr>
            </thead>
            <tbody>
              {[result.homeTeam, result.awayTeam].map(team => (
                <tr key={team}>
                  <td style={tdStyle}><strong>{team}</strong></td>
                  {result.quarterScores?.[team]?.map((score, i) => (
                    <td key={i} style={tdStyle}>{score}</td>
                  ))}
                  <td style={tdStyle}>
                    <strong>{result.quarterScores?.[team]?.reduce((a, b) => a + b, 0)}</strong>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* Player Stats */}
          {[result.homeTeam, result.awayTeam].map(team => (
            <div key={team} style={{ marginBottom: '30px' }}>
              <h3>{team}</h3>
              <table style={{ borderCollapse: 'collapse', width: '100%' }}>
                <thead>
                  <tr>
                    {['#', 'PTS', 'FG', '3P', 'FT', 'REB', 'AST', 'STL', 'BLK', 'TO', 'PF'].map(h => (
                      <th key={h} style={thStyle}>{h}</th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {Object.entries(result.stats?.[team] || {}).map(([num, p]) => (
                    <tr key={num}>
                      <td style={tdStyle}>{num}</td>
                      <td style={tdStyle}>{p.pts}</td>
                      <td style={tdStyle}>{p.fgm}/{p.fga}</td>
                      <td style={tdStyle}>{p.tpm}/{p.tpa}</td>
                      <td style={tdStyle}>{p.ftm}/{p.fta}</td>
                      <td style={tdStyle}>{p.rebOff + p.rebDef}</td>
                      <td style={tdStyle}>{p.ast}</td>
                      <td style={tdStyle}>{p.stl}</td>
                      <td style={tdStyle}>{p.blk}</td>
                      <td style={tdStyle}>{p.to}</td>
                      <td style={tdStyle}>{p.foulsPersonal}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ))}
        </div>
      )}

      {result?.errors?.length > 0 && (
        <div style={{ color: 'red' }}>
          <h3>Errors:</h3>
          {result.errors.map((e, i) => <div key={i}>{e}</div>)}
        </div>
      )}
    </div>
  )
}

const thStyle = { border: '1px solid #ccc', padding: '8px', background: '#f0f0f0', textAlign: 'left' }
const tdStyle = { border: '1px solid #ccc', padding: '8px' }

export default App