package ihm;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ChoixDuMode {
	
	Programme p;
	
  public ChoixDuMode() {
    JDialog.setDefaultLookAndFeelDecorated(true);
    Object[] selectionValues = { "Image haute qualit� (raytracing)", "Distribution spatiale d'un rayonnement r�fl�chi (m�catro)" };
    String initialSelection = "Dogs";
    Object selection = JOptionPane.showInputDialog(null, "Quel type de simulation cherchez-vous?",
        "Choix du mode", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
    
    
    p = null;
    switch (selection.toString()) {
    case "Image haute qualit� (raytracing)":
    	p = new Programme(Mode.Raytracing);
    	break;
    case "Distribution spatiale d'un rayonnement r�fl�chi (m�catro)":
    	p = new Programme(Mode.Miroirs);
    	break;
    }
    
    
  }
  
}