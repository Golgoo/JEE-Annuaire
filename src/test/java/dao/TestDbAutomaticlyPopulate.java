package dao;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import annuaire.dao.DataLoader;
import annuaire.dao.IPersonDAO;
import annuaire.dao.SpringDAOConfiguration;
import annuaire.model.Group;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDAOConfiguration.class)
public class TestDbAutomaticlyPopulate {
	
	@Autowired
	@Qualifier("dao_v1.0")
	protected IPersonDAO dao;
	
	@Test
	public void testGroupArePresent() {
		assertFalse(dao.findAllGroups().isEmpty());
	}
	
	@Test
	public void testUsersAreInGroup() {
		Collection<Group> groups = dao.findAllGroups();
		
		int count = 0 ;
		for(Group g : groups) {
			count += dao.findAllPersonsFromGroup(g.getId()).size();
		}
		assertEquals(DataLoader.nbPersons, count);
	}
}
