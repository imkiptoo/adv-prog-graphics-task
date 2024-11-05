# Advanced Programming - Chapter 2 - Graphics

This project showcases three classic games: **Brick Breaker**, **Pong**, and **Billiards**, implemented in Java. Each game leverages Java's AWT and Swing libraries for graphical representation, keyboard/mouse event handling, and animation, demonstrating key programming concepts in 2D graphics and game development.

## Project Structure

The project consists of the following Java files:

1. `BrickBreaker.java`: A version of the Brick Breaker game, where players control a paddle to destroy bricks with a bouncing ball.
2. `Pong.java`: A two-player Pong game featuring simple paddle and ball mechanics with collision detection.
3. `Billiard.java`: A single-player billiards simulation demonstrating realistic ball movement and collisions within a confined space.

## Requirements

- **Java Development Kit (JDK)**: JDK 8 or higher is recommended to compile and run the programs.
- **Java IDE (Optional)**: While the games can be run from the command line, an IDE such as IntelliJ IDEA or Eclipse is suggested for easier visualization and debugging.

## How to Run

1. Clone this repository or download the files directly.
2. Compile the `.java` files:
   ```bash
   javac BrickBreaker.java Pong.java Billiard.java
   ```
3. Run each game individually with:
   ```bash
   java BrickBreaker
   ```
   ```bash
   java Pong
   ```
   ```bash
   java Billiard
   ```

## Game Details

### 1. Brick Breaker

In **Brick Breaker**, the player controls a paddle at the bottom of the screen, aiming to destroy bricks by bouncing a ball toward them. The objective is to clear all the bricks without letting the ball fall below the paddle.

**How it works**:
- **Ball Movement**: The ball continuously bounces off the walls and the paddle. If it hits a brick, it rebounds and the brick disappears.
- **Paddle Control**: The player can move the paddle left or right using the arrow keys.
- **Game Over Condition**: If the ball passes the paddle and exits the screen at the bottom, the game ends.
- **Victory Condition**: The player wins by clearing all bricks from the screen.

**Core Concepts**:
- **Collision Detection**: The game detects when the ball collides with bricks, walls, or the paddle, changing the ball’s direction accordingly.
- **Graphics Rendering**: Using Java’s `Graphics` class, bricks, paddle, and ball are rendered in the game window, with repainting occurring continuously to achieve smooth animation.

### 2. Pong

**Pong** is a two-player game where players control paddles on opposite sides of the screen. They attempt to bounce a ball back and forth, preventing it from passing their paddle.

**How it works**:
- **Ball Movement**: The ball begins in the center of the screen, moving in a random direction. Each time it collides with a paddle, it rebounds, and the speed may increase slightly, making the game more challenging over time.
- **Paddle Control**: Player 1 (left paddle) uses `W` and `S` keys to move up and down. Player 2 (right paddle) uses the up and down arrow keys.
- **Game Over Condition**: If the ball passes a paddle and reaches the screen edge, the opposing player scores a point.
- **Scoring**: The game could track scores to determine a winner after a set number of rounds.

**Core Concepts**:
- **Boundary Detection**: The game checks whether the ball touches the top, bottom, or paddle boundaries, reversing direction upon collision.
- **Multiplayer**: This implementation uses keyboard controls for both players, facilitating two-player gameplay on the same device.
- **Graphics and Animation**: The ball and paddles are rendered in real-time, with continuous repainting for smooth movement.

### 3. Billiard

**Billiard** is a single-player simulation game in which the player uses a cue ball to strike other balls on the screen, aiming to pocket or hit them based on realistic billiards physics.

**How it works**:
- **Cue Ball Control**: The player controls the cue ball's direction and power by clicking on the screen, with the ball moving toward the click position. The further the click is from the cue ball, the greater the force.
- **Ball Movement**: The cue ball, upon being struck, moves toward the target direction. Upon collision with other balls, momentum is transferred, causing them to move.
- **Physics Simulation**: Basic physics principles, such as velocity and collision detection, are applied. When two balls collide, they transfer energy, and their paths change accordingly.
- **Game Mechanics**: This version does not include pockets but focuses on the physics of ball movement and collisions.

**Core Concepts**:
- **2D Physics**: Ball movement and collisions are simulated using 2D physics formulas for velocity and direction change.
- **Mouse Interaction**: The cue ball's movement direction is based on mouse clicks, making it more interactive.
- **Collision Response**: When balls collide, the program calculates new velocities and directions, mimicking real-life billiard interactions.

## Features

- **Real-Time Graphics and Animation**: Each game features dynamic, smooth animations, achieved through Java’s graphics libraries.
- **Collision Detection and Physics**: Basic physics and collision detection algorithms create realistic movement and interaction for the game objects.
- **User Interaction**: Players interact with the games using keyboard and mouse controls, enabling responsive and immersive gameplay.

## Future Improvements

Here are some potential enhancements to make the games more advanced:

- **Enhanced Physics in Billiard**: Add spin, friction, and pockets to make the billiard simulation closer to real-life billiards.
- **Single-Player AI in Pong**: Implement basic AI logic for the paddle, allowing a single-player mode against a computer-controlled opponent.
- **Sound Effects**: Add sounds for events like ball collisions, game-over signals, and winning effects for a more immersive experience.
- **Customizable Difficulty Levels**: Implement difficulty settings that adjust ball speed, paddle size, or game speed for players of different skill levels.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

Isaac Mulwa