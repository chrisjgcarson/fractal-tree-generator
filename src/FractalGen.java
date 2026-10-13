import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates and stores the fractal tree data and implements the FractalSubject interface to let the observers know
 * when the data is updated.
 */
public class FractalGen implements FractalSubject {
    /** List of observers to notify */
    private final List<FractalObserver> observers = new ArrayList<>();

    /** List of branches that make up the fractal tree */
    private final List<FractalElement> elements = new ArrayList<>();

    /** The recursion depth of the fractal tree */
    private int depth;

    /** The angle of the left child branches */
    private double leftAngle;

    /** The angle of the right child branches */
    private double rightAngle;

    /** The color of the trunk of the tree */
    private Color trunkColor;

    /** The color of the leaves on the tree */
    private Color leafColor;

    /** Indicates if the fractal tree needs to be refreshed */
    private boolean needsUpdate = true;

    /** The child to parent length ratio */
    private double ratio;

    /** The length of the base of the tree */
    private int trunkLength;

    /** The width of the trunk of the tree */
    private int trunkWidth;

    /** The width of the panel of where the tree will be drawn */
    private int panelWidth = 1000;

    /** The height of the panel of where the tree will be drawn */
    private int panelHeight = 800;

    /**
     * Constructor for creating a new instance of FractalGen.
     */
    public FractalGen() {}

    /**
     * Attaches an observer.
     * @param o The attached observer.
     */
    @Override
    public void attach(FractalObserver o) {
        observers.add(o);
    }

    /**
     * Detaches an observer.
     * @param o The detached observer.
     */
    @Override
    public void detach(FractalObserver o) {
        observers.remove(o);
    }

    /**
     * Notifies all attached observers to update.
     */
    @Override
    public void notifyObservers() {
        for (FractalObserver o : observers) {
            o.update();
        }
    }

    /**
     * Updates all fractal settings and refreshes.
     * @param depth The recursion depth of the fractal tree.
     * @param leftAngle The angle of the left child branches.
     * @param rightAngle The angle of the right child branches.
     * @param ratio The child to parent length ratio.
     * @param trunkLength The initial length of the trunk.
     * @param trunkWidth The initial width of the trunk.
     * @param trunkColor The initial color of the trunk.
     * @param leafColor The initial color of the leaves.
     */
    @Override
    public void setData(int depth, double leftAngle, double rightAngle, double ratio, int trunkLength, int trunkWidth, Color trunkColor, Color leafColor) {
        this.depth = depth;
        this.leftAngle = leftAngle;
        this.rightAngle = rightAngle;
        this.ratio = ratio;
        this.trunkLength = trunkLength;
        this.trunkWidth = trunkWidth;
        this.trunkColor = trunkColor;
        this.leafColor = leafColor;
        this.needsUpdate = true;
        notifyObservers();
    }

    /**
     * Updates and sets the panels size to ensure the tree is always centered correctly even when the panel
     * is resized.
     *
     * @param width The new width of the drawing panel.
     * @param height The new height of the drawing panel.
     */
    public void setPanelSize(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;
        this.needsUpdate = true;
    }

    /**
     * Provides a list of the current fractal elements.
     * @return A list of the current fractal elements.
     */
    @Override
    public List<FractalElement> getData() {
        if (needsUpdate) {
            elements.clear();
            generateFractal(panelWidth, panelHeight);
            needsUpdate = false;
        }
        return elements;
    }

    /**
     * Generates the fractal starting at the base of the tree (trunk).
     *
     * @param width the width of the drawing panel which is also used to center the tree.
     * @param height the height of the drawing panel which is also used to start the tree at the bottom.
     */
    private void generateFractal(int width, int height) {
        drawBranches(depth, width / 2.0, height, -Math.PI / 2, trunkLength);
    }

    /**
     * Generates branches recursively and adds them to the list.
     *
     * @param level The depth level.
     * @param x1 Starting x coordinates.
     * @param y1 Starting y coordinates.
     * @param angle The branch angles.
     * @param length The branch length.
     */
    private void drawBranches(int level, double x1, double y1, double angle, double length) {
        if (level == 0) {
            return;
        }

        double x2 = x1 + Math.cos(angle) * length;
        double y2 = y1 + Math.sin(angle) * length;

        double transition = (double) (depth - level) / depth;
        Color color = interpolateColors(trunkColor, leafColor, transition);
        float thickness = Math.max(0.2f, (float)(trunkWidth * Math.pow(ratio, depth - level)));

        elements.add(new Branch(x1, y1, x2, y2, color, thickness));

        drawBranches(level - 1, x2, y2, angle - this.leftAngle, length * this.ratio);
        drawBranches(level - 1, x2, y2, angle + this.rightAngle, length * this.ratio);
    }

    /**
     *Interpolates colors for smooth transitioning.
     *
     * @param start The starting color.
     * @param end The ending color.
     * @param transition Transition progress represented by a value.
     * @return The interpolated color.
     */
    private Color interpolateColors(Color start, Color end, double transition) {
        int r = (int) (start.getRed() + transition * (end.getRed() - start.getRed()));
        int g = (int) (start.getGreen() + transition * (end.getGreen() - start.getGreen()));
        int b = (int) (start.getBlue() + transition * (end.getBlue() - start.getBlue()));
        return new Color(r, g, b);
    }
}
