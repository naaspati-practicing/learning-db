package sam.mongo.entities;

import org.mongodb.morphia.annotations.AlsoLoad;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("counts")
public class CountResult {
	@Id private String author;
	@AlsoLoad("value")
	private int count;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
