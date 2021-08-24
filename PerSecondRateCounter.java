package com.dz_fs_dev.common;

/**
 * Thread-safe Per Second Rate Counter which employs an Exponential Moving Average with a period defined on construction.
 * @since 15
 * @version 0.1
 * @author DZ-FSDev
 *
 */

public class PerSecondRateCounter {
	private final int emaPeriod;
	private final double emaRate, emaRate2;
	private double ts;
	private double count = 0;

	/**
	 * Constructs the Per Second Rate Counter with an Exponential Moving Average.
	 * @throws IllegalArgumentException Thrown when emaPeriod is less than 2.
	 * @since 15
	 * @param emaPeriod 
	 */
	public PerSecondRateCounter(int emaPeriod){
		if(emaPeriod < 2)throw new IllegalArgumentException("emaPeriod cannot be less than 2.");
		this.emaPeriod = emaPeriod;
		this.emaRate = 2.0 / (emaPeriod + 1.0);
		this.emaRate2 = 1.0 - this.emaRate;
		this.ts = System.currentTimeMillis();
	}

	/**
	 * Increments the counter by 1; thread-safe.
	 * @since 15
	 */
	public synchronized void tick(){
		count ++;
		tock();
	}
	
	/**
	 * Increments the counter by a custom amount passed as a parameter; thread-safe.
	 * @since 15
	 * @param amount The amount to be incremented in the counter. Supports decimals and negative numbers.
	 */
	public synchronized void tick(double amount){
		count += amount;
		tock();
	}
	
	/**
	 * Private helper method to calculate the Exponential Moving Average
	 * @since 15
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
	 * @since 15
	 * @return The per second exponential moving average rate.
	 */
	public synchronized double pollPS(){
		tock();
		return count * 1000 / (System.currentTimeMillis()-ts);
	}
}
