package objets.objetPhong;

import java.awt.Color;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.objetmaths.surfacemaths.degre1.PlanMath;
import auxMaths.objetmaths.surfacemaths.degre1.RectangleMath;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.Scalaire;
import ihm.fenetre1.edition.entrees.Vecteur;
import optique.couleur.CouleurS;

public class Rectangle extends Surface<RectangleMath>{

	
	protected Rectangle(String name, RectangleMath surfMath, CouleurS... tab) {
		super(name,surfMath,tab);
		majListeAttributs();
	}
	
	
	public Rectangle(String name, R3 normale, Point3 centre, double largeur, double longueur, Color c) {
		this(name,new RectangleMath(normale,centre, longueur, longueur),new CouleurS(c));
	}
	
	@Override
	public Rectangle clone() {
		return new Rectangle(getNom(),getGeometrie(),listeCouleurs);
	}
	
	
	//Méthodes d'édition
	
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


	
	//Autres
	public Point3 getCentre() {
		return getGeometrie().getCentre();
	}


	public double getLongueur1() {
		return getGeometrie().getLongueur1();
	}

	public double getLongueur2() {
		return getGeometrie().getLongueur2();
	}


/*	public void reset(R3 n, Point3 p,double largeur, double longueur, Color c) {
		((Rectangle) surf).reset(n,p, largeur, longueur, c);
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
	} */


	@Override
	public String toString() {
		return String.format("Rectangle : { Normal = %s ; Centre = %s ; Dimension = %s x %s", getNorm(Point3.origine),getCentre(), getLongueur1(), getLongueur2() );
	}



	





}
