package de.quinscape;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private int currentAttempt;
    private int numberOfGuessedChars;
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
        System.out.println("===Willkommen beim Glücksrad===");
        String wordToGuess = getRandomWord(words);
        String underscoreWord = wordToGuess.replaceAll("[A-Za-z]", "_");
        System.out.println(underscoreWord);

        boolean runningGame = true;

        while(runningGame){

            String guessedChar = scanner.nextLine();

            if (!isValidUserInput(guessedChar)){
                outputAttemptsAndWordProgress(underscoreWord);
                continue;
            }
            currentAttempt++;
            underscoreWord = generateWordWithUnderscores(wordToGuess, guessedChar, underscoreWord);
            outputAttemptsAndWordProgress(underscoreWord);

            runningGame = isGameOver(wordToGuess, underscoreWord);

        }
    }
    public void outputAttemptsAndWordProgress(String underscoreWord){
        System.out.println(underscoreWord);
        System.out.println("Versuch: " + currentAttempt );
    }
    public boolean isValidUserInput(String guessedChar){
        if(guessedChar.length() != 1 || !guessedChar.matches("[A-Za-z]")){
            System.out.println("Bitte nur einen Buchstaben eingeben!");
            return false;
        }
        return true;
    }

    public boolean isGameOver(String wordToGuess, String underscoreWord){
        if(currentAttempt >= wordToGuess.length()*2){
            System.out.printf("In %s Versuchen haben sie %s richtige Buchstaben erraten. Das richtige Wort ist %s%n"
                    , currentAttempt, numberOfGuessedChars, wordToGuess);
            return false;
        } else if(underscoreWord.equals(wordToGuess)){
            System.out.printf("In %s Versuchen haben sie das Wort %s erraten.", currentAttempt, wordToGuess);
            return false;
        }
        return true;
    }

    public String getRandomWord(List<String> words){
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public String generateWordWithUnderscores(String wordToGuess, String guessedCharacter, String underscoreWord){
        StringBuilder wordWithUnderscores = new StringBuilder(underscoreWord);
        char guessedChar = guessedCharacter.charAt(0);

        for (int i = 0; i < wordToGuess.length(); i++){
            if(wordToGuess.toLowerCase().charAt(i) == guessedChar){
                if(i == 0){
                    wordWithUnderscores.setCharAt(i, Character.toUpperCase(guessedChar));
                } else {
                    wordWithUnderscores.setCharAt(i, guessedChar);
                }
                numberOfGuessedChars++;
            }
        }
        return wordWithUnderscores.toString();
    }



}
