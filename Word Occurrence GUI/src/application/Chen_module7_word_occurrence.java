//Author Name: Michelle Chen
// Date: 10/19/2019
//Program Name: Chen_module7_word_occurrence
// Purpose: Show results of word occurrence in GUI 
//

/**
 * @author Michelle Chen
 * @version 1.0
 */
package application;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.io.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Chen_module7_word_occurrence extends Application {
	HashMap<String, Integer> wordCount = new HashMap<String, Integer>(); // Using HashMap to hold the words and occurrences
																		
	public static String[] array = new String[3454]; // Placeholder for the words and occurrences to use in JavaFX
	URL url;

	/**
	 * Constructor that set the url value of the chosen website when call
	 * @throws Exception to handle using url functions from MalformedURLException 
	 */
	public Chen_module7_word_occurrence() throws Exception {
		url = new URL("http://shakespeare.mit.edu/macbeth/full.html");
	}

	/**
	 * Read the url contents by each line and store it in a string array
	 * @throws Exception for IOException to get input from the url to an array variable.
	 */
	public void read() throws Exception{
		// Using BufferedReader to read the link
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		int i = 0;
		// Reading the URL link by each line
		while ((inputLine = in.readLine()) != null) {
			if (i >= 10) {
				inputLine = inputLine.replaceAll("<[^>]*>", ""); // Removing all HTML tags
				String[] text = inputLine.toLowerCase().split(" "); // Split the line into words using a delimiter and
																	// changing them to lowercase
				for (String t : text) {
					t = t.replaceAll(",|!|;|:|\\.|\\?|--", ""); // Removing punctuation at the end of words
					// Putting each words in the map
					if (wordCount.containsKey(t)) {
						wordCount.put(t, wordCount.get(t) + 1);
					} else {
						wordCount.put(t, 1);
					}

				}
			}
			i++;
		}
		in.close();
	}

	/**
	 * Sorts the words that is collected from the read method in decreasing order
	 */
	public void sort() {

		Set<Entry<String, Integer>> entrySet = wordCount.entrySet(); // Using set to put in the entries of wordCount
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(entrySet); // Put the entry in a list
		// Using collection to sort the list in decreasing order
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				return (e2.getValue().compareTo(e1.getValue()));
			}
		});

		// Printing the repeated words along with their occurrences

		for (int i = 1; i <= 3454; i++) {
			String s = list.get(i).toString();
			array[i - 1] = s;
		}
	}

	/**
	 * This method is use to put together the scene and stage of the GUI program
	 * and display the words compiled into a list from the previous methods 
	 * @param primaryStage The first parameter to the start method
	 * @exception Exception to handle two types of exception from the constructor and read method
	 */
	public void start(Stage primaryStage) throws Exception {
		// Call the word occurrence functions to read and sort the words.
		Chen_module7_word_occurrence wo = new Chen_module7_word_occurrence();
		wo.read();
		wo.sort();

		// Create a ListView UI to display text
		ListView<String> listView = new ListView<String>();
		// Add the words and occurrences into the List View from the array.
		for (int i = 0; i < 3454; i++) {
			System.out.println(array[i]);
			listView.getItems().add(array[i]);
		}

		// Creates the UI display scene
		HBox hbox = new HBox(listView);
		Scene scene = new Scene(hbox, 300, 500);
		primaryStage.setTitle("Word Occurrence");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * This is the main method that launch the GUI
	 * @param args use for launching the functions of JavaFX GUI
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
