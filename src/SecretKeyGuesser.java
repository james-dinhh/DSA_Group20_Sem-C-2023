import java.util.Arrays;

public class SecretKeyGuesser {
    private char[] currentGuess;
    private boolean[] fixed;
    private final char[] characters = {'M', 'O', 'C', 'H', 'A'};
    private final int keyLength = 12;

    public SecretKeyGuesser() {
        currentGuess = new char[keyLength];
        fixed = new boolean[keyLength];
        Arrays.fill(currentGuess, 'M');
        Arrays.fill(fixed, false);
    }

    public void start() {
        SecretKey key = new SecretKey();
        int matched;
        while ((matched = key.guess(new String(currentGuess))) != keyLength) {
            updateGuess(matched, key);
        }
        System.out.println("I found the secret key. It is " + new String(currentGuess));

    }

    private void updateGuess(int matchedCount, SecretKey key) {
        char[] previousGuess = currentGuess.clone();
        for (int i = 0; i < keyLength; i++) {
            if (!fixed[i]) {
                for (char c : characters) {
                    if (c != currentGuess[i]) {
                        currentGuess[i] = c;
                        int matched = key.guess(new String(currentGuess));
                        if (matched > matchedCount) {
                            fixed[i] = true;
                            break;
                        } else {
                            currentGuess[i] = previousGuess[i]; // Revert if no improvement
                        }
                    }
                }
            }

        }
    }
}

