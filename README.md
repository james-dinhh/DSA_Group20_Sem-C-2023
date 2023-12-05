# DSA---Group20
1. 'generateArray':
  + Takes a map of character frequencies (frequencies) as input.
  + Creates a list (array) where each character appears as many times as its frequency.
  + Shuffles the list to randomize the order.
  + Converts the list to a string and returns it.

2. 'correctArray':
  + Takes two strings as input (resultArray and targetArray).
  + Identifies incorrect characters by comparing each character in both strings.
  + Shuffles the list of incorrect characters.
  + Replaces incorrect characters in resultArray with shuffled ones.
  + Returns the corrected string.

3. 'countCorrectCharacters':
  + Takes two strings as input (generatedArray and targetArray).
  + Counts the number of correct characters at corresponding positions in both strings.

4. 'main':
  + Defines a map of character frequencies (frequencies) and a target array (targetArray).
  + Generates an initial array using the generateArray method.
  + Prints the initial array and the number of correct characters.
  + Checks if the generated array matches the target array. If not, it enters a loop:
  + Generates a corrected array using the correctArray method.
  + Prints the corrected array and the number of correct characters.
  + Repeats until the entire array is correct.
  + Prints a message when the generated array matches the target array.
