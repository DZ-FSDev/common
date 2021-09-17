package com.dz_fs_dev.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Various String formatting tools.
 * 
 * @author DZ-FSDev
 * @version 0.0.1
 */
public final class StringFormatTools {
	/**
	 * Encodes a string into a UTF 8 charset URI
	 * 
	 * @param string The string to be encoded as a uri.
	 * @return The encoded uri.
	 * @throws UnsupportedEncodingException Thrown if UTF 8 is unsupported.
	 * @since 16
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
	 * @since 16
	 */
	public static String uriDecode(String encoded) throws UnsupportedEncodingException {
	    return URLDecoder.decode(encoded, StandardCharsets.UTF_8);
	}
}
