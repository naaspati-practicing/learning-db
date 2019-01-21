package sam.mongo.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Validation;

@Entity("validation")
@Validation("{number:{$gt:10}}")
public class DocumentValidation {
	@Id
	private ObjectId id;
	private String string;
	private int number;
	private LocalDateTime time;
	
	public DocumentValidation() {}

	public DocumentValidation(String string, int number, LocalDateTime time) {
		this.string = string;
		this.number = number;
		this.time = time;
	}
	public DocumentValidation(String string, int number) {
		this.string = string;
		this.number = number;
		this.time = LocalDateTime.now();
	}
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + number;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentValidation other = (DocumentValidation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number != other.number)
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	
	
	
	

}
