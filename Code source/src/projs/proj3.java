package projs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class proj3 extends JFrame {

    public proj3() {
        setTitle("Conversion Binaire");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBackground(Color.DARK_GRAY);

        JLabel label = new JLabel("Entrez un nombre réel :");
        label.setForeground(Color.WHITE);
        JTextField input = new JTextField();
        input.setBackground(Color.GRAY);
        input.setForeground(Color.WHITE);

        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setBackground(Color.BLACK);
        btnConvertir.setForeground(Color.WHITE);

        JTextArea fixeResultat = new JTextArea();
        fixeResultat.setBackground(Color.BLACK);
        fixeResultat.setForeground(Color.GREEN);
        fixeResultat.setEditable(false);

        JTextArea simpleResultat = new JTextArea();
        simpleResultat.setBackground(Color.BLACK);
        simpleResultat.setForeground(Color.GREEN);
        simpleResultat.setEditable(false);

        JTextArea doubleResultat = new JTextArea();
        doubleResultat.setBackground(Color.BLACK);
        doubleResultat.setForeground(Color.GREEN);
        doubleResultat.setEditable(false);

        panel.add(label);
        panel.add(input);
        panel.add(btnConvertir);
        panel.add(fixeResultat);
        panel.add(simpleResultat);
        panel.add(doubleResultat);
        add(panel);

        btnConvertir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double nombre = Double.parseDouble(input.getText());
                    
                    // Vérification si le nombre est entier
                    if (nombre % 1 == 0) {
                        JOptionPane.showMessageDialog(null, "Le nombre doit être décimal!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        fixeResultat.setText("Virgule Fixe : " + virguleFixe(nombre));
                        simpleResultat.setText("IEEE 754 Simple : " + ieee754Simple((float) nombre));
                        doubleResultat.setText("IEEE 754 Double : " + ieee754Double(nombre));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public String virguleFixe(double nombre) {
        boolean sign = nombre < 0;
        nombre = Math.abs(nombre);
        long partieEntiere = (long) nombre;
        double partieFractionnaire = nombre - partieEntiere;
        String entierBin = Long.toBinaryString(partieEntiere);
        StringBuilder fracBin = new StringBuilder(".");
        while (partieFractionnaire > 0 && fracBin.length() < 32) {
            partieFractionnaire *= 2;
            if (partieFractionnaire >= 1) {
                fracBin.append("1");
                partieFractionnaire -= 1;
            } else {
                fracBin.append("0");
            }
        }
        String resultat = entierBin + fracBin.toString();
        return sign ? "1" + resultat + " (sur " + (resultat.length() + 1) + " bits)" : resultat;
    }

    public String ieee754Simple(float nombre) {
        int bits = Float.floatToIntBits(nombre);
        return String.format("%32s", Integer.toBinaryString(bits)).replace(' ', '0');
    }

    public String ieee754Double(double nombre) {
        long bits = Double.doubleToLongBits(nombre);
        return String.format("%64s", Long.toBinaryString(bits)).replace(' ', '0');
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new proj3().setVisible(true);
            }
        });
    }
}
