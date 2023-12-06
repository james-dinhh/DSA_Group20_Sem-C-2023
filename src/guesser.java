class Node {
    char data;
    Node next;

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}

class CharLinkedList {
    Node head;

    public void add(char data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void shuffle() {
        Node current = head;
        int length = length();

        for (int i = length - 1; i > 0; i--) {
            int j = customRandom(i + 1);

            char temp = getAtIndex(i).data;
            getAtIndex(i).data = getAtIndex(j).data;
            getAtIndex(j).data = temp;
        }
    }

    public int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public Node getAtIndex(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private int customRandom(int bound) {
        return (int) (Math.random() % bound);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        while (current != null) {
            result.append(current.data);
            current = current.next;
        }
        return result.toString();
    }
}

public class guesser {

    public static String generateArray(char[] characters, int[] frequencies) {
        CharLinkedList array = new CharLinkedList();

        for (int i = 0; i < characters.length; i++) {
            char character = characters[i];
            int frequency = frequencies[i];

            for (int j = 0; j < frequency; j++) {
                array.add(character);
            }
        }

        array.shuffle();

        return array.toString();
    }

    public static String correctArray(String resultArray, String targetArray) {
        CharLinkedList incorrectCharacters = new CharLinkedList();
        for (int i = 0; i < targetArray.length(); i++) {
            if (resultArray.charAt(i) != targetArray.charAt(i)) {
                incorrectCharacters.add(resultArray.charAt(i));
            }
        }

        incorrectCharacters.shuffle();

        StringBuilder correctedArray = new StringBuilder(resultArray);
        for (int i = 0, index = 0; i < targetArray.length(); i++) {
            if (resultArray.charAt(i) != targetArray.charAt(i)) {
                correctedArray.setCharAt(i, incorrectCharacters.getAtIndex(index).data);
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
        char[] characters = {'M', 'O', 'C', 'H', 'A'};
        int[] frequencies = {3, 3, 2, 2, 2};

        // Target array
        String targetArray = "MOCHAMOCHAMO";

        // Generate the initial array
        String resultArray = generateArray(characters, frequencies);

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
