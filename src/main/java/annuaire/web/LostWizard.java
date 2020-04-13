package annuaire.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LostWizard implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long userId ;
	
	private boolean answerQuestion ;
	
	private boolean newPassword ;


	@PostConstruct
	public void clear(){
		userId = null;
		answerQuestion = false;
		newPassword = false;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isAnswerQuestion() {
		return answerQuestion;
	}

	public void setAnswerQuestion(boolean answerQuestion) {
		this.answerQuestion = answerQuestion;
	}
	
	public boolean isNewPassword() {
		return newPassword;
	}

	public void setNewPassword(boolean newPassword) {
		this.newPassword = newPassword;
	}
	
}
