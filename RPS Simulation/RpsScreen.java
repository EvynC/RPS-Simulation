import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RpsScreen extends JPanel {
    private ArrayList<Rock> rockObjects = new ArrayList<Rock>(); // holds objects
    private ArrayList<Paper> paperObjects = new ArrayList<Paper>(); // holds objects
    private ArrayList<Scissor> scissorObjects = new ArrayList<Scissor>(); // holds objects
    private int speed, size, x, y;
    private Dimension dimension;
    private JLabel rockCount, paperCount, scissorCount, gameWon; // Labels
    private boolean gameOver;

    public RpsScreen(JLabel rc, JLabel pc, JLabel sc, JLabel gw) {
        gameOver = false;
        rockCount = rc;
        paperCount = pc;
        scissorCount = sc;
        gameWon = gw;
        size = 20;
        speed = 1;
        dimension = new Dimension(800, 600); // Instantiates variables 

        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));

        for (int i = 0; i < 15; i++) {
            randomize();
            rockObjects.add(new Rock(this, x, y, size, speed));
        }
        for (int i = 0; i < 15; i++) {
            randomize();
            paperObjects.add(new Paper(this, x, y, size, speed));
        }
        for (int i = 0; i < 15; i++) {
            randomize();
            scissorObjects.add(new Scissor(this, x, y, size, speed));
        } // creates objects
    }

    public void randomize() { // randomizes the starting x,y coordinates as well as the speed of the object
        x = (int) (Math.random() * (dimension.getWidth() - size));
        y = (int) (Math.random() * (dimension.getHeight() - size));
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < rockObjects.size(); i++) {
            rockObjects.get(i).paint(g);
        }
        for (int i = 0; i < paperObjects.size(); i++) {
            paperObjects.get(i).paint(g);
        }
        for (int i = 0; i < scissorObjects.size(); i++) {
            scissorObjects.get(i).paint(g);
        }

        checkCollisions();

        rockCount.setText("Rocks = " + rockObjects.size());
        paperCount.setText("Papers = " + paperObjects.size());
        scissorCount.setText("Scissors = " + scissorObjects.size());

        checkGameEnd();
    }

    public void move() {
        if (gameOver != true) {

            for (int i = 0; i < rockObjects.size(); i++) {
                rockObjects.get(i).move();
            }

            for (int i = 0; i < paperObjects.size(); i++) {
                paperObjects.get(i).move();
            }
            for (int i = 0; i < scissorObjects.size(); i++) {
                scissorObjects.get(i).move();
            }
        }
    }

    public void checkGameEnd() {
        if (paperObjects.size() == 0 & scissorObjects.size() == 0) {
            gameWon.setText("ROCK IS THE WINNER");
            gameOver = true;
        } else if (paperObjects.size() == 0 & rockObjects.size() == 0) {
            gameWon.setText("SCISSOR IS THE WINNER");
            gameOver = true;
        } else if (rockObjects.size() == 0 & scissorObjects.size() == 0) {
            gameWon.setText("PAPER IS THE WINNER");
            gameOver = true;
        }
    }

    public void checkCollisions() { 
        int[] data = new int[4];

        for (int i = 0; i < rockObjects.size(); i++) { // ROCK
            for (int j = 0; j < paperObjects.size(); j++) {
                if (paperObjects.get(j).getRect().intersects(rockObjects.get(i).getRect())
                        && rockObjects.iterator().hasNext()) {
                    data[0] = rockObjects.get(i).getX();
                    data[1] = rockObjects.get(i).getY();
                    data[2] = rockObjects.get(i).getSize();
                    data[3] = rockObjects.get(i).getSpeed();
                    rockObjects.remove(i);
                    paperObjects.add(new Paper(this, data[0], data[1], data[2], data[3]));
                    if (i > 0)
                        i--;
                }
                if (rockObjects.size() == 0)
                    break;
            }
        }

        for (int i = 0; i < scissorObjects.size(); i++) { // SCISSOR
            for (int j = 0; j < rockObjects.size(); j++) {
                if (rockObjects.get(j).getRect().intersects(scissorObjects.get(i).getRect())
                        && scissorObjects.iterator().hasNext()) {
                    data[0] = scissorObjects.get(i).getX();
                    data[1] = scissorObjects.get(i).getY();
                    data[2] = scissorObjects.get(i).getSize();
                    data[3] = scissorObjects.get(i).getSpeed();
                    scissorObjects.remove(i);
                    rockObjects.add(new Rock(this, data[0], data[1], data[2], data[3]));
                    if (i > 0)
                        i--;
                }
                if (scissorObjects.size() == 0)
                    break;
            }
        }

        for (int i = 0; i < paperObjects.size(); i++) { // PAPER
            for (int j = 0; j < scissorObjects.size(); j++) {
                if (scissorObjects.get(j).getRect().intersects(paperObjects.get(i).getRect())
                        && paperObjects.iterator().hasNext()) {
                    data[0] = paperObjects.get(i).getX();
                    data[1] = paperObjects.get(i).getY();
                    data[2] = paperObjects.get(i).getSize();
                    data[3] = paperObjects.get(i).getSpeed();
                    paperObjects.remove(i);
                    scissorObjects.add(new Scissor(this, data[0], data[1], data[2], data[3]));
                    if (i > 0)
                        i--;
                }
                if (paperObjects.size() == 0)
                    break;
            }
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public ArrayList<Paper> getPaper() {
        return paperObjects;
    }

    public ArrayList<Scissor> getScissor() {
        return scissorObjects;
    }

    public ArrayList<Rock> getRock() {
        return rockObjects;
    }
}