package ihm.fenetre1.edition;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import ihm.fenetre1.edition.entrees.TypeEntrable;
import ihm.fenetre1.ongletsEdition.ongletScene.ABouttonOK;
import objets.objetPhong.Rectangle;
import objets.scene.Objet;




public class FenetreEntree extends JFrame implements ABouttonOK{


	/**
	 * 
	 */
	private static final long serialVersionUID = -207388865346983657L;
	PanelEntree pan;

	private FenetreEntree(PanelEntree pan) {
		super(pan.getName());
		this.pan=pan;
		add(pan);
		if(!pan.isVraimentEditable)
			dispose();
		else {
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			setVisible(true);
		}

	}


	public FenetreEntree(Object obj, String nomObjet) {
		this(new PanelEntree(obj,nomObjet));
	}

	public FenetreEntree(Objet obj) {
		this(new PanelEntree(obj));
	}

	public FenetreEntree(Objet obj, ActionListener aFaireApresOK) {
		this(new PanelEntree(obj,aFaireApresOK));
	}


	@Override
	public ActionListener getActionPostValidation() {
		return pan.getActionPostValidation();
	}

	@Override
	public void setActionPerformedAtValidation(ActionListener a) {
		pan.setActionPerformedAtValidation(a);

	}


}
