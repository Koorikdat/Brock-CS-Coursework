package GA;

public class popContainer{
    
    private String key;
    private double fitScore;

    // Constructor
    public popContainer(String key, double fitScore) {
        this.key = key;
        this.fitScore = fitScore;
    }

    // Getter and setter methods for the string value
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    // Getter and setter methods for the double value
    public double getFitScore() {
        return fitScore;
    }

    public void setFitScore(double fitScore) {
        this.fitScore = fitScore;
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "Data {" +
                "Key = '" + key + '\'' +
                ", Fitness = " + fitScore +
                '}';
    }

    // method to compare fitness scores and return the instance with the higher score
    public static popContainer compareByFitness(popContainer container1, popContainer container2) {
        if (container1.getFitScore() > container2.getFitScore()) {
            return container1;
        } else {
            return container2;
        }
    }


}