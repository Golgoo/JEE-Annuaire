package annuaire.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Basic(optional = false)
	@Column(name = "first_name", length = 200, nullable = false)
	@NotNull
    @Size(min = 1, message = "{person.firstname.short}")
	@Size(max = 200, message = "{person.firstname.long}")
	private String firstName;

	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_day")
	private Date birthDay;

	@ManyToOne(optional = true)
	@JoinColumn(name = "group_ref")
	private Group group;

	@Basic(optional = false)
	@Column(length = 200, nullable = false)
	@NotNull
    @Size(min = 1, message = "{person.lastname.short}")
	@Size(max = 200, message = "{person.lastname.long}")
	private String lastName;

	@Basic(optional = true)
	@Column(nullable = true)
	private String website;

	@Basic(optional = false)
	@Column(nullable = false, unique = true)
	private String email;

	@Basic(optional = false)
	@Column(nullable = false)
	private String password;

	@Version()
	private long version = 0;

	public Person() {
		super();
	}

	public Person(String firstName, Date birthDay) {
		super();
		this.firstName = firstName;
		this.birthDay = birthDay;
	}

	@PreUpdate
	public void beforeUpdate() {
		System.err.println("PreUpdate of " + this);
	}

	@PostUpdate
	public void afterUpdate() {
		System.err.println("PostUpdate of " + this);
	}

	@Override
	public String toString() {
		return "Person(id=" + getId() + "," + firstName + "," + birthDay + "," + ",v" + getVersion() + ")";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}