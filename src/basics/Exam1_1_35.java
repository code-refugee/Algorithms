package basics;

/*模拟掷色子*/
import edu.princeton.cs.algs4.*;

public class Exam1_1_35 {
	public static double[] diceProbability() // 准确概率
	{
		int sides = 6;
		double[] dist = new double[2 * sides + 1]; // 此处由于不用0下标,所以加1
		for (int i = 1; i <= sides; i++)
			for (int j = 1; j <= sides; j++) {
				dist[i + j] = dist[i + j] + 1.0;
			}

		for (int k = 2; k < 2 * sides + 1; k++)
			dist[k] /= 36.0;
		return dist;
	}

	public static double[] simulateProbability(int N) {
		int sides = 6;
		double[] dist = new double[2 * sides + 1];
		int m = 0;
		int n = 0;
		for (int i = 0; i < N; i++) {
			m = StdRandom.uniform(1, 7);
			n = StdRandom.uniform(1, 7);
			dist[m + n] += 1.0;
		}
		for (int k = 2; k < 2 * sides + 1; k++)
			dist[k] /= N;
		return dist;
	}

	public static void main(String[] args) {
		int n = 100000;
		double[] fact = diceProbability();
		double[] simulate = simulateProbability(n);
		StdOut.println("fact probability:");
		for (double f : fact)
			StdOut.printf("%.3f  ", f);
		StdOut.println();
		StdOut.println("simulate probability:");
		for (double s : simulate)
			StdOut.printf("%.3f  ", s);
		StdOut.println();
	}

}
