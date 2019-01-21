import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class Main {

	public static void main(String[] args) {
		MyLoggerFactory.logger( "org.mongodb.driver" ).setLevel(Level.SEVERE);
		
		MongoClient client = new MongoClient(
				IntStream.range(27000, 27003)
				.mapToObj(port -> new ServerAddress("localhost", port))
				.collect(Collectors.toList()));

		Morphia morphia = new Morphia();

		morphia.map(Person.class);
		Datastore datastore = morphia.createDatastore(client, "test");

		datastore.getCollection(Person.class).drop();
		
		Person p = new Person("Sameer", "veda", 26);
		datastore.save(p);
		
		System.out.println(p);
		datastore.find(Person.class).asList().forEach(s -> System.out.println(s)); 
		
		client.close();
	}

}
