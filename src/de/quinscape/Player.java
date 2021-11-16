package de.quinscape;
    public class Player {
        private String playerName;
        private int currentAttempt;
        private int numberOfGuessedChars;
        private String wordToGuess;
        private String underscoreWord;

        public Player(String playerName) {
            this.playerName = playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void increaseCurrentAttempts(){
            currentAttempt++;
        }

        public void increaseNumberOfGuessedChars(){
            numberOfGuessedChars++;
        }

        public int getNumberOfGuessedChars() {
            return numberOfGuessedChars;
        }

        public int getCurrentAttempt() {
            return currentAttempt;
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
    }
