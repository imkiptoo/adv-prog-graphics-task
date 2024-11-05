import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Pong simulates a simple Pong game with two paddles and a ball.
 * The ball bounces off the walls and paddles, and the paddles can be controlled using specific keys.
 *
 * @author: Isaac Mulwa
 */
public class Pong extends GraphicsProgram {
    private final double BALL_SIZE = 32;
    private final int TABLE_WIDTH = 600;
    private final int TABLE_HEIGHT = 500;
    private final double BALL_SPEED = 2;
    private final double PADDLE_SPEED = 5;
    private final double FRAME_RATE = 60.0;
    private final double PADDLE_WIDTH = 8;
    private final double PADDLE_HEIGHT = 100;
    private final double X_MARGIN = 20;
    private double vx = BALL_SPEED;
    private double vy = BALL_SPEED;

    private GOval ball;
    private GRect leftPaddle;
    private GRect rightPaddle;

    /**
     * Constructor for Pong class.
     * Initializes a new instance of the Pong game.
     * <p>
     * PreCondition: None.
     * PostCondition: A new instance of Pong is created, but not yet running.
     */
    public Pong() {
    }

    /**
     * Runs the main game loop.
     * Sets up the environment, adds key listeners, and continuously updates the ball's position and checks for collisions.
     * <p>
     * PreCondition: None.
     * PostCondition: The game starts and runs indefinitely, with the ball and paddles interacting based on user input and collisions.
     */
    public void run() {
        setup();
        addKeyListeners();

        while (true) {
            checkForBallCollisionsWithWall();
            checkForBallCollisionsWithPaddles();

            moveBall();

            pause(1000 / FRAME_RATE); // 60 fps
        }
    }

    /**
     * Sets up the Pong game environment, including window size, background color, and creation of the ball and paddles.
     * <p>
     * PreCondition: None.
     * PostCondition: The window size and background are set, and the ball and paddles are added to their initial positions.
     */
    private void setup() {
        setSize(TABLE_WIDTH, TABLE_HEIGHT);
        setBackground(Color.BLACK);

        createBall();
        createPaddles();
    }

    /**
     * Creates the Pong ball and places it at the center of the table.
     * <p>
     * PreCondition: Table dimensions are set.
     * PostCondition: A filled white ball is added to the center of the table.
     */
    private void createBall() {
        ball = new GOval(BALL_SIZE, BALL_SIZE);
        ball.setLocation((double) getWidth() / 2 - (BALL_SIZE / 2), (double) getHeight() / 2 - (BALL_SIZE / 2));
        ball.setFilled(true);
        ball.setFillColor(Color.WHITE);
        add(ball);
    }

    /**
     * Creates the left and right paddles and places them at their respective starting positions.
     * <p>
     * PreCondition: Table dimensions are set.
     * PostCondition: Two paddles are added to the left and right sides of the table.
     */
    private void createPaddles() {
        double paddleY = ((double) getSize().height / 2) - (PADDLE_HEIGHT / 2);

        leftPaddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        leftPaddle.setLocation(X_MARGIN, paddleY);
        leftPaddle.setFilled(true);
        leftPaddle.setFillColor(Color.WHITE);
        add(leftPaddle);

        rightPaddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle.setLocation(getWidth() - X_MARGIN, paddleY);
        rightPaddle.setFilled(true);
        rightPaddle.setFillColor(Color.WHITE);
        add(rightPaddle);
    }

    /**
     * Moves the Pong ball by the current velocity in both x and y directions.
     * <p>
     * PreCondition: Ball has been created and added to the table.
     * PostCondition: The ball's position is updated based on its velocity.
     */
    private void moveBall() {
        ball.move(vx, vy);
    }

    /**
     * Checks if the ball has collided with any of the walls and updates velocity accordingly.
     * <p>
     * PreCondition: Ball is on the table and moving.
     * PostCondition: If the ball hits a wall, its velocity direction is reversed to simulate a bounce.
     */
    private void checkForBallCollisionsWithWall() {
        double x = ball.getX();
        double y = ball.getY();

        if (x < 0 || x >= (getWidth() - BALL_SIZE)) {
            vx = -vx;
        }

        if (y < 0 || y >= (getHeight() - BALL_SIZE)) {
            vy = -vy;
        }
    }

    /**
     * Checks if the ball has collided with either paddle and updates velocity accordingly.
     * <p>
     * PreCondition: Ball is on the table and moving.
     * PostCondition: If the ball hits a paddle, its velocity direction is reversed to simulate a bounce.
     */
    private void checkForBallCollisionsWithPaddles() {
        double x = ball.getX() + BALL_SIZE;
        double y = ball.getY() + BALL_SIZE;
        GObject obj = getElementAt(x, y);
        if (obj != null) {
            if (obj == leftPaddle || obj == rightPaddle) {
                vx = -vx;
            }
        }
    }

    /**
     * Handles the key press events to move the paddles.
     * 'q' moves the left paddle up, 'a' moves it down, 'p' moves the right paddle up, and 'l' moves it down.
     * <p>
     * PreCondition: Paddles are created and added to the table.
     * PostCondition: The paddle positions are updated based on user input.
     *
     * @param e The key event triggered by the user.
     */
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        switch (c) {
            case 'q': // left paddle up
            {
                leftPaddle.setLocation(leftPaddle.getX(), leftPaddle.getY() - PADDLE_SPEED);
                break;
            }
            case 'a': // left paddle down
            {
                leftPaddle.setLocation(leftPaddle.getX(), leftPaddle.getY() + PADDLE_SPEED);
                break;
            }
            case 'p': // right paddle up
            {
                rightPaddle.setLocation(rightPaddle.getX(), rightPaddle.getY() - PADDLE_SPEED);
                break;
            }
            case 'l': // right paddle down
            {
                rightPaddle.setLocation(rightPaddle.getX(), rightPaddle.getY() + PADDLE_SPEED);
                break;
            }
        }
    }
}
