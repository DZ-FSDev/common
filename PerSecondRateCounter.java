package com.dz_fs_dev.common;

/**
 * Thread-safe Per Second Rate Counter which employs an Exponential Moving Average with a period defined on construction.
 * Exponential moving averages weigh recent data over past data reacting to current event counts over time by decaying past events over time.
 * 
 * @author DZ-FSDev
 * @since 16.0.1
 * @version 0.0.1
 */
public class PerSecondExponentialRateCounter {
	private static int idGen = 0;
	private final int id;
	private final int emaPeriod;
	private final double emaRate, emaRate2;
	private double ts;
	private double count = 0;

	/**
	 * Constructs the Per Second Rate Counter with an Exponential Moving Average.
	 * 
	 * @param emaPeriod The exponential moving average period in seconds that will be used to calculate the exponential moving average rates.
	 * @throws IllegalArgumentException Thrown when emaPeriod is less than 2.
	 * @since 0.0.1
	 */
	public PerSecondExponentialRateCounter(int emaPeriod){
		if(emaPeriod < 2)throw new IllegalArgumentException("emaPeriod cannot be less than 2 seconds.");
		this.emaPeriod = emaPeriod;
		this.emaRate = 2.0 / (emaPeriod + 1.0);
		this.emaRate2 = 1.0 - this.emaRate;
		this.ts = System.currentTimeMillis();
		this.id = idGen++;
	}

	/**
	 * Increments the counter by 1; thread-safe.
	 * 
	 * @since 0.0.1
	 */
	public synchronized void tick(){
		count ++;
		tock();
	}
	
	/**
	 * Increments the counter by a custom amount passed as a parameter; thread-safe.
	 * 
	 * @param amount The amount to be incremented in the counter. Supports decimals and negative numbers.
	 * @since 0.0.1
	 */
	public synchronized void tick(double amount){
		count += amount;
		tock();
	}
	
	/**
	 * Private helper method to calculate the Exponential Moving Average
	 * 
	 * @since 0.0.1
	 * @see <a href=https://www.investopedia.com/ask/answers/122314/what-exponential-moving-average-ema-formula-and-how-ema-calculated.asp>
	 * 		Investopedia - Exponential Moving Average</a>
	 */
	private void tock() {
		while(System.currentTimeMillis() - ts > emaPeriod * 1000) {
			ts = emaRate2*ts + emaRate*System.currentTimeMillis();
			count = emaRate2*count;
		}
	}

	/**
	 * Polls the per second exponential moving average rate. Thread-safe.
	 * 
	 * @since 0.0.1
	 * @return The per second exponential moving average rate.
	 */
	public synchronized double pollPS(){
		tock();
		return count * 1000 / (System.currentTimeMillis()-ts);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PerSecondExponentialRateCounter))
			return false;
		PerSecondExponentialRateCounter other = (PerSecondExponentialRateCounter) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
