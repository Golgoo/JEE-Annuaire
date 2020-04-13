package annuaire.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import annuaire.dao.IPersonDAO;
import annuaire.model.Person;
import annuaire.web.LostWizard;

@Service
public class LostPasswordService implements ILostPasswordService {

	@Autowired
	private LostWizard lostWizard ;
	
	@Autowired
	private IPersonDAO dao ;
	
	private Person getCurrentPerson() {
		if(lostWizard.getUserId() == null) return null;
		return dao.findPerson(lostWizard.getUserId());
	}
	
	@Override
	public String getNextStep() {
		if(lostWizard.getUserId() == null) {
			return "lostWizardEmail";
		}
		if(! lostWizard.isAnswerQuestion()) {
			return "lostWizardQuestion";
		}
		if(! lostWizard.isNewPassword()) {
			return "lostWizardPassword";
		}
		return "lostWizardFinished";	
	}
	
	@Override
	public String getSecretQuestion() {
		Person p = getCurrentPerson();
		if(p == null) return null;
		return p.getSecretQuestion().getQuestion();	
	}

	@Override
	public boolean updatePassword(String newPassword) {
		if(! lostWizard.isAnswerQuestion()) {
			return false;
		}
		Person p = getCurrentPerson();
		if(p == null) return false;
		p.setPassword(newPassword);
		dao.updatePerson(p);
		lostWizard.setNewPassword(true);
		return true;
	}

	@Override
	public boolean updateEmail(String userEmail) {
		Person p = dao.findPerson(userEmail);
		if(p == null) return false;
		lostWizard.setUserId(p.getId());
		return true;
	}

	@Override
	public boolean updateSecretAnswer(String answer) {
		Person p = getCurrentPerson();
		if(p == null) return false;
		if(! p.getSecretQuestion().getAnswer().equals(answer)) return false;
		lostWizard.setAnswerQuestion(true);
		return true;
	}

	@Override
	public void clear() {
		lostWizard.clear();
	}

}
