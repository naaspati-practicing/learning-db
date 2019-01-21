import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.annotations.Version;

@Entity
public class Person {
	@Id
	private ObjectId id;
	@Version
	private long version;
	private String name;
	private String lastname;
	private int age;
	
	private String email;

	@Embedded
	private Address address;
	
	//@Reference
	@Transient
	private PhoneNumber phonenumber;
	//@Reference
	@Transient
	private List<PhoneNumber> phonenumberAlternative;
	
	public Person() {
		this.name = "sameer";
		this.lastname = "veda";
		this.age = 26;
		
		address = new Address();
		phonenumber = new PhoneNumber();
		phonenumberAlternative = new ArrayList<>(Arrays.asList(new PhoneNumber(), new PhoneNumber(), new PhoneNumber()));
		
		
	}
	
	public Person(String name, String lastname, int age) {
		this.name = name;
		this.lastname = lastname;
		this.age = age;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId _id) {
		this.id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PhoneNumber getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(PhoneNumber phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<PhoneNumber> getPhonenumberAlternative() {
		return phonenumberAlternative;
	}

	public void setPhonenumberAlternative(List<PhoneNumber> phonenumberAlternative) {
		this.phonenumberAlternative = phonenumberAlternative;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", age=" + age + "]";
	}
}
