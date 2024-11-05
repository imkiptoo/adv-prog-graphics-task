import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Billiard simulates a billiard ball bouncing within a rectangular table.
 * The ball moves with constant speed and changes direction upon collision with the walls.
 *
 * @author: Isaac Mulwa
 */
public class Billiard extends GraphicsProgram {
    private final double BALL_SIZE = 32;
    private final int TABLE_WIDTH = 400;
    private final int TABLE_HEIGHT = 600;
    private final double BALL_SPEED = 2;
    private final double FRAME_RATE = 60.0;

    private GOval ball;
    private double vx = BALL_SPEED;
    private double vy = BALL_SPEED;

    /**
     * Constructor for Billiard class.
     * Initializes a new instance of the Billiard simulation.
     * <p>
     * Preconditions: None.
     * PostCondition: A new instance of Billiard is created, but not yet running.
     */
    public Billiard() {
    }

    /**
     * Runs the main simulation loop.
     * Sets up the environment, and continuously updates the ball's position, checking for collisions.
     * <p>
     * Preconditions: None.
     * PostCondition: The simulation starts and runs indefinitely, with the ball bouncing around the table.
     */
    public void run() {
        setup();

        while (true) {
            checkForCollisionsWithWall();
            moveBall();

            pause(1000 / FRAME_RATE); // 60 fps
        }
    }

    /**
     * Sets up the billiard table environment, including window size, background color, and ball creation.
     * <p>
     * Preconditions: None.
     * PostCondition: The window size and background are set, and the ball is added to the center of the table.
     */
    private void setup() {
        setSize(TABLE_WIDTH, TABLE_HEIGHT);
        setBackground(Color.GREEN);

        createBall();
    }

    /**
     * Creates the billiard ball and places it at the center of the table.
     * <p>
     * Preconditions: Table dimensions are set.
     * PostCondition: A filled black ball is added to the center of the table.
     */
    private void createBall() {
        ball = new GOval(BALL_SIZE, BALL_SIZE);
        ball.setLocation((double) getWidth() / 2 - (BALL_SIZE / 2), (double) getHeight() / 2 - (BALL_SIZE / 2));
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        add(ball);
    }

    /**
     * Moves the billiard ball by the current velocity in both x and y directions.
     * <p>
     * Preconditions: Ball has been created and added to the table.
     * PostCondition: The ball's position is updated based on its velocity.
     */
    private void moveBall() {
        ball.move(vx, vy);
    }

    /**
     * Checks if the ball has collided with any of the walls and updates velocity accordingly.
     * <p>
     * Preconditions: Ball is on the table and moving.
     * PostCondition: If the ball hits a wall, its velocity direction is reversed to simulate a bounce.
     */
    private void checkForCollisionsWithWall() {
        double x = ball.getX();
        double y = ball.getY();

        if (x < 0 || x >= (getWidth() - BALL_SIZE)) {
            vx = -vx;
        }

        if (y < 0 || y >= (getHeight() - BALL_SIZE)) {
            vy = -vy;
        }
    }
}
