import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Version;

@Entity("employees")
@Indexes(@Index(fields=@Field("salary"))) 
public class Employee {
	@Id private ObjectId id;
	@Version private long version;
	private String name;
	private int age;
	@Reference private Employee manager;
	@Reference private List<Employee> directReports = new ArrayList<>();
	
	@Property("wage")
	private double salary;
	
	public Employee() {}
	public Employee(String name, int age, double salary) {
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public List<Employee> getDirectReports() {
		return directReports;
	}
	public void setDirectReports(List<Employee> directReports) {
		this.directReports = directReports;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void addDirectReports(Employee...employees) {
		Objects.requireNonNull(employees);
		for (Employee e : employees) 
			directReports.add(Objects.requireNonNull(e));
	}
}
