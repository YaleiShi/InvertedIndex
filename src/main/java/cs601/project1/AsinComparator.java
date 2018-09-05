package cs601.project1;

import java.util.Comparator;

public class AsinComparator implements Comparator<AmazonMessage>{

	@Override
	public int compare(AmazonMessage o1, AmazonMessage o2) {
		// TODO Auto-generated method stub
		return o1.getAsin().compareTo(o2.getAsin());
	}

}
