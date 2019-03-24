import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

class GameDrawingPanel2 extends JComponent {



    public static ArrayList<Rock> rocks = new ArrayList<Rock>();

    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;

    static SpaceShip theShip = new SpaceShip();


    int width = GameBoard.boardWidth;
    int height = GameBoard.boardHeight;


    public GameDrawingPanel2() {

        for(int i = 0; i < 10; i++){

            int randomStartXPos = (int) (Math.random() * (GameBoard.boardWidth - 40) + 1);
            int randomStartYPos = (int) (Math.random() * (GameBoard.boardHeight - 40) + 1);

            rocks.add(new Rock(Rock.getpolyXArray(randomStartXPos), Rock.getpolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));

            Rock.rocks = rocks;

        }

    }

    public void paint(Graphics g) {

        Graphics2D graphicSettings = (Graphics2D)g;

        AffineTransform identity = new AffineTransform();


        graphicSettings.setColor(Color.BLACK);
        graphicSettings.fillRect(0, 0, getWidth(), getHeight());


        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        graphicSettings.setPaint( Color.WHITE );


        for(Rock rock : rocks){

            if(rock.onScreen){

                rock.move(theShip, GameBoard.torpedos);

                graphicSettings.draw(rock);

            }

        }


        if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 68){

            theShip.increaseRotationAngle();

        } else


            if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 65){

                theShip.decreaseRotationAngle();

            } else

            if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 87){

                theShip.setMovingAngle(theShip.getRotationAngle());

                theShip.increaseXVelocity(theShip.shipXMoveAngle(theShip.getMovingAngle())*0.1);
                theShip.increaseYVelocity(theShip.shipYMoveAngle(theShip.getMovingAngle())*0.1);

            }

        theShip.move();


        graphicSettings.setTransform(identity);

        graphicSettings.translate(theShip.getXCenter(),theShip.getYCenter());

        graphicSettings.rotate(Math.toRadians(theShip.getRotationAngle()));

        graphicSettings.draw(theShip);

        for(PhotonTorpedo torpedo : GameBoard.torpedos){

            torpedo.move();


            if(torpedo.onScreen){


                graphicSettings.setTransform(identity);


                graphicSettings.translate(torpedo.getXCenter(),torpedo.getYCenter());

                graphicSettings.draw(torpedo);

            }

        }


    }


}