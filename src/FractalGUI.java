import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI that gathers user input for drawing the fractal tree.
 * Contains a separate control window with sliders and buttons that adjust the different elements of the fractal tree.
 * Updates in real time using Observer.
 */
public class FractalGUI extends JPanel {
    /** The default/starting color of the tree when the program is first run. */
    private Color defaultTrunkColor = new Color(108, 48, 0);

    /** The default/starting color of the leaves of the tree when the program is first run. */
    private Color defaultLeafColor = new Color(1, 89, 10, 255);

    /**
     * Constructor that creates the GUI containing two separate windows. One window for drawing the tree and one
     * window for settings that contain sliders and buttons for adjusting specifics of the fractal tree. Sends updates
     * to the fractal generator through the observer so changes made on the slider and buttons so updates are made in
     * real time.
     *
     * @param subj The FractalSubject that processes the user's input/settings.
     */
    public FractalGUI(FractalSubject subj) {

        FractalDraw draw = new FractalDraw(subj);
        JFrame drawFrame = new JFrame("Fractal Display");
        drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawFrame.setSize(1000, 800);
        drawFrame.setLocation(0, 0);
        drawFrame.add(draw);
        drawFrame.setVisible(true);

        JFrame guiFrame = new JFrame("Fractal Settings");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setSize(300, 600);
        guiFrame.setLocation(1000, 0);
        guiFrame.setResizable(false);
        guiFrame.add(this);
        guiFrame.setVisible(true);

        setPreferredSize(new Dimension(800, 100));
        setBackground(new Color(236, 236, 236));
        setLayout(null);

        JLabel depthLabel = new JLabel("Recursion Depth:");
        depthLabel.setForeground(Color.BLACK);
        depthLabel.setBounds(50, 10, 200, 20);
        add(depthLabel);

        JSlider depthSlider = new JSlider(4, 20, 12);
        depthSlider.setBounds(40, 35, 200, 40);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);
        depthSlider.setMajorTickSpacing(2);
        add(depthSlider);

        JLabel ratioLabel = new JLabel("Child to parent ratio");
        ratioLabel.setForeground(Color.BLACK);
        ratioLabel.setBounds(50, 80, 150, 20);
        add(ratioLabel);

        JSlider ratioSlider = new JSlider(40, 80, 70);
        ratioSlider.setBounds(40, 100, 200, 40);
        ratioSlider.setPaintTicks(true);
        ratioSlider.setPaintLabels(true);
        ratioSlider.setMajorTickSpacing(10);
        add(ratioSlider);

        JLabel leftAngleLabel = new JLabel("Left child angle");
        leftAngleLabel.setForeground(Color.BLACK);
        leftAngleLabel.setBounds(50, 140, 150, 20);
        add(leftAngleLabel);

        JSlider leftAngleSlider = new JSlider(0, 90, 20);
        leftAngleSlider.setBounds(40, 160, 200, 40);
        leftAngleSlider.setPaintTicks(true);
        leftAngleSlider.setPaintLabels(true);
        leftAngleSlider.setMajorTickSpacing(10);
        add(leftAngleSlider);

        JLabel rightAngleLabel = new JLabel("Right child angle");
        rightAngleLabel.setForeground(Color.BLACK);
        rightAngleLabel.setBounds(50, 200, 150, 20);
        add(rightAngleLabel);

        JSlider rightAngleSlider = new JSlider(0, 90, 30);
        rightAngleSlider.setBounds(40, 220, 200, 40);
        rightAngleSlider.setPaintTicks(true);
        rightAngleSlider.setPaintLabels(true);
        rightAngleSlider.setMajorTickSpacing(10);
        add(rightAngleSlider);

        JLabel trunkLengthLabel = new JLabel("Trunk Length");
        trunkLengthLabel.setForeground(Color.BLACK);
        trunkLengthLabel.setBounds(50, 260, 150, 20);
        add(trunkLengthLabel);

        JSlider trunkLengthSlider = new JSlider(100, 400, 200);
        trunkLengthSlider.setBounds(40, 280, 200, 40);
        trunkLengthSlider.setPaintTicks(true);
        trunkLengthSlider.setPaintLabels(true);
        trunkLengthSlider.setMajorTickSpacing(100);
        add(trunkLengthSlider);

        JLabel trunkWidthLabel = new JLabel("Trunk Width");
        trunkWidthLabel.setForeground(Color.BLACK);
        trunkWidthLabel.setBounds(50, 320, 150, 20);
        add(trunkWidthLabel);

        JSlider trunkWidthSlider = new JSlider(10, 50, 15);
        trunkWidthSlider.setBounds(40, 340, 200, 40);
        trunkWidthSlider.setPaintTicks(true);
        trunkWidthSlider.setPaintLabels(true);
        trunkWidthSlider.setMajorTickSpacing(10);
        add(trunkWidthSlider);

        JButton trunkColorButton = new JButton("Trunk Color");
        trunkColorButton.setBounds(70, 400, 150, 20);
        add(trunkColorButton);

        JButton leafColorButton = new JButton("Leaf Color");
        leafColorButton.setBounds(70, 430, 150, 20);
        add(leafColorButton);

        JButton randomButton = new JButton("Random");
        randomButton.setBounds(70, 460, 150, 20);
        add(randomButton);

        ChangeListener updateListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int depth = depthSlider.getValue();
                int ratioPercent = ratioSlider.getValue();
                double ratio = ratioPercent / 100.0;
                int leftAngle = leftAngleSlider.getValue();
                int rightAngle = rightAngleSlider.getValue();
                double leftRad = Math.toRadians(leftAngle);
                double rightRad = Math.toRadians(rightAngle);
                int trunkLength = trunkLengthSlider.getValue();
                int trunkWidth = trunkWidthSlider.getValue();

                subj.setData(depth, leftRad, rightRad, ratio, trunkLength, trunkWidth, defaultTrunkColor, defaultLeafColor);
            }
        };

        depthSlider.addChangeListener(updateListener);
        ratioSlider.addChangeListener(updateListener);
        leftAngleSlider.addChangeListener(updateListener);
        rightAngleSlider.addChangeListener(updateListener);
        trunkLengthSlider.addChangeListener(updateListener);
        trunkWidthSlider.addChangeListener(updateListener);

        trunkColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(FractalGUI.this, "Trunk Color", defaultTrunkColor);
                if (selectedColor != null) {
                    defaultTrunkColor = selectedColor;
                    subj.setData(depthSlider.getValue(), Math.toRadians(leftAngleSlider.getValue()),
                            Math.toRadians(rightAngleSlider.getValue()), ratioSlider.getValue() / 100.0,
                            trunkLengthSlider.getValue(), trunkWidthSlider.getValue(), defaultTrunkColor, defaultLeafColor);

                }
            }
        });

        leafColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(FractalGUI.this, "Leaf Color", defaultLeafColor);
                if (selectedColor != null) {
                    defaultLeafColor = selectedColor;
                    subj.setData(depthSlider.getValue(), Math.toRadians(leftAngleSlider.getValue()),
                            Math.toRadians(rightAngleSlider.getValue()), ratioSlider.getValue() / 100.0,
                            trunkLengthSlider.getValue(), trunkWidthSlider.getValue(), defaultTrunkColor, defaultLeafColor);
                }
            }
        });

        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int depth = (int) (Math.random() * (20 - 4 + 1)) + 4;
                int ratio = (int) (Math.random() * (80 - 40 + 1)) + 40;
                int left = (int) (Math.random() * 91);
                int right = (int) (Math.random() * 91);
                int length = (int) (Math.random() * (400 - 100 + 1) + 100);
                int width = (int) (Math.random() * (50 - 10 + 1) + 10);
                Color trunk = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                        (int) (Math.random() * 256));
                Color leaf = new Color((int) (Math.random() * 256), (int) (Math.random() * 256),
                        (int) (Math.random() * 256));

                depthSlider.setValue(depth);
                ratioSlider.setValue(ratio);
                leftAngleSlider.setValue(left);
                rightAngleSlider.setValue(right);
                trunkLengthSlider.setValue(length);
                trunkWidthSlider.setValue(width);
                defaultTrunkColor = trunk;
                defaultLeafColor = leaf;

                subj.setData(depth, Math.toRadians(left), Math.toRadians(right), ratio / 100.0,
                        length, width, trunk, leaf);

            }
        });

        repaint();

        subj.setData(12, Math.toRadians(20), Math.toRadians(30), 0.7, 200, 15,
                defaultTrunkColor, defaultLeafColor);
    }
}
