package assignment1_000899551;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;
import java.util.Scanner;
import javafx.scene.text.Font;


/**
 * Class header. This is the class Assignment1 of my work.
 * Created on 21st of May, 2023
 * @author XUAN HUY PHAM
 * Student ID: 000899551
 */
public class Assignment1 extends Application {
    /**
     * @param args unused
     * private was used to make sure all variables can only be used in the same class
     */
    private int numrow;
    private int numcolumn;
    private int currentscore;
    private int highscore;
    private int level;
    private int ballX;
    private int ballY;
    private int paddleX;
    private Image paddleImage;
    private Image backgroundImage;




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scanner input = new Scanner(System.in);
        //ASKING USER FOR NUM OF ROWS (MAXIMUM OF 10)
        //IF USER ENTERS A NUMBER BIGGER THAN 10, IT WILL KEEP ASKING THEM UNTIL THEY GOT IT RIGHT
        do {
            System.out.println("Enter number of rows (maximum of 10): ");
            numrow = input.nextInt();
            input.nextLine();

            if (numrow > 10) {
                System.out.println("It MUST be 10 or less. Try again.");
            }
        } while (numrow >10);

        //SAME THING AS ABOVE. ASKING USER FOR NUM OF COLUMNS (MAX OF 8)
        do {
            System.out.println("Enter number of columns (maximum of 8): ");
            numcolumn = input.nextInt();
            input.nextLine();

            if (numcolumn > 8) {
                System.out.println("It MUST be 8 or less. Try again.");
            }
        } while (numcolumn >8);

        //ASKING USER FOR CURRENT SCORE
        System.out.println("Enter current score: ");
        currentscore = input.nextInt();

        //ASKING USER FOR HIGH SCORE. IF CURRENT SCORE IS HIGHER THAN HIGH SCORE. IT WILL BE REPLACED.
        //IF IT'S STILL LOWER THAN HIGH SCORE. IT REMAINS THE SAME
        System.out.println("Enter high score:");
        highscore = input.nextInt();
        input.nextLine();

        if (currentscore > highscore) {
            highscore = currentscore;
            System.out.println("New high score is set!");
        } else {
            System.out.println("Current high score is still the same!");
        }



        //ASKING USER FOR THE LEVEL THEY ARE IN
        System.out.println("What level are you in?:");
        level = input.nextInt();


        //ASKING USER FOR THE BALL'S POSITION
        //THE RANGE FOR THE BALL X IS 10-450 AND FOR Y IS 300-500
        //IF USER'S INPUT IS OUT OF RANGE. THEY WILL BE ASKED TO INPUT AGAIN UNTIL THEY GOT IT RIGHT
        System.out.println("Enter the ball's position.");
        do {
            System.out.println("Enter X position of the ball (between 10 and 450)");
            ballX = input.nextInt();
            input.nextLine();

            if (ballX < 10 || ballX > 450) {
                System.out.println("Wrong input. X has to be within the range of 10 and 500. \nTry again.");
                continue;
            }

            System.out.println("Enter Y position of the ball (between 300 and 500)");
            ballY = input.nextInt();
            input.nextLine();
            if (ballY < 300 || ballY > 500) {
                System.out.println("Wrong input. Y has to be within the range of 300 and 500. \nTry again.");
                continue;
            }
            break;
        } while (true);



        //ASK FOR PADDLE'S X POSITION

        do {
            System.out.println("Enter the paddle's X position (within 10 and 390):");
            paddleX = input.nextInt();
            input.nextLine();

            if (paddleX < 10 || paddleX > 390) {
                System.out.println("Wrong input. It must be within 6 and 390. Try again.");
            }
        } while (paddleX < 10 || paddleX > 390);

        //REPLACE SOME SHAPES WITH IMAGES
        paddleImage = new Image("metal.jpg");
        backgroundImage = new Image("galaxy.jpg");




        stage.setTitle("ARKANOID VERSION 2023"); // window title here
        Canvas canvas = new Canvas(700, 600); // canvas size here
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // This code starts a "thread" which will run your animation
        Thread t = new Thread(() -> animate(gc));
        t.start();
    }

    /**
     * drawing shapes from here
     * @param gc
     */

    public void animate(GraphicsContext gc) {
        //BACKGROUND FOR PLAY AREA
        gc.setFill(Color.MEDIUMBLUE);
        gc.fillRect(0, 0, 520, 600);
        gc.drawImage(backgroundImage, 0, 0, 520, 600);
        //background for score area
        gc.setFill(Color.BLACK);
        gc.fillRect(500, 0, 180, 600);

        //THE BORDER OF THE PLAY AREA
        gc.setStroke(Color.YELLOWGREEN);
        gc.setLineWidth(10.0);
        gc.strokeRect(0, 0, 500, 600);

        //PADDLE
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillRect(paddleX, 520, 100, 20);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        gc.strokeRect(paddleX, 520, 100, 20);
        //REPLACE THE PADDLE WITH IMAGE
        gc.drawImage(paddleImage, paddleX, 520, 100, 20);

        //BALL
        gc.setFill(Color.RED);
        gc.fillOval(ballX, ballY, 20, 20);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        gc.strokeOval(ballX, ballY, 20, 20);



        //BRICKKKKKKKKS

        int countrow = 0; //start counting num of rows
        double j = 30; //starting point of the first brick in Y-coordinate

        //i will put the loop for counting columns inside the loop for counting rows
        while (countrow < numrow) {
            int countcolumn = 0;//start counting num of columns
            double i = 16; // same as "j" but in X-coordinate


            while (countcolumn < numcolumn) {
                gc.setFill(Color.YELLOWGREEN);
                gc.fillRect(i, j, 50, 20);

                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2.0);
                gc.strokeRect(i, j, 50, 20);

                if (countcolumn >= numcolumn) {
                    break;
                }
                i += 60;
                countcolumn++;
            }
            if (countrow >= numrow){
                break;
            }
            j+=25;
            countrow++;
        }
        //end of loops for brickkkkkk



        //NAME OF THE GAME
        gc.setFont(Font.font(20));
        gc.setFill(Color.RED);
        gc.fillText("COMP 10062",550,50);
        gc.setFont(Font.font(30));
        gc.setFill(Color.RED);
        gc.fillText("ARKANOID",520,75);
        //CREDITS
        gc.setFont(Font.font(13));
        gc.setFill(Color.WHITE);
        gc.fillText("by HUY XUAN PHAM",530,95);


        //DISPLAY CURRENT SCORE
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITESMOKE);
        gc.fillText("YOUR SCORE \n ",520,210);
        gc.setFont(Font.font(25));
        gc.setFill(Color.KHAKI);
        gc.fillText(String.valueOf(currentscore),520,240);


        //DISPLAY HIGH SCORE
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITESMOKE);
        gc.fillText("**HIGH SCORE** \n ",520,300);
        gc.setFont(Font.font(35));
        gc.setFill(Color.YELLOW);
        gc.fillText(String.valueOf(highscore),520,340);

        //DISPLAY LEVEL/WAVE
        gc.setFont(Font.font(30));
        gc.setFill(Color.WHITESMOKE);
        gc.fillText("LEVEL \n ",520,400);
        gc.setFont(Font.font(40));
        gc.setFill(Color.FUCHSIA);
        gc.fillText(String.valueOf(level),520,440);


    }

    private void play_audio()
    {
        AudioClip note = new AudioClip((this.getClass().getResource("gamemusic.wav").toString()));
        note.play();
    }

    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}