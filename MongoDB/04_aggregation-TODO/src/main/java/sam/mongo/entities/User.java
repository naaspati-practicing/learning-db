package sam.mongo.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Validation;

@Entity
@Validation("{age: {$gt:13}}")
public class User {
	@Id private ObjectId id;
	private String name;
	private LocalDate joined;
	private List<String> likes;
	private int age;
	
	public User() {}
	
	public User(final String name, final LocalDate joined, final String... likes) {
        this.name = name;
        this.joined = joined;
        this.likes = Arrays.asList(likes);
    }
	public User(final String name, final String... likes) {
        this.name = name;
        this.joined = LocalDate.now();
        this.likes = Arrays.asList(likes);
    }
	public User(final String name) {
        this.name = name;
        this.joined = LocalDate.now();
    }

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getJoined() {
		return joined;
	}

	public void setJoined(LocalDate joined) {
		this.joined = joined;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
