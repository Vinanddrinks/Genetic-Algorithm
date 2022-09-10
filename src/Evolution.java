// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
// This class is the main class of our project about genetic algorithms to solve decimal equations.
// it contains the main script that allows the simulation of an evolution, hence its name.
public class Evolution {
    private final static String errorCommonDisplay = "Correct syntax is <popSize> <mutationProbability> <convergenceMargin> <maxBound> <elitismCap> <mutationCap> <highMutationProbability>";
    public static void main(String[] args) {
        try {
            if(args.length != 7){
                System.out.println("Incorrect number of arguments.");
                System.out.println(errorCommonDisplay);
                System.exit(-1);
            }
            if(Integer.parseInt(args[6]) < Integer.parseInt(args[1])){
                System.out.println("highMutationProbability cannot be lower than mutationProbability (number in reverse)");
                System.exit(-1);
            
            }
            Population population = new Population(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]));
            System.out.print(population);
            while (!population.isConverged()){ // we evolve the population until it has converged, when it has the evolution process is terminated.
                population.evolve();
                System.out.print(population);
            }
            System.out.println("The evolution process was terminated after " + population.getGenNb() + " generations.");
            System.exit(0);
        }catch(Exception ex){
            System.out.println("Incorrect arguments syntax.");
            System.out.println(errorCommonDisplay);
            System.exit(-1);
        }
    }
}
