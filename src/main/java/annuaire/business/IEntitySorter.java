package annuaire.business;

import java.util.ArrayList;

import annuaire.model.Group;
import annuaire.model.Person;

public interface IEntitySorter {
	public void sortGroups(ArrayList<Group> groups, String sortType);
	public void sortPersons(ArrayList<Person> persons, String sortType);
}
