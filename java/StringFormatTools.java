package com.dz_fs_dev.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Various String formatting tools.
 * 
 * @author DZ-FSDev
 * @since 16
 * @version 0.0.3
 */
public final class StringFormatTools{
	/**
	 * Encodes a string into a UTF 8 charset URI
	 * 
	 * @param string The string to be encoded as a uri.
	 * @return The encoded uri.
	 * @throws UnsupportedEncodingException Thrown if UTF 8 is unsupported.
	 * @since 0.0.1
	 */
	public static String uriEncode(String string) throws UnsupportedEncodingException {
	    return URLEncoder.encode(string,  StandardCharsets.UTF_8);
	}
	
	/**
	 * Decodes a UTF 8 charset encoded URI to its String representation
	 * 
	 * @param encoded The encoded uri to be decoded.
	 * @return The decoded uri.
	 * @throws UnsupportedEncodingException Thrown if UTF 8 is unsupported.
	 * @since 0.0.1
	 */
	public static String uriDecode(String encoded) throws UnsupportedEncodingException {
	    return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
	}
	
	/**
	 * Adds whitespace padding to the left of a {@link String} up to a specified length. 
	 * 
	 * @param text The text to pad up to a specified length.
	 * @param length The specified length to pad the String up to.
	 * @return The {@link String} padded with whitespace on the left up to the specified length. 
	 * @since 0.0.2
	 */
	public static String leftPad(String text, int length) {
		return String.format("%" + length + "." + length + "s", text);
	}

	/**
	 * Adds whitespace padding to the right of a {@link String} up to a specified length. 
	 * 
	 * @param text The text to pad up to a specified length.
	 * @param length The specified length to pad the String up to.
	 * @return The {@link String} padded with whitespace on the right up to the specified length. 
	 * @since 0.0.2
	 */
	public static String rightPad(String text, int length) {
		return String.format("%-" + length + "." + length + "s", text);
	}

	/**
	 * Adds whitespace padding to the left and right of a {@link String} up to specified lengths. Performs
	 * {@link #leftPad(String, int) leftPad} followed by {@link #rightPad(String, int) rightPad}.
	 * 
	 * @param text The text to pad up to a specified length.
	 * @param leftLength The specified length to pad the String up to.
	 * @param rightLength The specified length to pad the String up to.
	 * @return The {@link String} padded with whitespace on the right up to the specified length.
	 * @throws IllegalArgumentException Thrown if leftLength less than 1 or rightLength less than leftLength.
	 * @since 0.0.3
	 */
	public static String leftRightPad(int leftLength, String text, int rightLength){
		if(leftLength < 1)throw new IllegalArgumentException(String.format("leftLength cannot be less than 1! leftLength = %d", leftLength));
		if(rightLength < leftLength)throw new IllegalArgumentException(String.format("rightLength(%d) cannot be less than leftLength(%d)!", rightLength, leftLength));
		return rightPad(leftPad(text, leftLength),rightLength);
	}
}
