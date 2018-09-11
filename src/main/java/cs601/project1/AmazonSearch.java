package cs601.project1;

import java.sql.Time;

public class AmazonSearch {

	public static void main(String[] args) {
		AmazonProcessor ap = new AmazonProcessor(args);
		if(!ap.getArgs(args)) {
			System.out.println("input is not valid");
			return;
		}
		
		long start = System.currentTimeMillis();
		ap.process();
		long end = System.currentTimeMillis();
		System.out.println("Loading Time: " + (end - start));
		
		AmazonAnswer aa = new AmazonAnswer(ap.getForReview(), ap.getForQA());
		aa.answer();
	}
}
