import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author YalchinAlv
 *
 */
public class Triangle {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// reading input from stdin
		ArrayList<String> input = new ArrayList<>();
		while(scan.hasNextLine()) {
			input.add(scan.nextLine());
		}

		int max = Integer.parseInt(input.get(0));
		int[][] triangle = new int[input.size()][];
		for (int i = 0; i < triangle.length; i++) {
			scan = new Scanner(input.get(i));
			int[] tmp = new int[i + 1];
			
			for (int j = 0; j < tmp.length; j++) {
				tmp[j] = scan.nextInt();
				if (tmp[j] > max)
					max = tmp[j];
			}
			
			triangle[i] = tmp;
		}
		
		System.out.println(getMaxSum(triangle));
		
	}
	
	/**
	 * This is the method to be used
	 * @param triangle
	 * @return
	 */
	public static int getMaxSum(int[][] triangle) {
		int max = triangle[0][0];
		for (int[] is : triangle) {
			for (int i : is) {
				if (max < i)
					max = i;
			}
		}
		
		Integer[][] maxSumAt = new Integer[triangle.length][triangle.length];
		return getMaxSum(triangle, 0, 0, maxSumAt, getPrimes(max));
	}
	
	/**
	 * Find the max sum recursively.
	 * Assuming that the numbers are positive.
	 * @param triangle
	 * @param row
	 * @param col
	 * @param isNotPrime
	 * @return 0 if there is not any path from the 1st row to the last row
	 */
	private static Integer getMaxSum(int[][] triangle, int row, int col, Integer[][] maxSumAt, boolean[] isNotPrime) {

		if (maxSumAt[row][col] != null) {
			return maxSumAt[row][col];
		}
		
		if (isNotPrime[triangle[row][col]]) {

			if (row == triangle.length - 1) {
				maxSumAt[row][col] = triangle[row][col];
			}
			else {
				int sum1 = getMaxSum(triangle, row + 1, col, maxSumAt, isNotPrime);
				int sum2 = getMaxSum(triangle, row + 1, col + 1, maxSumAt, isNotPrime);
	
				maxSumAt[row][col] =  Math.max(sum1, sum2) > 0 ? triangle[row][col] + Math.max(sum1, sum2) : 0;
			}
			
			return maxSumAt[row][col];
		}
		maxSumAt[row][col] = 0;
		return 0;
	}
	
	/**
	 * sieve of Eratosthenes algorithm to find all primes up to max value
	 * @param max
	 * @return
	 */
	private static boolean[] getPrimes(int max) {
//		ArrayList<Integer> primes = new ArrayList<>();
		
		boolean[] isNotPrime = new boolean[max + 1];
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for(int i = 2; i <= Math.sqrt(max); i++)
			if(!isNotPrime[i])
				for(int j = i * i; j <= max; j += i)
					isNotPrime[j] = true;
		
//		for(int i = 2; i <= max; i++)
//			if(!isNotPrime[i])
//				primes.add(i);
		
		return isNotPrime;				
	}
}
