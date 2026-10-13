import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Renders the fractal tree using JPanel.
 * Implements FractalObserver to update when specifics for the fractal tree change through FractalSubject.
 * When updated it repaints.
 */
public class FractalDraw extends JPanel implements FractalObserver {

    /** Holds all the fractal data that is used to update branch changes and listen for changes. */
    private final FractalSubject fractalSubject;

    /**
     * Creates a panel and attaches it to the subject while setting background color and attaching an observer.
     *
     * @param fractalSubject the subject that holds fractal data.
     */
    public FractalDraw(FractalSubject fractalSubject) {
        this.fractalSubject = fractalSubject;
        fractalSubject.attach(this);
        setBackground(Color.BLACK);
    }

    /**
     * Notified when fractal data has changed and repaints the panel.
     */
    @Override
    public void update() {
        repaint();
    }

    /**
     * Iterates over each FractalElement and draws it while also updating the panel size to ensure fractal tree
     * is always centered.
     *
     * @param g the graphics used to draw the fractal tree.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fractalSubject instanceof FractalGen gen) {
            gen.setPanelSize(getWidth(), getHeight());
        }

        List<FractalElement> elements = fractalSubject.getData();
        for (FractalElement elem : elements) {
            elem.draw(g);
        }
    }
}
