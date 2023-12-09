package Project1;

import java.util.*;
import static java.lang.Math.max;

public class SecretKeyGuesser {
    // declare all variables
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
        // check the number of appearances of each character in the secret key
        int MFreq = key.guess(Mstr);
        int OFreq = key.guess(Ostr);
        int CFreq = key.guess(Cstr);
        int HFreq = key.guess(Hstr);
        int AFreq = key.guess(Astr);

        System.out.printf("Your string contains: %d M,%d O,%d C,%d H,%d A \n", MFreq, OFreq, CFreq, HFreq, AFreq);

        // find the character with the most appearances and use the string with only that letter as the first base to guess
        currentScore = max(MFreq, max(OFreq, max(CFreq, max(HFreq, AFreq))));
        Map<String, Integer> freqMap = new HashMap<>();
        freqMap.put("M", MFreq);
        freqMap.put("O", OFreq);
        freqMap.put("C", CFreq);
        freqMap.put("H", HFreq);
        freqMap.put("A", AFreq);

        highestFreqChar = Collections.max(freqMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        highestFreqStr = highestFreqChar.repeat(KEY_LENGTH);

        // if a string contains only 1 distinct letter is the correct key, return it immediately
        if (currentScore == KEY_LENGTH) {
            System.out.println("I found the secret key. It is " + highestFreqStr);
        } else { // arrange the characters in descending order of its number of appearances
            // sort the map by value in descending order
            List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
            list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            // skip the letter with highest number of appearances
            for (int i = 1; i < list.size(); i++) {
                Map.Entry<String, Integer> entry = list.get(i);
                String character = entry.getKey();
                int freq = entry.getValue();

                for (int j = 0; j < freq; j++) {
                    guessWithResult(character.charAt(0));
                }
            }
            // when the number of correct positions reach 12, print out the secret key
            if (currentScore == KEY_LENGTH) {
                System.out.println("I found the secret key. It is " + highestFreqStr);
            } else {
                System.out.println("Current Score: " + currentScore);
            }
        }
    }

    void guessWithResult(char update){
        char[] strArray = highestFreqStr.toCharArray();
        // checking the string from the last to the first index
        for(int i = strArray.length - 1; i >= 0; i--){
            if(strArray[i] == highestFreqChar.charAt(0)){
                // replace each wrong character with another character (based on the order above)
                strArray[i] = update;
                // update current guess
                System.out.println("Current guess is: " + String.valueOf(strArray) + "\n");
                // if guessing one more character correct, update the base string and number of correct positions
                if (key.guess(String.valueOf(strArray)) > currentScore){
                    currentScore++;
                    highestFreqStr = String.valueOf(strArray);
                    return;
                } else { // if no improvement, keep things as it is
                    strArray[i] = highestFreqChar.charAt(0);
                }
            }
        }
    }
}
