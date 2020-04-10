package annuaire.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Groupe")
public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	
	@Column(
		name = "groupe_name", 
		length = 200,
		nullable = false, 
		unique = true)
	@Basic(optional = false)
	private String nom ;
	
	@OneToMany(
			cascade = { CascadeType.PERSIST },
			fetch = FetchType.LAZY, mappedBy = "group")
	private Set<Person> members ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Person> getMembers() {
		return members;
	}

	public void setMembers(Set<Person> members) {
		this.members = members;
	}
}
