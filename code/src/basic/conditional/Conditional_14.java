package basic.conditional;

/*  */

public class Conditional_14 {

    public boolean isPythagorean(int side1, int side2, int side3) {
        int pow1 = side1 * side1;
        int pow2 = side2 * side2;
        int pow3 = (int) Math.pow(side3, 2);

        if (pow1 == pow2 + pow3 || pow2 == pow1 + pow3 || pow3 == pow1 + pow2) {
            return true;
        }

        return false;
    }
}
