import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Scissor extends RpsObject{ 
    private BufferedImage image;
    private final String path = "./assets/scissor.png";

    public Scissor(RpsScreen s, int x, int y, int size, int sp) {
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
        Paper paper = closestPrey();

        if (paper != null) {
            int targetX = paper.getX() + paper.getSize() / 2;
            int targetY = paper.getY() + paper.getSize() / 2;
            x += (int) Math.signum(targetX - (getX() + getSize() / 2));
            y += (int) Math.signum(targetY - (getY() + getSize() / 2));
        }
    }

    public Paper closestPrey() { // Uses the distance formula to find closest Prey
        ArrayList<Paper> papers = screen.getPaper();
        if (papers != null) {
            double newClosest;
            double closest = Integer.MIN_VALUE;
            Paper closestPaper = null;

            for (int i = 0; i < papers.size(); i++) {
                newClosest = Math.sqrt(Math.pow((papers.get(i).getX() + papers.get(i).getSize() / 2) - (x + size / 2), 2) + Math.pow((papers.get(i).getY() + papers.get(i).getSize() / 2) - (y + size / 2), 2));

                if (Math.abs(newClosest) <= Math.abs(closest)) {
                    closest = newClosest;
                    closestPaper = papers.get(i);
                }
            }
            return closestPaper;
        }
        return null;
    }
}