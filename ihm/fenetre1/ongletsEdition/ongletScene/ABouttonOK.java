package ihm.fenetre1.ongletsEdition.ongletScene;

import java.awt.event.ActionListener;

/**Une interface pour inciter � suivre une certaine impl�mentation du motif "agir apr�s validation"
 * Un attribut ActionListener, dont l'actionPerformed est vide par d�faut, est �ventuellement modifi�.
 * La m�thode actionPerformed de cet attribut sera appel�e dans chaque bouton "valider" � la fin du corps de son actionPerformed.
 * 
 * @author Adel
 *
 */
public interface ABouttonOK {
	
	
	ActionListener getActionPostValidation();
	
	public void setActionPerformedAtValidation(ActionListener a);
}
