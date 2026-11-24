/**
 * RouteResult - маршрут есебінің нәтижесін сақтайтын класс.
 */
public class RouteResult {
    private final String mode;
    private final String from;
    private final String to;
    private final double distanceKm;
    private final double timeMinutes;
    private final double cost;

    public RouteResult(String mode, String from, String to,
                       double distanceKm, double timeMinutes, double cost) {
        this.mode = mode;
        this.from = from;
        this.to = to;
        this.distanceKm = distanceKm;
        this.timeMinutes = timeMinutes;
        this.cost = cost;
    }

    public String getMode() { return mode; }
    public double getTimeMinutes() { return timeMinutes; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return String.format(
            "Маршрут: %s -> %s%nТүрі: %s%nҚашықтық: %.1f км%nУақыт: %.0f мин%nҚұны: %.0f тг",
            from, to, mode, distanceKm, timeMinutes, cost);
    }
}
