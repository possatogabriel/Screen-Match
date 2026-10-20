package possatogabriel.com.github.screenmatch.model;

import possatogabriel.com.github.screenmatch.classification.Classifiable;

public class Episode implements Classifiable {
    private String name;
    private int number;
    private Series series;
    private int viewsTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getViewsTotal() {
        return viewsTotal;
    }

    public void setViewsTotal(int viewsTotal) {
        this.viewsTotal = viewsTotal;
    }

    @Override
    public int getClassification() {
        if (viewsTotal > 100) {
            return 4;
        } else {
            return 2;
        }
    }
}
