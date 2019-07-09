package auxMaths.pavage;

public class Couple<T> {
	protected T x;
	protected T y;
	
	public Couple(T t1, T t2) {
		this.x=t1;
		this.y=t2;
	}
	
	public T get1() {
		return x;
	}
	
	public T get2() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Couple<?> other = (Couple<?>) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Couple (" + x + " , " + y + ")";
	}
	


}
