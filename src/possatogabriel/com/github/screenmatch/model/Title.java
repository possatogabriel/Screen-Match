package possatogabriel.com.github.screenmatch.model;

public class Title {
    private String name;
    private int releaseYear;
    private int durationMin;
    private double ratingTotal;
    private int ratingQuantity;
    private boolean inPlan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public double getRatingTotal() {
        return ratingTotal;
    }

    public int getRatingQuantity() {
        return ratingQuantity;
    }

    public double getAverageRating() {
        return ratingTotal / ratingQuantity;
    }

    public void showTechnicalSheet() {
        System.out.println("> Title name: " + getName());
        System.out.println("> Release year: " + getReleaseYear());
        System.out.println("> Duration (in minutes): " + getDurationMin());
        System.out.println("> Overall rating: " + getRatingTotal());
        System.out.println("> Number of ratings: " + getRatingQuantity());
        System.out.printf("> Average rating: %.2f \n\n", getAverageRating());
    }

    public void rate(double rating) {
        ratingTotal += rating;
        ratingQuantity++;
    }
}
