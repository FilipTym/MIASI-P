import { useState } from 'react'
import './DSLCheatSheet.css'

const CHEAT_SHEET = [
  {
    rule: '2pt',
    meaning: '2-point made shot',
    example: 'HOME #4 2pt;',
    exampleMeaning: 'home team player #4 scores 2 points'
  },
  {
    rule: '3pt',
    meaning: '3-point made shot',
    example: 'AWAY #11 3pt;',
    exampleMeaning: 'away team player #11 scores 3 points'
  },
  {
    rule: 'ft',
    meaning: 'free throw made',
    example: 'HOME #8 ft;',
    exampleMeaning: 'home team player #8 makes a free throw'
  },
  {
    rule: 'miss',
    meaning: 'missed shot',
    example: 'AWAY #9 miss;',
    exampleMeaning: 'away team player #9 misses a shot'
  },
  {
    rule: 'reb_off',
    meaning: 'offensive rebound',
    example: 'HOME #10 reb_off;',
    exampleMeaning: 'home team player #10 gets an offensive rebound'
  },
  {
    rule: 'reb_def',
    meaning: 'defensive rebound',
    example: 'AWAY #15 reb_def;',
    exampleMeaning: 'away team player #15 gets a defensive rebound'
  },
  {
    rule: 'ast',
    meaning: 'assist',
    example: 'HOME #7 ast;',
    exampleMeaning: 'home team player #7 records an assist'
  },
  {
    rule: 'stl',
    meaning: 'steal',
    example: 'AWAY #3 stl;',
    exampleMeaning: 'away team player #3 records a steal'
  },
  {
    rule: 'blk',
    meaning: 'block',
    example: 'HOME #12 blk;',
    exampleMeaning: 'home team player #12 records a block'
  },
  {
    rule: 'to',
    meaning: 'turnover',
    example: 'AWAY #2 to;',
    exampleMeaning: 'away team player #2 commits a turnover'
  },
  {
    rule: 'pf',
    meaning: 'personal foul',
    example: 'HOME #4 pf;',
    exampleMeaning: 'home team player #4 commits a personal foul'
  }
]

function DSLCheatSheet() {
  const [open, setOpen] = useState(false)

  return (
    <section className="dsl-sheet">
      <button
        type="button"
        className="dsl-sheet__toggle"
        onClick={() => setOpen((current) => !current)}
        aria-expanded={open}
      >
        <span className="dsl-sheet__arrow" aria-hidden="true">
          {open ? '▼' : '▶'}
        </span>
        <span>DSL Cheat Sheet</span>
      </button>

      {open && (
        <div className="dsl-sheet__content">
          <p className="dsl-sheet__intro">
            Quick guide to the shortest DSL forms used in this app.
          </p>

          <div className="dsl-sheet__list">
            {CHEAT_SHEET.map((item) => (
              <div key={item.rule} className="dsl-sheet__item">
                <div className="dsl-sheet__rule">
                  <code>{item.rule}</code> — {item.meaning}
                </div>
                <div className="dsl-sheet__example">
                  <code>{item.example}</code> — {item.exampleMeaning}
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
    </section>
  )
}

export default DSLCheatSheet
