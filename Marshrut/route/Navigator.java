/**
 * Navigator - Strategy паттернінің "Context" класы.
 * Қандай стратегия бар екенін білмейді, тек RouteStrategy интерфейсімен жұмыс істейді.
 */
public class Navigator {
    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    // Стратегияны жұмыс кезінде ауыстыруға болады
    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public RouteResult buildRoute(String from, String to, double distanceKm) {
        return strategy.buildRoute(from, to, distanceKm);
    }
}
