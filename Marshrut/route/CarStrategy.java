/**
 * Көлікпен жүру: қала ішіндегі орташа жылдамдық 40 км/сағ,
 * шығын - жанармай (100 км-ге 8 л, 1 литр 300 тг деп алынған).
 */
public class CarStrategy implements RouteStrategy {
    private static final double SPEED_KMH = 40.0;
    private static final double LITERS_PER_100KM = 8.0;
    private static final double FUEL_PRICE = 300.0;

    @Override
    public RouteResult buildRoute(String from, String to, double distanceKm) {
        double minutes = distanceKm / SPEED_KMH * 60;
        double cost = distanceKm / 100 * LITERS_PER_100KM * FUEL_PRICE;
        return new RouteResult("Көлікпен", from, to, distanceKm, minutes, cost);
    }
}
