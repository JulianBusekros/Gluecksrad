package de.quinscape;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Words {
    List<String> words;

    public Words() throws IOException{
        copyWordsFromWebsite();
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

    public void copyWordsFromWebsite() throws IOException {
        URL website = new URL("http://www.netzmafia.de/software/wordlists/deutsch.txt");

        try (InputStream in = website.openStream()) {
            Path source = Paths.get("words.txt");
            Files.copy(in, source, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public List<String> getWords() {
        return words;
    }
}

