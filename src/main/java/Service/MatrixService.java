package Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MatrixService {
    private StringService stringService;

    public MatrixService() {
        stringService = new StringService();
    }

    public String findSequence(String[][] matrix, String word) {
        matrixCheck(matrix);
        wordCheck(word);
        word = word.toUpperCase();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i]. length; j++) {
                Node node = new Node();
                if (matrix[i][j].equals(word.substring(0, 1))) {
                    node.currentPosition = new int[]{i, j};
                    findNext(matrix, word, node);
                    if (node.path.size() == word.length()) {
                        String[] sequence = stringService.convertToStringArray(node.path);
                        return String.join("->", sequence);
                    }
                }
            }
        }
        return "Can't create requested sequence";
    }

    private void matrixCheck(String[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Matrix can't be empty!");
        }
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix have to be square");
        }
        for (String[] rows : matrix) {
            for (String cell : rows) {
                cell = cell.toUpperCase();
                if (!Character.isLetter(cell.charAt(0))) {
                    throw new IllegalArgumentException("Matrix must contain only letters!");
                }
            }
        }
    }

    private void wordCheck(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word can't be empty!");
        }
        for(char c : word.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Word must contain only letters!");
            }
        }
    }

    private void setAvailable(String[][] matrix, Node node) {
        int x = node.currentPosition[0];
        int y = node.currentPosition[1];
        int length = matrix.length - 1;

        Set<int[]> reachableCells = new HashSet<>();
        if (x == 0) {
            if (y == 0) {
                reachableCells.add(new int[]{x + 1, y});
                reachableCells.add(new int[]{x, y + 1});
            } else if (y == length) {
                reachableCells.add(new int[]{x + 1, y});
                reachableCells.add(new int[]{x, y - 1});
            } else {
                reachableCells.add(new int[]{x + 1, y});
                reachableCells.add(new int[]{x, y - 1});
                reachableCells.add(new int[]{x, y + 1});
            }
        } else if (x == length) {
            if (y == 0) {
                reachableCells.add(new int[]{x - 1, y});
                reachableCells.add(new int[]{x, y + 1});
            } else if (y == length) {
                reachableCells.add(new int[]{x - 1, y});
                reachableCells.add(new int[]{x, y - 1});
            } else {
                reachableCells.add(new int[]{x - 1, y});
                reachableCells.add(new int[]{x, y - 1});
                reachableCells.add(new int[]{x, y + 1});
            }
        } else {
            if (y == 0) {
                reachableCells.add(new int[]{x - 1, y});
                reachableCells.add(new int[]{x + 1, y});
                reachableCells.add(new int[]{x, y + 1});
            } else if (y == length) {
                reachableCells.add(new int[]{x - 1, y});
                reachableCells.add(new int[]{x + 1, y});
                reachableCells.add(new int[]{x, y - 1});
            } else {
                reachableCells.add(new int[]{x - 1, y});
                reachableCells.add(new int[]{x + 1, y});
                reachableCells.add(new int[]{x, y - 1});
                reachableCells.add(new int[]{x, y + 1});
            }
        }

        removeTraversedCells(node, reachableCells);
        node.availableCells = reachableCells;
    }

    private void removeTraversedCells(Node node, Set<int[]> reachableCells) {
        Set<int[]> cellsToRemove = new HashSet<>();
        for(int[] cell : reachableCells) {
            for (int[] path : node.path) {
                if (cell[0] == path[0] && cell[1] == path[1]) {
                    cellsToRemove.add(cell);
                }
            }
        }
        reachableCells.removeAll(cellsToRemove);
    }

    private void findNext(String[][] matrix, String word, Node node) {
        node.path.add(node.currentPosition);

        int index = node.path.size();

        if (index == word.length()) {
            return;
        }

        setAvailable(matrix, node);

        if (node.availableCells.size() == 0) {
            return;
        }

        String nextCellValue = word.substring(index, index + 1);
        for(int[] position : node.availableCells) {
            if (matrix[position[0]][position[1]].equals(nextCellValue)) {
                node.currentPosition = position;
                findNext(matrix, word, node);
                if (node.path.size() == word.length()) {
                    return;
                }
            }
        }

        node.path.remove(getLastPosition(node));
        node.currentPosition = getLastPosition(node);
    }

    private int[] getLastPosition(Node node) {
        int[] lastPosition = new int[2];
        for (int[] position: node.path) {
            lastPosition = position;
        }
        return lastPosition;
    }

    static class Node {
        private Set<int[]> path;
        private Set<int[]> availableCells;
        private int[] currentPosition;

        public Node() {
            path = new LinkedHashSet<>();
            currentPosition = new int[2];
            availableCells = new HashSet<>();
        }
    }
}
