package de.quinscape;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private final Player player = new Player();
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
        setPlayerVariables();

        boolean runningGame = true;

        while (runningGame) {

            String guessedChar = scanner.nextLine();

            if (!isValidUserInput(guessedChar)) {
                outputAttemptsAndWordProgress();
                continue;
            }
            player.increaseCurrentAttempts();
            player.setUnderscoreWord(generateWordWithUnderscores(guessedChar));
            outputAttemptsAndWordProgress();

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

    public void setPlayerVariables(){
        player.setPlayerName(scanner.nextLine());
        System.out.println("Hallo " + player.getPlayerName());
        player.setWordToGuess(getRandomWord(words));
        player.setUnderscoreWord(player.getWordToGuess().replaceAll("[A-Za-z]", "_"));
        System.out.println(player.getUnderscoreWord());
    }

    public String getRandomWord(List<String> words){
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public void printGreetings(){
        System.out.println("===Willkommen beim Glücksrad===");
        System.out.println("Spieler Name: ");

    }

    public void outputAttemptsAndWordProgress(){
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
                if (i == 0) {
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
