package com.dz_fs_dev.common.counters;

/**
 * Thread-safe Per Minute Rate Counter which employs an Exponential Moving Average with a period defined on construction.
 * Exponential moving averages weigh recent data over past data reacting to current event counts over time by decaying past events over time.
 * 
 * @author DZ-FSDev
 * @since 16.0.1
 * @version 0.0.2
 */
public class PerMinuteExponentialRateCounter {
	private static int idGen = 0;

	private final int ID;
	private final int emaPeriod;
	private final double emaRate, emaRate2;
	private double ts;
	private double count = 0;

	/**
	 * Constructs the Per Minute Rate Counter with an Exponential Moving Average.
	 * 
	 * @param emaPeriod The exponential moving average period in minutes that will be used to calculate the exponential moving average rates.
	 * @throws IllegalArgumentException Thrown when emaPeriod is less than 2.
	 * @since 0.0.1
	 */
	public PerMinuteExponentialRateCounter(int emaPeriod){
		if(emaPeriod < 2)throw new IllegalArgumentException("emaPeriod cannot be less than 2 minutes.");
		this.emaPeriod = emaPeriod;
		this.emaRate = 2.0 / (emaPeriod + 1.0);
		this.emaRate2 = 1.0 - this.emaRate;
		this.ts = System.currentTimeMillis();
		this.ID = idGen++;
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
	 * @see <a href=https://www.investopedia.com/ask/answers/122314/what-exponential-moving-average-ema-formula-and-how-ema-calculated.asp>
	 * 		Investopedia - Exponential Moving Average</a>
	 * @since 0.0.1
	 */
	private void tock() {
		while(System.currentTimeMillis() - ts > emaPeriod * 60000) {
			ts = emaRate2*ts + emaRate*System.currentTimeMillis();
			count = emaRate2*count;
		}
	}

	/**
	 * Polls the per minute exponential moving average rate. Thread-safe.
	 * 
	 * @return The per minute exponential moving average rate.
	 * @since 0.0.1
	 */
	public synchronized double pollPS(){
		tock();
		return count * 60000 / (System.currentTimeMillis()-ts);
	}
	
	/**
	 * @since 0.0.2
	 */
	@Override
	public int hashCode() {
		final int prime = 131;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/**
	 * @since 0.0.2
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PerMinuteExponentialRateCounter))
			return false;
		PerMinuteExponentialRateCounter other = (PerMinuteExponentialRateCounter) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
