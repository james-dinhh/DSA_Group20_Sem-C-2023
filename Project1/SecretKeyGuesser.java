package Project1;

public class SecretKeyGuesser {
    String Mstr = null;
    String Ostr = null;
    String Cstr = null;
    String Hstr = null;
    String Astr = null;
    String highestFreqChar = null;
    String highestFreqStr = null;
    SecretKey key = null;
    final int KEY_LENGTH = 12;
    int currentScore;
    public SecretKeyGuesser(){
        Mstr = "M".repeat(KEY_LENGTH);
        Ostr = "O".repeat(KEY_LENGTH);
        Cstr = "C".repeat(KEY_LENGTH);
        Hstr = "H".repeat(KEY_LENGTH);
        Astr = "A".repeat(KEY_LENGTH);
    }

    public void start() {
        key = new SecretKey();
        int[] freqArr = new int[5];
        String[] charArr = {"M", "O", "C", "H", "A"};

        // check the number of appearances of each character in the secret key
        freqArr[0] = key.guess(Mstr);
        freqArr[1] = key.guess(Ostr);
        freqArr[2] = key.guess(Cstr);
        freqArr[3] = key.guess(Hstr);
        freqArr[4] = key.guess(Astr);

        System.out.printf("Your string contains: %d M,%d O,%d C,%d H,%d A \n", freqArr[0], freqArr[1], freqArr[2], freqArr[3], freqArr[4]);

        // bubble sort to sort freqArr and charArr in descending order of frequencies
        for (int i = 0; i < freqArr.length - 1; i++) {
            for (int j = 0; j < freqArr.length - i - 1; j++) {
                if (freqArr[j] < freqArr[j + 1]) {
                    int temp = freqArr[j];
                    freqArr[j] = freqArr[j + 1];
                    freqArr[j + 1] = temp;

                    String tempStr = charArr[j];
                    charArr[j] = charArr[j + 1];
                    charArr[j + 1] = tempStr;
                }
            }
        }

        // find the character with the most appearances and use the string with only that letter as the first base to guess
        highestFreqChar = charArr[0];
        highestFreqStr = highestFreqChar.repeat(KEY_LENGTH);
        currentScore = freqArr[0];

        // if a string contains only 1 distinct letter is the correct key, return it immediately
        if (currentScore == KEY_LENGTH) {
            System.out.println("I found the secret key. It is " + highestFreqStr);
        } else { // update the guessed string in descending order of its number of appearances
            for (int i = 1; i < freqArr.length; i++) {
                for (int j = 0; j < freqArr[i]; j++) {
                    guessWithResult(charArr[i].charAt(0));
                }
            }
            // when the number of correct positions reaches 12, print out the secret key
            if (currentScore == KEY_LENGTH) {
                System.out.println("I found the secret key. It is " + highestFreqStr);
            } else {
                System.out.println("Current Score: " + currentScore);
            }
        }
    }

    void guessWithResult(char update){
        char[] strArr = highestFreqStr.toCharArray();
        // checking the string from the last to the first index
        for (int i = strArr.length - 1; i >= 0; i--){
            if (strArr[i] == highestFreqChar.charAt(0)){
                // replace each wrong character with another character (based on the order above)
                strArr[i] = update;
                // update current guess
                System.out.println("Current guess is: " + String.valueOf(strArr) + "\n");
                // if guessing one more character correct, update the base string and number of correct positions
                if (key.guess(String.valueOf(strArr)) > currentScore){
                    currentScore++;
                    highestFreqStr = String.valueOf(strArr);
                    return;
                } else { // if no improvement, keep things as it is
                    strArr[i] = highestFreqChar.charAt(0);
                }
            }
        }
    }
}
