package annuaire.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable()
public class SecretQuestion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String question;
	private String answer;
	
	public SecretQuestion() {
		super();
	}
	
	public SecretQuestion(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
}
