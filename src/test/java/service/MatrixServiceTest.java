package service;

import org.junit.Assert;
import org.junit.Test;

public class MatrixServiceTest {
    private MatrixService matrixService = new MatrixService();

    String[][] matrixA = new String[][]{
            {"M", "D", "E", "M"},
            {"B", "A", "C", "Y"},
            {"M", "A", "A", "R"},
            {"S", "T", "E", "N"}
    };
    String[][] matrixB = new String[][]{
            {"E", "L", "A", "D"},
            {"E", "V", "R", "S"},
            {"N", "N", "U", "E"},
            {"U", "R", "S", "F"}
    };
    String[][] matrixC = new String[][]{
            {"S", "I", "N"},
            {"P", "R", "G"},
            {"W", "X", "C"}
    };

    @Test
    public void sequenceValidTest() {
        Assert.assertEquals("[2,0]->[2,1]->[3,1]->[3,2]->[2,2]->[1,2]->[1,1]->[0,1]->[0,2]->[0,3]->[1,3]",
                matrixService.findSequence(matrixA, "MATEACADEMY"));
        Assert.assertEquals("[2,0]->[2,1]->[3,1]->[3,2]->[2,2]->[1,2]->[1,1]->[0,1]->[0,2]->[0,3]->[1,3]",
                matrixService.findSequence(matrixA, "mateACADEMY"));
        Assert.assertEquals("[0,0]->[0,1]->[1,1]->[1,0]->[2,0]->[2,1]->[2,2]->[1,2]->[1,3]->[2,3]",
                matrixService.findSequence(matrixB, "ELVENNURSE"));
        Assert.assertEquals("[0,0]->[0,1]->[1,1]->[1,0]->[2,0]->[2,1]->[2,2]->[1,2]->[1,3]->[2,3]",
                matrixService.findSequence(matrixB, "ELVENnurse"));
        Assert.assertEquals("[0,0]->[0,1]->[1,1]->[1,0]->[2,0]->[3,0]->[3,1]->[3,2]",
                matrixService.findSequence(matrixB, "ELVENURS"));
        Assert.assertEquals("[0,0]->[1,0]->[1,1]->[0,1]->[0,2]->[1,2]",
                matrixService.findSequence(matrixC, "SPRING"));
        Assert.assertEquals("[0,0]->[1,0]->[1,1]->[0,1]->[0,2]->[1,2]",
                matrixService.findSequence(matrixC, "spring"));
    }

    @Test
    public void sequenceBranchingTest() {
        Assert.assertEquals("[3,1]->[2,1]->[1,1]->[1,2]->[1,3]->[2,3]->[2,2]",
                matrixService.findSequence(matrixA, "TAACYRA"));
        Assert.assertEquals("[3,1]->[2,1]->[2,2]->[1,2]->[1,1]->[1,0]",
                matrixService.findSequence(matrixA, "TAACAB"));
    }

    @Test
    public void sequenceInvalidTest() {
        Assert.assertEquals("Can't create requested sequence",
                matrixService.findSequence(matrixA, "MATEACADEMR"));
        Assert.assertEquals("Can't create requested sequence",
                matrixService.findSequence(matrixB, "ELVENNURSA"));
        Assert.assertEquals("Can't create requested sequence",
                matrixService.findSequence(matrixC, "WINX"));
    }

    @Test
    public void longWordTest() {
        Assert.assertEquals("Can't create requested sequence",
                matrixService.findSequence(matrixA, "mateacademylongtest"));
        Assert.assertEquals("Can't create requested sequence",
                matrixService.findSequence(matrixB, "elvennurseelvennurseelvennurseelvennurse"));
        Assert.assertEquals("Can't create requested sequence",
                matrixService.findSequence(matrixC, "springgoodbuywinteriscoming"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyWordTest() {
        matrixService.findSequence(matrixA, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullWordTest() {
        matrixService.findSequence(matrixA, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void spaceWordTest() {
        matrixService.findSequence(matrixA, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digitWordTest() {
        matrixService.findSequence(matrixA, "1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialSymbolWOrdTest() {
        matrixService.findSequence(matrixA, "!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyMatrixTest() {
        matrixService.findSequence(new String[][]{}, "ABC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullMatrixTest() {
        matrixService.findSequence(null, "ABC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void spaceMatrixTest() {
        matrixService.findSequence(new String[][]{{" "}}, "ABC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void digitMatrixTest() {
        matrixService.findSequence(new String[][]{{"1"}}, "ABC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialSymbolMatrixTest() {
        matrixService.findSequence(new String[][]{{"@"}}, "ABC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notSquareMatrixTest() {
        matrixService.findSequence(new String[][]{{"a"}, {"b"}}, "ABC");
    }
}
