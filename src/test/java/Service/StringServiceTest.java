package Service;

import org.junit.Assert;
import org.junit.Test;

public class StringServiceTest {
    private StringService stringService = new StringService();

    @Test
    public void toMatrixValidTest() {
        Assert.assertArrayEquals(new String[][]{{"A", "B"}, {"C", "D"}},
                stringService.convertToMatrix("ABCD"));
        Assert.assertArrayEquals(new String[][]{{"A"}},
                stringService.convertToMatrix("A"));
        Assert.assertArrayEquals(new String[][]{{"A", "B", "X"}, {"C", "D", "Y"}, {"E", "F", "Z"}},
                stringService.convertToMatrix("ABXCDYEFZ"));
    }

    @Test
    public void toMatrixInvalidTest() {
        Assert.assertNotEquals(new String[][]{{"A", "C"}, {"B", "D"}},
                stringService.convertToMatrix("ABCD"));
        Assert.assertNotEquals(new String[][]{{"A", "B", "X"}, {"C", "D", "Y"}, {"E", "F", "Z"}},
                stringService.convertToMatrix("ABCDEFXYZ"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void toMatrixNotSquareLengthTest() {
        stringService.convertToMatrix("abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toMatrixNullTest() {
        stringService.convertToMatrix(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void toMatrixEmptyTest() {
        stringService.convertToMatrix("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toMatrixSpaceTest() {
        stringService.convertToMatrix(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toMatrixDigitTest() {
        stringService.convertToMatrix("3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void toMatrixSpecialSymbolTest() {
        stringService.convertToMatrix("#");
    }
}
