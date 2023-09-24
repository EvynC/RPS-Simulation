import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Rock extends RpsObject{
    private BufferedImage image;
    private final String path = "./assets/rock.png";

    public Rock(RpsScreen s, int x, int y, int size, int sp) {
        super(s, x, y, size, sp);

        try {
            image = ImageIO.read(new File(path)); // Creating a variable that holds the image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, size, size, null);
    }

    public void move() { // Moves towards the closest Prey
        Scissor scissor = closestPrey();

        if (scissor != null) {
            int targetX = scissor.getX() + scissor.getSize() / 2;
            int targetY = scissor.getY() + scissor.getSize() / 2;
            x += (int) Math.signum(targetX - (getX() + getSize() / 2));
            y += (int) Math.signum(targetY - (getY() + getSize() / 2));
        }
    }

    public Scissor closestPrey() {  // Uses the distance formula to find closest Prey
        ArrayList<Scissor> scissor = screen.getScissor();
        if (scissor != null) {
            double newClosest;
            double closest = Integer.MIN_VALUE;
            Scissor closestScissor = null;

            for (int i = 0; i < scissor.size(); i++) {
                newClosest = Math.sqrt(Math.pow((scissor.get(i).getX() + scissor.get(i).getSize() / 2) - (x + size / 2), 2) + Math.pow((scissor.get(i).getY() + scissor.get(i).getSize() / 2) - (y + size / 2), 2));

                if (Math.abs(newClosest) <= Math.abs(closest)) {
                    closest = newClosest;
                    closestScissor = scissor.get(i);
                }
            }
            return closestScissor;
        }
        return null;
    }
}