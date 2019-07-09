package objets.objetMecatro;

import java.awt.Color;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.objetmaths.surfacemaths.degre1.RectangleMath;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.Scalaire;
import ihm.fenetre1.edition.entrees.Vecteur;

/*Miroir Rectangle avec une unique source ponctuelle en 0
 * 
 */
public class MiroirRectangle extends MiroirPlanConvexe<RectangleMath>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8575518834820268971L;

	public MiroirRectangle(String nom, O3 base, Point3 centre, double longueur, double largeur) {
		super(nom,new RectangleMath(base, centre, largeur, longueur), Color.RED);
		majListeAttributs();
		
	}
	
	public MiroirRectangle(String nom, VectUnitaire n, Point3 centre, double longueur, double largeur) {
		super(nom,new RectangleMath(n, centre, largeur, longueur), Color.RED);
		majListeAttributs();
		
	}
	
	public MiroirRectangle(String nom, R3 n, Point3 centre, double longueur, double largeur) {
		this(nom,n.normer(),centre,longueur,largeur);
		
	}
	
	@Override
	public void majListeAttributs() {
		attributs.put("Normale", new Vecteur(getGeometrie().getNorm(Point3.origine)));
		attributs.put("Centre", new Point(getGeometrie().getCentre()));
		attributs.put("Largeur",new Scalaire(getGeometrie().getLongueur1()));
		attributs.put("Longueur",new Scalaire(getGeometrie().getLongueur2()));
		attributs.put("Couleur", new Couleur(listeCouleurs[0]));
		
	}

	@Override
	public void maj() {
		R3 normale;
		Point3 centre;
		double largeur;
		double longueur;
		centre = ((Point) attributs.get("Centre")).getValue();
		normale = ((Vecteur) attributs.get("Normale")).getValue();
		longueur = ((Scalaire) attributs.get("Longueur")).getValue();
		largeur = ((Scalaire) attributs.get("Largeur")).getValue();
		listeCouleurs[0] = ((Couleur) attributs.get("Couleur")).getValueS();
		setGeometrie(new RectangleMath(normale,centre, longueur, largeur));
	
	}

}


