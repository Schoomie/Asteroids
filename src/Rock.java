/**
 * Created by Kuba on 01/03/2019.
 */
import java.awt.*;
public class Rock extends Polygon {

    int uLeftXPos, uLeftYPos;

    int xDirection = 1;
    int yDirection = 1;

    int width = Main.boardWidth;
    int height = Main.boardHeight;

    int[] polyXArray, polyYArray;
    //starting positions
    public static int[] sPolyXArray = {10, 13, 15, 16, 17, 14, 15, 18};

    public static int[] sPolyYArray = {5, 1, 5, 6, 8, 3, 15, 18};

    public Rock(int[] polyXArray, int[] polyYArray, int pointsInPoly, int randomStartXPosition, int randomStartYPosition) {
        super(polyXArray, polyYArray, pointsInPoly);
        this.xDirection = (int) (Math.random() * 4 + 1);

        this.yDirection = (int) (Math.random() * 4 + 1);

        this.uLeftXPos = randomStartXPosition;
        this.uLeftYPos = randomStartYPosition;


    }

    public void move() {
        int uLeftXpos = super.xpoints[0];
        int uLeftYpos = super.ypoints[0];

        if (uLeftXPos < 0 || uLeftXPos + 25 > width) xDirection = -xDirection;

        if (uLeftYPos < 0 || uLeftYPos + 25 > width) xDirection = -yDirection;
//rysowanie
        for (int i = 0; i < super.xpoints.length; i++) {
            super.xpoints[i] += xDirection;
            super.ypoints[i] += yDirection;
        }
    }

    public static int[] getpolyXArray(int randomStartXPos) {


        int[] tempPolyXArray = (int[]) sPolyXArray.clone();

        for (int i = 0; i < tempPolyXArray.length; i++) {

            tempPolyXArray[i] += randomStartXPos;

        }

        return tempPolyXArray;

    }

    public static int[] getpolyYArray(int randomStartYPos) {

        int[] tempPolyYArray = (int[]) sPolyYArray.clone();

        for (int i = 0; i < tempPolyYArray.length; i++) {

            tempPolyYArray[i] += randomStartYPos;

        }

        return tempPolyYArray;


    }
}