import possatogabriel.com.github.screenmatch.model.Movie;

void main() {
    Movie myMovie = new Movie();
    myMovie.setName("Dune: Part Three");
    myMovie.setReleaseYear(2026);
    myMovie.setDurationMin(140);

    myMovie.rate(8);
    myMovie.rate(5);
    myMovie.rate(10);
    myMovie.getAverageRating();

    myMovie.showTechnicalSheet();
}