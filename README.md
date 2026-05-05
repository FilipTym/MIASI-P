# MIASI-P — Basketball Boxscore DSL

Aplikacja do protokołowania i generowania statystyk meczów koszykówki w czasie rzeczywistym przy użyciu własnego języka DSL.

---

## Spis treści

- [Uruchomienie](#uruchomienie)
- [Krok 1 — Konfiguracja zasad gry](#krok-1--konfiguracja-zasad-gry)
- [Krok 2 — Definicja drużyn](#krok-2--definicja-drużyn)
- [Krok 3 — Protokołowanie meczu](#krok-3--protokołowanie-meczu)
- [Krok 4 — Generowanie boxscore](#krok-4--generowanie-boxscore)
- [Ściągawka DSL](#ściągawka-dsl)

---

## Uruchomienie

Uruchom backend (Spring Boot) oraz frontend i otwórz aplikację w przeglądarce. Aplikacja składa się z dwóch ekranów: **strony konfiguracji** oraz **strony meczu**.

---

## Krok 1 — Konfiguracja zasad gry

W panelu **RULES block** zdefiniuj podstawowe parametry meczu:

| Pole | Opis | Przykład |
|---|---|---|
| `max_team_size` | Maksymalna liczba zawodników w drużynie | `15` |
| `quarters` | Liczba kwart | `4` |
| `quarter_length` | Długość kwarty w minutach | `12` |

> Sekcję **Game Specific Rules** pozostaw w wartościach domyślnych, chyba że masz powód by je zmieniać.

---

## Krok 2 — Definicja drużyn

W panelu **Team Definitions** uzupełnij dane obu drużyn.

### Nazwy i aliasy drużyn

Podaj pełną nazwę każdej drużyny oraz krótki alias, który będzie używany podczas protokołowania meczu w celu przyspieszenia wprowadzania danych:

```
Home:  Los Angeles Lakers   alias: LAL
Away:  Boston Celtics        alias: BOS
```

### Składy drużyn

W polach **Home Roster** i **Away Roster** wprowadź numery koszulek wszystkich zawodników biorących udział w meczu, oddzielone przecinkami i poprzedzone znakiem `#`:

```
Skład gospodarzy:  #5, #23, #3, #11, #2
Skład gości:       #0, #11, #7, #13, #36
```

> Tylko zawodnicy zadeklarowani w składzie mogą być przywoływani podczas meczu. Akcja przypisana do niezadeklarowanego numeru wygeneruje ostrzeżenie.

### Podgląd DSL

Panel **Live DSL Preview** aktualizuje się na bieżąco podczas uzupełniania formularza. Sprawdź czy wygenerowany DSL jest poprawny przed przejściem dalej.

Gdy wszystko się zgadza, kliknij **Start Match** aby przejść do strony meczu.

---

## Krok 3 — Protokołowanie meczu

### Zegar

Przycisku **Pause / Resume** używaj do zatrzymywania i wznawiania zegara gry podczas protokołowania.

### Wprowadzanie akcji

Wpisuj kolejne wydarzenia meczowe w polu **Action Input** używając składni DSL i zatwierdź Enterem. Zdarzenia są zapisywane w kolejności chronologicznej.

**Przykład:**
```
H #4 pf
```
Oznacza: zawodnik numer 4 z drużyny gospodarzy (`H`) popełnił faul personalny (`pf`).

Pełna lista dostępnych komend znajduje się w sekcji [Ściągawka DSL](#ściągawka-dsl) poniżej.

### Podgląd akcji

Panel **Live Action Log** wyświetla wszystkie zapisane zdarzenia w kolejności. Używaj go do bieżącego monitorowania protokołu i szybkiego wychwytywania błędów.

### Ściągawka komend

Zakładka **DSL Cheat Sheet** dostępna na stronie meczu zawiera skrócony opis wszystkich dostępnych komend — możesz ją otworzyć w dowolnym momencie bez opuszczania ekranu.

---

## Krok 4 — Generowanie boxscore

Po zakończeniu meczu kliknij **Parse & Generate Boxscore**.

Aplikacja przetworzy wszystkie zapisane zdarzenia i wyświetli pełną tabelę statystyk zawierającą:

- Punkty, rzuty z gry, trójki i rzuty wolne każdego zawodnika
- Zbiórki, asysty, przechwyty, bloki i straty
- Faule (personalne, techniczne, flagrant)
- Wyniki kwartowe obu drużyn

### Ostrzeżenia

Sekcja **Warnings** na dole strony wylistuje wszystkie problemy wykryte podczas parsowania — na przykład akcję przypisaną do zawodnika niezadeklarowanego w składzie.

### Ręczna korekta

Jeśli ostrzeżenie wskazuje na błędny wpis, otwórz panel **Generated DSL** gdzie wyświetlany jest pełny tekst protokołu. Edytuj konkretną linię bezpośrednio w polu tekstowym, a następnie kliknij ponownie **Parse & Generate Boxscore** aby zaktualizować tabelę statystyk.

---

## Ściągawka DSL

### Odwołania do drużyn

| Token | Znaczenie |
|---|---|
| `Lakers` | Pełna nazwa drużyny |
| `LAL` | Alias zadeklarowany podczas konfiguracji |
| `H` | Drużyna gospodarzy (zawsze dostępny) |
| `A` | Drużyna gości (zawsze dostępny) |

### Punktacja

| Komenda | Opis |
|---|---|
| `H #5 2pt` | Trafiona dwójka zawodnika #5 gospodarzy |
| `H #5 3pt` | Trafiona trójka |
| `H #5 ft` | Trafiony rzut wolny |
| `H #5 2pt x` | Niecelna dwójka (`x` = pudło) |
| `H #5 3pt x` | Niecelna trójka |
| `H #5 ft x` | Niecelny rzut wolny |
| `H #5 2pt #23 ast` | Trafiona dwójka z asystą zawodnika #23 |

### Pozostałe statystyki

| Komenda | Opis |
|---|---|
| `H #5 reb_off` | Zbiórka ofensywna |
| `H #5 reb_def` | Zbiórka defensywna |
| `H #5 ast` | Asysta |
| `H #5 stl` | Przechwyt |
| `H #5 blk` | Blok |
| `H #5 to` | Strata |

### Faule

| Komenda | Opis |
|---|---|
| `H #5 pf` | Faul personalny (`personal_foul`) |
| `H #5 tf` | Faul techniczny (`technical_foul`) |
| `H #5 ff` | Faul flagrant (`flagrant_foul`) |

### Zmiany zawodników

| Komenda | Opis |
|---|---|
| `H sub_in #5` | Zawodnik #5 wchodzi na boisko |
| `H sub_out #5` | Zawodnik #5 schodzi z boiska |
