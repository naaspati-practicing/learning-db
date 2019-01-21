package sam.mongo.entities;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("orders")
public class Order {
	@Id private int id;
	private String item;
	private int price;
	private int quantity;
	
	@Embedded
	List<Inventory> inventoryDocs;
	
	public Order() {}
	

    Order(final int id) {
        this.id = id;
    }

    Order(final int id, final String item, final int price, final int quantity) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public List<Inventory> getInventoryDocs() {
		return inventoryDocs;
	}
	public void setInventoryDocs(List<Inventory> inventoryDocs) {
		this.inventoryDocs = inventoryDocs;
	}
}
