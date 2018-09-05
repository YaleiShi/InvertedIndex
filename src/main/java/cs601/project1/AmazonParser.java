package cs601.project1;

import com.google.gson.JsonObject;

public class AmazonParser {
	
	public AmazonMessage parse(JsonObject jo, boolean ifReview) {
		AmazonMessage am;
		if(ifReview) {
			am = this.parseReview(jo);
		}else {
			am = this.parseQA(jo);
		}
		return am;
	}
	
	public AmazonMessage parseReview(JsonObject jo) {
		String asin = jo.get("asin").getAsString();
		String score = jo.get("overall").getAsString();
		String text = jo.get("reviewText").getAsString();
		String reviewId = jo.get("reviewerID").getAsString();
		AmazonMessage am = new AmazonReview(asin, score, text, reviewId);
		return am;
	}
	
	public AmazonMessage parseQA(JsonObject jo) {
		String asin = jo.get("asin").getAsString();
		String question = jo.get("question").getAsString();
		String answer = jo.get("answer").getAsString();
		AmazonMessage am = new AmazonQA(asin, question, answer);
		return am;
	}

}
