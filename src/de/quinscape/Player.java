package de.quinscape;
    public class Player {
        private final String playerName;
        private int currentAttempt;
        private int numberOfGuessedChars;
        private String wordToGuess;
        private String underscoreWord;

        public Player(String playerName) {
            this.playerName = playerName;
        }

        public void increaseCurrentAttempts(){
            currentAttempt++;
        }

        public void setWordToGuess(String wordToGuess) {
            this.wordToGuess = wordToGuess;
        }

        public String getWordToGuess() {
            return wordToGuess;
        }

        public String getUnderscoreWord() {
            return underscoreWord;
        }

        public void setUnderscoreWord(String underscoreWord) {
            this.underscoreWord = underscoreWord;
        }

        public void outputAttemptsAndWordProgress(){
            System.out.println(underscoreWord);
            System.out.println("Versuch: " + currentAttempt);
        }

        public boolean isGameOver(){
            if(currentAttempt >= wordToGuess.length()*2){
                System.out.printf("Vielleicht mehr Glück beim nächsten Mal %s. \nIn %s Versuchen haben sie %s richtige Buchstaben erraten. Das richtige Wort ist %s%n \n"
                        ,playerName, currentAttempt, numberOfGuessedChars, wordToGuess);
                return false;
            } else if(underscoreWord.equals(wordToGuess)){
                System.out.printf("==Herzlichen Glückwunsch %s== \nIn %s Versuchen haben sie das Wort %s erraten. \n", playerName, currentAttempt, wordToGuess);
                return false;
            }
            return true;
        }

        public void generateWordWithUnderscores(String guessedCharacter){
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
            underscoreWord = wordWithUnderscores.toString();
        }
    }
