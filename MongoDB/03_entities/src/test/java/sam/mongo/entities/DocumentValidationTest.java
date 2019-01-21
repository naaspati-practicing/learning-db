package sam.mongo.entities;

import org.junit.Test;

import com.mongodb.WriteConcernException;

import sam.mongo.commons.TestBase;

public class DocumentValidationTest extends TestBase {

	@Test(expected=WriteConcernException.class)
	public void test() {
		dropDatabase();
		DocumentValidation d = new DocumentValidation("anime", 5);
		getMorphia().map(DocumentValidation.class);
		ds().enableDocumentValidation();
		ds().save(d);	
	}
}
