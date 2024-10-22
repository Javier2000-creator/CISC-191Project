import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordList {

    // Attribute: A list of words for the game
    private List<String> wordList;

    // Constructor: Initializes the word list with some default words
    public WordList() {
        wordList = new ArrayList<>();
        // Adding some default words
        wordList.add("programming");
        wordList.add("java");
        wordList.add("cybersecurity");
        wordList.add("phone");
        wordList.add("internet");
    }

    // Method: Get a random word from the list
    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(wordList.size()); // Select a random index
        return wordList.get(index); // Return the word at that index
    }

    // Method: Add a word to the list
    public void addWord(String word) {
        wordList.add(word);
    }

    // Optional: Method to print all words in the list (for testing)
    public void printWordList() {
        for (String word : wordList) {
            System.out.println(word);
        }
    }

    // Main method (for testing purposes)
    public static void main(String[] args) {
        WordList wordList = new WordList();
        
        // Test getting a random word
        System.out.println("Random Word: " + wordList.getRandomWord());
        
        // Test adding a new word
        wordList.addWord("security");
        System.out.println("Word added. Here's the updated word list:");
        wordList.printWordList();
    }
}


