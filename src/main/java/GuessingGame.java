import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class GuessingGame {
    int maxNumber = 100;
    int numberOfAllowedAttempts = 10;
    int currentAttempts = 0;
    boolean gameIsRunning = false;

    public static void main(String[] args) {
        GuessingGame Game = new GuessingGame();
    }

    int ComputerNumberChoice() {
        return ThreadLocalRandom.current().nextInt(1, this.maxNumber);
    }

    int PlayerNumberChoice() {
        // checks if user exceeded limit
        // get input and turn it to Int
        // make sure it's not float

        System.out.print("Enter a number: ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );

        try{

            String playerChoiceInput = reader.readLine(); // might require String as variable type
            int playerChoice = Integer.parseInt(playerChoiceInput);

            if ((playerChoice <= this.maxNumber) & (playerChoice > 0)){
                System.out.println("Number accepted");
                return playerChoice;
            } else {
                System.out.println("Number is not withing the accepted range");
            }

        } catch(IOException e) {
            System.out.println("Your input can't be processed");
        } catch(NumberFormatException e) {
            System.out.println("Your input should not be decimal");
        }

        return 0;

    }

    static boolean DifferencePlayerChoiceComputerChoice(int playerChoice, int computerChoice){
        if(playerChoice < computerChoice ){
            System.out.println("The number you chose is lower");
        } else if(playerChoice > computerChoice) {
            System.out.println("The number you chose is higher");
        } else {
            System.out.println("Nice, You found the number!");
            return true;
        }
        return false;
    }

    boolean GameLoop(){
        int computerChoice = this.ComputerNumberChoice();
        int playerChoice = this.PlayerNumberChoice();

        if(currentAttempts < numberOfAllowedAttempts){
            boolean result = this.DifferencePlayerChoiceComputerChoice(playerChoice, computerChoice);
            if(result){
                this.currentAttempts = 0;
                this.gameIsRunning = false;
            }
        }


        return false;
    }

}
