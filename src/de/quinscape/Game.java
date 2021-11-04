package de.quinscape;

import java.util.*;

public class Game {

    private final Random r = new Random();
    private Scanner s = new Scanner(System.in);
    private int currentAttempt;
    private int numberOfGuessedChars;

    public void startGame(){
        System.out.println("===Willkommen beim Glücksrad===");
        String wordToGuess = getRandomWord();
        String underscoreWord = wordToGuess.replaceAll("[A-Za-z]", "_");
        System.out.println(underscoreWord);

        boolean runningGame = true;

        while(runningGame){
            
            String guessedChar = s.nextLine();
            currentAttempt++;
            underscoreWord = updateWordWithUnderscores(wordToGuess, guessedChar, underscoreWord);
            System.out.println(underscoreWord);
            System.out.println("Versuch: " + currentAttempt);

            if(isGameOver(wordToGuess, underscoreWord)){
                runningGame = false;
            }
        }
    }

    public boolean isGameOver(String word, String underscoreWord){
        if(currentAttempt >= word.length()*2){
            System.out.println("In " + currentAttempt + " Versuchen haben sie " + numberOfGuessedChars + " richtige Buchstaben erraten.");
            return true;
        } else if(underscoreWord.equals(word)){
            System.out.println("In " + currentAttempt + " Versuchen haben sie das Wort " + word + " erraten.");
            return true;
        }
        return false;
    }

    public String getRandomWord(){
        List<String> words = Arrays.asList(
                "Quinscape",
                "Antarktis",
                "Halloween",
                "Ahornblatt",
                "Hochhaus"
        );

        int randomIndex = r.nextInt(words.size());

        return words.get(randomIndex);
    }

    public String updateWordWithUnderscores(String wordToGuess, String guess, String underscoreWord){
        StringBuilder wordWithUnderscores = new StringBuilder(underscoreWord);
        char guessedChar = guess.charAt(0);

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
