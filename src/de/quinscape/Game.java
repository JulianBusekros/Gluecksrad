package de.quinscape;

import java.io.IOException;
import java.util.*;

public class Game {

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private final Player player = new Player("default");

    public void startGame(){
        printGreetings();
        createPlayer();

        boolean runningGame = true;

        while (runningGame) {

            String guessedChar = scanner.nextLine();

            if (!isValidUserInput(guessedChar)) {
                printAttemptsAndWordProgress();
                continue;
            }
            player.increaseCurrentAttempts();
            player.setUnderscoreWord(generateWordWithUnderscores(guessedChar));
            printAttemptsAndWordProgress();

            runningGame = isGameOver();
            }
        }


    public boolean isValidUserInput(String guessedChar){
        if(guessedChar.length() != 1 || !guessedChar.matches("[A-Za-z]")){
            System.out.println("Bitte nur einen Buchstaben eingeben!");
            return false;
        }
        return true;
    }

    public void createPlayer(){
        player.setPlayerName(scanner.nextLine());
        System.out.println("Hallo " + player.getPlayerName());
        player.setWordToGuess(getRandomWord());
        player.setUnderscoreWord(player.getWordToGuess().replaceAll("[A-Za-z]", "_"));
        System.out.println(player.getUnderscoreWord());
    }

    public String getRandomWord(){
        try{
            Words words = new Words();
            List<String> wordList = words.getWords();
            int randomIndex = random.nextInt(wordList.size());
            return wordList.get(randomIndex);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void printGreetings(){
        System.out.println("===Willkommen beim Glücksrad===");
        System.out.println("Spieler Name: ");

    }

    public void printAttemptsAndWordProgress(){
        System.out.println(player.getUnderscoreWord());
        System.out.println("Versuch: " + player.getCurrentAttempt());
    }

    public boolean isGameOver(){
        if(player.getCurrentAttempt() >= player.getWordToGuess().length()*2){
            System.out.printf("Vielleicht mehr Glück beim nächsten Mal %s. \nIn %s Versuchen haben sie %s richtige Buchstaben erraten. Das richtige Wort ist %s%n \n"
                    ,player.getPlayerName(), player.getCurrentAttempt(), player.getNumberOfGuessedChars(), player.getWordToGuess());
            return false;
        } else if(player.getUnderscoreWord().equals(player.getWordToGuess())){
            System.out.printf("==Herzlichen Glückwunsch %s== \nIn %s Versuchen haben sie das Wort %s erraten. \n", player.getPlayerName(), player.getCurrentAttempt(), player.getWordToGuess());
            return false;
        }
        return true;
    }

    public String generateWordWithUnderscores(String guessedCharacter){
        StringBuilder wordWithUnderscores = new StringBuilder(player.getUnderscoreWord());
        char guessedChar = guessedCharacter.charAt(0);

        for (int i = 0; i < player.getWordToGuess().length(); i++){
            if(player.getWordToGuess().toLowerCase().charAt(i) == guessedChar) {
                if (Character.isUpperCase(player.getWordToGuess().charAt(i))) {
                    wordWithUnderscores.setCharAt(i, Character.toUpperCase(guessedChar));
                } else {
                    wordWithUnderscores.setCharAt(i, guessedChar);
                }
                player.increaseNumberOfGuessedChars();
            }
        }
        return wordWithUnderscores.toString();
    }



}
