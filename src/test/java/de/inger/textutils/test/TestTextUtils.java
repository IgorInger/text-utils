package de.inger.textutils.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.inger.textutils.TextUtils;

import static org.junit.Assert.assertEquals;

/**
 * Main Test class.
 * 
 * @author Igor Inger
 * 
 */
public class TestTextUtils {

	/**
	 * Test function <code>parseList</code>.
	 */
	@Test
	public void testParseList() {
		List<String> list = TextUtils.parseList("", ",");
		assertEquals(0, list.size());

		list = TextUtils.parseList("igor", ",");
		assertEquals(1, list.size());

		list = TextUtils.parseList("    ,    ,   ,  ,,, ,,,      ", ",");
		assertEquals(0, list.size());

		list = TextUtils.parseList("  ,   igor   ,, , , y  x     , silly sally", ",");
		assertEquals("igor", list.get(0));
	}

	/**
	 * Test function <code>generateList</code>.
	 */
	@Test
	public void testGenerateList() {
		List<String> list = new ArrayList<String>();
		list.add("igor");

		String text = TextUtils.generateList(list, ", ");
		assertEquals("igor", text);

		list = new ArrayList<String>();
		list.add("igor");
		list.add("peter harms");

		text = TextUtils.generateList(list, ", ");
		assertEquals("igor, peter harms", text);
	}

	/**
	 * test function <code>capitalize</code>.
	 */
	@Test
	public void testCapitalize() {
		assertEquals("Igor", TextUtils.capitalize("igor"));
		assertEquals("Igor", TextUtils.capitalize("IGOR"));
		assertEquals("Igor", TextUtils.capitalize("iGOR"));
		assertEquals("Igor", TextUtils.capitalize("Igor"));

		assertEquals("Игорь", TextUtils.capitalize("игорь"));
		assertEquals("Игорь", TextUtils.capitalize("иГорь"));
		assertEquals("Игорь", TextUtils.capitalize("Игорь"));
	}

}
