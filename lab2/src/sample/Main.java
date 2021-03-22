package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
    Timer timer;

    private static int maxWidth = 300;
    private static int maxHeight = 200;

    private double angle = 0;
    private double dx = 5;
    private double tx = 0;
    private double dy = 0;
    private double ty = 0;

    private double centerX = maxWidth / 2;
    private double centerY = 50;

    public void actionPerformed(ActionEvent e) {
        angle -= 0.1;
        if ( tx < -maxWidth/3 ) {
            tx = - maxWidth/3;
            dx = 0;
            dy = 5;
        } else if ( tx > maxWidth/3 ) {
            tx = maxWidth / 3;
            dx = 0;
            dy = -5;
        } else if ( ty <  -maxHeight/3 ) {
            ty = - maxHeight/3;
            dy = 0;
            dx = -5;
        } else if ( ty > maxHeight/3 ) {
            ty = maxHeight/3;
            dy = 0;
            dx = 5;
        }
        tx += dx;
        ty += dy;
        centerX += dx;
        centerY += dy;
        repaint();
    }

    public Main() {
        timer = new Timer(90, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke defaultStroke = g2d.getStroke();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setBackground(new Color(0, 255, 128));
        g2d.clearRect(0, 0, maxWidth, maxHeight);


        g2d.setColor(Color.RED);
        BasicStroke bs1 = new BasicStroke(20, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawRect(20, 20, 1000, 800);
        g2d.setStroke(defaultStroke);

        int[] coreX = {70, -10, -55, -50, 0, 80};
        int[] coreY = {275, 250, 280, 350, 380, 330};
        incrementArray(coreX, maxWidth / 2);
        GeneralPath core = new GeneralPath();
        core.moveTo(coreX[0], coreY[0]);
        for(int i = 1; i < coreX.length; i ++) {
            core.lineTo(coreX[i], coreY[i]);
        }
        core.closePath();

        g2d.rotate(angle, centerX, centerY);
        g2d.translate(tx, ty);

        g2d.setColor(new Color(0, 179, 52));
        g2d.fillRoundRect(maxWidth / 2 - 7, 300, 15, 400, 20, 5);
        GradientPaint gp = new GradientPaint(0, 3, Color.GREEN, 10, 40, Color.BLUE, true);
        g2d.setPaint(gp);
        g2d.fill(core);

        g2d.setColor(Color.RED);
        int[] petal1X = {95, 0, 5, 100};
        int[] petal1Y = {130, 130, 220, 200};
        incrementArray(petal1X, maxWidth / 2 - 150);
        incrementArray(petal1Y, 150);
        g2d.fillPolygon(petal1X, petal1Y, 4);
        int[] petal2X = {100, 50, 100, 150};
        int[] petal2Y = {200, 260, 300, 230};
        incrementArray(petal2X, maxWidth / 2 - 150);
        incrementArray(petal2Y, 150);
        g2d.fillPolygon(petal2X, petal2Y, 4);
        int[] petal3X = {230, 150, 220, 300};
        int[] petal3Y = {180, 230, 290, 250};
        incrementArray(petal3X, maxWidth / 2 - 150);
        incrementArray(petal3Y, 150);
        g2d.fillPolygon(petal3X, petal3Y, 4);
        int[] petal4X = {320, 220, 230, 340};
        int[] petal4Y = {90, 125, 180, 170};
        incrementArray(petal4X, maxWidth / 2 - 150);
        incrementArray(petal4Y, 150);
        g2d.fillPolygon(petal4X, petal4Y, 4);
        int[] petal5X = {220, 150, 140, 220};
        int[] petal5Y = {30, 25, 100, 125};
        incrementArray(petal5X, maxWidth / 2 - 150);
        incrementArray(petal5Y, 150);
        g2d.fillPolygon(petal5X, petal5Y, 4);
        int[] petal6X = {80, 15, 95, 140};
        int[] petal6Y = {30, 60, 130, 100};
        incrementArray(petal6X, maxWidth / 2 - 150);
        incrementArray(petal6Y, 150);
        g2d.fillPolygon(petal6X, petal6Y, 4);
    }
    public void incrementArray(int[] arr, int increment) {
        for (int i = 0; i < arr.length; i ++) {
            arr[i] += increment;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.add(new Main());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 900);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }
}