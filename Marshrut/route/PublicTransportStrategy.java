/**
 * Қоғамдық көлік: орташа жылдамдық 20 км/сағ + күту уақыты 7 мин,
 * жол ақысы тұрақты (120 тг).
 */
public class PublicTransportStrategy implements RouteStrategy {
    private static final double SPEED_KMH = 20.0;
    private static final double WAITING_MINUTES = 7.0;
    private static final double FARE = 120.0;

    @Override
    public RouteResult buildRoute(String from, String to, double distanceKm) {
        double minutes = distanceKm / SPEED_KMH * 60 + WAITING_MINUTES;
        return new RouteResult("Қоғамдық көлік", from, to, distanceKm, minutes, FARE);
    }
}
