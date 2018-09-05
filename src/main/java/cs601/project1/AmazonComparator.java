package cs601.project1;
import java.util.Comparator;

public class AmazonComparator implements Comparator<AmazonMessage> {
	String key;
	
	public AmazonComparator(String key) {
		this.key = key;
	}
	@Override
	public int compare(AmazonMessage o1, AmazonMessage o2) {
		// TODO Auto-generated method stub
		return o1.getFreq(key) - o2.getFreq(key);
	}

}
