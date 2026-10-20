void main() {
    Movie myMovie = new Movie();
    myMovie.name = "Dune: Part Three";
    myMovie.releaseYear = 2026;
    myMovie.durationMin = 140;

    myMovie.rate(8);
    myMovie.rate(5);
    myMovie.rate(10);
    myMovie.getAverageRating();

    myMovie.showTechnicalSheet();
}