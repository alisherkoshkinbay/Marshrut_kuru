/**
 * Жаяу жүру: жылдамдық 5 км/сағ, ақысыз.
 */
public class WalkingStrategy implements RouteStrategy {
    private static final double SPEED_KMH = 5.0;

    @Override
    public RouteResult buildRoute(String from, String to, double distanceKm) {
        double minutes = distanceKm / SPEED_KMH * 60;
        return new RouteResult("Жаяу", from, to, distanceKm, minutes, 0);
    }
}
