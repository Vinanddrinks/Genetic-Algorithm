import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
// This Class contains all methods and attributes used to manage a population.
// We chose to keep track of only one generation at a time as we print our population details on each one
public class Population {
    //Attributes
    //The ArrayList can be put as finals as they are only dynamically accessed
    private final ArrayList<Individual> people = new ArrayList<>(); // array of individuals constituting the population
    private final ArrayList<Individual> tempPopulation = new ArrayList<>(); // temporary array used for evolution
    private int genNb; // variable to keep track of which generation we're on
    private final int mutationProbability; // fixed mutation probability for the population
    private final int convergenceMargin; // fixed limit for convergence recognizer

    //Constructors
    Population(int popSize, int mutationProbability, int convergenceMargin,int maxBound){
        //override constructor to generate a random population with fixed parameters
        this.convergenceMargin = convergenceMargin;
        this.genNb = 1;
        this.mutationProbability = mutationProbability;
        for (int i = 0;i < popSize-1;i++){
            people.add(new Individual(maxBound));
        }
        sortPopulation();
    }

    //Getter methods

    public int getGenNb() {
        return genNb;
    }

    //Methods
    private void sortPopulation(){ // method used to sort the population based on the fitness score
        Collections.sort(people); // lowest is better (gap to solution)
    }

    private void elitism(){ //take the two fittest individuals and add them to the next generation
        tempPopulation.add(people.get(0));
        tempPopulation.add(people.get(1));
    }

    private void crossover(){ // generate new population from the fittest individuals using crossover method
        int i = 1;
        while (people.size() > tempPopulation.size()){
            String[] res = people.get(i-1).cross(people.get(i));
            tempPopulation.add(new Individual(res[0]));
            tempPopulation.add(new Individual(res[1]));
            i++;
        }
    }

    private void mutate(){//run across all genes of all individuals and mutate them based on the mutationProbability
        Random rn = new Random();
        for(Individual current : tempPopulation){
            StringBuilder worker = new StringBuilder(current.getGenes());
            int i = 0;
            for(char letter : current.getGenes().toCharArray()){
                if (rn.nextInt(mutationProbability)==0){
                    if (letter == '0'){
                        worker.setCharAt(i,'1');
                    }else worker.setCharAt(i,'0');
                }
                i++;
            }
            current.setGenes(worker.toString());
        }
    }

    public void evolve(){ // this method uses all the above methods to move to the next generation of the population
        elitism();
        crossover();
        mutate();
        people.clear();
        people.addAll(tempPopulation);
        sortPopulation();
        tempPopulation.clear();
        this.genNb++;
    }

    public boolean isConverged(){ // this method verifies the gap between individuals' scores based on the fittest.
        // if all the distances to the fittest are within the convergenceMargin then the population has converged.
        for(Individual current : people) {
            if (Math.abs(people.get(0).getScore() - current.getScore()) > convergenceMargin) {
                return false;
            }
        }
        return true;
    }

    @Override // marked for compilation
    public String toString(){ // overridden for better readability
        String res = "Population  : Generation = " + genNb + "\n";
        StringBuilder worker = new StringBuilder(res);
        for (Individual current : people){
            worker.append(current).append("\n");
        }
        return worker.toString();
    }
}
