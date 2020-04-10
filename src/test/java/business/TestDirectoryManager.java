package business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import annuaire.business.DirectoryManager;
import annuaire.business.SpringBusinessConfiguration;
import annuaire.dao.IPersonDAO;
import annuaire.model.Group;
import annuaire.model.Person;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringBusinessConfiguration.class})
@AutoConfigureMockMvc
public class TestDirectoryManager {
		
    @MockBean
    protected IPersonDAO daoService;
	
	@Autowired
	protected DirectoryManager directoryManager ;
	
	@BeforeAll
	public static void printTestName() {
		System.out.println("-----------------------------------");
		System.out.println("--- TESTING DIRECTORY MANAGER -----");
		System.out.println("-----------------------------------");
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
	
	protected Group groupe_a;
	protected Group groupe_b;
	protected final int nb_Groups = 2;
	protected final int groupe_a_id = 10 ;
	protected final int groupe_b_id = 20 ;
	
	protected Person john ;
	protected Person jane ;
	
	protected final long john_id = 20 ;
	protected final long jane_id = 40 ;
	protected final int nb_persons = 2 ;
	
	@BeforeEach
	public void setUp() {
		groupe_a = createGroupA();
		groupe_b = createGroupB();
		groupe_a.setId(groupe_a_id);
		groupe_b.setId(groupe_b_id);
		john = createJohn();
		jane = createJane();
		john.setId(john_id);
		jane.setId(jane_id);
		
		when(daoService.findAllPersons()).thenReturn(Arrays.asList(john, jane));
		when(daoService.findPerson(john_id)).thenReturn(john);
		when(daoService.findPerson(jane_id)).thenReturn(jane);
		
		when(daoService.findAllGroups()).thenReturn(Arrays.asList(groupe_a, groupe_b));
		when(daoService.findGroup(groupe_a_id)).thenReturn(groupe_a);
		when(daoService.findGroup(groupe_b_id)).thenReturn(groupe_b);
	}
	
	@AfterEach
	public void logout() {
		directoryManager.logout();
	}
	
	@Test
	public void testGetAndSetConfig() {
		long id_value = 12 ;
		directoryManager.getUser().setPersonId(id_value);
		assertEquals(id_value, directoryManager.getUser().getPersonId());
	}
	
	@Test
	public void testMockWorking() {
		Person person_a = new Person();
		long fakeId = 12 ;
		person_a.setId(fakeId);
		
		when(daoService.findPerson(anyLong())).thenReturn(person_a);
		
		Person p = directoryManager.findPerson(15);
		assertNotNull(p);
		assertEquals(fakeId, p.getId());
	}
	
	
		
	@Test
	public void anonymousCanAccessGroup() {
		assertEquals(nb_Groups, directoryManager.findAllGroup().size());
	}
	
	@Test
	public void anonymousCannotAccessConfidentialPersonInfo() {
		long johnId = 20 ;
		john.setId(johnId);

		Person findPerson = directoryManager.findPerson(johnId);
		
		assertNull(findPerson.getBirthDay());
		assertNull(findPerson.getEmail());
	}
	
	@Test
	public void anonymousCannotAccessConfidentialPersonInfoViaGroup() {
		Set<Person> persons = new HashSet<Person>();
		persons.add(john);
		persons.add(jane);
		groupe_a.setMembers(persons);
		
		Group g = directoryManager.findGroup(groupe_a.getId());
		
		for(Person p : g.getMembers()) {
			assertNull(p.getEmail());
			assertNull(p.getBirthDay());
		}
	}
	
	@Test
	public void anonymousCanAccessPublicPersonInfo() {
		Person findPerson = directoryManager.findPerson(john_id);
		
		assertNotNull(findPerson.getFirstName());
		assertNotNull(findPerson.getLastName());
	}
	
	@Test
	public void successAuth() {
		assertTrue(directoryManager.login(john_id, "123456"));
		assertNotNull(directoryManager.getUser().getPersonId());
	}
	
	@Test
	public void failAuth() {
		assertFalse(directoryManager.login(john_id, "incorrectpassword"));
		assertNull(directoryManager.getUser().getPersonId());
	}

	@Test
	public void loggedCanAccessPublicPersonInfo() {
		directoryManager.login(john_id, "123456");
		
		Person findPerson = directoryManager.findPerson(jane_id);
		
		assertNotNull(findPerson.getFirstName());
		assertNotNull(findPerson.getLastName());
	}
		
	@Test
	public void loggedCanAccessConfidentialPersonInfo() {
		directoryManager.login(john_id, "123456");
		
		Person findPerson = directoryManager.findPerson(jane_id);
		
		assertNotNull(findPerson.getFirstName());
		assertNotNull(findPerson.getLastName());
	}
	
	@Test
	public void loggedCanAccessConfidentialPersonInfoViaGroup() {
		directoryManager.login(john_id, "123456");
		
		Person findPerson = directoryManager.findPerson(jane_id);
		
		assertNotNull(findPerson.getEmail());
		assertNotNull(findPerson.getBirthDay());
	}
	
	@Test
	public void loggedCannotUpdateAnotherPerson() {
		directoryManager.login(john_id, "123456");
		jane.setFirstName("NewName");
		
		directoryManager.savePerson(jane);
		
		verify(daoService, Mockito.times(0)).updatePerson(jane);
	}
	
	@Test
	public void anonymousCannotUpdateAnyPerson() {
		jane.setFirstName("NewName");
		
		directoryManager.savePerson(jane);
		
		verify(daoService, Mockito.times(0)).updatePerson(jane);
	}
	
	@Test
	public void loggedCanUpdateHimself() {
		directoryManager.login(john_id, "123456");
		john.setFirstName("NewName");
		
		directoryManager.savePerson(john);
		
		verify(daoService, Mockito.times(1)).updatePerson(john);
	}
}
