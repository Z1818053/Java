import java.awt.*;

// Class: Ball
// Creates a ball object with various attributes.
// Used for the BallAnimationPanel
public class Ball {
    private Color color;
    private int radius;
    private int x, y;     // (x,y) position of ball
    private int dx, dy;   // Change in x and change in y

    // Ball constructor
    // Takes attributes for ball appearance and movement
    public Ball (Color color, int radius, int x, int y, int dx, int dy) {
        this.color = color;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    // draw
    // Draws the ball on the screen
    public void draw(Graphics g) {
        // Sets the color of the circle.
        g.setColor(color);

        // Creates the circle of the correct size.
        g.fillOval(x - radius, y - radius, 2*radius, 2*radius);
    }

    // move
    // Causes the ball to move whenever necessary
    public void move(Dimension d) {
        // Reflect off boundaries
        if (x <= radius || x >= (d.width - radius))
        {
            dx = -1 * dx;
        }
        if (y <= radius || y >= (d.height - radius))
        {
            dy = -1 * dy;
        }

        // Change position based on deltas
        x += dx;
        y += dy;
    }
}
