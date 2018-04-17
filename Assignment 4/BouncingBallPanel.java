/***************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 *
 * CSCI 470
 *
 * Assignment 4
 *
 * Creates the panels for the bouncing ball animation and for the start and stop button
 */


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*  BouncingBallPanel Class
    Combines the animation panel with a button panel interface
 */

public class BouncingBallPanel extends JPanel implements ActionListener
{
    private JButton startButton;
    private JButton stopButton;
    private JPanel buttonPanel;
    private BallAnimationPanel animationPanel;

    /* BouncingBallPanel Constructor
       This constructor creates the stop and start buttons.
       It also creates the ball animation panel.
     */
    public BouncingBallPanel ()
    {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));

        // Create the "start" button and add an ActionListener
        startButton = new JButton("Start");
        startButton.addActionListener(this);

        // Create the "stop" button and add an ActionListener. Disabled initially.
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);

        // Create the panel and add the buttons
        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        // Create the ball animation panel
        animationPanel = new BallAnimationPanel();

        // Position the panels
        add(buttonPanel, BorderLayout.PAGE_START);
        add(animationPanel, BorderLayout.CENTER);

    }

    // actionPerformed
    // Handles the button clicks and starts and stops the ball animation
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            // Begin ball bouncing
            animationPanel.start();

            // Swap enabled buttons
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
        else if (e.getActionCommand().equals("Stop"))
        {
            // Cease ball bouncing
            animationPanel.stop();

            // Swap enabled buttons
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }
}
