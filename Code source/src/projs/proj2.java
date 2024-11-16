package projs;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class proj2 extends JFrame {

    private JTextField inputDecimalField, inputBitsField;
    private JTextArea resultArea;
    private JComboBox<String> methodComboBox;

    public proj2() {
        setTitle("Representation d'Entiers Signés");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel decimalLabel = new JLabel("Nombre Décimal :");
        decimalLabel.setForeground(Color.WHITE);
        inputDecimalField = new JTextField();

        JLabel bitsLabel = new JLabel("Nombre de Bits :");
        bitsLabel.setForeground(Color.WHITE);
        inputBitsField = new JTextField();

        JLabel methodLabel = new JLabel("Méthode :");
        methodLabel.setForeground(Color.WHITE);

        String[] methods = {"Valeur signée", "Complément à 1", "Complément à 2"};
        methodComboBox = new JComboBox<>(methods);
        methodComboBox.setBackground(new Color(50, 50, 50));
        methodComboBox.setForeground(Color.WHITE);

        JButton calculateButton = new JButton("Calculer");
        calculateButton.setBackground(new Color(0, 102, 204));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(this::calculerRepresentation);

        resultArea = new JTextArea();
        resultArea.setBackground(new Color(20, 20, 20));
        resultArea.setForeground(Color.GREEN);
        resultArea.setEditable(false);

        mainPanel.add(decimalLabel); mainPanel.add(inputDecimalField);
        mainPanel.add(bitsLabel); mainPanel.add(inputBitsField);
        mainPanel.add(methodLabel); mainPanel.add(methodComboBox);
        mainPanel.add(new JLabel()); mainPanel.add(calculateButton);

        add(mainPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        inputDecimalField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateMethodComboBox(); }
            public void removeUpdate(DocumentEvent e) { updateMethodComboBox(); }
            public void changedUpdate(DocumentEvent e) { updateMethodComboBox(); }
        });
    }

    private void updateMethodComboBox() {
        String nombreStr = inputDecimalField.getText();
        if (estDecimal(nombreStr)) {
            methodComboBox.setEnabled(Integer.parseInt(nombreStr) < 0);
        } else {
            methodComboBox.setEnabled(true);
        }
    }

    private void calculerRepresentation(ActionEvent e) {
        String nombreStr = inputDecimalField.getText();
        String bitsStr = inputBitsField.getText();
        if (!estDecimal(nombreStr) || !estDecimal(bitsStr)) {
            resultArea.setText("Veuillez entrer un nombre décimal et un nombre de bits valides.");
            return;
        }

        int nombre = Integer.parseInt(nombreStr);
        int bits = Integer.parseInt(bitsStr);
        String method = (String) methodComboBox.getSelectedItem();
        String result = "";

        if(nombre >= 0)
            resultArea.setText(versBinaire(nombre, bits));
        else {
            if ("Valeur signée".equals(method)) {
                result = "Valeur signée : " + valeurSignee(nombre, bits);
            } else if ("Complément à 1".equals(method)) {
                result = "Complément à 1 : " + complementAUn(nombre, bits);
            } else if ("Complément à 2".equals(method)) {
                result = "Complément à 2 : " + complementADeux(nombre, bits);
            }
            resultArea.setText(result);
        }
    }

    public static boolean estDecimal(String nombre) {
        try { Integer.parseInt(nombre); return true; }
        catch (NumberFormatException e) { return false; }
    }

    public static String versBinaire(int nombre, int bits) {
        StringBuilder binaire = new StringBuilder();
        int n = Math.abs(nombre);
        while (n > 0) {
            binaire.insert(0, n % 2);
            n /= 2;
        }
        while (binaire.length() < bits) {
            binaire.insert(0, '0');
        }
        return binaire.length() > bits ? binaire.substring(binaire.length() - bits) : binaire.toString();
    }

    public static String valeurSignee(int nombre, int bits) {
        return (nombre < 0 ? "1" : "0") + versBinaire(nombre, bits).substring(1);
    }

    public static String complementAUn(int nombre, int bits) {
        String valeurSignee = valeurSignee(nombre, bits);
        return valeurSignee.replace('0', '2').replace('1', '0').replace('2', '1');
    }

    public static String complementADeux(int nombre, int bits) {
        StringBuilder resultat = new StringBuilder(complementAUn(nombre, bits));
        boolean foundOne = false;
        for (int i = resultat.length() - 1; i >= 0; i--) {
            if (resultat.charAt(i) == '1') foundOne = true;
            else if (foundOne) resultat.setCharAt(i, (resultat.charAt(i) == '0') ? '1' : '0');
        }
        return resultat.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new proj2().setVisible(true));
    }
}