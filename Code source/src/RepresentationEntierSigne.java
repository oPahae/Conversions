import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepresentationEntierSigne {

    public static boolean estDecimal(String nombre) {
        try {
            Integer.parseInt(nombre);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
        return (nombre < 0 ? "1" : "0")
            + versBinaire(nombre, bits).substring(1);
    }

    public static String complementAUn(int nombre, int bits) {
        if(nombre >= 0) return versBinaire(nombre, bits);
        String v = versBinaire(nombre, bits);
        return v.replace('0', '2').replace('1', '0').replace('2', '1');
    }

    public static String complementADeux(int nombre, int bits) {
        if(nombre >= 0) return versBinaire(nombre, bits);
        StringBuilder resultat = new StringBuilder(versBinaire(nombre, bits));
        boolean foundOne = false;
        for (int i = resultat.length() - 1; i >= 0; i--) {
            if (foundOne) resultat.setCharAt(i, (resultat.charAt(i) == '0') ? '1' : '0');
            else if (resultat.charAt(i) == '1') foundOne = true;
        }
        return resultat.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int n = 2;
            String title = "Représenter un nombre signé :) !";
            String[] labels = {"Nombre à représenter", "Nombre de bits"};
            String[] comboOptions = {"Valeur Signée", "Complément à 1", "Complément à 2"};
            String buttonLabel = "Calculer";

            ActionListener buttonAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CustomGUI gui = (CustomGUI) ((JButton) e.getSource()).getTopLevelAncestor();
                    
                    String[] inputs = gui.getInputValues();
                    String nombre = inputs[0];
                    String bits = inputs[1];
                    String operation = gui.getComboBoxSelection();

                    if(!estDecimal(nombre) || !estDecimal(bits))
                        gui.setOutputText("Veuillez saisir un nombre valide !");
                    else {
                        if("Valeur Signée".equals(operation))
                            gui.setOutputText(valeurSignee(Integer.parseInt(nombre), Integer.parseInt(bits)));
                        if("Complément à 1".equals(operation))
                            gui.setOutputText(complementAUn(Integer.parseInt(nombre), Integer.parseInt(bits)));
                        if("Complément à 2".equals(operation))
                            gui.setOutputText(complementADeux(Integer.parseInt(nombre), Integer.parseInt(bits)));
                    }
                }
            };
            new CustomGUI(title, n, labels, comboOptions, buttonLabel, buttonAction);
        });
    }
}
