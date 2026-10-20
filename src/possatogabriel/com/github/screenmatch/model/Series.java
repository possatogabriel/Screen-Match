package possatogabriel.com.github.screenmatch.model;

public class Series extends Title {
    private int seasons;
    private int episodesPerSeasons;
    private int minutesPerEpisode;
    private boolean active;

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodesPerSeasons() {
        return episodesPerSeasons;
    }

    public void setEpisodesPerSeasons(int episodesPerSeasons) {
        this.episodesPerSeasons = episodesPerSeasons;
    }

    public int getMinutesPerEpisode() {
        return minutesPerEpisode;
    }

    public void setMinutesPerEpisode(int minutesPerEpisode) {
        this.minutesPerEpisode = minutesPerEpisode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int getDurationMin() {
        return getMinutesPerEpisode() * getEpisodesPerSeasons() * getSeasons();
    }
}
