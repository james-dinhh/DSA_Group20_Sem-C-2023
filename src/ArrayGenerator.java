import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayGenerator {

    public static String generateArray(Map<Character, Integer> frequencies) {
        List<Character> array = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();

            for (int i = 0; i < frequency; i++) {
                array.add(character);
            }
        }

        Collections.shuffle(array);  // Shuffle to randomize the order

        StringBuilder result = new StringBuilder();
        for (char character : array) {
            result.append(character);
        }

        return result.toString();
    }

    public static String correctArray(String resultArray, String targetArray) {
        List<Character> incorrectCharacters = new ArrayList<>();
        for (int i = 0; i < targetArray.length(); i++) {
            if (resultArray.charAt(i) != targetArray.charAt(i)) {
                incorrectCharacters.add(resultArray.charAt(i));
            }
        }

        Collections.shuffle(incorrectCharacters);

        StringBuilder correctedArray = new StringBuilder(resultArray);
        for (int i = 0, index = 0; i < targetArray.length(); i++) {
            if (resultArray.charAt(i) != targetArray.charAt(i)) {
                correctedArray.setCharAt(i, incorrectCharacters.get(index));
                index++;
            }
        }

        return correctedArray.toString();
    }

    public static int countCorrectCharacters(String generatedArray, String targetArray) {
        int count = 0;
        for (int i = 0; i < targetArray.length(); i++) {
            if (generatedArray.charAt(i) == targetArray.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Given frequencies
        Map<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('M', 2);
        frequencies.put('O', 2);
        frequencies.put('C', 2);
        frequencies.put('H', 3);
        frequencies.put('A', 3);

        // Target array
        String targetArray = "CHAMOMOCHAHA";

        // Generate the initial array
        String resultArray = generateArray(frequencies);

        // Print the initial array and the number of correct characters
        int correctCount = countCorrectCharacters(resultArray, targetArray);
        System.out.println("Generated Array: " + resultArray + " (Correct Characters: " + correctCount + ")");

        // Compare with the target array
        while (correctCount < targetArray.length()) {
            System.out.println("Generated array does not match the target array. Generating a corrected array...");

            // Generate a corrected array
            resultArray = correctArray(resultArray, targetArray);

            // Count the correct characters
            correctCount = countCorrectCharacters(resultArray, targetArray);

            // Print the corrected array and the number of correct characters
            System.out.println("Corrected Array: " + resultArray + " (Correct Characters: " + correctCount + ")");
        }

        System.out.println("Generated array matches the target array.");
    }
}
