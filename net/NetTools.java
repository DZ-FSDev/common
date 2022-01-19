/*  Original Licensing Copyright
 * 
 *  Common network IO and manipulation tools.
 *  Copyright (C) 2021  DZ-FSDev
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.dz_fs_dev.common.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Utility class containing common network IO and manipulation tools.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.2
 */
public final class NetTools {
	private NetTools() {}
	
	/**
	 * Attempts a GET request and serializes the response to return as a 
	 * {@link org.json.JSONObject}. If any step fails, a null value is
	 * returned.
	 * 
	 * @param url The URL to send a GET request.
	 * @return The serialized {@link org.json.JSONObject}.
	 * @since 0.0.2
	 */
	@SuppressWarnings("finally")
	public static JSONObject tryReadJSONFromUrl(String url) {
		InputStream myInputStream = null;
		JSONObject json = null;
		try {
			myInputStream = new URL(url).openStream();
			BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(myInputStream, StandardCharsets.UTF_8));
			StringBuilder contents = new StringBuilder();
			String data = myBufferedReader.readLine();
			while(data != null){
				contents.append(data);
				contents.append('\n');
				data = myBufferedReader.readLine();
			}
			json = new JSONObject(contents.toString());
		}finally {
			try {
				myInputStream.close();
			} finally {
				return json;
			}
		}
	}
	
	/**
	 * Attempts a GET request and serializes the response to return as a 
	 * {@link org.json.JSONArray}. If any step fails, a null value is
	 * returned.
	 * 
	 * @param url The URL to send a GET request.
	 * @return The serialized {@link org.json.JSONArray}.
	 * @since 0.0.2
	 */
	@SuppressWarnings("finally")
	public static JSONArray tryReadJSONArrayFromUrl(String url) {
		InputStream myInputStream = null;
		JSONArray json = null;
		try {
			myInputStream = new URL(url).openStream();
			BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(myInputStream, StandardCharsets.UTF_8));
			StringBuilder contents = new StringBuilder();
			String data = myBufferedReader.readLine();
			while(data != null){
				contents.append(data);
				contents.append('\n');
				data = myBufferedReader.readLine();
			}
			json = new JSONArray(contents.toString());
		}finally {
			try {
				myInputStream.close();
			} finally {
				return json;
			}
		}
	}
}
