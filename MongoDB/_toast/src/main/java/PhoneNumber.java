import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

public class PhoneNumber {
	
	@Id
	private ObjectId id;
	@Version
	private long version;
	private int areaCode;
	private int phonenumber;
	
	public PhoneNumber(int areaCode, int phonenumber) {
		this.areaCode = areaCode;
		this.phonenumber = phonenumber;
	}
	
	public PhoneNumber() {
		areaCode = 011;
		phonenumber = 2626262;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
	
	

}
