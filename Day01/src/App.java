import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            File input = new File("./input.txt");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            int highestCalorieCount = 0;
            int secondHighest = 0;
            int thirdHighest = 0;
            int caloriesCarriedByCurrentElf = 0;
            
            String currentLine = reader.readLine();

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.isEmpty()) {

                    caloriesCarriedByCurrentElf = caloriesCarriedByCurrentElf + Integer.parseInt(currentLine);   

                } else if (currentLine.isEmpty()) {
                    System.out.println("Current elf is carrying: " + caloriesCarriedByCurrentElf);
                    System.out.println("Comparing current elf inventory to top three highest total calorie counts...");
                   
                    if (caloriesCarriedByCurrentElf > highestCalorieCount) {
                       
                        System.out.println("Previous highest value was: " + highestCalorieCount);
                        thirdHighest = secondHighest;
                        secondHighest = highestCalorieCount;
                        highestCalorieCount = caloriesCarriedByCurrentElf;
                        
                        System.out.println("The highest calorie count is now: " + highestCalorieCount);
                       
                        caloriesCarriedByCurrentElf = 0;
                    } else if (caloriesCarriedByCurrentElf > secondHighest) {
                        System.out.println("Previous second highest value was: " + secondHighest);
                        
                        thirdHighest = secondHighest;
                        secondHighest = caloriesCarriedByCurrentElf;
                        
                        System.out.println("The second highest calorie count is now: " + secondHighest);
                       
                        caloriesCarriedByCurrentElf = 0;
                    } else if (caloriesCarriedByCurrentElf > thirdHighest) {
                        System.out.println("Previous third highest value was: " + thirdHighest);
                       
                        thirdHighest = caloriesCarriedByCurrentElf;
                        
                        System.out.println("The third highest calorie count is now: " + thirdHighest);
                       
                        caloriesCarriedByCurrentElf = 0;
                    }else {
                        System.out.println("Current elf did not have a higher calorie count than any of them! Checking next elf!");
                        caloriesCarriedByCurrentElf = 0;
                    }
                }
            }

            System.out.println("HIGHEST CALORIES SCOREBOARD: \n" + highestCalorieCount + "\n" + secondHighest + "\n" + thirdHighest);
            System.out.println("Top three elves combined calorie total: ");

            int total = highestCalorieCount + secondHighest + thirdHighest;
            System.out.println(total);

            reader.close();

        } catch (FileNotFoundException e) {
            throw e;
        }
    }
}