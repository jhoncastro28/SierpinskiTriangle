package sierspinskitriangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangle extends JFrame {
    private int iterations;
    private int screenSize;

    public SierpinskiTriangle() {
        screenSize = Math.min(Toolkit.getDefaultToolkit().getScreenSize().width,
                             Toolkit.getDefaultToolkit().getScreenSize().height) - 100;

        setTitle("Triángulo de Sierpinski");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize, screenSize);

        JPanel controlPanel = new JPanel();
        JLabel label = new JLabel("Ingrese el número de iteraciones:");
        JTextField textField = new JTextField(5);
        JButton drawButton = new JButton("Dibujar");

        controlPanel.add(label);
        controlPanel.add(textField);
        controlPanel.add(drawButton);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iterations = Integer.parseInt(textField.getText());
                    repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de iteraciones.");
                }
            }
        });

        add(controlPanel, BorderLayout.NORTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int triangleHeight = (int) (Math.sqrt(3) * screenSize / 2);
        drawTriangle(g, iterations, getWidth() / 2, getHeight() / 2 + triangleHeight / 2, triangleHeight / 2);
    }

    private void drawTriangle(Graphics g, int n, int x, int y, int size) {
        if (n == 0) {
            int[] xPoints = {x, x + size, x - size};
            int[] yPoints = {y - size, y + size, y + size};
            g.drawPolygon(xPoints, yPoints, 3);
        } else {
            int newSize = size / 2;
            drawTriangle(g, n - 1, x, y - newSize, newSize);
            drawTriangle(g, n - 1, x + newSize, y + newSize, newSize);
            drawTriangle(g, n - 1, x - newSize, y + newSize, newSize);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SierpinskiTriangle app = new SierpinskiTriangle();
                app.setVisible(true);
            }
        });
    }
}

