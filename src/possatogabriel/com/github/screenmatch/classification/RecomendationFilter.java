package possatogabriel.com.github.screenmatch.classification;

public class RecomendationFilter {

    public void filter(Classifiable classifiable) {
        if (classifiable.getClassification() >= 4) {
            System.out.println("\n> One of the most populars titles at the moment!");
        } else if (classifiable.getClassification() >= 2) {
            System.out.println("\n> It's trending!");
        } else {
            System.out.println("\n> Put at the end of your list!");
        }
    }
}
