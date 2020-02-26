package Service;

import java.util.Set;

public class StringService {
    public String[][] convertToMatrix(String input) {
        inputStringCheck(input);
        int sideLength = (int) Math.sqrt(input.length());
        input = input.toUpperCase();
        String[][] output = new String[sideLength][sideLength];
        int counter = 0;
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                output[i][j] = input.substring(counter, ++counter);
            }
        }
        return output;
    }

    public String[] convertToStringArray(Set<int[]> path) {
        String[] result = new String[path.size()];
        int index = 0;
        for (int[] position : path) {
            result[index++] = String.format("[%d,%d]", position[0], position[1]);
        }
        return result;
    }

    private void inputStringCheck(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string can't be empty!");
        }
        int sideLength = (int) Math.sqrt(input.length());
        if (input.length() != (int) Math.pow(sideLength, 2)) {
            throw new IllegalArgumentException("Wrong input string length!\n"
                    + "Input string length must be a square of a natural number.");
        }
        for(char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Input string must contain only letters!");
            }
        }
    }
}
