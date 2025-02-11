package GA;

import java.io.*;
import java.util.*;

import GA.popContainer;


//encrypt, decrypt, and fitness were taken from the
public class GA {

    String text;
    int popLength;

    public GA ( ) {

        //The filepath for both files
        // "decryptga/GA/Data1.txt"
        // "decryptga/GA/Data2.txt"

    //  int popLength = (int) readKeyLength("decryptga/GA/Data1.txt");

    popLength = readKeyLength("decryptga/GA/Data1.txt");
    String inputTextStream =  readTextFile("decryptga/GA/Data1.txt");
   

    doGA(10, popLength, inputTextStream);
    

    
    }

    // this will implement the complete GA Algorithm
    public void doGA(int popSize, int popLength, String inputText){

        // initialize the population 

        //String[] keyPopulationContainer = new String[popSize];

        // evaluate all individuals instering the key with the fitness score into custom object popContainer and putting the objects into an array
        popContainer[] keyAndScoreContainer = new popContainer[popSize];

        for (int i = 0; i < popSize; i++) {

            String key = generateCharArr(popLength); // fill every element of rand gen String of letters - this is our initial pop of "keys"
            double fitnessScore = Evaluation.fitness(key, inputText);
        
            popContainer keyAndScoreHolder = new popContainer(key, fitnessScore);
            keyAndScoreContainer[i] = keyAndScoreHolder;
            

        }

        // start loop

        int limit = 10;
        int generationNum = 0;

            // set a condition for generation loop to keep cycling
            for (int j = 0 ; j <= limit; j++){

            // increase generation number

            generationNum ++;

            // do selection algorithm until new winners has popSize elements
            
            popContainer[] winners = new popContainer[popSize];

            for (int i=0; i<popSize; i+=2){
                popContainer[] selectedChildren = tournamentSelection(keyAndScoreContainer, 4);
                winners[i] = selectedChildren[0];
                winners[i+1] = selectedChildren[1];
            }            


            // keep running tournament selection and crossing over array til it is popsize from offspring
            String[] crossed = new String[popSize];

            for (int i=0; i<popSize; i+=2){
                String[] crossing = onePointCrossOver(winners[i].getKey(), winners[i+1].getKey(), 90);
                crossed[i] = crossing[0];
                crossed[i+1] = crossing[1];
            }

        


            // mutate mated population

            String[] moddedPop = new String[popSize]; 
            for (int i=0;i<popSize;i++){
               moddedPop[i] = mutateElement(crossed[i]);
            }

            
            // evaluate new individuals
            for(int i = 0; i < popSize; i++){
               System.out.println(Evaluation.fitness(moddedPop[i], inputText) + " " + (moddedPop[i])); 
            }

            System.out.println("---------");
            }
         }



    // take in 2 parents, and apply uniform crossover
    public String[] uniformCrossOver(String parent1, String parent2, int probability){ // this is 1-point

        //calculate probability of successful crossover, else the parents will continue in the next generation



        Random random = new Random();


        if (random.nextInt(100) <= probability){

            char[] offspring1 = new char[parent1.length()];
            char[] offspring2 = new char[parent2.length()];
    
            // Perform uniform crossover
            for (int i = 0; i < parent1.length(); i++) {
                // Randomly decide whether to exchange the bits
                if (random.nextBoolean()) {
                    offspring1[i] = parent2.charAt(i);
                    offspring2[i] = parent1.charAt(i);
                } else {
                    offspring1[i] = parent1.charAt(i);
                    offspring2[i] = parent2.charAt(i);
                }
            }
    
            // Convert char arrays to strings
            String[] children = { new String(offspring1), new String(offspring2) };
    
            return children;

        }

        else{
            //turn the parents into children and return them



            String[] children = {parent1, parent2};
            return children;
            }
        }

    // take in 2 parents, and apply 1-point crossover
    public String[] onePointCrossOver(String parent1, String parent2, int probability){ // this is 1-point

        //calculate probability of successful crossover, else the parents will continue in the next generation

        String[] children = new String[2];

        Random random = new Random();


        if (random.nextInt(100) <= probability){

        int crossoverPoint = random.nextInt(parent1.length()); // Random crossover point

        // Create child strings by swapping substrings before and after the crossover point
        String child1 = parent1.substring(0, crossoverPoint) + parent2.substring(crossoverPoint);
        String child2 = parent2.substring(0, crossoverPoint) + parent1.substring(crossoverPoint);

        children[0] = child1;
        children[1] = child2;


        return children;

        }

        else{
            //turn the parents into children and return them

            children[0] = parent1;
            children[1] = parent2;


            return children;
        }



    }

    // take in a complete popContainer array of keys, and apply a selection algorithm to them
    public popContainer[] tournamentSelection(popContainer[] array, int K){

        popContainer[] finalSelected = new popContainer[2];

        
        popContainer[] selectedNodes = new popContainer[K];

        // Select K random nodes
    for (int i = 0; i < K; i++) {
        Random random = new Random();
        int selectPoint = random.nextInt(array.length);

        //System.out.println(selectPoint);

        selectedNodes[i] = array[selectPoint];

        // System.out.println("selected nodes:");
        // System.out.println(selectedNodes[i].toString());
    }

    // Find the two highest fitness scores among selected nodes
    popContainer highest = selectedNodes[0];
    popContainer secondHighest = selectedNodes[1];

    for (int i = 2; i < selectedNodes.length; i++) {
        highest = popContainer.compareByFitness(selectedNodes[i], highest);
        if (selectedNodes[i] != highest) {
            secondHighest = popContainer.compareByFitness(selectedNodes[i], secondHighest);
        }
    }

    finalSelected[0] = highest;
    finalSelected[1] = secondHighest;

        return finalSelected;

    }

    // this method will take in an element and apply a mutation to it
    public String mutateElement(String element){

        // Convert string into a char array in order to apply changes easier
        String[] stringArray = new String[element.length()];

        // Convert the string to a string array
        for (int i = 0; i < element.length(); i++) {
        stringArray[i] = String.valueOf(element.charAt(i));
        }


        // we will utilize insertion mutation algo

        String tempHolder;
        int randIdx1 = new Random().nextInt(element.length());
        int randIdx2 = new Random().nextInt(element.length());

        //swapping the string elements of two random indecies of the array
        tempHolder = stringArray[randIdx1];
        stringArray[randIdx1] = stringArray[randIdx2];
        stringArray[randIdx2] = tempHolder;



        
        // convert back into a string to be returned

        // Using StringBuilder to concatenate the elements of the string array
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : stringArray) {
            stringBuilder.append(str);
        }
        
        // Convert StringBuilder to String
        String mutatedElement = stringBuilder.toString();

        return mutatedElement;
    }

    // this will build a string of the given size of random lowercase roman alphabet chars
    public static String generateCharArr(int arrSize) {
        String rand = new String();
        for (int i = 0; i <= arrSize-1; i++) {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            rand = rand + c;

        }
        return rand;
    }

    // read the textfile, returns the key length
    public int readKeyLength(String filePath) {

        int key = 0;

        StringBuilder line = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the first line from the file
            line.append(reader.readLine());
        } catch (IOException e) {
            // Handle file reading errors
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Store the imported line in the 'text' variable
        this.text = line.toString();

        try {
            key = Integer.parseInt(text);
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Cannot convert the string to an integer.");
        }
    
        return key; 
}

    // read the textfile, returns the key length
    public String readTextFile(String filePath) {
    StringBuilder content = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        // Skip the first line (containing the integer)
        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {
            // Remove non-alphabetic characters and append the rest to the content
            content.append(line.replaceAll("[^a-zA-Z]", ""));
        }
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception according to your requirements
    }

    return content.toString();
}


      public static void main ( String[] args ) { GA s = new GA(); }

} // Turtle
