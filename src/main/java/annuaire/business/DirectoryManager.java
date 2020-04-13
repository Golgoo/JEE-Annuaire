package annuaire.business;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import annuaire.dao.IPersonDAO;
import annuaire.model.Group;
import annuaire.model.Person;
import annuaire.web.User;

@Service
public class DirectoryManager implements IDirectoryManager{

	@Autowired
	private User user ;
	
	@Autowired
	private IPersonDAO dao ;
	
	@Override
	public User getUser() {
		return user;
	}
		
	private boolean userIsAnonymous() {
		return user.getPersonId() == null ;
	}
	
	private void hideSensibleData(Person p) {
		if(p!=null) {
			p.setEmail(null);
			p.setBirthDay(null);
		}
	}
	
	private void hideSensibleData(Set<Person> persons ) {
		if(persons != null) {
			for(Person p :persons) {
				hideSensibleData(p);
			}
		}
	}
		
	private void hideSensibleData(Group g) {
		if(g!=null) {
			hideSensibleData(g.getMembers());
		}
	}
	
	private void hideSensibleData(Collection<Group> groups) {
		if(groups != null) {
			for(Group g : groups)
				hideSensibleData(g);
		}
	}

	@Override
	public Person findPerson(long personId) {
		Person p = dao.findPerson(personId);
		if(userIsAnonymous()) {
			hideSensibleData(p);
		}
		return p;
	}

	@Override
	public Group findGroup(long groupId) {
		Group g = dao.findGroup(groupId);
		if(userIsAnonymous()) {
			hideSensibleData(g);
		}
		return g;
	}

	@Override
	public Collection<Person> findAllPersonsFromGroup(long groupId) {
		Set<Person> persons = new LinkedHashSet<Person>(dao.findAllPersonsFromGroup(groupId));
		if(userIsAnonymous()) {
			hideSensibleData(persons);
		}
		return persons;
	}

	@Override
	public boolean login(long personId, String password) {
		Person p = dao.findPerson(personId);
		if(p == null) {
			return false;
		}
		if(p.getPassword().equals(password)) {
			user.setPersonId(personId);
			return true;
		}
		return false;
	}

	@Override
	public void logout() {
		user.setPersonId(null);
	}
	
	@Override
	public void savePerson(Person p) {
		if(p != null && user.getPersonId() != null) {
			if(p.getId() == user.getPersonId()) {
				dao.updatePerson(p);
			}
		}
	}

	@Override
	public Collection<Group> findAllGroup() {
		Collection<Group> groups = dao.findAllGroups();
		if(userIsAnonymous()) {
			hideSensibleData(groups);
		}
		return groups;
	}

	@Override
	public Collection<Person> findAllPersons() {
		Set<Person> persons = new LinkedHashSet<Person>(dao.findAllPersons());
		if(userIsAnonymous()) {
			hideSensibleData(persons);
		}
		return persons;
	}

	@Override
	public Collection<Group> findAllGroup(String pattern) {
		Collection<Group> groups = dao.findAllGroups(pattern);
		if(userIsAnonymous()) {
			hideSensibleData(groups);
		}
		return groups;
	}

	@Override
	public Collection<Person> findAllPersons(String pattern) {
		Set<Person> persons = new LinkedHashSet<Person>(dao.findAllPersons(pattern));
		if(userIsAnonymous()) {
			hideSensibleData(persons);
		}
		return persons;
	}

	@Override
	public Collection<Person> findAllPersonsFromGroup(long id, String pattern) {
		Set<Person> persons = new LinkedHashSet<Person>(dao.findAllPersonsFromGroup(id, pattern));
		if(userIsAnonymous()) {
			hideSensibleData(persons);
		}
		return persons;
	}

}
