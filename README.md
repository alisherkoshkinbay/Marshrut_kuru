[README.md](https://github.com/user-attachments/files/35316526/README.md)
# 🗺️ Маршрут құру — Strategy паттерні

**Тақырып:** Маршрут құру: жаяу / көлікпен / қоғамдық көлікпен есептеу әдістерін ауыстыру және оны **Strategy** паттерні арқылы орындау.

Бағдарлама пайдаланушыдан қайдан, қайда және қашықтықты (км) сұрайды, сосын жүру тәсілін таңдатып, маршруттың **уақыты** мен **құнын** есептейді. Тәсілді бағдарлама жұмыс істеп тұрған кезде ауыстыруға болады, бұл ретте `Navigator` класының коды өзгермейді.

---

## 📌 Strategy паттерні деген не?

**Strategy** — мінез-құлық (behavioral) паттерні. Бір есепті шешудің бірнеше әдісі (алгоритмі) болса, әр әдісті **жеке класқа** бөліп шығарамыз да, оларды **ортақ интерфейс** арқылы бір-бірімен алмастырамыз.

Паттернсіз бәрі бір класта `if / switch` арқылы жазылар еді:

```java
// ❌ Паттернсіз
if (mode.equals("walk"))      { time = km / 5 * 60; }
else if (mode.equals("car"))  { time = km / 40 * 60; cost = ...; }
else if (mode.equals("bus"))  { time = km / 20 * 60 + 7; cost = 120; }
```

Жаңа тәсіл қосылған сайын (велосипед, такси...) осы кодты өзгертуге тура келеді. Strategy паттерні бұл мәселені шешеді:

```java
// ✅ Strategy арқылы
navigator.setStrategy(new CarStrategy());
navigator.buildRoute(from, to, km);
```

### Паттерннің қатысушылары

| Рөлі | Біздің жобада | Міндеті |
|---|---|---|
| **Strategy** (интерфейс) | `RouteStrategy` | Барлық тәсілге ортақ `buildRoute(...)` әдісін жариялайды |
| **ConcreteStrategy** | `WalkingStrategy`, `CarStrategy`, `PublicTransportStrategy` | Әрқайсысы маршрутты өз формуласымен есептейді |
| **Context** | `Navigator` | Стратегияға сілтеме сақтайды, жұмысты соған тапсырады, `setStrategy()` арқылы ауыстырады |
| **Client** | `Main` | Пайдаланушының таңдауы бойынша қажетті стратегияны жасап, `Navigator`-ға береді |
| Нәтиже | `RouteResult` | Түрі, қашықтық, уақыт, құн |

---

## 🧩 UML кластар диаграммасы

![UML class diagram](docs/uml-diagram.png)

> Диаграмманың PlantUML коды: [`docs/uml-diagram.puml`](docs/uml-diagram.puml)

**Диаграмманы оқу:**
- `RouteStrategy <|.. WalkingStrategy` (үшбұрышты үзік сызық) — класс интерфейсті **іске асырады** (implements).
- `Navigator o--> RouteStrategy` (ромб) — **агрегация**: Navigator ішінде бір стратегия сақтайды, бірақ ол нақты қай класс екенін білмейді.
- Үзік сызықты нұсқар (`..>`) — **тәуелділік**: `Main` стратегияларды жасайды, стратегиялар `RouteResult` қайтарады.

## 🔄 Тізбек (sequence) диаграммасы

Пайдаланушы алдымен «Көлікпен», сосын «Жаяу» таңдағанда не болатыны:

![Sequence diagram](docs/sequence-diagram.png)

> PlantUML коды: [`docs/sequence-diagram.puml`](docs/sequence-diagram.puml)

---

## 🧮 Есептеу әдістері

| Тәсіл | Класс | Уақыт формуласы | Құны |
|---|---|---|---|
| 🚶 Жаяу | `WalkingStrategy` | `км / 5 × 60` (5 км/сағ) | 0 тг |
| 🚗 Көлікпен | `CarStrategy` | `км / 40 × 60` (40 км/сағ) | `км / 100 × 8 л × 300 тг` (жанармай) |
| 🚌 Қоғамдық көлік | `PublicTransportStrategy` | `км / 20 × 60 + 7` (20 км/сағ + 7 мин күту) | 120 тг (тұрақты) |

---

## 📁 Жоба құрылымы

```
route/
├── Main.java                     # Client: мәзір, стратегияны таңдау
├── Navigator.java                # Context
├── RouteStrategy.java            # Strategy интерфейсі
├── WalkingStrategy.java          # ConcreteStrategy — жаяу
├── CarStrategy.java              # ConcreteStrategy — көлік
├── PublicTransportStrategy.java  # ConcreteStrategy — қоғамдық көлік
├── RouteResult.java              # Есеп нәтижесі
└── docs/
    ├── uml-diagram.puml / .png       # Кластар диаграммасы
    └── sequence-diagram.puml / .png  # Тізбек диаграммасы
```

---

## ▶️ Іске қосу

**IntelliJ IDEA:** жобаны ашып, `Main.java` → ▶ Run.

**Терминал арқылы:**

```bash
javac -encoding UTF-8 *.java
java Main
```

### Мысал

```
Қайдан: Абай даңғылы
Қайда: Медеу
Қашықтық (км): 12

1 - Жаяу
2 - Көлікпен
3 - Қоғамдық көлік
0 - Шығу
Тәсілді таңдаңыз: 2

Маршрут: Абай даңғылы -> Медеу
Түрі: Көлікпен
Қашықтық: 12.0 км
Уақыт: 18 мин
Құны: 288 тг
```

Сол 12 км үшін үш тәсілдің нәтижесі:

| Тәсіл | Уақыт | Құны |
|---|---|---|
| Жаяу | 144 мин | 0 тг |
| Көлікпен | 18 мин | 288 тг |
| Қоғамдық көлік | 43 мин | 120 тг |

---

## ✅ Паттерннің пайдасы

1. **Ашық/жабық принципі (Open/Closed):** жаңа тәсіл қосу үшін (мысалы, `BicycleStrategy`) тек жаңа класс жазамыз, `Navigator` өзгермейді.
2. **`if/switch` тізбегі жоқ:** әр алгоритм өз класында тұрады, код таза әрі оқуға жеңіл.
3. **Жұмыс кезінде ауыстыру:** `setStrategy()` арқылы тәсіл бағдарлама тоқтамай ауысады.
4. **Жеке тестілеу:** әр стратегияны бөлек тексеруге болады.

### Жаңа стратегия қосу мысалы

```java
public class BicycleStrategy implements RouteStrategy {
    @Override
    public RouteResult buildRoute(String from, String to, double distanceKm) {
        double minutes = distanceKm / 15.0 * 60;   // 15 км/сағ
        return new RouteResult("Велосипед", from, to, distanceKm, minutes, 0);
    }
}
```

`Main`-дағы мәзірге бір жол қоссақ жеткілікті, басқа класстар өзгермейді.

---

## 🛠️ Технологиялар

- Java (JDK 17+)
- IntelliJ IDEA
- PlantUML (UML диаграммалар)
