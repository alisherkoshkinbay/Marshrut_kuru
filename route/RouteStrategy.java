/**
 * RouteStrategy - Strategy паттернінің "Strategy" интерфейсі.
 * Әр жүру тәсілі маршрутты өзінше есептейді.
 */
public interface RouteStrategy {
    RouteResult buildRoute(String from, String to, double distanceKm);
}
