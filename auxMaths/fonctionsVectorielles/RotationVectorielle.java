package auxMaths.fonctionsVectorielles;

import auxMaths.Quaternion;
import auxMaths.algLin.R3;
/**Impl�mente O3+(R) avec la sph�re unit� de l'espace des quaternions
 * 
 * @author Adel
 *
 */
public class RotationVectorielle implements MorphismeOrthogonal{

	protected Quaternion q;	//norm�
	public static final RotationVectorielle id = new RotationVectorielle(Quaternion.un);
	
	//===========================================
	//Constructeur
	
	protected RotationVectorielle(Quaternion q) {
		this.q=q;
	}
	
	
	
	//=======================================

	/**Renvoie la fonction qui � un point applique r2 puis r1.
	 * 
	 * @param r2
	 * @return
	 */
	public RotationVectorielle rond(RotationVectorielle r2) {
		return new RotationVectorielle(q.fois(r2.getQuaternion()));
	}


	public Quaternion getQuaternion() {
		return q;
	}

	@Override
	public R3 fonction(R3 r) {
		return q.agirSur(r);
	}

	@Override
	public RotationVectorielle inverse() {
		return new RotationVectorielle(q.inv());
	}

}