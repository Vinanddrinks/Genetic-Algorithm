import java.util.Random;
// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
// The purpose of this class is to define and manage actions of the Individual object
// which are members of the population we will use for genetic algorithms operation
public class Individual implements Comparable<Individual>{
    //Attributes
    private int value; // the decimal value of the individual
    private int score; // the fitness score of the individual
    private String genes; // the "genes" of the Individual as the binary value of the decimal value

    //Constructors
    Individual(int maxBound ){ //override constructor to generate randomly an individual with a max value passed as argument
        Random rn = new Random();
        value = rn.nextInt(maxBound);
        String maxBinary = Integer.toBinaryString(maxBound);
        convertDToB();
        while(maxBinary.length() > genes.length()){
            StringBuilder worker = new StringBuilder(genes);
            genes = worker.insert(0,"0").toString();
        }
        computeScore();
    }

    Individual(String genes){ //override constructor to generate an individual given its genes set
        this.genes = genes;
        convertBToD();
        computeScore();
    }

    //Getter methods
    public String getGenes() {
        return genes;
    }

    public int getScore() {
        return score;
    }

    //Setter methods
    public void setGenes(String genes) {
        this.genes = genes;
        convertBToD();
        computeScore();
    }

    //Methods
    private void computeScore(){ // this is the fitness function that computes the score of an individual as an absolute value
        this.score = Math.abs((value + 3)*(value + 3) -25);
    }

    private void convertDToB(){ // method used to set the genes from the decimal value
        this.genes = Integer.toBinaryString(value);
    }

    private void convertBToD(){ // method used to set the decimal value from the genes set
        this.value = Integer.parseInt(genes, 2);
    }

    public String[] cross(Individual second){ // this method creates two sets of genes from two individuals using crossover method
        Random rn = new Random();
        int crossPoint = rn.nextInt(genes.length()-1);
        //We split the genes into two parts F (first) and L (last) based on the random crossPoint
        //then we concatenate the strings to create two new sets of genes
        String firstF = genes.substring(0,crossPoint);
        String firstL = genes.substring(crossPoint);
        String secondF = second.genes.substring(0,crossPoint);
        String secondL = second.genes.substring(crossPoint);
        return new String[]{firstF+secondL,secondF+firstL};
    }

    @Override // marked for compilation
    public String toString(){ // overridden toString method for better readability
        return "Individual : Decimal = " + value + " Binary = " + genes + " Fitness Score = " + score;
    }

    @Override // marked for compilation
    /*
    implemented from Comparable interface in order to allow java sorting function from Collection class
    to sort Individuals based on their scores.
    */
    public int compareTo(Individual o){
        return Integer.compare(this.score, o.score); // same as an if-else-if-else statement
    }

}
