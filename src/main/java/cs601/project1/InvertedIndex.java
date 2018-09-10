package cs601.project1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class InvertedIndex {
	public HashMap<String, TreeMap<Integer, ArrayList<AmazonMessage>>> termIndex;
	public HashMap<String, ArrayList<AmazonMessage>> asinIndex;
	
	public InvertedIndex() {
		this.termIndex = new HashMap<String, TreeMap<Integer, ArrayList<AmazonMessage>>>();
		this.asinIndex = new HashMap<String, ArrayList<AmazonMessage>>();
	}
	
	public void add(AmazonMessage message) {
		//add it into the termIndex by the hash map inside of it
		for(String s: message.getTermFrequency().keySet()) {
			if(!this.termIndex.containsKey(s)) {
				ArrayList<AmazonMessage> al = new ArrayList<AmazonMessage>();
				al.add(message);
				TreeMap<Integer, ArrayList<AmazonMessage>> tm = new TreeMap<Integer, ArrayList<AmazonMessage>>(new IntComparator());
				tm.put(message.getFreq(s), al);
				this.termIndex.put(s, tm);
			}else {
				if(!this.termIndex.get(s).containsKey(message.getFreq(s))) {
					ArrayList<AmazonMessage> al = new ArrayList<AmazonMessage>();
					al.add(message);
					this.termIndex.get(s).put(message.getFreq(s), al);
				}else {
					this.termIndex.get(s).get(message.getFreq(s)).add(message);
				}
			}
		}
		
		//add it into the asinIndex by the asin inside of the message
		String asin = message.getAsin();
		if(!this.asinIndex.containsKey(asin)) {
			ArrayList<AmazonMessage> al = new ArrayList<AmazonMessage>();
			al.add(message);
			this.asinIndex.put(asin, al);
		}else {
			this.asinIndex.get(asin).add(message);
		}
	}
	
	public void print() {
		System.out.println("Term List: ");
		for(String s: this.termIndex.keySet()) {
			System.out.println("word: " + s + "; documents: ");
			for(int i: this.termIndex.get(s).keySet()){
				System.out.print("\tfreq: " + i);
				for(AmazonMessage am: this.termIndex.get(s).get(i)) {
					System.out.print(" " + am.getAsin());
				}
				System.out.println();
			}
		}
		
		System.out.println("Asin List: ");
		for(String s: this.asinIndex.keySet()) {
			System.out.print("word: " + s + "; documents: ");
			ArrayList<AmazonMessage> ts = this.asinIndex.get(s);
			for(AmazonMessage am: ts) {
				System.out.print(am.getAsin() + " ");
			}
			System.out.println();
		}
	}
	
	public class IntComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2 - o1;
		}
		
	}

}
