import java.awt.Polygon;

@SuppressWarnings("serial")

public class SpaceShip extends Polygon{

    int uLeftXPos = 500, uLeftYPos = 400;

    int xDirection = 0;

    int yDirection = 0;

    int width = GameBoard.boardWidth;

    int height = GameBoard.boardHeight;

    public static int[] polyXArray = {500,527,500,508,500};

    public static int[] polyYArray = {400,415,430,415,400};

    public SpaceShip(){

        super(polyXArray, polyYArray, 5);

    }

    public void move(){

        int uLeftXPos = super.xpoints[0];

        int uLeftYPos = super.ypoints[0];
        // If the ship hits a wall it will go in the opposite direction
        if (uLeftXPos < 0 || (uLeftXPos + 25) > width) xDirection = -xDirection;
        if (uLeftYPos < 0 || (uLeftYPos + 50) > height) yDirection = -yDirection;


        for (int i = 0; i < super.xpoints.length; i++){



            super.xpoints[i] += xDirection;

            super.ypoints[i] += yDirection;
        }

    }
}
