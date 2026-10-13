import java.awt.Color;
import java.util.List;

/**
 * This interface manages observers and notifies them when fractal data changes.
 */
public interface FractalSubject {
    /**
     * Attaches an observer.
     *
     * @param o the observer to attach.
     */
    void attach(FractalObserver o);

    /**
     * Detaches an observer.
     *
     * @param o the observer to detach.
     */
    void detach(FractalObserver o);

    /**
     * Notifies all attached observers when data has changed and to update.
     */
    void notifyObservers();

    /**
     *Sets the data for the fractal generation.
     *
     * @param depth the recursion depth of the branches.
     * @param leftAngle the angle of the left child branches.
     * @param rightAngle the angle of the right child branches.
     * @param ratio the child to parent length ratio.
     * @param trunkLength the length of the base of the tree.
     * @param trunkWidth the width of the base of the tree.
     * @param trunkColor the color of the trunk of the tree.
     * @param leafColor the color of the leaves on the tree.
     */
    void setData(int depth, double leftAngle, double rightAngle, double ratio, int trunkLength,
                 int trunkWidth, Color trunkColor, Color leafColor);

    /**
     * The list of the fractal elements to draw.
     *
     * @return the list of the fractal elements to draw.
     */
    List<FractalElement> getData();
}
