import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Person {
	@Id
	private ObjectId id;
	private String name;
	private String lastname;
	private int age;
	
	public Person() {}
	
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastname=" + lastname + ", age=" + age + "]";
	}
}
