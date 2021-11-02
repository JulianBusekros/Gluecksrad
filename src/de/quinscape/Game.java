package de.quinscape;

import java.util.*;

public class Game {

    private final Random r = new Random();
    private final Scanner s = new Scanner(System.in);
    private int currentAttempt;
    private int numberOfGuessedChars;
    //private List<String> correctLetters;

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

            if (currentAttempt >= wordToGuess.length()*2){
                System.out.println("In " + currentAttempt + " Versuchen haben sie " + numberOfGuessedChars + " richtige Buchstaben erraten.");
                runningGame = false;
            }
            if(underscoreWord.equals(wordToGuess)){
                System.out.println("In " + currentAttempt + " haben sie das Wort " + wordToGuess + " erraten.");
                runningGame = false;
            }
        }
    }

    public String getRandomWord(){
        List<String> words = Arrays.asList(
                "quinscape",
                "antarktis",
                "halloween",
                "ahornblatt",
                "hochhaus"
        );

        int randomIndex = r.nextInt(words.size());

        return words.get(randomIndex);
    }

    public String updateWordWithUnderscores(String wordToGuess, String guess, String underscoreWord){
        StringBuilder wordWithUnderscores = new StringBuilder(underscoreWord);
        char guessedChar = guess.charAt(0);

        for (int i = 0; i < wordToGuess.length(); i++){
            if(wordToGuess.charAt(i) == guessedChar){
                wordWithUnderscores.setCharAt(i, guessedChar);
                numberOfGuessedChars++;
            }
        }

        /*if (wordToGuess.contains(guess)){
            wordWithUnderscores.setCharAt(wordToGuess.indexOf(guessedChar), guessedChar);
        }*/

        return wordWithUnderscores.toString();
    }



}
