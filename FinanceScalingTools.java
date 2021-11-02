package com.dz_fs_dev.common;

import java.math.BigInteger;

/**
 * Non-contructable class containing different functions used to do pixel & number scaling for financial dashboards and displays.
 * 
 * @author DZ-FSDev
 * @since 16.0.1
 * @version 0.0.5
 */
public final class FinanceScalingTools {
	private FinanceScalingTools() {}
	
	private static String[] magnitudeSuffixes = {""," K"," M"," B"," T"," A"," C"," D"," E"," F"," G"};
	
	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value.
	 * Used for linear scaled data in 1D.
	 * 
	 * @param value	The value that should be placed between the linear line segment. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The point on the line segment which corresponds to the linear position of the value passed.
	 * @since 0.0.1
	 */
	public static int slider(double value, double min_value, double max_value, double startPixel, double endPixel){
		return (int)(startPixel + (endPixel - startPixel) * (value - min_value) / (max_value - min_value));
	}
	
	/**
	 * Calculates the value on a pixel line segment given the terminal ends representing a min and max value.
	 * Used for linear scaled data in 1D.
	 * 
	 * @param position	The point on the line segment which corresponds to the linear position of the value to be calculated. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The value that correlates with the position on a linear line segment.
	 * @since 0.0.3
	 */
	public static double antiSlider(int position, double min_value, double max_value, double startPixel, double endPixel){
		return (position - startPixel) / (endPixel-startPixel) * (max_value - min_value) + min_value;
	}

	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value.
	 * Used for logarithmic scaled data in 1D.
	 * 
	 * @param value	The value that should be placed between the linear line segment. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The point on the line segment which corresponds to the logarithmic position of the value passed.
	 * @since 0.0.1
	 */
	public static int logSlider(double value, double min_value, double max_value, double startPixel, double endPixel){
		value = Math.log(value);
		min_value = Math.log(min_value);
		max_value = Math.log(max_value);
		return (int)(startPixel + (endPixel - startPixel)*(value - min_value)/(max_value - min_value));
	}
	
	/**
	 * Calculates the value on a pixel line segment given the terminal ends representing a min and max value.
	 * Used for logarithmic scaled data in 1D.
	 * 
	 * @param position	The point on the line segment which corresponds to the linear position of the value to be calculated. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The value that correlates with the position on a logarithmic line segment.
	 * @since 0.0.5
	 */
	public static double antiLogSlider(int position, double min_value, double max_value, double startPixel, double endPixel){
		return Math.pow(10, (position - startPixel) * (max_value - min_value) / (endPixel - startPixel) + min_value);
	}

	/**
	 * Formats a double precision value into a string up to a specified precision. Rounds down.
	 * 
	 * @param d The double precision value to be formatted.
	 * @param prec The number of decimal precision to return.
	 * @return The formatted {@link String} representation.
	 * @since 0.0.1
	 */
	public static String formatDbl(double d, int prec) {
		return String.format("%." + prec + "f",d);
	}

	/**
	 * Formats a {@link BigInteger} into more relatable {@link String} representation up to a specific precision.
	 * 
	 * @param bi The {@link BigInteger} to be formatted.
	 * @param prec The precision of the formatted number.
	 * @return The {@link String} representation of the formatted number.
	 * @since 0.0.1
	 */
	public static String formatBigInt(BigInteger bi, int prec){
		String ret = bi.toString();
		if(ret.length() < prec)return ret;
		int suf = (ret.length() - 1) / 3;
		return ret.substring(0, ret.length() - suf * 3) + "." + ret.substring(ret.length() - suf * 3, 5) + magnitudeSuffixes[suf];
	}

	/**
	 * Performs the calculation of numerator divided by denominator and converts the result into a percentage through multiplication of 100.
	 * The result is trimmed to the specified decimal precision by calling {@link #formatDbl(double, int) formatDbl}.
	 * NaN is return if a denominator of zero is passed. No Exception will be thrown.
	 * IMPORTANT: This method uses primitve double calculations. For mission critical applications, use {@link formatBigRatio}
	 * 
	 * @param numerator The numerator of the ratio.
	 * @param denominator The denominator of the ratio.
	 * @param prec The number of decimal precision to return.
	 * @return The formatted {@link String} representation.
	 * @throws IllegalArgumentException Thrown when precision is less than 1
	 * @since 0.0.2
	 */
	public static String formatRatio(double numerator, double denominator, int prec) throws IllegalArgumentException{
		if(denominator == 0)return ""+Double.NaN;
		if(prec < 1)throw new IllegalArgumentException("fmtRatio cannot process a precision(" + prec + ") less than 1!");
		return formatDbl(numerator / denominator * 100.0, prec);
	}
}
