package cs601.project1;

import java.util.Comparator;

public class ScoreComparator implements Comparator<AmazonMessage>{

	@Override
	public int compare(AmazonMessage o1, AmazonMessage o2) {
		// TODO Auto-generated method stub
		AmazonReview ar1 = (AmazonReview) o1;
		AmazonReview ar2 = (AmazonReview) o2;
		return ar1.getScore().compareTo(ar2.getScore());
	}

}
