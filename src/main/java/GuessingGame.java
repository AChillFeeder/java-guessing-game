import exceptions.NotInRangeException;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


// better exception and method names
// method and attributes order
// new Player class - score, averages, history
// Database


public class GuessingGame {
    int maxNumber = 100;
    int numberOfAllowedAttempts = 10;
    int currentAttempts = 0;
    boolean gameIsRunning = true;
    int computerChoice = 0;
    boolean victory = false;
    Scanner in;

    public GuessingGame(){
        this.computerChoice = ThreadLocalRandom.current().nextInt(1, this.maxNumber);
        in = new Scanner(System.in); // create input Scanner instance
    }

    public static void main(String[] args) {
        GuessingGame gameInstance = new GuessingGame();
        while(true) {
            int choice = gameInstance.menu();
            switch (choice) {
                case 1:
                    gameInstance.game();
                    gameInstance.gameOverMessage();
                    break;
                default:
                    gameInstance.in.close();
                    System.exit(0);
            }
        }
    }

    void game(){
        int playerChoice;

        for(int i=1; i<=this.numberOfAllowedAttempts; i++) {

            playerChoice = this.playerNumberChoiceErrorHandling();

            gameRound(playerChoice);
            currentAttempts = i;
            if(!gameIsRunning){
                break;
            }
        }
        this.gameIsRunning = false;
    }

    Integer menu(){
        System.out.print("Do you want to start a guessing game? (yes / no) -> ");
        String userAnswer = in.nextLine();
        switch (userAnswer){
            case "yes":
            case "y":
                return 1;
            case "no":
            case "n":
                return 0;
            default:
                return -1;
        }
    }

    Integer playerNumberChoiceErrorHandling(){
        try{
            return playerNumberChoice();
        } catch (NumberFormatException | NotInRangeException err) {
            System.out.println(err.getMessage());
            return playerNumberChoice();
        }
    }

    Integer playerNumberChoice(){

        System.out.print("Enter a number: ");

        String playerChoiceString = in.nextLine();
        int playerChoice = 0;
        playerChoice = Integer.parseInt(playerChoiceString);


        if ((playerChoice <= this.maxNumber) & (playerChoice > 0)){
            return playerChoice;
        } else {
            throw new NotInRangeException("Number is not withing the accepted range");
        }
    }

    boolean isPlayerChoiceEqualsComputerChoice(int playerChoice){
        if(playerChoice < this.computerChoice ){
            System.out.println("The number you chose is lower");
        } else if(playerChoice > this.computerChoice) {
            System.out.println("The number you chose is higher");
        } else {
            System.out.println("Nice, You found the number!");
            return true;
        }
        return false;
    }

    void gameOverMessage(){
        // show: this.attempts
        if(!this.gameIsRunning){
            String message = (this.victory) ? "you have won!" : "you have lost";
            System.out.println(message);
        }else{
//            exception
        }
    }

    void gameRound(int playerChoice){
        boolean result = this.isPlayerChoiceEqualsComputerChoice(playerChoice);
        if (result) { // player won
            this.gameIsRunning = false;
            this.victory = true;
        }}

}
