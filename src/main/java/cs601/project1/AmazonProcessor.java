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

public class AmazonProcessor {
	private InvertedIndex forReview;
	private InvertedIndex forQA;
	private String inputReview;
	private String inputQA;
	
	public AmazonProcessor(String[] args) {
		this.forReview = new InvertedIndex();
		this.forQA = new InvertedIndex();
		this.inputReview = null;
		this.inputQA = null;
		this.getArgs(args);
	}
	
	public boolean getArgs(String[] args) {
		if(args.length != 4) return false;
		for(int i = 0; i< 3; i++) {
			if(args[i].equals("-reviews")) this.inputReview = args[i + 1];
			if(args[i].equals("qa")) this.inputQA = args[i + 1];
		}
		if(this.inputQA == null || this.inputReview == null) return false;
		return true;
	}
	
	public void process() {
		AmazonParser aParser = new AmazonParser();

		JsonParser parser = new JsonParser();
		
		try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(new File(this.inputReview)));
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"),10 * 1024 * 1024)){
			String line = reader.readLine();
			JsonElement element = parser.parse(line);	
			if(element.isJsonObject()) {
				JsonObject jo = (JsonObject) element;
				AmazonMessage am = aParser.parse(jo, true);
				this.forReview.add(am);
			}	
				
				
		}catch(FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}catch(JsonSyntaxException jse) {
			System.out.println(jse.getMessage());
		}
		
		try(BufferedInputStream stream = new BufferedInputStream(new FileInputStream(new File(this.inputQA)));
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"),10 * 1024 * 1024)){
				String line = reader.readLine();
				JsonElement element = parser.parse(line);	
				if(element.isJsonObject()) {
					JsonObject jo = (JsonObject) element;
					AmazonMessage am = aParser.parse(jo, false);
					this.forQA.add(am);
				}	
			}catch(FileNotFoundException fnfe) {
				System.out.println(fnfe.getMessage());
			}catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}catch(JsonSyntaxException jse) {
				System.out.println(jse.getMessage());
			}
		
	}

	public InvertedIndex getForReview() {
		return forReview;
	}

	public InvertedIndex getForQA() {
		return forQA;
	}
	
	

}
