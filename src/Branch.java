import java.awt.*;
import java.awt.geom.Line2D;

/**
 * This record represents a single line of the fractal tree and its different segments.
 *
 * @param x1 the starting x coordinate of the branch.
 * @param y1 the starting y coordinate of the branch.
 * @param x2 the end x coordinate of the branch.
 * @param y2 the end y coordinate of the branch.
 * @param color the color of the branch.
 * @param thickness the thickness of the branch.
 */
public record Branch(double x1, double y1, double x2, double y2,
                     Color color, float thickness) implements FractalElement {

    /**
     * Draws the branch as a line with specified color and thickness.
     *
     * @param g the provided graphics for drawing the fractal tree.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke originalStroke = g2.getStroke();

        g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(color);
        g2.draw(new Line2D.Double(x1, y1, x2, y2));

        g2.setStroke(originalStroke);
    }
}
