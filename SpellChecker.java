/* Kassey Chang
 * cc4876
 * SpellChecker.java */

import java.io.*;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface {

    private HashSet<String> dictionary;

    public SpellChecker(String filename) throws IOException {
        // Initialize dictionary
        dictionary = new HashSet<String>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            // Add word to dictionary (in lower case and without punctuation)
            dictionary.add(line.toLowerCase().replaceAll("[^a-z0-9 ]", ""));
        }
        reader.close();
    }

    /** Method to get a list of incorrect words
    @return List<String> an array list of string */
    public List<String> getIncorrectWords(String filename) {
    List<String> incorrectWords = new ArrayList<String>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;
    while ((line = reader.readLine()) != null) {
        // skip if the line is empty
        if (line.trim().isEmpty()) {
            continue;
        }
        // Split line into words
        String[] words = line.toLowerCase().replaceAll("[^a-z0-9 ]", "").split("\\s+");
        for (String word : words) {
            // Check each word against the dictionary 
            if (!dictionary.contains(word)) {
                
                // If the word is not in the dictionary, add it to the list of incorrect words 
               incorrectWords.add(word);
            }
        }
    }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    
    return incorrectWords;
}

    /** Method to get suggestion words
    @param String word that is incorrect
    @return hash set of suggested word 
    */
    public Set<String> getSuggestions(String word) {
        Set<String> suggestions = new HashSet<String>();
        
        for (int i = 0; i <= word.length(); i++) {
            // Add one new character to each position in word
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String newWord = word.substring(0, i) + ch + word.substring(i);
                // check each word against the dictionary 
                if (dictionary.contains(newWord)) {
                    suggestions.add(newWord);
                }
            }
        }
        for (int i = 0; i < word.length(); i++) {
            // Remove one character from each position of word
            String newWord = word.substring(0, i) + word.substring(i+1);
            // check each word against the dictionary 
            if (dictionary.contains(newWord)) {
                suggestions.add(newWord);
            }
        }
        for (int i = 0; i < word.length()-1; i++) {
            // Swap adjacent characters
            String newWord = word.substring(0, i) + word.charAt(i+1) + word.charAt(i) + word.substring(i+2);
            // check each word against the dictionary 
            if (dictionary.contains(newWord)) {
                suggestions.add(newWord);
            }
        }
        return suggestions;
    }
}
