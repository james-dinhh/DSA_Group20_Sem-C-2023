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
        return (int) (Math.random() * bound);
    }

    public String toString() {
        String result = "";
        Node current = head;
        while (current != null) {
            result += current.data;
            current = current.next;
        }
        return result;
    }
}

public class SecretKeyGuesser {

    String Mstr = "MMMMMMMMMMMM";
    String Ostr = "OOOOOOOOOOOO";
    String Cstr = "CCCCCCCCCCCC";
    String Hstr = "HHHHHHHHHHHH";

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
    
        char[] correctedArray = resultArray.toCharArray();
        for (int i = 0, index = 0; i < targetArray.length(); i++) {
            if (resultArray.charAt(i) != targetArray.charAt(i)) {
                // Check if the index is within bounds of the incorrectCharacters list
                if (index < incorrectCharacters.length()) {
                    correctedArray[i] = incorrectCharacters.getAtIndex(index).data;
                    index++;
                } else {
                    // Handle the case when index exceeds the length of incorrectCharacters
                    // This could happen if the frequencies of characters are not accurately calculated
                    // You may print a message or throw an exception depending on your requirements
                    System.err.println("Error: Index out of range in incorrectCharacters list.");
                    break;
                }
            }
        }
    
        return new String(correctedArray);
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

    public void start() {
        // Find frequencies
        keysecret key = new keysecret();

        int MFreq = key.comparison(Mstr);
        int OFreq = key.comparison(Ostr);
        int CFreq = key.comparison(Cstr);
        int HFreq = key.comparison(Hstr);
        int AFreq = 12 - MFreq - OFreq - CFreq - HFreq;
        System.out.printf("Your string contain: %d M, %d O, %d C, %d H, %d A \n", MFreq, OFreq, CFreq, HFreq, AFreq);

        char[] characters = {'M', 'O', 'C', 'H', 'A'};
        int[] frequencies = {MFreq, OFreq, CFreq, HFreq, AFreq};

        // Generate the initial array
        String resultArray = generateArray(characters, frequencies);
        String targetArray = key.getCorrectKey();
    
        // Print the initial array and the number of correct characters
        int correctCount = 2; //comparison(resultArray, targetArray);
        System.out.println("Generated Array: " + resultArray + " (Correct Characters: " + correctCount + ")");
    
        // Compare with the target array
        int attempts = 1; // Track the number of attempts
        while (correctCount < targetArray.length()) {
            System.out.println("Generated array does not match the target array. Generating a corrected array...");
    
            // Generate a corrected array
            resultArray = correctArray(resultArray, targetArray);
    
            // Count the correct characters
            correctCount = countCorrectCharacters(resultArray, targetArray);
    
            // Print the corrected array and the number of correct characters
            System.out.println("Corrected Array: " + resultArray + " (Correct Characters: " + correctCount + ")");
    
            attempts++; // Increment the number of attempts
        }
    
        System.out.println("Generated array matches the target array.");
        System.out.println("Number of attempts: " + attempts);
    }
}
