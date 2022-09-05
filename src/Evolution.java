// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616

public class Evolution {
    public static void main(String[] args) {
        Population population = new Population(20,100,0, 4000);
        System.out.print(population);
        while (!population.isConverged()){
            population.evolve();
            System.out.print(population);
        }
    }
}