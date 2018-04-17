/***************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 *
 * CSCI 470
 *
 * Assignment 4
 *
 * Adds the balls to the panels and starts and stop the animation
 */


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*  BallAnimationPanel Class
    Creates a animation panel for bouncing balls
    This Panel runs on a separate thread.
 */
public class BallAnimationPanel extends JPanel implements Runnable
{
    private ArrayList<Ball> BallList = new ArrayList<>();
    private Dimension dimension = null;
    private volatile Thread animationThread = null;

    // start
    // Creates a dedicated thread for the animation
    public void start()
    {
        if (animationThread == null)
        {
            animationThread = new Thread(this);
            animationThread.start();
        }
    }

    // stop
    // Ceases the simulation by stopping the thread
    public void stop()
    {
        animationThread.interrupt();
        animationThread = null;
    }

    // paintComponent
    // Adds balls to the panel and draws it as well
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Create balls and get size.
        if( dimension == null) {
            dimension = getSize();
            BallList.add(new Ball(Color.RED, 10, 11, 11, 5, 1));
            BallList.add(new Ball(Color.BLACK, 20, 21, 21, 3, -7));
            BallList.add(new Ball(Color.BLUE, 15, 16, 16, 11, 4));
            BallList.add(new Ball(Color.YELLOW, 10, 11, 11, -4, -8));
            BallList.add(new Ball(Color.DARK_GRAY, 5, 6, 6, 7, 2));
        }

        // Fill background
        g.setColor(Color.WHITE);
        g.fillRect(0,0, dimension.width, dimension.height);

        // Draw and move each ball in the list
        for (Ball ball : BallList)
        {
            ball.draw(g);
            ball.move(dimension);
        }
    }

    // run
    // Updates the balls every 25 ms until the thread is stopped.
    public void run()
    {
        while (Thread.currentThread() == animationThread)
        {
            try
            {
                Thread.sleep(25);
            }
            catch (InterruptedException e )
            {
                return;
            }
            repaint();
        }

    }
}
