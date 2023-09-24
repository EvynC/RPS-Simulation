import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Paper extends RpsObject {
    private BufferedImage image;
    private final String path = "./assets/paper.png";

    public Paper(RpsScreen s, int x, int y, int size, int sp) {
        super(s, x, y, size, sp); // Initiates the instance variables

        try { // Reads the image
            image = ImageIO.read(new File(path)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) { 
        g.drawImage(image, x, y, size, size, null);
    }

    public void move() { // Moves towards the closest Prey
        Rock rock = closestPrey();

        if (rock != null) {
            int targetX = rock.getX() + rock.getSize() / 2;
            int targetY = rock.getY() + rock.getSize() / 2;
            x += (int) Math.signum(targetX - (getX() + getSize() / 2));
            y += (int) Math.signum(targetY - (getY() + getSize() / 2));
        }
    }

    public Rock closestPrey() { // Uses the distance formula to find closest Prey
        ArrayList<Rock> rock = screen.getRock();
        if (rock != null) {
            double newClosest;
            double closest = Integer.MIN_VALUE;
            Rock closestRock = null;

            for (int i = 0; i < rock.size(); i++) { 
                newClosest = Math.sqrt(Math.pow((rock.get(i).getX() + rock.get(i).getSize() / 2) - (x + size / 2), 2) + Math.pow((rock.get(i).getY() + rock.get(i).getSize() / 2) - (y + size / 2), 2));

                if (Math.abs(newClosest) <= Math.abs(closest)) {
                    closest = newClosest;
                    closestRock = rock.get(i);
                }
            }
            return closestRock;
        }
        return null;
    }
}