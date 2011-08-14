package de.inger.textutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

/**
 * Text utility.
 * 
 * @author Igor Inger
 *
 */
public abstract class TextUtils {
	
	/**
	 * Sole constructor.
	 */
	protected TextUtils() {		
	}

	/**
	 * Accurate (each chunk will be trimmed) split a string into a chunks list.<br/>
	 * 
	 * <code>"a  , b, c, Foo,bar,  " -> {"a", "b", "c", "Foo", "bar"}</code>
	 * 
	 * @param list list
	 * @param delimiter delimiter
	 * @return chunks list.
	 */
	public static List<String> splitAccurate(String list, String delimiter) {
		String[] chunksArray = list.split(delimiter);
		List<String> chunksList = new ArrayList<String>(Arrays.asList(chunksArray));
		Transformer transformer = new Transformer() {
			@Override
			public Object transform(Object input) {
				return ((String) input).trim();
			}
		};
		CollectionUtils.transform(chunksList, transformer);
		Predicate predicate = new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				String text = (String) object;
				if (text.equals("")) {
					return false;
				}
				return true;
			}
		};
		CollectionUtils.filter(chunksList, predicate);
		return chunksList;
	}
	
	/**
	 * Accurate (each chunk will be trimmed) split a string into a chunks list.<br/>
	 * 
	 * "a  , b, c, Foo,bar,  " -> {"a", "b", "c", "Foo", "bar"}
	 * 
	 * @param list - list
	 * @param delimiter - delimiter
	 * @return chunks list.
	 */	
	public static List<String> parseList(String list, String delimiter) {
		return splitAccurate(list, delimiter);
	}

	/**
	 * Generates a string list from chunks.<br/>
	 * 
	 * {"1", "3", "foo"} -> "1, 3, foo" 
	 * 
	 * @param list - chunks.
	 * @param delimeter - delimiter.
	 * @return parsed text.
	 */
	public static String generateList(List<?> list, String delimeter) {
		return generateList(list, delimeter, null);
	}

	/**
	 * Generates a string list from chunks.<br/>
	 * 
	 * {"1", "3", "foo"} -> "1, 3, foo" 
	 * 
	 * @param list - chunks.
	 * @param delimeter - delimiter.
	 * @param closure - closure method to convert an object to readable string.
	 * @return parsed text.
	 */	
	public static String generateList(List<?> list, String delimeter, RetrieveFieldClosure closure) {
		StringBuilder builder = new StringBuilder();
		boolean secondObject = false;
		for (Object obj : list) {
			if (secondObject) {
				builder.append(delimeter);
			}
			if (closure == null) {
				builder.append(obj);
			} else {
				String field = closure.retrieveField(obj);
				builder.append(field);
			}
			secondObject = true;
		}
		return builder.toString();
	}
	
	/**
	 * Capitalize given word.<br/> 
	 * "igor" -> "Igor"<br/>
	 * "Igor Inger" -> "Igor inger"<br/>
	 * "IGOR INGER" -> "Igor inger"
	 * 
	 * @param word given word.
	 * @return capitalized word.
	 */
	public static String capitalize(final String word) {
		return capitalize(word, Locale.getDefault());
	}

	/**
	 * Capitalize given word with default locale.<br/> 
	 * "igor" -> "Igor"<br/>
	 * "Igor Inger" -> "Igor inger"<br/>
	 * "IGOR INGER" -> "Igor inger"
	 * 
	 * @param word given word.
	 * @param locale given locale.
	 * @return capitalized word.
	 */	
	public static String capitalize(final String word, Locale locale) {
		String temp = word;
		temp = temp.toLowerCase(locale);
		char c = temp.charAt(0);
		String s = new String(new char[]{c});
		s = s.toUpperCase(locale);
		temp = temp.substring(1);
		temp = s.concat(temp);
		return temp;
	}
	
	
	
	

}
