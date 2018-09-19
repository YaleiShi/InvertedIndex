package cs601.project1;

import java.sql.Time;

public class AmazonSearch {

	public static void main(String[] args) {
		//process the args, if it is invalid, end the program
		AmazonProcessor ap = new AmazonProcessor(args);
		if(!ap.getArgs(args)) {
			System.out.println("input is not valid");
			return;
		}
		
		//start to process the json file and record the running time
		long start = System.currentTimeMillis();
		ap.process();
		long end = System.currentTimeMillis();
		System.out.println("Loading Time: " + (end - start));
		
		//prepare the answer program to take care of the user request
		AmazonAnswer aa = new AmazonAnswer(ap.getForReview(), ap.getForQA());
		aa.answer();
	}
}
