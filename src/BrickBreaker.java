import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * BrickBreaker simulates a classic brick breaker game where the player controls a paddle to bounce a ball to break bricks.
 * The ball moves continuously, bouncing off the walls, paddle, and bricks, and the goal is to remove all bricks from the table.
 *
 * @author: Isaac Mulwa
 */
public class BrickBreaker extends GraphicsProgram {

    private static final int APPLICATION_WIDTH = 400;
    private static final int APPLICATION_HEIGHT = 600;
    private static final double BALL_DIAMETER = 20;
    private static final double PADDLE_WIDTH = 100;
    private static final double PADDLE_HEIGHT = 20;
    private static final double BRICK_WIDTH = 30;
    private static final double BRICK_HEIGHT = 20;
    private static final double BRICK_SEP = 5;
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 10;
    private static final double PADDLE_Y_OFFSET = 80;
    private static final double BALL_START_VX = 4;
    private static final double BALL_START_VY = 3;
    private static final int FRAME_PAUSE = 40;

    private GOval ball;
    private GRect paddle;
    private double vx = BALL_START_VX;
    private double vy = BALL_START_VY;
    private RandomGenerator randomGen = RandomGenerator.getInstance();

    /**
     * Runs the main game loop.
     * Sets up the game environment, and continuously moves the ball and checks for collisions.
     * <p>
     * PreCondition: None.
     * PostCondition: The game starts and runs indefinitely until the ball falls out or all bricks are cleared.
     */
    public void run() {
        setup();

        // game loop
        while (true) {
            moveBall();
            checkForCollision();
            pause(FRAME_PAUSE);
        }
    }

    /**
     * Sets up the game environment, including the paddle, ball, and bricks.
     * <p>
     * PreCondition: None.
     * PostCondition: The game window size is set, the ball, paddle, and bricks are created.
     */
    private void setup() {
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        createBall();
        createPaddle();
        createWall();
        addMouseListeners();
    }

    /**
     * Creates the ball and places it at the center of the window.
     * <p>
     * PreCondition: Application dimensions are set.
     * PostCondition: A filled ball is added to the center of the window.
     */
    private void createBall() {
        ball = new GOval(BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        add(ball, APPLICATION_WIDTH / 2 - BALL_DIAMETER / 2, APPLICATION_HEIGHT / 2 - BALL_DIAMETER / 2);
    }

    /**
     * Creates the paddle and places it at the bottom of the window.
     * <p>
     * PreCondition: Application dimensions are set.
     * PostCondition: A filled paddle is added at the specified Y offset from the bottom.
     */
    private void createPaddle() {
        paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle, APPLICATION_WIDTH / 2 - PADDLE_WIDTH / 2, APPLICATION_HEIGHT - PADDLE_Y_OFFSET);
    }

    /**
     * Creates a wall of bricks at the top of the window.
     * <p>
     * PreCondition: Application dimensions are set.
     * PostCondition: A grid of filled bricks is added at the top of the window.
     */
    private void createWall() {
        int x = 20;
        int y = 10;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(randomGen.nextColor());
                add(brick, x, y);
                x += BRICK_WIDTH + BRICK_SEP;
            }
            x = 20;
            y += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    /**
     * Moves the ball according to its velocity.
     * <p>
     * PreCondition: Ball has been created and added to the window.
     * PostCondition: The ball's position is updated based on its velocity.
     */
    private void moveBall() {
        ball.move(vx, vy);
    }

    /**
     * Checks for collisions with walls, bricks, or the paddle and updates velocity or removes bricks accordingly.
     * <p>
     * PreCondition: Ball is on the window and moving.
     * PostCondition: The ball's velocity changes upon collision, and bricks are removed if hit.
     */
    private void checkForCollision() {
        checkForCollisionWithWall();
        checkForCollisionWithBricksOrPaddle();
    }

    /**
     * Checks if the ball has collided with any walls and updates velocity accordingly.
     * <p>
     * PreCondition: Ball is on the window and moving.
     * PostCondition: If the ball hits a wall, its velocity direction is reversed.
     */
    private void checkForCollisionWithWall() {
        double x = ball.getX();
        double y = ball.getY();
        if ((x < 0) || (x > APPLICATION_WIDTH - BALL_DIAMETER)) {
            vx = -vx;
        }
        if ((y < 0) || (y > APPLICATION_HEIGHT - BALL_DIAMETER)) {
            vy = -vy;
        }
    }

    /**
     * Checks if the ball has collided with any bricks or the paddle and updates velocity or removes bricks accordingly.
     * <p>
     * PreCondition: Ball is on the window and moving.
     * PostCondition: If the ball hits a brick or paddle, its velocity direction is reversed, and bricks are removed if hit.
     */
    private void checkForCollisionWithBricksOrPaddle() {
        double x = ball.getX();
        double y = ball.getY();
        GObject obj = getElementAt(x, y);
        if (obj != null) {
            if (obj == paddle) {
                vy = -vy;
            } else { // must be brick
                remove(obj);
                vy = -vy;
            }
        }
    }

    /**
     * Handles mouse movement events to move the paddle.
     * <p>
     * PreCondition: Paddle has been created and added to the window.
     * PostCondition: The paddle's position is updated based on mouse movement.
     *
     * @param e The mouse event triggered by the user.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        paddle.setLocation(x, paddle.getY());
    }
}
