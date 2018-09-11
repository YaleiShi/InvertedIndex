package cs601.project1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

/**
 * the process class
 * process the json file and save all the data into data base
 * @author yalei
 *
 */
public class AmazonProcessor {
	private InvertedIndex forReview;
	private InvertedIndex forQA;
	private String inputReview;
	private String inputQA;
	
	/**
	 * constructor of the processor
	 * call the getArgs method to get the args
	 * @param args the args pass into the processor
	 */
	public AmazonProcessor(String[] args) {
		this.forReview = new InvertedIndex();
		this.forQA = new InvertedIndex();
		this.inputReview = null;
		this.inputQA = null;
		this.getArgs(args);
	}
	
	/**
	 * take the args to get the input path and output path
	 * if the args is invalid, return false
	 * @param args args pass into the processor
	 * @return true if args valid, false if not
	 */
	public boolean getArgs(String[] args) {
		if(args.length != 4) return false;
		for(int i = 0; i< 3; i++) {
			if(args[i].equals("-reviews")) this.inputReview = args[i + 1];
			if(args[i].equals("-qa")) this.inputQA = args[i + 1];
		}
		if(this.inputQA == null || this.inputReview == null) return false;
		return true;
	}
	
	/**
	 * process the two json file to get all the data and save into the data base
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void process(){
		this.process(this.inputReview, this.forReview, true, "review");
		this.process(this.inputQA, this.forQA, false, "QA");
	}
	
	/**
	 * process one specific file, get all the data, save into one data base
	 * count the lines read, print it out for debug
	 * @param input the input path
	 * @param index the data base saved into
	 * @param ifReview if parse the data into a review or a QA
	 * @param title the title print out in the console
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void process(String input, InvertedIndex index, boolean ifReview, String title){
		//prepare the parsers
		AmazonParser aParser = new AmazonParser();
		JsonParser parser = new JsonParser();
		int read = 0;
		
		//read the file
		try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(new File(input)));
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"),10 * 1024 * 1024)){
			String line = reader.readLine();
			while(line != null) {
				read++;
				JsonElement element;
				try {
					element = parser.parse(line);
				}catch(JsonSyntaxException jse) {
					line = reader.readLine();
					continue;
				}	
				if(element.isJsonObject()) {
					JsonObject jo = (JsonObject) element;
					AmazonMessage am = aParser.parse(jo, ifReview);
					index.add(am);
				}	
				line = reader.readLine();
			}	
		}catch(FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.println("we read " + title + " lines: " + read);	
	}
	

	/**
	 * get the review index
	 * @return the review index
	 */
	public InvertedIndex getForReview() {
		return forReview;
	}

	/**
	 * get the QA index
	 * @return the QA index
	 */
	public InvertedIndex getForQA() {
		return forQA;
	}
	
	

}
