package optique.couleur;

import java.awt.Color;
import java.io.Serializable;

/**
 * Cette classe implémente le résultat d'une mesure de puissance lumineuse surfacique.
 * Superposition de 3 rayonnements monochromatiques incohérents. i est la puissance surfacique totale.
 * r,g,b caractérisent la part de la puissance du rayonnement monochromatique correspondant dans la puissance totale.
 * 
 */
public class CouleurL implements Serializable{

	private static final long serialVersionUID = -3224752714424626019L;
	double i;
	//double r,g,b;
	Color coul;
	//double maxCoul;
	public static final CouleurL noir = new CouleurL(1,1,1,0);


	//Constructeurs
	public CouleurL(float r1, float g1, float b1, float i) {
		
		if (i<0 || r1<0 || g1<0 || b1<0 )
			throw new IllegalArgumentException("");
		if (r1+g1+b1==0) {
			//r=g=b=1;
			coul=Color.black;
			this.i=0;
		}
		else {
			this.i=i;
			/*this.r=r1/(r1+g1+b1);
			this.g=g1/(r1+g1+b1);
			this.b=b1/(r1+g1+b1);*/
			coul = new Color(r1/(r1+g1+b1), g1/(r1+g1+b1), b1/(r1+g1+b1));
		}
		

/*		maxCoul=r;
		if (g>r)
			maxCoul=g;
		if (b>maxCoul)
			maxCoul=b;*/
	}
/*	
	private void initialize(double r1,double g1,double b1) {
		r=r1/(r1+g1+b1);
		g=g1/(r1+g1+b1);
		b=b1/(r1+g1+b1);

	}*/

	public CouleurL(Color c, double i) {
		if (i<0)
			throw new IllegalArgumentException("");
		else {
			this.i=i;
			coul=c;
		}
		//this(c.getRed(),c.getGreen(),c.getBlue(),i);
	}

	private CouleurL(CouleurL lux) {
		i=lux.i;
		coul=lux.coul;
		/*r=lux.r;
		g=lux.g;
		b=lux.b;*/
	}
	
	
	//=========================================================
	//Getters
	public double getIntensite() {
		return i;
	}
	
	
	public float getProportionR() {
		return (float) ((0.0+coul.getRed())/255);
	}
	public float getProportionG() {
			return (float) ((0.0+coul.getGreen())/255);
	}
	public float getProportionB() {
			return (float) ((0.0+coul.getBlue())/255);
	}

	public Color getColor() {
		return appliquerIntGlobale(i);
	}

	//==================================================================
	//Methodes de luminosite



	public void multiplierIntensite(double k) {
		i=k*i;
	}

	public CouleurL multiplieIntensite(double k) {
		CouleurL result = new CouleurL(this);
		result.multiplierIntensite(k);
		return result;
	}


	public Color appliquerIntGlobale(double val) {
		if (val<0)
			throw new IllegalArgumentException("intensité négative appliquée!");
		else {
			float[] hsb = Color.RGBtoHSB(coul.getRed(), coul.getGreen(), coul.getBlue(),null);
			return Color.getHSBColor(hsb[0], hsb[1], Float.min(1,(float) (i/val)));
		}
	}



	
	//Melange de CouleurL

	/**Addition naïve des composantes ; à manier de manière appropriée pour en dégager un sens optique.
	 * 
	 * @param lux
	 * @return
	 */
	public CouleurL plus(CouleurL lux) {
		if (lux.i==0)
			return this;
		else if (i==0)
			return lux;
		else {
			//Simple moyenne arithmetique des couleurs, ponderee par l intensite des sources
			Color resultante = new Color( (int) (( i*coul.getRed() + lux.i*lux.coul.getRed() )/(lux.i+i) ),
											(int) (( i*coul.getGreen() + lux.i*lux.coul.getGreen() )/(lux.i+i) ),
											(int) (( i*coul.getBlue() + lux.i*lux.coul.getBlue() )/(lux.i+i) ) );				
			return new CouleurL( resultante , i+lux.i);
		}
	}
	
	
	

	@Override
	public int hashCode() {
		return coul.getRGB();
	}

	@Override
	public boolean equals(Object o2) {
		if (o2 instanceof CouleurL) {
			return coul==((CouleurL)o2).coul && i== ((CouleurL)o2).i;
		}
		else return false;
	}

	//Autres
	

	@Override
	public String toString() {
		return String.format("( Intensite: %.3g  |  Couleur: (%.3g ; %.3g ; %.3g) )",i,coul);
	}


	public static void main(String[] args) {
		CouleurL l1 = new CouleurL(1,0,0,4);
		//CouleurL l2 = new CouleurL(0.333,0.333,0.333,0.06);
		//CouleurL l3 = l1.plus(l2);
		Color c= new Color(1,1,(float)(0.5));
		//System.out.println(l2);
		

	}





}
