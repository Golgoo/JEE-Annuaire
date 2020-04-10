package annuaire.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import annuaire.model.Group;
import annuaire.model.Person;

@Component
public class DataLoader {
	
	@Autowired
	@Qualifier("dao_v1.0")
	private IPersonDAO dao;
	
	private List<String> names = Arrays.asList(
							"Fareignant",
							"Beth",
							"Fenope",
							"Aradol",
							"Saquet",
							"Smith"
						);
	
	private List<String> firstNames = Arrays.asList(
							"John",
							"Jane",
							"Jack",
							"Daniels",
							"Williams"
						);
	
	public static final int nbPersons = 100 ;
	
	private List<Group> groups = new LinkedList<Group>();
	private final int nbGroup = 10 ;
	private final int seed = 22 ;
	private Random r = new Random(seed);
	
	private String selectRandomName() {
		return names.get(r.nextInt(names.size()));
	}
	private String selectRandomFirstName() {
		return firstNames.get(r.nextInt(firstNames.size()));
	}
	
	@PostConstruct
	public void loadData() {
		createGroups();
		createPersons();
	}
	
	private void createGroups() {
		for(int i = 0 ; i < nbGroup ; i ++) {
			Group g = new Group();
			g.setNom(selectRandomName() + "Groupe" + selectRandomName() + i );
			groups.add(g);
			dao.saveGroup(g);
		}
	}

	private void createPersons() {
		for(int i = 0 ; i < nbPersons ; i ++) {
			StringBuffer nameBuffer = new StringBuffer();
			nameBuffer.append(selectRandomName());
			nameBuffer.append("-" + selectRandomName());
			StringBuffer firstNameBuffer = new StringBuffer();
			firstNameBuffer.append(selectRandomFirstName());
			firstNameBuffer.append("-" + selectRandomFirstName());
			Person p = new Person();
			p.setBirthDay(new Date(r.nextInt(20000)));
			p.setEmail("mail@"+i);
			p.setFirstName(firstNameBuffer.toString());
			p.setLastName(nameBuffer.toString());
			p.setPassword("pass" + i);
			p.setGroup(groups.get(r.nextInt(groups.size())));
			dao.savePerson(p);
		}
	}
	
}
