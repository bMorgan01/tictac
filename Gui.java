import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;

@SuppressWarnings("serial")
public class Gui extends JFrame {
    private JPanel panel = new JPanel(new GridLayout(3, 3));
    private boolean turn = true;
    private JLabel[][] labels = new JLabel[][] {{new JLabel(" "), new JLabel(" "), new JLabel(" ")}, {new JLabel(" "), new JLabel(" "), new JLabel(" ")}, {new JLabel(" "), new JLabel(" "), new JLabel(" ")}};

    public Gui(String s) {
        super(s);

        initComponents();
    }

    private void initComponents() {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                labels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JLabel l = (JLabel) e.getSource();

                        if (l.getText().equals(" ")) {
                            if (turn) {
                                l.setText("X");
                                l.setForeground(Color.BLUE);
                            } else {
                                l.setText("O");
                                l.setForeground(Color.RED);
                            }
                            turn = !turn;
                        }

                        for (int i = 0; i < labels.length; i++) {
                            if (labels[i][0].getText().equals(labels[i][1].getText()) && labels[i][0].getText().equals(labels[i][2].getText()) && labels[i][0].getText().equals("X")) win("X");
                            else if (labels[i][0].getText().equals(labels[i][1].getText()) && labels[i][0].getText().equals(labels[i][2].getText()) && labels[i][0].getText().equals("O")) win("O");
                        }

                        for (int i = 0; i < labels[0].length; i++) {
                            if (labels[0][i].getText().equals(labels[1][i].getText()) && labels[1][i].getText().equals(labels[2][i].getText()) && labels[0][i].getText().equals("X")) win("X");
                            else if (labels[0][i].getText().equals(labels[1][i].getText()) && labels[1][i].getText().equals(labels[2][i].getText()) && labels[0][i].getText().equals("O")) win("O");
                        }

                        if (labels[0][0].getText().equals(labels[1][1].getText()) && labels[1][1].getText().equals(labels[2][2].getText()) && labels[0][0].getText().equals("X")) win("X");
                        else if (labels[0][0].getText().equals(labels[1][1].getText()) && labels[1][1].getText().equals(labels[2][2].getText()) && labels[0][0].getText().equals("O")) win("O");

                        if (labels[0][2].getText().equals(labels[1][1].getText()) && labels[1][1].getText().equals(labels[2][0].getText()) && labels[0][2].getText().equals("X")) win("X");
                        else if (labels[0][2].getText().equals(labels[1][1].getText()) && labels[1][1].getText().equals(labels[2][0].getText()) && labels[0][2].getText().equals("O")) win("O");

                        if(allfilled()) win(" ");
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}
                });

                labels[i][j].setFont(new Font("Monospaced",Font.BOLD,128));
                labels[i][j].setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
                labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(labels[i][j]);
            }
        }

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
        pack();
        setVisible(true);
    }

    private void win(String s) {
        String winString;

        if (s.equals("X")) winString = "X Wins!";
        else if (s.equals("O")) winString = "O Wins!";
        else winString = "It's a tie!";

        JOptionPane.showMessageDialog(this, winString);

        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                labels[i][j].setText(" ");
                turn = true;
            }
        }
    }

    private boolean allfilled() {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                if (labels[i][j].getText().equals(" ")) return false;
            }
        }

        return true;
    }

    /** The entry main() method */
    public static void main(String[] args) {
      // Run GUI codes in the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui("Tic Tac Toe");
            }
        });
    }
}