package annuaire.business;

import java.util.Collection;

import annuaire.model.Group;
import annuaire.model.Person;
import annuaire.web.User;


public interface IDirectoryManager {
	
	// créer un utilisateur anonyme
    public User getUser();

    // chercher une personne
    public Person findPerson(long personId);

    // chercher un groupe
    public Group findGroup(long groupId);

    // chercher les personnes d'un groupe
    public Collection<Person> findAllPersonsFromGroup(long groupId);
    
    public Collection<Person> findAllPersons();
    
    public Collection<Group> findAllGroup();

    // identifier un utilisateur
    public boolean login(long personId, String password);
    
    // oublier l'utilisateur
    public void logout();

    // enregistrer une personne
    public void savePerson(Person p);

	public Collection<Group> findAllGroup(String pattern);

	public Collection<Person> findAllPersons(String pattern);

}
