/**
 * Interface for classes that should be notified when fractal data changes.
 */
public interface FractalObserver {

    /**
     * Called by the subject when fractal data is changed. Updates the data in classes using the interface to properly
     * represent the changed visuals.
     */
    void update();
}
