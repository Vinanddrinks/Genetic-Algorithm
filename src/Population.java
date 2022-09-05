import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616

public class Population {
    //Attributes
    private final int popSize;
    private ArrayList<Individual> people = new ArrayList<>();
    private ArrayList<Individual> tempPopulation = new ArrayList<>();
    private int genNb;
    private final int mutationProbability;
    private final int convergenceMargin;

    //Constructors
    Population(int popSize, int mutationProbability, int convergenceMargin,int maxBound){
        this.convergenceMargin = convergenceMargin;
        this.popSize = popSize;
        this.genNb = 1;
        this.mutationProbability = mutationProbability;
        for (int i = 0;i < popSize-1;i++){
            people.add(new Individual(maxBound));
        }
        sortPopulation();
    }

    //Methods
    private void sortPopulation(){
        Collections.sort(people);
    }

    private void elitism(){
        tempPopulation.add(people.get(0));
        tempPopulation.add(people.get(1));
    }

    private void crossover(){
        int i = 1;
        while (people.size() > tempPopulation.size()){
            String[] res = people.get(i-1).cross(people.get(i));
            tempPopulation.add(new Individual(res[0]));
            tempPopulation.add(new Individual(res[1]));
            i++;
        }
    }

    private void mutate(){
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

    public void evolve(){
        elitism();
        crossover();
        mutate();
        people.clear();
        people.addAll(tempPopulation);
        sortPopulation();
        tempPopulation.clear();
        this.genNb++;
    }

    public boolean isConverged(){
        for(Individual current : people) {
            if (Math.abs(people.get(0).getScore() - current.getScore()) > convergenceMargin) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        String res = "Population  : Generation = " + genNb + "\n";
        for (Individual current : people){
            res += current + "\n";
        }
        return res;
    }
}
