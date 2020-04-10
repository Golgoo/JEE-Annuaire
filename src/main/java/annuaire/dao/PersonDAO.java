package annuaire.dao;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import annuaire.model.Group;
import annuaire.model.Person;

@Service
@Repository
@Transactional
@Qualifier("dao_v1.0")
public class PersonDAO implements IPersonDAO{

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

	@Override
	public Collection<Group> findAllGroups() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Group> cq = cb.createQuery(Group.class);
	    Root<Group> rootEntry = cq.from(Group.class);
	    CriteriaQuery<Group> all = cq.select(rootEntry);
	 
	    TypedQuery<Group> allQuery = em.createQuery(all);
	    return allQuery.getResultList();
	}

	@Override
	public Collection<Person> findAllPersonsFromGroup(long groupId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	 	    
	    CriteriaQuery<Person> criteria = cb.createQuery( Person.class );
	    Root<Person> personRoot = criteria.from( Person.class );
	    criteria.select( personRoot );
	    criteria.where( cb.equal( personRoot.get( "group" ), groupId ) );
	    return em.createQuery( criteria ).getResultList();
	}
	
	@Override
	public Collection<Person> findAllPersons(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Person> cq = cb.createQuery(Person.class);
	    Root<Person> rootEntry = cq.from(Person.class);
	    CriteriaQuery<Person> all = cq.select(rootEntry);
	    
	 
	    TypedQuery<Person> allQuery = em.createQuery(all);
	    return allQuery.getResultList();
	}
	
	@Override
	public Collection<Group> findAllGroups(String pattern){
		if(pattern == null) return findAllGroups();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Group> cq = cb.createQuery(Group.class);
	    Root<Group> rootEntry = cq.from(Group.class);
	   
	    ParameterExpression<String> p = cb.parameter(String.class);
	    cq.select(rootEntry);
	    
	    cq.where(cb.like(rootEntry.get("nom"), p));
	    
	    TypedQuery<Group> query = em.createQuery(cq);
	    query.setParameter(p, "%"+ pattern +"%");
	    
		return query.getResultList();
	}
	
	@Override
	public Collection<Person> findAllPersons(String pattern) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Person> cq = cb.createQuery(Person.class);
	    Root<Person> rootEntry = cq.from(Person.class);
	   
	    ParameterExpression<String> p = cb.parameter(String.class);
	    cq.select(rootEntry);
	    
	    cq.where(cb.or(cb.like(rootEntry.get("firstName"), p), cb.like(rootEntry.get("lastName"), p)));
	    
	    TypedQuery<Person> query = em.createQuery(cq);
	    query.setParameter(p, "%"+ pattern +"%");
	    
		return query.getResultList();
	}

	@Override
	public Person findPerson(long id) {
		Person p = em.find(Person.class, id);
		if(p!=null)
			em.detach(p);
		return p;
	}
	
	@Override
	public Person findPerson(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> rootEntry = cq.from(Person.class);
		
		ParameterExpression<String> p = cb.parameter(String.class);
		cq.select(rootEntry);
		
		cq.where(cb.equal(rootEntry.get("email"), p));
		
		TypedQuery<Person> query = em.createQuery(cq);
		query.setParameter(p, email);
		try {
			return query.getSingleResult();
		}catch (NoResultException e) {
			return null ;
		}
	}

	@Override
	public void savePerson(Person p) {
		em.persist(p);
	}

	@Override
	public void saveGroup(Group g) {
		em.persist(g);
	}

	@Override
	public void clearAll() {
		clearTable(Person.class);
		clearTable(Group.class);
	}
	
	private <T> void clearTable(Class<T> clazz) {
		CriteriaBuilder cb =em.getCriteriaBuilder();
		CriteriaDelete<T> criteriaDelete = cb.createCriteriaDelete(clazz);
		criteriaDelete.from(clazz);
		em.createQuery(criteriaDelete).executeUpdate();
	}

	@Override
	public Group findGroup(long id) {
		if(em == null) {
			System.err.println("Enity manager is null ..");
		}
		return em.find(Group.class, id);
	}

	@Override
	public void updateGroup(Group g) {
		em.merge(g);
	}
	
	@Override 
	public void updatePerson(Person p) {
		em.merge(p);
	}





}
