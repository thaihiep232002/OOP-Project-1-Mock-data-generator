import java.lang.Math;

public class RandomIntegerGenerator {
	public RandomIntegerGenerator() {

	}

	public int next(int ceiling) {
		return (int)(Math.random() * (ceiling + 1));
	}	

	public int next(int left, int right) {
		return (int)(Math.random() * (right - left + 1) + left);
	}
}
