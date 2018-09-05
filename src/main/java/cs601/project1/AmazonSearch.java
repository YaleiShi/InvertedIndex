package cs601.project1;

import java.sql.Time;

public class AmazonSearch {

	public static void main(String[] args) {
		String[] input = {"-reviews", "reviews_Cell_Phones_and_Accessories_5.json", "-qa", "qa_Cell_Phones_and_Accessories.json"};
		AmazonProcessor ap = new AmazonProcessor(input);
		long start = System.currentTimeMillis();
		ap.process();
		long end = System.currentTimeMillis();
		System.out.println("Runing Time: " + (end - start));
		AmazonAnswer aa = new AmazonAnswer(ap.getForReview(), ap.getForQA());
		aa.answer();
	}
}
