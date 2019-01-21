package sam.mongo.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class StringDates {
	@Id private ObjectId id;
	private String string;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	

}
