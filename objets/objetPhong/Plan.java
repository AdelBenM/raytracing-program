package objets.objetPhong;

import java.awt.Color;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.objetmaths.surfacemaths.SurfMath;
import auxMaths.objetmaths.surfacemaths.degre1.PlanMath;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.Vecteur;
import optique.couleur.CouleurS;

public class Plan extends Surface<PlanMath>{


	private String nom;

	
	protected Plan(String name, PlanMath p, Color c) {
		super(name,p,c);
		majListeAttributs();
	}
	
	public Plan(String name, R3 n, Point3 p, Color c) {
		this(name,new PlanMath(n,p),c);
	}
	
	@Override
	public Surface<PlanMath> clone() {
		return new Plan(getNom(),getGeometrie(),listeCouleurs[0].getColor());
	}
	
	@Override
	public PlanMath getGeometrie() {
		return (PlanMath)super.getGeometrie();
	}

	@Override
	public void majListeAttributs() {
		// TODO Auto-generated method stub
		attributs.put("Normale", new Vecteur(getGeometrie().getNorm()));
		attributs.put("Point part", new Point(getPoint()));
		attributs.put("Couleur", new Couleur(listeCouleurs[0]));
	}
	
	@Override
	public void maj() {
		Point3 ptPart;
		R3 normale;
		ptPart = ((Point) attributs.get("Point part")).getValue();
		normale = ((Vecteur) attributs.get("Normale")).getValue();
		listeCouleurs[0] = ((Couleur) attributs.get("Couleur")).getValueS();
		
		setGeometrie(new PlanMath(normale,ptPart));
	}



	
	public Point3 getPoint() {
		return getGeometrie().getPoint();
	}



	public void reset(R3 n, Point3 p, Color c) {
		getGeometrie().reset(n,p);
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
	}


	@Override
	public String toString() {
		return String.format("Plan : { Normal = %s ; Point particulier = %s", getNorm(Point3.origine),getPoint() );
	}








}
