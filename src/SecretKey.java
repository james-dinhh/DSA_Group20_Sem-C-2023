public class SecretKey {
  private String correctKey;
  private int counter = 0 ;

  public SecretKey() {
    // for the real test, your program will not know this
    correctKey = "MMOOCCHHAAHM"; 
    counter = 0;
  }

  public String getCorrectKey() {
    return correctKey;
  }

  public int comparison(String guessedKey) {
    counter++;
    // validation
    if (guessedKey.length() != correctKey.length()) {
      return -1;
    }
    int matched = 0;
    for (int i = 0; i < guessedKey.length(); i++) {
      char c = guessedKey.charAt(i);
      if (c != 'M' && c != 'O' && c != 'C' && c != 'H' && c != 'A') {  
       return -1;
      }
      if (c == correctKey.charAt(i)) {
        matched++;
      }
    }
    if (matched == correctKey.length()) {
      System.out.println("Generated Array: " + guessedKey);
      System.out.println("Number of guesses: " + counter);
    }
    return matched;
  }

  public static void main(String[] args) {
    new guesserkeysecret().start();
  }
}
