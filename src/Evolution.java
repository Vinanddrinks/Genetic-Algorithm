// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
// This class is the main class of our project about genetic algorithms to solve decimal equations.
// it contains the main script that allow the simulation of an evolution hence its name.
public class Evolution {
    public static void main(String[] args) {
        Population population = new Population(60,100,0, 4000);
        System.out.print(population);
        while (!population.isConverged()){ // we evolve the population until it has converged
            population.evolve();
            System.out.print(population);
        }
    }
}