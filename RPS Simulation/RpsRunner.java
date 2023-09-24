import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RpsRunner extends JFrame {
    private RpsScreen screen;
    private Box box;

    public RpsRunner() {
        setTitle("Rock Paper Scissors Simulation"); // title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // defualt close operation
        setAlwaysOnTop(true);// Opens and keeps the window focused or on top

        box = new Box(BoxLayout.Y_AXIS); // box layout

        JLabel rockCount = new JLabel("Rocks = "); // game variabels
        JLabel paperCount = new JLabel("Papers = ");
        JLabel scissorCount = new JLabel("Scissors = ");
        JLabel gameWon = new JLabel("");

        screen = new RpsScreen(rockCount, paperCount, scissorCount, gameWon);

        JPanel vals = new JPanel(new FlowLayout());
        vals.add(rockCount);
        vals.add(paperCount);
        vals.add(scissorCount);

        JPanel vals2 = new JPanel(new FlowLayout());
        vals2.add(gameWon);

        box.add(Box.createVerticalGlue());
        box.add(vals);
        box.add(vals2);
        box.add(Box.createVerticalGlue());
        box.add(screen);
        box.add(Box.createVerticalGlue());
        box.add(Box.createVerticalGlue());
        box.add(Box.createVerticalGlue());
        box.add(Box.createVerticalGlue());

        add(box); // adds the layout to screen
        setSize(new Dimension(900, 700)); // sets size
        setLocationRelativeTo(null); // null makes it appear in the center of the screen
        setVisible(true); // In order to see objects in the program

        while (true) { // Continuasly Updates the screen

            screen.move(); // calls move 
            screen.repaint(); // calls repaint

            try {
                Thread.sleep(30); // the wait time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new RpsRunner(); 
    }
}