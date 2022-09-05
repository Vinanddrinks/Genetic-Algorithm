

import java.util.Random;
// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
// the purpose of this class is to define and manage action of Individuals
// which are members of the populations we will use for genetic algorithms
public class Individual implements Comparable<Individual>{
    //Attributes
    private int value;
    private int score;
    private String genes;

    //Constructors
    Individual(int maxBound ){
        Random rn = new Random();
        value = rn.nextInt(maxBound);
        convertDToB();
        while(Integer.toBinaryString(maxBound).length() > genes.length()){
            genes = "0"+genes;
        }
        computeScore();
    }

    Individual(String genes){
        this.genes = genes;
        convertBToD();
        computeScore();
    }

    //Getter
    public String getGenes() {
        return genes;
    }

    public int getScore() {
        return score;
    }

    //Setter
    public void setGenes(String genes) {
        this.genes = genes;
        convertBToD();
        computeScore();
    }

    //Methods
    private void computeScore(){
        this.score = Math.abs((value + 3)*(value + 3) -25);
    }

    private void convertDToB(){
        this.genes = Integer.toBinaryString(value);
    }

    private void convertBToD(){
        this.value = Integer.parseInt(genes, 2);
    }

    public String[] cross(Individual second){
        Random rn = new Random(System.currentTimeMillis());
        int crossPoint = rn.nextInt(genes.length()-1);
        String firstF = genes.substring(0,crossPoint);
        String firstL = genes.substring(crossPoint);
        String secondF = second.genes.substring(0,crossPoint);
        String secondL = second.genes.substring(crossPoint);
        return new String[]{firstF+secondL,secondF+firstL};
    }

    @Override
    public String toString(){
        return "Individual : Decimal = " + value + " Binary = " + genes + " Fitness Score = " + score;
    }

    @Override
    public int compareTo(Individual o){
        if (this.score > o.score) {
            return 1;
        }
        else if (this.score < o.score) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
