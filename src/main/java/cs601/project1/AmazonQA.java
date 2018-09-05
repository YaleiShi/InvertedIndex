package cs601.project1;

public class AmazonQA extends AmazonMessage {
	protected String question;
	protected String answer;
	
	public AmazonQA(String asin, String question, String answer) {
		super(asin);
		this.question = question;
		this.answer = answer;
		this.calculateFrequency();
	}

	@Override
	public void calculateFrequency() {
		// TODO Auto-generated method stub
		String[] argsQ = this.question.split(" ");
		String[] argsA = this.answer.split(" ");
		
		for(String s: argsQ) {
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
		
		for(String s: argsA) {
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

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	

}
