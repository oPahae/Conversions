import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Test");
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setBackground(Color.BLACK);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
                inputPanel.setBackground(Color.DARK_GRAY);

                    JLabel label = new JLabel("hhhhhhh");
                    label.setForeground(Color.RED);
                    JTextField input = new JTextField();
                    input.setBackground(Color.DARK_GRAY);
                    input.setForeground(Color.WHITE);
                    input.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));
                    input.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                
                JComboBox combo = new JComboBox<>(new String[]{
                    "op1", "op2"
                });
                combo.setBackground(Color.DARK_GRAY);
                combo.setForeground(Color.WHITE);
                combo.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 30));

                JButton btn = new JButton("Click me !");
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.PINK);
                btn.setFocusPainted(false);

                JTextArea output = new JTextArea(8, 20);
                output.setBackground(Color.GREEN);
                output.setForeground(Color.BLACK);

        inputPanel.add(label);
        inputPanel.add(input);
        panel.add(inputPanel);
        panel.add(combo);
        panel.add(btn);
        panel.add(output);
        frame.add(panel);
        frame.setVisible(true);
        
        ActionListener buttonAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("Clicked !");
            }
        };
        btn.addActionListener(buttonAction);
    }
}
