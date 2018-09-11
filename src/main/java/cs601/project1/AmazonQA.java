package cs601.project1;

/**
 * the data structure to store the qa
 * @author yalei
 *
 */
public class AmazonQA extends AmazonMessage implements Comparable<AmazonQA>{
	protected String question;
	protected String answer;
	
	/**
	 * constructor of qa
	 * @param asin the asin number
	 * @param question the text of question
	 * @param answer the answer of the question
	 */
	public AmazonQA(String asin, String question, String answer) {
		super(asin);
		this.question = question;
		this.answer = answer;
		this.calculateFrequency();
	}

	/**
	 * go through the question and answer to calculate the word frequency
	 */
	@Override
	public void calculateFrequency() {
		// TODO Auto-generated method stub
		this.calculateFrequency(this.question);
		this.calculateFrequency(this.answer);
	}
	
	/**
	 * the function used to calculate the input text words frequency
	 * @param input the text used to calculate
	 */
	public void calculateFrequency(String input) {
		String[] args = input.split("\\s+");
		
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
	 * get the text of question
	 * @return the question text
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * get the text of answer
	 * @return the answer text
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * compare two AmazonQA
	 */
	@Override
	public int compareTo(AmazonQA o) {
		// TODO Auto-generated method stub
		if(this.asin.compareTo(o.asin) != 0) return this.asin.compareTo(o.asin);
		if(this.answer.compareTo(o.answer) != 0) return this.answer.compareTo(o.answer);
		return this.question.compareTo(o.question);
	}
	
	

}
