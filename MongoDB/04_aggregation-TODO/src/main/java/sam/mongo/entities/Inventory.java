package sam.mongo.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "inventory", noClassnameStored = true)
public class Inventory {
	@Id private int id;
	private String sku;
	private String description;
	private int instock;
	
	public Inventory() {
    }

    Inventory(final int id) {
        this.id = id;
    }

    Inventory(final int id, final String sku, final String description) {
        this.id = id;
        this.sku = sku;
        this.description = description;
    }
    Inventory(final int id, final String sku, final String description, final int instock) {
        this.id = id;
        this.sku = sku;
        this.description = description;
        this.instock = instock;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getInstock() {
		return instock;
	}
	public void setInstock(int instock) {
		this.instock = instock;
	}
}
