package business;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import annuaire.business.EntitySorter;
import annuaire.business.SpringBusinessConfiguration;
import annuaire.dao.IPersonDAO;
import annuaire.model.Person;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBusinessConfiguration.class})
@AutoConfigureMockMvc
public class TestEntitySorter {
	
	@MockBean
	protected IPersonDAO dao;
	
	@Autowired 
	private EntitySorter entitySorter;

	private ArrayList<Person> persons ;
	
	private Person createPerson() {
		Person p = new Person();
		p.setBirthDay(new java.sql.Date(12000));
		p.setEmail("johnmail");
		p.setLastName("Jo");
		p.setFirstName("hn");
		p.setPassword("123456");
		return p ;
	}
	
	@BeforeEach
	public void setUpPersons() {
		persons = new ArrayList<Person>();
		for(int i = 0 ; i < 5 ; i ++) {
			persons.add(createPerson());
		}
	}
	
	@Test
	public void testIdAscSort() {
		persons.get(0).setId(12);
		persons.get(1).setId(8);
		persons.get(2).setId(18);
		persons.get(3).setId(18);
		persons.get(4).setId(5);
		
		entitySorter.sortPersons(persons, "id_asc");
		
		for(int i = 0 ; i < persons.size() - 1 ; i ++) {
			assertTrue(persons.get(i).getId() <= persons.get(i+1).getId());
		}
	}
	
	@Test
	public void testIdDescSort() {
		persons.get(0).setId(12);
		persons.get(1).setId(8);
		persons.get(2).setId(18);
		persons.get(3).setId(18);
		persons.get(4).setId(5);
		
		entitySorter.sortPersons(persons, "id_desc");
		
		for(int i = 0 ; i < persons.size() - 1 ; i ++) {
			assertTrue(persons.get(i).getId() >= persons.get(i+1).getId());
		}
	}
	
	@Test
	public void testLastNameAscSort() {
		persons.get(0).setLastName("name1");
		persons.get(1).setLastName("name4");
		persons.get(2).setLastName("name2");
		persons.get(3).setLastName("name5");
		persons.get(4).setLastName("name1");
		
		entitySorter.sortPersons(persons, "lastName_asc");
		
		assertEquals("name1", persons.get(0).getLastName());
		assertEquals("name1", persons.get(1).getLastName());
		assertEquals("name2", persons.get(2).getLastName());
		assertEquals("name4", persons.get(3).getLastName());
		assertEquals("name5", persons.get(4).getLastName());
	}
	
	@Test
	public void testLastNameDescSort() {
		persons.get(0).setLastName("name1");
		persons.get(1).setLastName("name4");
		persons.get(2).setLastName("name2");
		persons.get(3).setLastName("name5");
		persons.get(4).setLastName("name1");
		
		entitySorter.sortPersons(persons, "lastName_desc");
		
		assertEquals("name1", persons.get(4).getLastName());
		assertEquals("name1", persons.get(3).getLastName());
		assertEquals("name2", persons.get(2).getLastName());
		assertEquals("name4", persons.get(1).getLastName());
		assertEquals("name5", persons.get(0).getLastName());
	}
	
	@Test
	public void testFirstNameAscSort() {
		persons.get(0).setFirstName("name1");
		persons.get(1).setFirstName("name4");
		persons.get(2).setFirstName("name2");
		persons.get(3).setFirstName("name5");
		persons.get(4).setFirstName("name1");
		
		entitySorter.sortPersons(persons, "firstName_asc");
		
		assertEquals("name1", persons.get(0).getFirstName());
		assertEquals("name1", persons.get(1).getFirstName());
		assertEquals("name2", persons.get(2).getFirstName());
		assertEquals("name4", persons.get(3).getFirstName());
		assertEquals("name5", persons.get(4).getFirstName());
	}
	
	@Test
	public void testFirstNameDescSort() {
		persons.get(0).setFirstName("name1");
		persons.get(1).setFirstName("name4");
		persons.get(2).setFirstName("name2");
		persons.get(3).setFirstName("name5");
		persons.get(4).setFirstName("name1");
		
		entitySorter.sortPersons(persons, "firstName_desc");
		
		assertEquals("name1", persons.get(4).getFirstName());
		assertEquals("name1", persons.get(3).getFirstName());
		assertEquals("name2", persons.get(2).getFirstName());
		assertEquals("name4", persons.get(1).getFirstName());
		assertEquals("name5", persons.get(0).getFirstName());
	}
}
