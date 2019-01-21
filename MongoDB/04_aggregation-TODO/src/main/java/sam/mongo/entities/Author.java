package sam.mongo.entities;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("authors")
public class Author {
	@Id String name;
	private List<String> books;
}
