package annuaire.business;

/*
 * Utiliser un service pour MAJ lostWizard => 
 * Ce service retourne vrai ou faux pour dire si l'opération de récupération à avancé d'une étape ou non.
 * Il propose une fonction getNextStep qui retourne une String correspondant au prochain formulaire à remplir pour anvacer d'une étape
 * 
*/
public interface ILostPasswordService {
	public String getNextStep();
	
	public boolean updatePassword(String newPassword);
	
	public boolean updateEmail(String userEmail);
	
	public boolean updateSecretAnswer(String answer);

	public String getSecretQuestion();

	public void clear();
}
