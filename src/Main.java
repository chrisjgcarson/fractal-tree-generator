/**
 * Launches the Fractal Tree program and GUI.
 *
 * @author Chris Carson
 * @version 19-06-2025
 */
public class Main {

    /**
     * Private constructor to prevent instantiation.
     */
    private Main() {

    }

    /**
     * Initializes the Fractal Subject and GUI.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        FractalSubject gen = new FractalGen();
        new FractalGUI(gen);
    }
}