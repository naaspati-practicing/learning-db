
public class Address {
	private int houseNumber;
	private String road;
	
	public Address() {
		houseNumber = 218;
		road = "Madanpur kahder";
	}

	public Address(int houseNumber, String road) {
		this.houseNumber = houseNumber;
		this.road = road;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}
	
	
	
	

}
