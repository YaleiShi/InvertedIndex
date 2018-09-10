package cs601.project1;
public class AmazonReview extends AmazonMessage implements Comparable<AmazonReview> {
	protected String score;
	protected String text;
	protected String reviewID;
	
	public AmazonReview(String asin, String score, String text, String reviewID) {
		super(asin);
		this.score = score;
		this.text = text;
		this.reviewID = reviewID;
		this.calculateFrequency();
	}

	@Override
	public void calculateFrequency() {
		// TODO Auto-generated method stub
		String[] args = text.split(" ");
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

	public String getScore() {
		return score;
	}

	public String getText() {
		return text;
	}

	public String getReviewID() {
		return reviewID;
	}

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
