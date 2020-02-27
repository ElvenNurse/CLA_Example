import service.MatrixService;
import service.StringService;

public class Main {
    public static void main(String[] args) {
        StringService stringService = new StringService();
        MatrixService matrixService = new MatrixService();
        if (args.length == 2) {
            String[][] matrix = stringService.convertToMatrix(args[0]);
            System.out.println(matrixService.findSequence(matrix, args[1]));
        } else {
            throw new IllegalArgumentException("Wrong number of arguments!");
        }
    }
}
