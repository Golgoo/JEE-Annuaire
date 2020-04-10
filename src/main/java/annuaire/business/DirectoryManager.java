package annuaire.business;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
		p.setEmail(null);
		p.setBirthDay(null);
	}

	@Override
	public Person findPerson(long personId) {
		Person p = dao.findPerson(personId);
		if(p == null) {
			return null;
		}
		if(userIsAnonymous()) {
			hideSensibleData(p);
		}
		return p;
	}

	@Override
	public Group findGroup(long groupId) {
		Group g = dao.findGroup(groupId);
		if(g == null) return null;
		if(userIsAnonymous()) {
			for(Person p : g.getMembers()) {
				hideSensibleData(p);
			}
		}
		return g;
	}

	@Override
	public Collection<Person> findAllPersonsFromGroup(long groupId) {
		if(userIsAnonymous()) {
			return null ;
		}
		return dao.findAllPersonsFromGroup(groupId);
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
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void savePerson(Person p) {
		if(p != null && user.getPersonId() != null) {
			if(p.getId() == user.getPersonId()) {
				logger.info("UPDATING !!!");
				dao.updatePerson(p);
			}
		}
	}

	@Override
	public Collection<Group> findAllGroup() {
		Collection<Group> groups = dao.findAllGroups();
		if(userIsAnonymous()) {
			for(Group g : groups) {
				if(g.getMembers() != null) {
					for(Person p : g.getMembers()) {
						hideSensibleData(p);
					}
				}
			}
		}
		return groups;
	}

	@Override
	public Collection<Person> findAllPersons() {
		Collection<Person> persons = dao.findAllPersons();
		if(userIsAnonymous()) {
			for(Person p : persons) {
				hideSensibleData(p);
			}
		}
		return persons;
	}

	@Override
	public Collection<Group> findAllGroup(String pattern) {
		Collection<Group> groups = dao.findAllGroups(pattern);
		if(userIsAnonymous()) {
			for(Group g : groups) {
				if(g.getMembers() != null) {
					for(Person p : g.getMembers()) {
						hideSensibleData(p);
					}
				}
			}
		}
		return groups;
	}

	@Override
	public Collection<Person> findAllPersons(String pattern) {
		Collection<Person> persons = dao.findAllPersons(pattern);
		if(userIsAnonymous()) {
			for(Person p : persons) {
				hideSensibleData(p);
			}
		}
		return persons;
	}

}
