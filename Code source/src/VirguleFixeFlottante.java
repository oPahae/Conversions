import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirguleFixeFlottante {

    public static boolean estDouble(String nombre) {
        try { Double.parseDouble(nombre);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static String versBinaire(long nbr) {
        StringBuilder conv = new StringBuilder();
        while (nbr > 0) {
            long reste = nbr % 2;
            conv.append(reste < 10 ? (char) ('0' + reste) : (char) ('A' + reste - 10));
            nbr /= 2;
        }
        return conv.reverse().toString();
    }

    public static String virguleFixe(double nombre) {
        boolean negatif = nombre < 0;
        nombre = Math.abs(nombre);
        long partieEntiere = (long) nombre;
        double partieFractionnaire = nombre - partieEntiere;
        String entierBin = versBinaire(partieEntiere);
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
        return negatif ? "1" + resultat : "0" + resultat;
    }

    public static String completerBits(String nombre, int bits) {
        for(int i=0; i < bits-nombre.length(); i++) {
            nombre += "0";
        }
        return nombre;
    }

    public static String ieee754(double nombre, int bits) {
        String s = nombre > 0 ? "0" : "1";
        String fixe = virguleFixe(nombre);
        String mantisse = fixe.substring(1).replace(".", "");
        int e = fixe.split("\\.")[0].length() - 1;
        String eb = bits == 32 ? versBinaire(e + 127) : versBinaire(e + 2047);
        return s + (bits == 32 ?
            completerBits(eb, 8) + completerBits(mantisse, 23)
            :
            completerBits(eb, 11) + completerBits(mantisse, 54));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int n = 1;
            String title = "Virgule fixe vs Virgule floattante :) !";
            String[] labels = {"Nombre réel à convertir"};
            String[] comboOptions = {"Virgule fixe", "Virgule flottante simple précision", "Virgule flottante double précision"};
            String buttonLabel = "Calculer";

            ActionListener buttonAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CustomGUI gui = (CustomGUI) ((JButton) e.getSource()).getTopLevelAncestor();

                    String[] inputs = gui.getInputValues();
                    String nombre = inputs[0];
                    String operation = gui.getComboBoxSelection();

                    if(!estDouble(nombre))
                        gui.setOutputText("Veuiller saisir un nombre réel valide !");
                    else {
                        if("Virgule fixe".equals(operation))
                            gui.setOutputText(virguleFixe(Double.parseDouble(nombre)));
                        if("Virgule flottante simple précision".equals(operation))
                            gui.setOutputText(ieee754(Double.parseDouble(nombre), 32));
                        if("Virgule flottante double précision".equals(operation))
                            gui.setOutputText(ieee754(Double.parseDouble(nombre), 64));
                    }
                }
            };
            new CustomGUI(title, n, labels, comboOptions, buttonLabel, buttonAction);
        });
    }
}