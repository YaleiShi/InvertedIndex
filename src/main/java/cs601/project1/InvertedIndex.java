package cs601.project1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class InvertedIndex {
	public HashMap<String, TreeSet<AmazonMessage>> termIndex;
	public HashMap<String, HashSet<AmazonMessage>> asinIndex;
	
	public InvertedIndex() {
		this.termIndex = new HashMap<String, TreeSet<AmazonMessage>>();
		this.asinIndex = new HashMap<String, HashSet<AmazonMessage>>();
	}
	
	public void add(AmazonMessage message) {
		//add it into the termIndex by the hash map inside of it
		for(String s: message.getTermFrequency().keySet()) {
			if(!this.termIndex.containsKey(s)) {
				TreeSet<AmazonMessage> ts = new TreeSet<AmazonMessage>(new AmazonComparator(s));
				ts.add(message);
				this.termIndex.put(s, ts);
			}else {
				this.termIndex.get(s).add(message);
			}
		}
		
		//add it into the asinIndex by the asin inside of the message
		String asin = message.getAsin();
		if(!this.asinIndex.containsKey(asin)) {
			HashSet<AmazonMessage> hs = new HashSet<AmazonMessage>();
			hs.add(message);
			this.asinIndex.put(asin, hs);
		}else {
			this.asinIndex.get(asin).add(message);
		}
	}

}
