package first;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class HangMan {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] words = {
        "programming",
        "java",
        "developer",
        "algorithm",
        "computer",
        "hangman",
        "application",
        "interface",
        "technology",
        "software"
    };

    Random random = new Random();
    String word = words[random.nextInt(words.length)];

    ArrayList<Character> displayedWord = new ArrayList<>();
    ArrayList<Character> usedLetters = new ArrayList<>();

    for (int i = 0; i < word.length(); i++) {
      displayedWord.add('_');
    }

    openRandomLetters(displayedWord, word, 2);

    int lives = 6;
    String input;

    do {
      printHangman(lives);
      System.out.print("\nCurrent word: ");

      for (char c : displayedWord) {
        System.out.print(c + " ");
      }

      System.out.println("\nLives left: " + lives);

      System.out.println("Enter next letter: ");
      input = sc.nextLine().toLowerCase();

      if (input.isEmpty()) {
        System.out.println("Please enter a letter.");
        continue;
      }

      char character = input.charAt(0);

      if (!usedLetters.contains(character)) {
        usedLetters.add(character);

        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
          if (word.charAt(i) == character) {
            displayedWord.set(i, character);
            found = true;
          }
        }

        if (found) {
          System.out.println("Correct!");
        } else {
          lives--;
          System.out.println("Wrong!");
        }

        if (!displayedWord.contains('_')) {
          System.out.println("Congratulations! You won! You guessed the word!");
          break;
        }
      } else {
        System.out.println("You've already used this letter!");
      }

    } while (lives > 0);

    if (lives == 0) {
      printHangman(lives);
      System.out.println("You lost. The word was: " + word);
    }

    sc.close();
  }

  private static void openRandomLetters(ArrayList<Character> displayedWord, String word, int numLetters) {
    Random random = new Random();
    int[] indices = new int[numLetters];

    for (int i = 0; i < numLetters; i++) {
      int index;
      do {
        index = random.nextInt(word.length());
      } while (containsIndex(indices, index));

      indices[i] = index;
      displayedWord.set(index, word.charAt(index));
    }
  }

  private static boolean containsIndex(int[] array, int value) {
    for (int i : array) {
      if (i == value) {
        return true;
      }
    }
    return false;
  }

  public static void printHangman(int lives) {
    String[] stages = {
        """
             +---+
             |   |
             O   |
            /|\\  |
            / \\  |
                 |
            =========
            """,

        """
             +---+
             |   |
             O   |
            /|\\  |
            /    |
                 |
            =========
            """,

        """
             +---+
             |   |
             O   |
            /|\\  |
                 |
                 |
            =========
            """,

        """
             +---+
             |   |
             O   |
            /|   |
                 |
                 |
            =========
            """,

        """
             +---+
             |   |
             O   |
             |   |
                 |
                 |
            =========
            """,

        """
             +---+
             |   |
             O   |
                 |
                 |
                 |
            =========
            """,

        """
             +---+
             |   |
                 |
                 |
                 |
                 |
            =========
            """
    };

    if (lives < 0) lives = 0;
    if (lives > 6) lives = 6;

    System.out.println(stages[lives]);
  }
}
