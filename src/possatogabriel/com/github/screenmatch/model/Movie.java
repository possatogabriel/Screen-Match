package possatogabriel.com.github.screenmatch.model;

import possatogabriel.com.github.screenmatch.classification.Classifiable;

public class Movie extends Title implements Classifiable {
    private String director;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int getClassification() {
        return (int) (getAverageRating() / 2);
    }
}
