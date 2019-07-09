package ihm.fenetre1.edition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ihm.fenetre1.edition.entrees.TypeEntrable;

/**L'interface qu'impl�mente toutes les classes dont les instances ont une chance d'�tre �ditable par l'ihm. (FenetreEntree)
 * Les classes concern�es sont en principe celles qui impl�mentent des objets de la sc�ne.
 * Une instance qui n'a finalement pas vocation � �tre �dit�e n'aura qu'� renvoyer un dictionnaire vide � l'appel de "getAttributsEditables()".
 * 
 * @author Adel
 *
 */
public interface Editable {
	
	
	//=============================================================
	//Obtention de la liste des attributs
	
	//REMARQUE : la liste des attributs �ditables est cens�e �tre cod�e sous la forme d'un attributs, rpz� ici avec un getter. 
	
	/**
	 * Renvoie la liste des attributs caract�ristiques de l'objet �ditable sous
	 * la forme d'un dictionnaire qui associe son nom � un attribut.
	 * @return
	 */
	public Map<String,Entrable> getAttributsEditables();
	
	
	/**
	 * Renvoie la liste du type des attributs caract�ristiques de l'objet �ditable sous
	 * la forme d'un dictionnaire qui � un type d'entr�es associe la liste des attributs de ce type.
	 * Cette derni�re est g�n�r�e � partir du dictionnaire "classique" d'attributs obtenu par getAttributsEditables()
	 * @return
	 */
	public default Map<TypeEntrable, List<String>> getTypeAttributsEditables(){
		Map<TypeEntrable, List<String>> result = new HashMap<TypeEntrable, List<String>>();
		Map<String,Entrable> tab = getAttributsEditables();
		Iterator<String> itr = getAttributsEditables().keySet().iterator();
		
		String nomAttributEnCours;
		TypeEntrable typeAttributEnCours;		
		while (itr.hasNext()) {
			nomAttributEnCours = itr.next();
			typeAttributEnCours = tab.get(nomAttributEnCours).getTypeEntrable();
			result.putIfAbsent( typeAttributEnCours , new ArrayList<String>());
			result.get(typeAttributEnCours).add(nomAttributEnCours);
		}
		return result;
	}
	
	

	
	
	//===============================================================
	//Reconstruction de l'instance � partir de la liste de ses attributs
	
	/**
	 * Modifie l'instance en cours d edition conformement a la liste d attributs
	 * en entree.
	 * Le booleen renvoy� indique la r�ussite de l'op�ration.
	 * En cas d'�chec, l'instance n'est pas modifi�e. 
	 * @param attributs
	 * @return
	 */
	public default boolean reconstruireAvecAttributs(Map<String, Entrable> inputAttributs) {
		if ( getAttributsEditables().keySet().equals(inputAttributs.keySet()) ){	// 1ere v�rification de compatibilit� des attributs
			boolean isConformable= true;	// V�rifions que les entr�es sont de types compatibles
			Iterator<String> itr = getAttributsEditables().keySet().iterator();
			String tmp;
			while (isConformable && itr.hasNext()) {
				tmp=itr.next();
				isConformable = getAttributsEditables().get(tmp).conformerA(inputAttributs.get(tmp)); 
			}
			if (isConformable) {	//vrai ssi l'op�ration s'est faite sans heurt, i.e si toutes les entrees sont reconnues et de type compatible. Dans ce cas, attributs a �t� actualis� conform�ment � inputAttributs
				maj();	//on actualise les attributs de l'instance
				return true;
			}
			else {
				majListeAttributs(); //on annule les �ventuels changements qui sont arriv�s � attributs pendant le test pr�c�dent
				return false;
			} 
		}
		else return false;
	}

	/**
	 * Met � jour le dictionnaire des attributs au vu des attributs actuels.
	 * (� appeler si les attributs de l'instance sont modifi�s)
	 */
	public abstract void majListeAttributs();

	/**
	 * Met � jour les attributs de l'instance au vu du dictionnaire d'attributs. 
	 * Son comportement g�om�trique et optique est lui aussi actualis�.
	 * (� appeler dans reconstruireAvecAttributs, par exemple)
	 */
	public abstract void maj();
}

