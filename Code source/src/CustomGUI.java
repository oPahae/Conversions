import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomGUI extends JFrame {
    
    JTextField[] inputFields;
    JComboBox<String> comboBox;
    JTextArea outputArea;

    public CustomGUI(String title, int n, String[] labels, String[] comboOptions, String buttonLabel, ActionListener buttonAction) {
        setTitle(title);
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initUI(n, labels, comboOptions, buttonLabel, buttonAction);
        setVisible(true);
    }

    private void initUI(int n, String[] labels, String[] comboOptions, String buttonLabel, ActionListener buttonAction) {

        Color bgColor = new Color(20, 20, 20);
        Color panelColor = new Color(45, 45, 45);
        Color chibi = new Color(0, 255, 255);
        Color rose = new Color(255, 105, 180);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);
        Font outputFont = new Font("Arial", Font.BOLD, 16);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        inputFields = new JTextField[n];
        for(int i=0; i<n; i++) {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.setBackground(panelColor);
            inputPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(Color.DARK_GRAY)
            ));
            
            JLabel label = new JLabel(labels[i]);
            label.setForeground(chibi);
            label.setFont(labelFont);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            JTextField textField = new JTextField();
            textField.setBackground(bgColor);
            textField.setForeground(Color.WHITE);
            textField.setFont(inputFont);
            textField.setCaretColor(Color.WHITE);
            textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            inputFields[i] = textField;

            inputPanel.add(label);
            inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            inputPanel.add(textField);
            mainPanel.add(inputPanel);
        }

        if(comboOptions.length != 0) {
            comboBox = new JComboBox<>(comboOptions);
            comboBox.setBackground(Color.BLACK);
            comboBox.setForeground(Color.WHITE);
            comboBox.setFont(new Font("Arial", Font.BOLD, 16));
            comboBox.setBorder(BorderFactory.createLineBorder(chibi));
            comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(comboBox);
        }

        JButton actionButton = new JButton(buttonLabel);
        actionButton.setBackground(Color.BLACK);
        actionButton.setForeground(rose);
        actionButton.setFont(outputFont);
        actionButton.setFocusPainted(false);
        actionButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(chibi, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.addActionListener(buttonAction);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(actionButton);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.setBackground(panelColor);
        outputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.DARK_GRAY)
        ));

        JLabel resultLabel = new JLabel("Résultat :");
        resultLabel.setForeground(chibi);
        resultLabel.setFont(labelFont);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        outputArea = new JTextArea(8, 20);
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(rose);
        outputArea.setFont(outputFont);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(chibi),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        outputPanel.add(resultLabel);
        outputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        outputPanel.add(scrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(outputPanel);

        add(mainPanel);
    }

    public String[] getInputValues() {
        String[] values = new String[inputFields.length];
        for(int i=0; i<inputFields.length; i++) {
            values[i] = inputFields[i].getText();
        }
        return values;
    }

    public String getComboBoxSelection() {
        return
            (String) comboBox.getSelectedItem();
    }

    public void setOutputText(String text) {
        outputArea.setText(text);
        outputArea.setCaretPosition(0);
    }
}
