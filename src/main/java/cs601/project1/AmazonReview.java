package cs601.project1;

/**
 * the data structure to store the data from review text
 * @author yalei
 *
 */
public class AmazonReview extends AmazonMessage implements Comparable<AmazonReview> {
	protected String score;
	protected String text;
	protected String reviewID;
	
	/**
	 * constructor of AmazonReview
	 * @param asin the asin number
	 * @param score the overall score
	 * @param text the review text
	 * @param reviewID the review id
	 */
	public AmazonReview(String asin, String score, String text, String reviewID) {
		super(asin);
		this.score = score;
		this.text = text;
		this.reviewID = reviewID;
		//calculate the words frequency
		this.calculateFrequency();
	}

	/**
	 * the function used to calculate all the words frequency
	 * this function would be used in the constructor
	 */
	@Override
	public void calculateFrequency() {
		// TODO Auto-generated method stub
		String[] args = text.split("\\s+");
		for(String s: args) {
			s = s.replaceAll("\\W", "");
			s = s.toLowerCase();
			if(!super.termFrequency.containsKey(s)) {
				super.termFrequency.put(s, 1);
			}else {
				int freq = super.termFrequency.get(s);
				freq++;
				super.termFrequency.put(s, freq);
			}
		}
	}

	/**
	 * get the score
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * get the text
	 * @return the review text
	 */
	public String getText() {
		return text;
	}

	/**
	 * get the review id
	 * @return the review id
	 */
	public String getReviewID() {
		return reviewID;
	}

	/**
	 * the function used to compare two AmazonReview
	 */
	@Override
	public int compareTo(AmazonReview o) {
		// TODO Auto-generated method stub
		AmazonReview o2 = (AmazonReview) o;
		if(this.score.compareTo(o2.score) != 0) return this.score.compareTo(o2.score);
		if(this.asin.compareTo(o2.asin) != 0) return this.asin.compareTo(o2.asin);
		if(this.reviewID.compareTo(o2.reviewID) != 0) return this.reviewID.compareTo(o2.reviewID);
		return this.text.compareTo(o2.text);
	}

	
}
