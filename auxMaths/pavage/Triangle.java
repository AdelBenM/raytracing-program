package auxMaths.pavage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

/** Un triplet de 3 instances distinctes d une meme classe, qu on suppose immuable.
 * 
 * @author Adel
 *
 * @param <T>
 */
public class Triangle<T> {
	Set<T> contenu;
	
	public Triangle(T v1, T v2, T v3) {
		contenu = new HashSet<T>();
		contenu.add(v1);
		contenu.add(v2);
		contenu.add(v3);
		if (contenu.size()!=3)
			throw new IllegalArgumentException("Triangle mal défini : des sommets coïncident!");
	}
	
	public ArrayList<T> getContent() {
		return new ArrayList<T>(contenu);
	}
	
	
	/**Renvoie le triangle formé par l'image des sommets de l'instance par f.
	 * Si l'image de deux sommets coïncide, renvoie une erreur.
	 * @param f
	 * @return
	 */
	public <S> Triangle<S> appliquer(Function<T,S> f){
		Iterator<T> itr = getContent().iterator();
		return new Triangle<S>( f.apply(itr.next()), f.apply(itr.next()), f.apply(itr.next()));
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Triangle<?>)
			return contenu.containsAll(((Triangle<?>)o).contenu);	//ça marche pcq les éléments sont distincts
		else return false;
	}
	
	@Override
	public int hashCode() {
		return contenu.hashCode();
	}
	
	@Override
	public String toString() {
		Iterator<T> itr = contenu.iterator();
		return "Triangle [" + itr.next() + " | " + itr.next() + " | " + itr.next() + "]";
	}
	
	public static void main(String[] args) {
		Triangle<Integer> t1 = new Triangle<Integer>(0,1,2);
		Triangle<Couple<Integer>> t2 = new Triangle<Couple<Integer>>( new Couple<Integer> (0,0), new Couple<Integer> (0,1), new Couple<Integer> (1,0));
		Triangle<Integer> t3 = new Triangle<Integer>(0,1,2);
		
		Triangle<R2> t4 = new Triangle<R2>(new R2(1,0),new R2(0,0),new R2(0,1));
		Triangle<R2 >t5 = t4.appliquer(new Rotation2D(Math.PI/2));
		System.out.println(t5);
	}
	
	

}
