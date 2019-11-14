/**
 * Author: Michelle Chen 
 * Date: 10/27/2019 
 * Program Name: Chen_module8_unit_test
 * Purpose: Unit testing the word occurrence class for errors
 */
package application;


import static org.junit.jupiter.api.Assertions.*;
import java.net.HttpURLConnection;
import org.junit.jupiter.api.Test;


class Chen_module8_unit_test {

	//Call the program object and check if the URL exists
	@Test
	void test() throws Exception {
		Chen_module7_word_occurrence wo = new Chen_module7_word_occurrence();
		
		HttpURLConnection huc = (HttpURLConnection) wo.url.openConnection();
		int res = huc.getResponseCode();
		assertEquals(HttpURLConnection.HTTP_OK, res);
	}
	
	//Test a word from word occurrence if it exists
	@Test
	void testWord() throws Exception {
		Chen_module7_word_occurrence wo2 = new Chen_module7_word_occurrence();
		wo2.read();
		assertTrue(wo2.wordCount.containsKey("let"));
	}
	

}
