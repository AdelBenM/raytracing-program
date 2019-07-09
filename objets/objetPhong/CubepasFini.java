package objets.objetPhong;

import java.awt.Color;

import auxMaths.algLin.M3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.Rn;
import auxMaths.objetmaths.surfacemaths.CubeMath2;
import objets.AFaireTourner;
import objets.TourneAutour;
import optique.couleur.CouleurS;

public class CubepasFini extends Surface implements TourneAutour, AFaireTourner{

  
  
  public CubepasFini(String name, Point3 p, double cote, M3 b,  Color c) {
	super(name);
    surfGeometrie=new CubeMath2(p,cote,b);
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
 
  
  public CubepasFini(String name, Point3 p, double cote,  Color c) {
	super(name);
    surfGeometrie=new CubeMath2(p,cote,M3.id);
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
  
  public CubeMath2 getSurf() {
    return (CubeMath2)surfGeometrie;
  }

  
  
  public Point3 getPoint() {
    return ((CubeMath2)surfGeometrie).getCentre();
  }
  
  
  
  public void tourner(Rn axe, double theta) {
    ((CubeMath2) surfGeometrie).faireTourner(axe, theta);
  }
  
  
  public void reset(Point3 p, double cote, Rn valeurRot, M3 baseRot,  Color c) {
    ((CubeMath2) surfGeometrie).reset(p,cote,valeurRot,baseRot);
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
  
  
  @Override
public String toString() {
    return String.format("Cube : { Cote = %.3g ; Centre = %s", ((CubeMath2)surfGeometrie).getCote(),((CubeMath2) surfGeometrie).getCentre() );
  }


  @Override
  public Point3 getPointRef() {
    // TODO Auto-generated method stub
    return ((CubeMath2)surfGeometrie).getCentre();
  }


  @Override
  public void faireTourner(R3 val) {
    ((CubeMath2)surfGeometrie).faireTourner(val);
    
  }

  @Override
  public M3 getBaseRef() {
    return ((CubeMath2)surfGeometrie).getBase();
  }


  @Override
  public void faireTourner(R3 val, M3 base) {
    ((CubeMath2)surfGeometrie).faireTourner(val,base);
    
  }


  @Override
  public void faireTournerAvecJoystick(int type, double incrrot, M3 base1,
      Point3 oeil) {
    ((CubeMath2)surfGeometrie).setBase(((CubeMath2)surfGeometrie).getBase().transformation(type, incrrot, base1, oeil));
    
  }


@Override
public void faireTourner(double val, R3 axe) {
	// TODO Auto-generated method stub
	
}


@Override
public void majListeAttributs() {
	// TODO Auto-generated method stub
	
}


@Override
public void maj() throws ClassCastException {
	// TODO Auto-generated method stub
	
}




}
