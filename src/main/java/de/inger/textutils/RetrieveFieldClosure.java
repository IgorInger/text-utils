package de.inger.textutils;

/**
 * Retrieve field closure.
 * 
 * @author Igor Inger
 * 
 */
public interface RetrieveFieldClosure {

	/**
	 * Retrieves readable text from given object.
	 * 
	 * @param o
	 *            object
	 * @return readable text
	 */
	String retrieveField(Object o);

}
