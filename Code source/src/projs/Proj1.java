package projs;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Proj1 {

    public static boolean baseValide(String base) {
        try {
            return Integer.parseInt(base) >= 2 && Integer.parseInt(base) <= 16;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean nombreValide(String nombre, int base) {
        for (char c : nombre.toCharArray()) {
            int v = Character.isDigit(c) ?
                c - '0' : Character.isLetter(c) ? Character.toUpperCase(c) - 'A' + 10 : -1;
            if (v < 0 || v >= base) return false;
        }
        return true;
    }

    public static int versDecimal(String nbr, int base) {
        int dec = 0;
        for (char c : nbr.toCharArray()) {
            int i = (c >= '0' && c <= '9') ? c - '0' : c - 'A' + 10;
            dec = dec * base + i;
        }
        return dec;
    }

    public static String dapresDecimal(int nbr, int base) {
        StringBuilder conv = new StringBuilder();
        while (nbr > 0) {
            int reste = nbr % base;
            conv.append(reste < 10 ? (char) ('0' + reste) : (char) ('A' + reste - 10));
            nbr /= base;
        }
        return conv.reverse().toString();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conversion de bases :)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(25, 25, 25));

        Font font = new Font("Arial", Font.BOLD, 18);
        Color neonBlue = new Color(0, 255, 255);
        Color neonPink = new Color(255, 20, 147);
        Color neonGreen = new Color(50, 205, 50);

        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(25, 25, 25));

        JLabel labelBaseA = new JLabel("Base initiale:");
        styleLabel(labelBaseA, font, neonBlue);
        JTextField textBaseA = styleTextField(neonBlue);

        JLabel labelNombre = new JLabel("Nombre:");
        styleLabel(labelNombre, font, neonPink);
        JTextField textNombre = styleTextField(neonPink);

        JLabel labelBaseB = new JLabel("Base finale:");
        styleLabel(labelBaseB, font, neonGreen);
        JTextField textBaseB = styleTextField(neonGreen);

        JLabel labelResultat = new JLabel("Résultat:");
        styleLabel(labelResultat, font, neonBlue);
        JTextField textResultat = new JTextField();
        textResultat.setFont(font);
        textResultat.setBackground(new Color(50, 50, 50));
        textResultat.setForeground(neonBlue);
        textResultat.setEditable(false);

        JButton boutonConvertir = new JButton("Convertir");
        boutonConvertir.setFont(font);
        boutonConvertir.setBackground(neonBlue);
        boutonConvertir.setForeground(Color.BLACK);
        boutonConvertir.setFocusPainted(false);
        boutonConvertir.setBorder(BorderFactory.createLineBorder(neonBlue, 3));
        boutonConvertir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boutonConvertir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boutonConvertir.setBackground(neonPink);
                boutonConvertir.setBorder(BorderFactory.createLineBorder(neonPink, 3));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boutonConvertir.setBackground(neonBlue);
                boutonConvertir.setBorder(BorderFactory.createLineBorder(neonBlue, 3));
            }
        });

        mainPanel.add(labelBaseA);
        mainPanel.add(textBaseA);
        mainPanel.add(labelNombre);
        mainPanel.add(textNombre);
        mainPanel.add(labelBaseB);
        mainPanel.add(textBaseB);
        mainPanel.add(labelResultat);
        mainPanel.add(textResultat);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(25, 25, 25));
        buttonPanel.add(boutonConvertir);

        boutonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baseA = textBaseA.getText();
                String nombre = textNombre.getText();
                String baseB = textBaseB.getText();

                if (baseValide(baseA)) {
                    if (nombreValide(nombre, Integer.parseInt(baseA))) {
                        if (baseValide(baseB)) {
                            int nombreDecimal = versDecimal(nombre, Integer.parseInt(baseA));
                            String resultat = dapresDecimal(nombreDecimal, Integer.parseInt(baseB));
                            textResultat.setText(resultat);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Base finale invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Nombre invalide pour la base initiale", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Base initiale invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void styleLabel(JLabel label, Font font, Color color) {
        label.setFont(font);
        label.setForeground(color);
    }

    public static JTextField styleTextField(Color focusColor) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBackground(new Color(50, 50, 50));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBorder(BorderFactory.createLineBorder(focusColor, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
            }
        });
        return textField;
    }
}
