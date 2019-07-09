package ihm.fenetre1.ongletsEdition.ongletScene;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import objets.scene.Objet;


/**Cr�e l'objet Java.awt.List utilis� dans l'onglet objet. Passer par cette classe permet de synchroniser les modifications sur l'ihm et dans une liste.
 * 
 * @author Adel
 *
 */
public class IHMListe<O extends Objet>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724474838656119072L;
	
	private List<O> contenu;
	private Map<String,O> dictionnaireContenu;
	
	private java.awt.List affichage;
	
	/**Modifie la liste de base
	 * 
	 * @param base
	 */
	public IHMListe(List<O> base) {
		super();
		contenu=base;
		affichage = new java.awt.List();
		conformerAuContenu();
	}
	
	/**A priori modifie le tableau
	 * 
	 * @param base
	 */
	public IHMListe(O[] base) {
		this(Arrays.asList(base));
	}
	
	/**Creer l'ihm correspondant au contenu. Appel� � l'instanciation et lorsque contenu est modifi�.
	 * 
	 */
	public void conformerAuContenu() {
		creerDico();
		creerAffichage();
	}
	
	public java.awt.List getComponent() {
		return affichage;
	}
	
	//===============================================
	//Cr�er le dico exhaustif
	
	/**Compte le nombre de fois qu'appara�t un nom dans la liste de d�part (contenu).
	 * 
	 * @param nom
	 * @return
	 */
	public int nbOccurences(String nom) {
		int result = 0;
		for (O o : contenu)
			if (o.getNom().compareTo(nom)==0)
				result++;
		return result;
	}
	
	
	
	/**Appel� par cr�er dico et add. Ajoute o dans le dico avec un nom caract�ristique: le sien plus �ventuellement un num�ro " (i)"
	 * 
	 * @param o
	 */
	private void auxAjoutDansDico(O o) {
		String nom = o.getNom();
		int nbOccr = nbOccurences(nom);
		if (nbOccr==0)
			dictionnaireContenu.put( nom ,o);
		else dictionnaireContenu.put( nom + " ("+nbOccr+")", o );
	}
	
	/**Cree un dico � partir de contenu tel qu'il repr�prente une bijection de l'ensemble des cl�s vers contenu.
	 * 
	 */
	public void creerDico() {
		dictionnaireContenu = new HashMap<String,O>();
		for (O o : contenu) 
			auxAjoutDansDico(o);
	}
	
		
	
	/**Cree la liste affich�e � partir du dico
	 * 
	 */
	public void creerAffichage() {
		Iterator<String> itr = dictionnaireContenu.keySet().iterator();
		while (itr.hasNext())
			affichage.add(itr.next());
	}
	
	
	
	//================================================
	//Lire et manipuler la liste sous-jacente sur l'IHM
	
	/**Renvoie le ieme objet affich� dans l'IHM (en commen�ant par 0)
	 * 
	 * @param i
	 * @return
	 */
	public O get(int i) {
		return dictionnaireContenu.get( affichage.getItem(i));
	}
	
	/**Renvoie l'objet s�lectionn� dans l'IHM
	 * 
	 * @return
	 */
	public O getSelectedIndex() {
		return get(affichage.getSelectedIndex());
	}
	
	
	
	
	
	
	
	
	
}
