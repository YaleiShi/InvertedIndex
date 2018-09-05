package cs601.project1;
import java.util.HashMap;

public abstract class AmazonMessage {
	protected String asin;
	protected HashMap<String, Integer> termFrequency;
	
	public AmazonMessage(String asin) {
		this.asin = asin;
		this.termFrequency = new HashMap<String, Integer>();
	}

	public abstract void calculateFrequency();
	
	public int getFreq(String key) {
		return this.termFrequency.get(key);
	}

	public String getAsin() {
		return asin;
	}

	public HashMap<String, Integer> getTermFrequency() {
		return termFrequency;
	}
	
	
}
