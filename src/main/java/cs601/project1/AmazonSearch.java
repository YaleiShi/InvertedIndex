package cs601.project1;

public class AmazonSearch {

	public static void main(String[] args) {
		String[] input = {"-reviews", "reviews_Cell_Phones_and_Accessories_5.json", "-qa", "qa_Cell_Phones_and_Accessories.json"};
		AmazonProcessor ap = new AmazonProcessor(input);
		ap.process();
		AmazonAnswer aa = new AmazonAnswer(ap.getForReview(), ap.getForQA());
		aa.find("120401325X");
	}
}
