package objets.objetPhong;

import java.awt.Color;

import auxMaths.algLin.M3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.objetmaths.surfacemaths.Quadrique;
import auxMaths.objetmaths.surfacemaths.SurfMath;
import auxMaths.objetmaths.surfacemaths.degre1.PlanMath;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.Scalaire;
import objets.TourneAutour;
import optique.couleur.CouleurS;

public class Sphere extends Surface<Quadrique> implements TourneAutour{


	private Point3 centre;
	private double rayon;

	//Constructeur


	public Sphere(String name, Point3 m, double r, Color c) {
		super(name, initialize(r,m),c);
		centre=m;
		rayon=r;
		majListeAttributs();
	}
	
	private static Quadrique initialize(double rayon, Point3 centre) {
		return Quadrique.chgmtRepereCan( new Quadrique( M3.id,R3.zero,-rayon*rayon)  , M3.id, centre);
	}

	@Override
	public Sphere clone() {
		return new Sphere(getNom(),centre,rayon,listeCouleurs[0].getColor());
	}

	//Getters et Setters

	
	/**
	 * @return the centre
	 */
	public Point3 getCentre() {
		return centre;
	}

	/**
	 * @param centre the centre to set
	 */
	public void setCentre(Point3 centre) {
		this.centre = centre;
		attributs.put("Centre", new Point(centre));
		maj();
	}

	/**
	 * @return the rayon
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * @param rayon the rayon to set
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
		attributs.put("Rayon", new Scalaire(rayon));
		maj();
	}


	// Edition de l'objet
	
	@Override
	public void majListeAttributs() {
		attributs.put("Centre", new Point(centre));
		attributs.put("Rayon", new Scalaire(rayon));
		attributs.put("Couleur", new Couleur(listeCouleurs[0]));
	}

	@Override
	public void maj() {
		centre = ((Point) attributs.get("Centre")).getValue();
		rayon = ((Scalaire) attributs.get("Rayon")).getValue();
		listeCouleurs[0] = ((Couleur) attributs.get("Couleur")).getValueS();
		setGeometrie(Quadrique.chgmtRepereCan( new Quadrique( M3.id,R3.zero,-rayon*rayon)  , M3.id, centre));
	}

	



	public void reset(double r, Point3 p, Color c) {
		rayon=r;
		centre=p;
		listeCouleurs[0]=new CouleurS(c);
		majListeAttributs();
		maj();
	}




	
	//Autres
	
	@Override
	public String toString() {
		return String.format("Sphere : { Centre = %s ; Rayon = %3f }", centre, rayon);
	}

	@Override
	public Point3 getPointRef() {
		return centre;
	}






}
