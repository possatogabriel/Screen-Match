public class Movie {
    String name;
    int releaseYear;
    int durationMin;
    double ratingTotal;
    int ratingQuantity;
    boolean inPlan;

    void showTechnicalSheet() {
        System.out.println("> Movie name: " + name);
        System.out.println("> Release year: " + releaseYear);
        System.out.println("> Duration (in minutes): " + durationMin);
        System.out.println("> Overall rating: " + ratingTotal);
        System.out.println("> Number of ratings: " + ratingQuantity);
        System.out.printf("\n> Average rating: %.2f", getAverageRating());
    }

    void rate(double rating) {
        ratingTotal += rating;
        ratingQuantity++;
    }

    double getAverageRating() {
        return ratingTotal / ratingQuantity;
    }
}
