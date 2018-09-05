package cs601.project1;
public class AmazonReview extends AmazonMessage {
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

	
}
