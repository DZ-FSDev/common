/*  Licensing Copyright
 * 
 *  Wraps an object to allow local persistence on demand.
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
package com.dz_fs_dev.common.io;

import java.io.File;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;

import static com.dz_fs_dev.common.io.FileTools.tryWriteToFile;
import static com.dz_fs_dev.common.io.FileTools.tryReadFile;

/**
 * Wraps an object to allow local persistence on demand.
 * Limitation: Does not handle entity relationships.
 * 
 * @param <T> The type of the wrapped object must implement Serializable.
 * 
 * @author DZ-FSDev
 * @since 17.0.1
 * @version 0.0.5
 */
public class LocalPersistance<T extends Serializable>{
	private T obj;
	private File file;

	/**
	 * The directory all persists will be written to:
	 * {@value LocalPersistance#saveDirectory}.
	 */
	private static final String saveDirectory = "\\data\\";

	/**
	 * Instantiates a new Local Persistence object wrapper. All persisted
	 * objects will be stored to the {@value LocalPersistance#saveDirectory}
	 * directory.
	 * 
	 * @param obj The object to be wrapped.
	 * @param filename The name of the file to save to.
	 */
	public LocalPersistance(T obj, String filename){
		this.setObj(obj);
		this.setFile(new File(saveDirectory + filename));
	}

	/**
	 * Returns the wrapped object.
	 * 
	 * @return The wrapped object.
	 */
	public T getObj() {
		return obj;
	}

	/**
	 * Changes the instance of the wrapped object.
	 * 
	 * @param obj The new specified object to wrap.
	 */
	private void setObj(T obj) {
		this.obj = obj;
	}

	/**
	 * Persists the wrapped object to the
	 * {@value LocalPersistance#saveDirectory} directory.
	 * 
	 * @throws JsonProcessingException Thrown if object serialization failed.
	 */
	public void persist() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(this.getObj());

		tryWriteToFile(this.getFile(), json);
	}

	/**
	 * Returns the file that all perists are written to.
	 * 
	 * @return The file that all perists are written to.
	 */
	private File getFile() {
		return file;
	}

	/**
	 * Set the file that all persists are written to.
	 * 
	 * @param file The file that all persists are written to.
	 */
	private void setFile(File file) {
		this.file = file;
	}

	/**
	 * Attempts to read a persisted object from the disk for a specified
	 * Serializable class.
	 * 
	 * @param <T> The type of the wrapped object must implement Serializable.
	 * @param clazz The specified type of the wrapped object.
	 * @param file The specified file to load from.
	 * @return A new LocalPersistance object wrapping the loaded object.
	 * @throws JsonProcessingException Thrown if deserialization failed.
	 * @throws JsonMappingException Thrown if deserialization failed.
	 * @since 0.0.5
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> LocalPersistance<T> load(Class<T> clazz,
			File file) throws JsonMappingException, JsonProcessingException {
		T obj;		
		String json = tryReadFile(file.getPath());		
		ObjectMapper mapper = new ObjectMapper();
		obj = (T)mapper.readValue(json, clazz.getClass());

		return new LocalPersistance<T>(obj, file.getName());
	}
}
