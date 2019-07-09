package auxMaths.pavage;

import auxMaths.TesteurNullite;

public class R2 extends Couple<Double>{


	public static final R2 zero = new R2(0,0);
	
	public R2(double x, double y) {
		super(x,y);
	}
	
	//Opérations Algébriques simples
	
	public R2 fois(double lambda) {
		return new R2(x*lambda, y*lambda);
	}
	
	public R2 plus(R2 v2) {
		return new R2(x+v2.x, y+v2.y);
	}
	
	public R2 moins(R2 v2) {
		return new R2(x-v2.x, y-v2.y);
	}
	
	//Autres opérations
	
	public double normeCarree() {
		return x*x+y*y;
	}
	
	public R2 coordonneesPol() {
		double r = Math.pow(normeCarree(),0.5);
		if (TesteurNullite.estNul(y))
			return (x>=0)? new R2(r,0) : new R2(r,Math.PI);
		else
			return new R2(r, 0.5*Math.atan(y/(r+x)));
	}
	
	/**Rotation dans le sens direct
	 * 
	 * @param theta
	 * @return
	 */
	public R2 rotation(double theta) {
		return new R2( Math.cos(theta)*x - Math.sin(theta)*y,
						Math.sin(theta)*x + Math.cos(theta)*y);
	}
	
	//Autres
	@Override
	public String toString() {
		return "(" + x + " , " + y + ")";
	}
}
