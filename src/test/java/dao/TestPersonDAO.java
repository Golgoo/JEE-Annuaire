package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import annuaire.dao.IPersonDAO;
import annuaire.dao.SpringDAOConfiguration;
import annuaire.model.Group;
import annuaire.model.Person;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDAOConfiguration.class)
public class TestPersonDAO {

	@Autowired
	@Qualifier("dao_v1.0")
	protected IPersonDAO dao;
		
	@BeforeAll
	public static void startMessage() {
		System.err.println("----------------------------------");
		System.err.println("----------------------------------");
		System.err.println("------- STARTING DAO TEST --------");
		System.err.println("----------------------------------");
		System.err.println("----------------------------------");
	}
	
	private Person createJohn() {
		Person p = new Person();
		p.setBirthDay(new java.sql.Date(12000));
		p.setEmail("johnmail");
		p.setLastName("Jo");
		p.setFirstName("hn");
		p.setPassword("123456");
		return p ;
	}
	
	private Person createJane() {
		Person p = new Person();
		p.setBirthDay(new java.sql.Date(25000));
		p.setEmail("janemail");
		p.setLastName("Ja");
		p.setFirstName("ne");
		p.setPassword("abcdef");
		return p ;
	}
	
	private Group createGroupA() {
		Group g = new Group();
		g.setNom("GroupA");
		return g;
	}
	
	private Group createGroupB() {
		Group g = new Group();
		g.setNom("GroupB");
		return g;
	}
	
	@BeforeEach
	public void clearDb() {
		dao.clearAll();
	}
	
	@Test
	public void testClearDB() {
		Person john = createJohn();
		Person jane = createJane();
		dao.savePerson(john);
		dao.savePerson(jane);
				
		dao.clearAll();
		
		Person oldJohn = dao.findPerson(john.getId());
		Person oldJane = dao.findPerson(jane.getId());
		
		assertEquals(null, oldJane);
		assertEquals(null, oldJohn);
	}

	@Test
	public void testAddCorrectPerson() {
		Person john = createJohn();
		dao.savePerson(john);
		Person actualJohn = dao.findPerson(john.getId());
		assertEquals(john.getId(), actualJohn.getId());
		assertEquals(john.getBirthDay().toString(), actualJohn.getBirthDay().toString());
		assertEquals(john.getFirstName(), actualJohn.getFirstName());
		assertEquals(john.getEmail(), actualJohn.getEmail());
	}
	
	@Test
	public void testAddDuplicatedPerson() {
		Person jane1 = createJane();
		dao.savePerson(jane1);
		Person jane2 = createJane();
		assertThrows(DataIntegrityViolationException.class, () -> {
	        dao.savePerson(jane2);
	    });
	}
	
	@Test
	public void testAddTooLongName() {
		Person jane = createJane();
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < 250 ; i ++) {
			sb.append("l");
		}
		jane.setFirstName(sb.toString());
		assertThrows(Exception.class, () -> {
	        dao.savePerson(jane);
	    });
	}
	
	@Test
	public void testAddGroup() {
		Group ga = createGroupA();
		dao.saveGroup(ga);
		
		Group daoGroup = dao.findGroup(ga.getId());
		
		assertEquals(daoGroup.getId(), ga.getId());
		assertEquals(daoGroup.getNom(), ga.getNom());
	}
	
	@Test
	public void testAddDuplicatedGroup() {
		Group ga = createGroupA();
		dao.saveGroup(ga);
		
		Group gaBis = createGroupA();
		assertThrows(DataIntegrityViolationException.class, () -> {
	        dao.saveGroup(gaBis);
	    });
	}
	
	@Test
	public void testAddPersonToGroup() {
		Group ga = createGroupA();
		dao.saveGroup(ga);
		
		Person john = createJohn();
		john.setGroup(ga);

		dao.savePerson(john);
		
		Group dbGroup = dao.findGroup(ga.getId());
		assertEquals(1, dbGroup.getMembers().size());
		Person dbPerson = dao.findPerson(john.getId());
		assertNotNull(dbPerson.getGroup());
		assertEquals(ga.getNom(), dbPerson.getGroup().getNom());
	}
	
	@Test
	public void testAddMultiplePersonToGroup() {
		Group ga = createGroupA();
		dao.saveGroup(ga);
		Person john = createJohn();
		Person jane = createJane();
				
		john.setGroup(ga);
		jane.setGroup(ga);
		dao.savePerson(john);
		dao.savePerson(jane);
		
		Group g = dao.findGroup(ga.getId());
		
		assertEquals(2, g.getMembers().size());
	}
	
	@Test
	public void testPersonQuitGroup() {
		Group ga = createGroupA();
		dao.saveGroup(ga);
		Person john = createJohn();
		Person jane = createJane();
		john.setGroup(ga);
		jane.setGroup(ga);
		dao.savePerson(john);
		dao.savePerson(jane);
		
		Person p = dao.findPerson(john.getId());
		
		p.setGroup(null);
		dao.updatePerson(p);
		
		Group g = dao.findGroup(ga.getId());
		assertEquals(1, g.getMembers().size());
		
		Person dDb = dao.findPerson(john.getId());
		assertNull(dDb.getGroup());
	}
	
	@Test
	public void testPersonChangeGroup() {
		Group ga = createGroupA();
		Group gb = createGroupB();
		
		dao.saveGroup(ga);
		dao.saveGroup(gb);
		
		Person john = createJohn();
		Person jane = createJane();
		john.setGroup(ga);
		jane.setGroup(ga);
		
		dao.savePerson(john);
		dao.savePerson(jane);
		
		Person p = dao.findPerson(john.getId());
		
		p.setGroup(gb);
		dao.updatePerson(p);
		
		Group g = dao.findGroup(gb.getId());
		assertEquals(1, g.getMembers().size());
	}
	
	@Test
	public void findPersonWithEmail() {
		Person p = createJohn();
		dao.savePerson(p);
		
		Person f = dao.findPerson(p.getEmail());
		assertEquals(f.getId(), p.getId());
	}
	
	@Test
	public void findPersonWithEmailUnexisting() {
		Person f = dao.findPerson("randomemail");
		assertNull(f);
	}
	
	
	//@Test
	public void testFindAllPersonWithHideData() {
		Group ga = createGroupA();
		Group gb = createGroupB();
		
		dao.saveGroup(ga);
		dao.saveGroup(gb);
		
		Person john = createJohn();
		Person jane = createJane();
		john.setGroup(ga);
		jane.setGroup(ga);
		
		dao.savePerson(john);
		dao.savePerson(jane);
		
		Collection<Person> persons = dao.findAllPersonWithHideData();
		for(Person p : persons) {
			assertNotNull(p.getId());
			assertNotNull(p.getFirstName());
			assertNotNull(p.getGroup());
			assertNotNull(p.getLastName());
			assertNotNull(p.getWebsite());
			assertNotNull(p.getPassword());
			assertNull(p.getEmail());
			assertNull(p.getBirthDay());
		}
	}
}