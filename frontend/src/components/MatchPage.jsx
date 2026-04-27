import DSLCheatSheet from './DSLCheatSheet'
import BoxscoreResult from './BoxscoreResult'
import './MatchPage.css'

function MatchPage({
  rules,
  currentQuarter,
  clockText,
  clockRunning,
  actionInput,
  onActionChange,
  onActionSend,
  actions,
  generatedCode,
  loading,
  error,
  onParse,
  onBackToRules,
  result
}) {
  return (
    <section className="match-page">
      <div className="match-page__header">
        <div>
          <h2>
            Match: {rules.homeTeam} vs {rules.awayTeam}
          </h2>
          <div className="match-page__meta">
            Quarter {currentQuarter} • Quarter length: {rules.quarterMinutes} min
          </div>
        </div>

        <div className="match-page__clock-box">
          <div className="match-page__clock-label">Game Clock</div>
          <div className="match-page__clock-value">Q{currentQuarter} {clockText}</div>
          <div className="match-page__clock-status">{clockRunning ? 'Running' : 'Stopped'}</div>
        </div>
      </div>

      <div className="match-page__rules-preview">
        <h3>Rules (frontend-only preview)</h3>
        <div><strong>Home roster:</strong> {rules.homeRoster || '-'}</div>
        <div><strong>Away roster:</strong> {rules.awayRoster || '-'}</div>
        <div><strong>Game-specific rules:</strong> {rules.gameRules || '-'}</div>
      </div>

      <DSLCheatSheet />

      <div className="match-page__grid">
        <div className="match-page__left">
          <h3>Score Action Input</h3>
          <div className="match-page__action-row">
            <input
              value={actionInput}
              onChange={(e) => onActionChange(e.target.value)}
              placeholder="e.g. HOME #4 pf;"
            />
            <button type="button" onClick={onActionSend}>Send Action</button>
          </div>
          <p className="match-page__hint">
            Input clears after each send. Each action is logged with current quarter time.
          </p>

          <h4>Generated DSL (keeps backend parse flow)</h4>
          <textarea readOnly value={generatedCode} className="match-page__dsl-preview" />

          <div className="match-page__actions">
            <button type="button" onClick={onParse} disabled={loading}>
              {loading ? 'Parsing...' : 'Parse & Generate Boxscore'}
            </button>
            <button type="button" className="match-page__secondary-btn" onClick={onBackToRules}>
              Back to Rules
            </button>
          </div>

          {error && <div className="match-page__error">{error}</div>}
        </div>

        <div className="match-page__right">
          <h3>Live Action Log</h3>
          <div className="match-page__log">
            {actions.length === 0 && <div className="match-page__empty">No actions yet.</div>}
            {actions.map((entry) => (
              <div key={entry.id} className="match-page__log-item">
                <span className="match-page__log-time">[Q{entry.quarter} {entry.clock}]</span>{' '}
                <code>{entry.text}</code>
              </div>
            ))}
          </div>
        </div>
      </div>

      <BoxscoreResult result={result} />
    </section>
  )
}

export default MatchPage
