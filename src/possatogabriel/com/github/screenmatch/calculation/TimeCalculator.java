package possatogabriel.com.github.screenmatch.calculation;

import possatogabriel.com.github.screenmatch.model.Title;

public class TimeCalculator {
    private int timeTotal;

    public int getTimeTotal() {
        return timeTotal;
    }

    public void includes(Title title) {
        timeTotal += title.getDurationMin();
    }

    public void showFinalTime() {
        System.out.println("> Final time: " + getTimeTotal());
    }
}
