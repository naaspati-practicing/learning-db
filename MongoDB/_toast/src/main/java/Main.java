import java.time.LocalDate;
import java.util.Date;

import org.bson.BsonBoolean;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class Main {

	public static void main(String[] args) {
		// MyLoggerFactory.logger( "org.mongodb.driver" ).setLevel(Level.SEVERE);
		
		MongoClient client = new MongoClient();
		Morphia morphia = new Morphia();

		morphia.map(DOB.class);
		Datastore d = morphia.createDatastore(client, "test");
		
		d.save(new Person());
		client.close();
	}

}
