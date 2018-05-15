import java.util.ArrayList;

public class Triangle {
	
	public static void main(String[] args) {
		getPrimes(1000000);
		
//		System.out.println(getPrimes(1000000));
	}
	
	/**
	 * sieve of Eratosthenes algorithm to find all primes up to max value
	 * @param max
	 * @return
	 */
	public static ArrayList<Integer> getPrimes(int max) {
		ArrayList<Integer> primes = new ArrayList<>();
		
		boolean[] isPrime = new boolean[max + 1];
		for(int i = 2; i < Math.sqrt(max); i++)
			if(!isPrime[i])
				for(int j = i * i; j <= max; j += i)
					isPrime[j] = true;
		
		for(int i = 2; i <= max; i++)
			if(!isPrime[i])
				primes.add(i);
		
		return primes;				
	}
}
