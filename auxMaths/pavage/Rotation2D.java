package auxMaths.pavage;

import java.util.function.Function;

public class Rotation2D implements Function<R2, R2> {

	double theta;
	
	public Rotation2D(double angle) {
		theta = angle;
	}

	@Override
	public R2 apply(R2 t) {
		return t.rotation(theta);
	}

}
