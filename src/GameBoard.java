
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class GameBoard extends JFrame{

    private static final long serialVersionUID = 1L;


    public static int boardWidth = 1000;
    public static int boardHeight = 800;


    public static boolean keyHeld = false;


    public static int keyHeldCode;

    public static ArrayList<PhotonTorpedo> torpedos = new ArrayList<PhotonTorpedo>();

    String thrustFile = "file:./src/sounds/kaboom.wav";
    String laserFile = "file:./src/sounds/BEEP2.wav";

    public static void main(String [] args)
    {
        new GameBoard();

    }

    public GameBoard()
    {

        this.setSize(boardWidth, boardHeight);
        this.setTitle("Java Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==87)
                {
                    System.out.println("Forward");

                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;

                }
                else if (e.getKeyCode()==83){
                    System.out.println("Backward");

                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;

                }

                else if (e.getKeyCode()==68){
                    System.out.println("Rotate Right");

                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;

                }

                else if (e.getKeyCode()==65){
                    System.out.println("Rotate Left");

                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                }


                else if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    System.out.println("Shoot");


                    playSoundEffect(laserFile);


                    torpedos.add(new PhotonTorpedo(GameDrawingPanel2.theShip.getShipNoseX(),
                            GameDrawingPanel2.theShip.getShipNoseY(),
                            GameDrawingPanel2.theShip.getRotationAngle()));

                    System.out.println("RotationAngle " + GameDrawingPanel2.theShip.getRotationAngle());

                }

            }

            public void keyReleased(KeyEvent e) {

                keyHeld = false;

            }

        });

        GameDrawingPanel2 gamePanel = new GameDrawingPanel2();

        this.add(gamePanel, BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new RepaintTheBoard2(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.setVisible(true);
    }


    public static void playSoundEffect(String soundToPlay){

        URL soundLocation;
        try {
            soundLocation = new URL(soundToPlay);

            Clip clip = null;

            clip = AudioSystem.getClip();

            AudioInputStream inputStream;

            inputStream = AudioSystem.getAudioInputStream(soundLocation);

            clip.open( inputStream );

            clip.loop(0);

            clip.start();
        }

        catch (MalformedURLException e1) {

            e1.printStackTrace();
        }

        catch (UnsupportedAudioFileException | IOException e1) {

            e1.printStackTrace();
        }

        catch (LineUnavailableException e1) {

            e1.printStackTrace();
        }

    }


}

