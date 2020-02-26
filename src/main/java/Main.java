import Service.MatrixService;
import Service.StringService;

public class Main {
    private static StringService stringService = new StringService();
    private static MatrixService matrixService = new MatrixService();

    public static void main(String[] args) {
        if (args.length == 2) {
            String[][] matrix = stringService.convertToMatrix(args[0]);
            System.out.println(matrixService.findSequence(matrix, args[1]));
        } else {
            throw new IllegalArgumentException("Wrong number of arguments!");
        }
    }
}
