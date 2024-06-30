package Lecture3;

///米水
//Represents an immutable right triangle.*/
public class RightTriangle {
    private double[] sides;
    //sides[o] and sides[1] are the two legs,
// and sides[2]is the hypotenuse, so declare it to avoid having a
// magic number in the code:
    public static final int HYPOTENUSE = 2;

    //    /** Make a right triangle.
//     @param legA, legB
//     the two legs of the triangle
//     @param hypotenuse
//     the hypotenuse of the triangle.
//     Requires hypotenuse^2 = legA^2 + legB^2
//     (within the error tolerance of double arithme

    public double[] getAllsides() {
        return sides;
    }
}