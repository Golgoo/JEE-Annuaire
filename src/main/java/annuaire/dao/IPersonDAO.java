package annuaire.dao;

import java.util.Collection;

import org.springframework.stereotype.Service;

import annuaire.model.Group;
import annuaire.model.Person;

@Service
public interface IPersonDAO {
	// récupérer les groupes
	Collection<Group> findAllGroups();

	// récupérer les personnes
	Collection<Person> findAllPersonsFromGroup(long groupId);

	Collection<Person> findAllPersons();
	
	
	// lire une personne
	Person findPerson(long id);
	
	Person findPerson(String email);

	// modification ou ajout d'une nouvelle personne
	void savePerson(Person p);
	
	void updatePerson(Person p);
	
	Group findGroup(long id);

	// modification ou ajout d'une nouvelle personne
	void saveGroup(Group g);
	
	// Sauvegarde un groupe
	void updateGroup(Group g);

	// Vide la base de données
	void clearAll();

	Collection<Group> findAllGroups(String pattern);

	Collection<Person> findAllPersons(String pattern);


	

	
}
