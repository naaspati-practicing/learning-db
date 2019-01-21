package sam.mongo.entities;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value="books", noClassnameStored=true)
public class Book {
	@Id private ObjectId id;
	private String title;
	private String author;
	private int copies;
	private List<String> tags;
	
	public Book() {}
	
	public Book(final String title, final String author, final Integer copies, final String... tags) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.tags = Arrays.asList(tags);
    }
	
	@Override
    public String toString() {
        return String.format("Book{title='%s', author='%s', copies=%d, tags=%s}", title, author, copies, tags);
    }
	
	

}
