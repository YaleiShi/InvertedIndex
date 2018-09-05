package cs601.project1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class AmazonAnswer {
	private InvertedIndex forReview;
	private InvertedIndex forQA;
	
	public AmazonAnswer(InvertedIndex forReview, InvertedIndex forQA) {
		this.forQA = forQA;
		this.forReview = forReview;
	}
	
	
	public void answer() {
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		while(true) {
			if(input == null) {
				input = s.nextLine();
			}else if(input.equals("exit")) {
				System.exit(1);
			}else {
				this.request(input);
				input = s.nextLine();
			}
		}
//		while(true) {
//			try(Scanner reader = new Scanner(System.in)){
//				while(!reader.hasNextLine()) {}
//				String input = reader.nextLine();
//				if(input.equals("exit")) {
//					break;
//				}else {
//					this.request(input);
//				}
//			}
//		}
	}
	
	public void request(String input) {
		String[] args = input.split(" ");
		if(args.length != 2) {
			System.out.println("invalid args length");
			return;
		}
		if(args[0].equals("find")) {
			System.out.println("go to find");
			this.find(args[1]);
		}else if(args[0].equals("reviewsearch")) {
			System.out.println("go to review");
			this.reviewSearch(args[1]);
		}else if(args[0].equals("qasearch")) {
			System.out.println("go to qa");
			this.qaSearch(args[1]);
		}else if(args[0].equals("reviewpartialsearch")) {
			System.out.println("go to p review");
			this.reviewPartialSearch(args[1]);
		}else if(args[0].equals("qapartialsearch")) {
			System.out.println("go to p qa");
			this.qaPartialSearch(args[1]);
		}else {
			System.out.println("invalid input");
		}
		return;
	}
	
	public void find(String asin) {
		StringBuilder sb = new StringBuilder();
		sb.append("find asin number: " + asin + "\n");
		sb.append("********** Reviews ***********\n");
		HashSet<AmazonMessage> hs = this.forReview.asinIndex.get(asin);
		if(hs == null) {
			sb.append("no review\n");
		}else {
			for(AmazonMessage am: hs) {
				AmazonReview ar = (AmazonReview) am;
				sb.append("Review ID: " + ar.getReviewID() + "\n");
				sb.append("Score: " + ar.getScore() + "\n");
				sb.append("Text: " + ar.getText() + "\n\n");
			}
		}
		

		sb.append("********** QAs ***********\n");
		hs = this.forQA.asinIndex.get(asin);
		if(hs == null) {
			sb.append("no QA\n=======================\n");
		}else {
			for(AmazonMessage am: hs) {
				AmazonQA ar = (AmazonQA) am;
				sb.append("Question: " + ar.getQuestion() + "\n");
				sb.append("Answer: " + ar.getAnswer() + "\n\n");
			}
			sb.append("=========================\n");
		}
		
		System.out.println(sb);
	}
	
	public void reviewSearch(String term) {
		StringBuilder sb = new StringBuilder();
		sb.append("search review by the word: " + term + "\n");
		sb.append("********** Reviews ***********\n");
		TreeSet<AmazonMessage> ts = this.forReview.termIndex.get(term);
		if(ts == null) {
			sb.append("no review\n========================\n");
		}else {
			for(AmazonMessage am: ts) {
				AmazonReview ar = (AmazonReview) am;
				sb.append("Review ID: " + ar.getReviewID() + "\n");
				sb.append("Score: " + ar.getScore() + "\n");
				sb.append("Text: " + ar.getText() + "\n\n");
			}
			sb.append("=========================\n");
		}

		System.out.println(sb);
	}
	
	public void qaSearch(String term) {
		StringBuilder sb = new StringBuilder();
		sb.append("search QA by the word: " + term + "\n");
		sb.append("********** QAs ***********\n");
		TreeSet<AmazonMessage> ts = this.forQA.termIndex.get(term);
		if(ts == null) {
			sb.append("no QA\n========================\n");
		}else {
			for(AmazonMessage am: ts) {
				AmazonQA ar = (AmazonQA) am;
				sb.append("Question: " + ar.getQuestion() + "\n");
				sb.append("Answer: " + ar.getAnswer() + "\n\n");
			}
			sb.append("=========================\n");
		}

		System.out.println(sb);
	}
	
	public void reviewPartialSearch(String term) {
		StringBuilder sb = new StringBuilder();
		sb.append("partial search review by the word: " + term + "\n");
		sb.append("********** Reviews ***********\n");
		TreeSet<AmazonMessage> ts = new TreeSet<AmazonMessage>(new ScoreComparator());
		for(String s: this.forReview.termIndex.keySet()) {
			if(s.contains(term)) {
				for(AmazonMessage am: this.forReview.termIndex.get(s)) {
					ts.add(am);
				}
			}
		}
		
		if(ts.size() == 0) {
			sb.append("no review\n========================\n");
		}else {
			for(AmazonMessage am: ts) {
				AmazonReview ar = (AmazonReview) am;
				sb.append("Review ID: " + ar.getReviewID() + "\n");
				sb.append("Score: " + ar.getScore() + "\n");
				sb.append("Text: " + ar.getText() + "\n\n");
			}
			sb.append("=========================\n");
		}

		System.out.println(sb);
	}
	
	public void qaPartialSearch(String term) {
		StringBuilder sb = new StringBuilder();
		sb.append("partial search QA by the word: " + term + "\n");
		sb.append("********** QAs ***********\n");
		TreeSet<AmazonMessage> ts = new TreeSet<AmazonMessage>(new AsinComparator());
		for(String s: this.forQA.termIndex.keySet()) {
			if(s.contains(term)) {
				for(AmazonMessage am: this.forQA.termIndex.get(s)) {
					ts.add(am);
				}
			}
		}
		
		if(ts.size() == 0) {
			sb.append("no review\n========================\n");
		}else {
			for(AmazonMessage am: ts) {
				AmazonQA ar = (AmazonQA) am;
				sb.append("Question: " + ar.getQuestion() + "\n");
				sb.append("Answer: " + ar.getAnswer() + "\n\n");
			}
			sb.append("=========================\n");
		}

		System.out.println(sb);
	}
}
