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

import javax.swing.BorderFactory;
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

public class PanelEntree extends JPanel implements ABouttonOK{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3833609637587120688L;
	Map<TypeEntrable,List<PanelEntrable>> contenu;
	JPanel contenant;

	Editable objAModifier;
	JButton valider;
	
	boolean isVraimentEditable;
	
	ActionListener actionPostValidation = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// VIDE
			
		}
		
	};

	

	public PanelEntree(Object obj, String nomObjet) {
		super();
		setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(nomObjet),
						BorderFactory.createEmptyBorder(5,5,5,5)));
	
		
		isVraimentEditable = false;
		
		if(obj instanceof Editable) {
			Map<TypeEntrable, List<String>> listeContenu = ((Editable) obj).getTypeAttributsEditables();

			//Si l'objet n'est finalement pas modifiable, sa liste d'attributs modifiables est vide.
			if( !listeContenu.isEmpty()) { //On affiche la fen�tre d'�dition
				isVraimentEditable = true;
				objAModifier = (Editable)obj;
				initiate(listeContenu);
			}
		}
		
		if (!isVraimentEditable)
			JOptionPane.showMessageDialog(this, nomObjet + " n'est pas modifiable!");
		
	}

	
	public PanelEntree(Objet obj) {
		this(obj,"Edition de "+obj.getNom());
	}
	

	
	public PanelEntree(Objet obj, ActionListener aFaireApresOK) {
		this(obj);
		setActionPerformedAtValidation(aFaireApresOK);
	}
	
	
	private void initiate(Map<TypeEntrable,List<String>> listeContenu) {
		setLayout(new FlowLayout());

		construireContenu(listeContenu);
		majContenu();

		majFenetre();
		add(contenant);

		valider = new JButton("OK");
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				objAModifier.reconstruireAvecAttributs(construireListeAttributs());
				actionPostValidation.actionPerformed(e);
				//dispose();							//TODO
			}
		});
		add(valider);

		setVisible(true);
	}


	//Modification de contenu

	/**Construit le dictionnaire des entr�es � partir de la liste des entr�es class�es par type.
	 * 
	 * @param m
	 */
	private void construireContenu(Map<TypeEntrable, List<String>> m) {
		contenu = new HashMap<TypeEntrable, List<PanelEntrable>>();
		Iterator<TypeEntrable> itr = m.keySet().iterator();
		Iterator<String> itr2 ;

		TypeEntrable typeEnCours;
		while (itr.hasNext()) {
			typeEnCours = itr.next();
			contenu.put(typeEnCours, new ArrayList<PanelEntrable>()); //initialisation de la liste 

			itr2 = m.get(typeEnCours).iterator();	//on va remplir la liste de contenu[typeEnCours]
			while(itr2.hasNext())
				contenu.get(typeEnCours).add(typeEnCours.entreeDefaut(itr2.next())); //on ajoute � la liste l'entr�e d�faut correspondant au type en cours et dont le nom est donn� dans la liste 	
		}		
	}


	/**Conforme une entr�e de contenu � une valeur donn�e
	 * 
	 */
	private void majEntree(String nom, Object newValeur) {
		Iterator<TypeEntrable> itr = contenu.keySet().iterator();
		Iterator<PanelEntrable> itr2;
		boolean continuer = true;

		PanelEntrable tmp;
		while (itr.hasNext() &&continuer) {
			itr2 = contenu.get(itr.next()).iterator();
			while (itr2.hasNext() && continuer) {
				tmp =itr2.next();
				if (tmp.getNom().compareTo(nom)==0) {
					continuer= false;
					tmp.majValeur(newValeur);
				}
			}
		}

	}

	/**Conforme contenu � la valeur des attributs actuels de objAModifier.
	 * (Remarque : ne cr�e pas de nouvelles entr�es dans contenu) 
	 */
	private void majContenu() {
		Map<String,Entrable> m = objAModifier.getAttributsEditables();
		Iterator<String> itr = m.keySet().iterator();
		String nomEnCours;
		while (itr.hasNext()) {
			nomEnCours=itr.next();
			majEntree(nomEnCours, m.get(nomEnCours));
		}
	}

	//Mise � jour de l'affichage

	/**Conforme la fen�tre au contenu
	 *  
	 */
	private void majFenetre() {
		contenant = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;

		int nbColonnesMax = 0;

		int numeroColonneAct=0;
		int numeroLigneAct=0;

		Iterator<TypeEntrable> itr = contenu.keySet().iterator();
		Iterator<PanelEntrable> itr2;

		TypeEntrable typeEnCours;
		PanelEntrable entreeEnCours;
		while (itr.hasNext()) {
			typeEnCours = itr.next();


			c.gridwidth = typeEnCours.entreeDefaut("").getNombreCol();
			c.gridheight = typeEnCours.entreeDefaut("").getNombreLig();
			itr2 = contenu.get(typeEnCours).iterator();
			while (itr2.hasNext()) {
				entreeEnCours = itr2.next();
				c.gridx = numeroColonneAct;
				c.gridy = numeroLigneAct;
				contenant.add(entreeEnCours, c);
				numeroColonneAct += c.gridwidth;
			}
			numeroLigneAct += c.gridheight;
			if (nbColonnesMax<numeroColonneAct +1)
				nbColonnesMax=numeroColonneAct+1;

			numeroColonneAct = 0;


		}

		contenant.validate();
		contenant.repaint();
	}

	//Mise � jour de l'objet

	/**Renvoie les entr�es de contenu organis�es sous la forme d'un dictionnaire d'entrables 
	 * 
	 * @return
	 */
	private Map<String, Entrable> construireListeAttributs(){
		Map<String, Entrable> result = new HashMap<String, Entrable>();
		Iterator<TypeEntrable> itr1 = contenu.keySet().iterator();
		Iterator<PanelEntrable> itr2;

		TypeEntrable typeEnCours;
		PanelEntrable entreeEnCours;
		while (itr1.hasNext()) {
			typeEnCours = itr1.next();
			itr2 = contenu.get(typeEnCours).iterator();
			while (itr2.hasNext()) {
				entreeEnCours = itr2.next();
				result.put(entreeEnCours.getNom(), entreeEnCours.getValeurLue());
			}
		}
		return result;
	}






	@Override
	public ActionListener getActionPostValidation() {
		return actionPostValidation;
	}



	@Override
	public void setActionPerformedAtValidation(ActionListener a) {
		actionPostValidation = a;
		
	}
	
	public static void main(String[] args) {
		Rectangle s = new Rectangle("rect1", R3.ux, Point3.origine, 2, 3, Color.BLACK);
		PanelEntree fen = new PanelEntree(s);
	}

}
