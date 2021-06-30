package com.dzappz.common;

import java.math.BigInteger;

/**
 * @author DzAppz
 * @version 0.1
 */

public final class FinanceDisplayTools {
	private static String[] fmt_suffixes = {""," K"," M"," B"," T"," A"," C"," D"," E"," F"," G"};
	
	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value. Used for linear scaled data in 1D.
	 * @param value	The value that should be placed between the linear line segment. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The point on the line segment which corresponds to the linear position of the value passed.
	 * @since 15.0.2
	 */
	public static int slider(double value, double min_value, double max_value, double startPixel, double endPixel){
		return (int)(startPixel + (endPixel-startPixel)*(value-min_value)/(max_value-min_value));
	}
	
	/**
	 * Calculates the point on a line segment which a value exists given the terminal ends representing a min and max value. Used for logarithmic scaled data in 1D.
	 * @param value	The value that should be placed between the linear line segment. 
	 * @param min_value The value that corresponds to the start of the line segment.
	 * @param max_value The value that corresponds to the end of the line segment.
	 * @param startPixel The start of the line segment.
	 * @param endPixel The end of the line segment.
	 * @return The point on the line segment which corresponds to the logarithmic position of the value passed.
	 * @since 15.0.2
	 */
	public static int logSlider(double value, double min_value, double max_value, double startPixel, double endPixel){
		value=Math.log(value);
		min_value=Math.log(min_value);
		max_value=Math.log(max_value);
		return (int)(startPixel + (endPixel-startPixel)*(value-min_value)/(max_value-min_value));
	}
	
	/**
	 * Formats a double precision value into a string up to a specified precision. Rounds down.
	 * @param d The double precision value to be formatted.
	 * @param prec The number of decimal precision to return.
	 * @return The formatted string representation.
	 * @since 15.0.2
	 */
	public static String formatDbl(double d, int prec) {
		return String.format("%." + prec + "f",d);
	}

	/**
	 * Formats a {@link BigInteger} into more relatable string representation up to a specific precision.
	 * @param bi The {@link BigInteger} to be formatted.
	 * @param prec The precision of the formatted number.
	 * @return The string representation of the formatted number.
	 * @since 15.0.2
	 */
	public static String fmt(BigInteger bi, int prec){
		String ret = bi.toString();
		if(ret.length() < prec)return ret;
		int suf = (ret.length()-1) / 3;
		return ret.substring(0,ret.length()-suf*3) + "." + ret.substring(ret.length()-suf*3,5) + fmt_suffixes[suf];
	}
}
