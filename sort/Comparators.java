package com.dz_fs_dev.common.sort;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * Contains various comparators.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.1
 */
public final class Comparators {
	private Comparators() {};
	
	public static final Comparator<BigInteger> BIGINTEGER_ASCENDING = new Comparator<BigInteger>(){
		@Override
		public int compare(BigInteger prime, BigInteger delta) {
			return prime.compareTo(delta);
		}
	};
	
	public static final Comparator<BigInteger> BIGINTEGER_DESCENDING = new Comparator<BigInteger>(){
		@Override
		public int compare(BigInteger prime, BigInteger delta) {
			return delta.compareTo(prime);
		}
	};
	
	public static final Comparator<BigInteger[]> BIGINTEGER_ARRAY_ASCENDING = new Comparator<BigInteger[]>(){
		@Override
		public int compare(BigInteger[] prime, BigInteger[] delta) {
			int result = 0;
			
			for(int i = 0; result == 0 && i < prime.length && i < delta.length; i++) {
				result = prime[i].compareTo(delta[i]);
			}
			
			return result;
		}
	};
	
	public static final Comparator<BigInteger[]> BIGINTEGER_ARRAY_DESCENDING = new Comparator<BigInteger[]>(){
		@Override
		public int compare(BigInteger[] prime, BigInteger[] delta) {
			int result = 0;
			
			for(int i = 0; result == 0 && i < prime.length && i < delta.length; i++) {
				result = delta[i].compareTo(delta[i]);
			}
			
			return result;
		}
	};
}
