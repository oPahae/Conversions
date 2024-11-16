import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionBinaire {

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
            int i = (c >= '0' && c <= '9') ? c - '0' : Character.toUpperCase(c) - 'A' + 10;
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
        SwingUtilities.invokeLater(() -> {
            int n = 3;
            String title = "Convertir en binaire :) !";
            String[] labels = {"Base initiale", "Nombre à convertir", "Base finale"};
            String buttonLabel = "Calculer";

            ActionListener buttonAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CustomGUI gui = (CustomGUI) ((JButton) e.getSource()).getTopLevelAncestor();

                    String[] inputs = gui.getInputValues();
                    String base1 = inputs[0];
                    String nombre = inputs[1];
                    String base2 = inputs[2];
                    
                    if(!baseValide(base1) || !baseValide(base2))
                        gui.setOutputText("Veuillez entrer une base valide !");
                    else if(!nombreValide(nombre, Integer.parseInt(base1)))
                        gui.setOutputText("Veuillez entrer un nombre valide !");
                    else
                        gui.setOutputText("" + dapresDecimal(versDecimal(nombre, Integer.parseInt(base1)), Integer.parseInt(base2)));
                }
            };
            new CustomGUI(title, n, labels, new String[]{}, buttonLabel, buttonAction);
        });
    }
}
