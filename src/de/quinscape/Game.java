package de.quinscape;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    List<String> words;

    public Game() {
        Scanner scanner;
        words = new ArrayList<>();

        try{
            scanner = new Scanner(new FileReader("words.txt"));
            while(scanner.hasNextLine()){
                words.add(scanner.nextLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void startGame(){
        printGreetings();
        int amountOfPlayers = scanner.nextInt();
        scanner.nextLine();
        int counter = 0;

        while(counter < amountOfPlayers) {
            System.out.printf("Spieler %s Name: ", counter + 1);
            Player player = new Player(scanner.nextLine());
            player.setWordToGuess(getRandomWord(words));
            player.setUnderscoreWord(player.getWordToGuess().replaceAll("[A-Za-z]", "_"));
            System.out.println(player.getUnderscoreWord());

            boolean runningGame = true;

            while (runningGame) {

                String guessedChar = scanner.nextLine();

                if (!isValidUserInput(guessedChar)) {
                    player.outputAttemptsAndWordProgress();
                    continue;
                }
                player.increaseCurrentAttempts();
                player.generateWordWithUnderscores(guessedChar);
                player.outputAttemptsAndWordProgress();

                runningGame = player.isGameOver();

            }
            counter++;
        }
    }

    public boolean isValidUserInput(String guessedChar){
        if(guessedChar.length() != 1 || !guessedChar.matches("[A-Za-z]")){
            System.out.println("Bitte nur einen Buchstaben eingeben!");
            return false;
        }
        return true;
    }

    public String getRandomWord(List<String> words){
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public void printGreetings(){
        System.out.println("===Willkommen beim Glücksrad===");
        System.out.println("Geben Sie die Anzahl der Spieler ein:");
    }

}
