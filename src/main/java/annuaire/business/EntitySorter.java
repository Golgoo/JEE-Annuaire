package annuaire.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import annuaire.model.Group;
import annuaire.model.Person;

/**
 * 
 * Il existe surement une meilleur conception pour ce service
 * 
 */
@Service
public class EntitySorter implements IEntitySorter{

	private Comparator<Person> comparePersonById = (Person o1, Person o2) -> ((Long)o1.getId()).compareTo((Long)o2.getId());
	
	private Comparator<Person> comparePersonByLastName = (Person o1, Person o2) -> o1.getLastName().compareTo(o2.getLastName());
	
	private Comparator<Person> comparePersonByFirstName = (Person o1, Person o2) -> o1.getFirstName().compareTo(o2.getFirstName());
	
	@Override
	public void sortPersons(ArrayList<Person> persons, String sortType) {
		boolean descendant = false ;
		Comparator<Person> comparator ;
		switch (sortType) {
		case "id_desc":
			descendant = true;
		case "id_asc": 
			comparator = comparePersonById ;
			break;
		
		case "lastName_desc":
			descendant = true ;
		case "lastName_asc":
			comparator = comparePersonByLastName;
			break;

		case "firstName_desc":
			descendant = true ;
		case "firstName_asc":
			comparator = comparePersonByFirstName;
			break;

		default:
			comparator = comparePersonById;
			break;
		}
		Collections.sort(persons, comparator);
		if(descendant) {
			Collections.reverse(persons);
		}
	}
	
	private Comparator<Group> compareGroupById = (Group o1, Group o2) -> ((Long)o1.getId()).compareTo((Long)o2.getId());
	
	private Comparator<Group> compareGroupByName = (Group o1, Group o2) -> o1.getNom().compareTo(o2.getNom());
	
	@Override
	public void sortGroups(ArrayList<Group> groups, String sortType) {
		boolean descendant = false ;
		Comparator<Group> comparator ;
		switch (sortType) {
		case "id_desc":
			descendant = true;
		case "id_asc": 
			comparator = compareGroupById ;
			break;
			
		case "name_desc":
			descendant = true;
		case "name_asc": 
			comparator = compareGroupByName ;
			break;
		
		default:
			comparator = compareGroupById ;
			break;
		}
		Collections.sort(groups, comparator);
		if(descendant) {
			Collections.reverse(groups);
		}
	}


}
