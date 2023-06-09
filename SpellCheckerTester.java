import java.util.List;
import java.util.Set;
import java.io.IOException;


public class SpellCheckerTester {
    public static void main(String[] args) {
    try {
        SpellChecker spellChecker = new SpellChecker("words.txt");
        List<String> incorrectWords = spellChecker.getIncorrectWords("test.txt");
        System.out.println("Incorrect words:");
        for (String word : incorrectWords) {
            System.out.println(word);
            Set<String> suggestions = spellChecker.getSuggestions(word);
            System.out.println("Suggestions:");
            if (suggestions.isEmpty()) {
                System.out.println("None");
            } else {
                for (String suggestion : suggestions) {
                    System.out.println(suggestion);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    }

