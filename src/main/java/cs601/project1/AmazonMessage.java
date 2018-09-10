package cs601.project1;
import java.util.HashMap;

/**
 * the parent class of amazon qa and review
 * @author yalei
 *
 */
public abstract class AmazonMessage {
	protected String asin;
	protected HashMap<String, Integer> termFrequency;
	
	/**
	 * take the asin to construct
	 * @param asin the asin number of the message
	 */
	public AmazonMessage(String asin) {
		this.asin = asin;
		this.termFrequency = new HashMap<String, Integer>();
	}

	/**
	 * calculate all the words frequency of the text
	 */
	public abstract void calculateFrequency();
	
	/**
	 * get the key word frequency
	 * @param key
	 * @return
	 */
	public int getFreq(String key) {
		return this.termFrequency.get(key);
	}

	/**
	 * get the asin
	 * @return
	 */
	public String getAsin() {
		return asin;
	}

	/**
	 * get the frequency hash map
	 * @return
	 */
	public HashMap<String, Integer> getTermFrequency() {
		return termFrequency;
	}
	
	/**
	 * print out the data of the document
	 */
	public void print() {
		for(String s: this.termFrequency.keySet()) {
			System.out.println("word: " + s + " times: " + this.termFrequency.get(s));
		}
	}
	
}
