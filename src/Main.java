import possatogabriel.com.github.screenmatch.calculation.TimeCalculator;
import possatogabriel.com.github.screenmatch.model.Movie;
import possatogabriel.com.github.screenmatch.model.Series;

void main() {
    Movie myMovie = new Movie();
    myMovie.setName("Dune: Part Three");
    myMovie.setReleaseYear(2026);
    myMovie.setDurationMin(140);
    myMovie.rate(10);
    myMovie.rate(10);
    myMovie.rate(10);
    myMovie.getAverageRating();
    myMovie.showTechnicalSheet();

    Movie myMovie2 = new Movie();
    myMovie2.setName("Avengers: Doomsday");
    myMovie2.setReleaseYear(2026);
    myMovie2.setDurationMin(165);
    myMovie2.rate(9);
    myMovie2.rate(9);
    myMovie2.rate(9);
    myMovie2.getAverageRating();
    myMovie2.showTechnicalSheet();

    Series mySeries = new Series();
    mySeries.setName("Cyberpunk: Edgerunners");
    mySeries.setReleaseYear(2022);
    mySeries.rate(8);
    mySeries.rate(5);
    mySeries.rate(10);
    mySeries.getAverageRating();
    mySeries.setMinutesPerEpisode(30);
    mySeries.setEpisodesPerSeasons(10);
    mySeries.setSeasons(2);
    mySeries.showTechnicalSheet();

    TimeCalculator calculator = new TimeCalculator();
    calculator.includes(myMovie);
    calculator.includes(myMovie2);
    calculator.includes(mySeries);
    calculator.showFinalTime();
}