package sam.mongo.commons;

import java.util.Iterator;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("deprecation")
public abstract class TestBase {
	public static final String TEST_DB_NAME = "morphia_test";
	private static MongoClient client;
	private static Morphia morphia = new Morphia();
	private static MongoDatabase database;
	private static Datastore datastore;
	
	@BeforeClass
	public static void before() {
		if(TestBase.client != null)
			throw new IllegalStateException("mongoclient is not null");
		
		TestBase.client = Optional.ofNullable(System.getProperty("MONGO_URI"))
				.map(MongoClientURI::new)
				.map(MongoClient::new)
				.orElseGet(() -> new MongoClient());
		
		database = client.getDatabase(TEST_DB_NAME);
		datastore = morphia.createDatastore(client, TEST_DB_NAME);
	}
	public MongoClient getClient() {
		return client;
	}
	public Morphia getMorphia() {
		return morphia;
	}
	public MongoDatabase getDatabase() {
		return database;
	}
	public Datastore getDatastore() {
		return datastore;
	}
	/**
	 * {@link #getDatastore()}
	 * @return
	 */
	public Datastore ds() {
		return datastore;
	}
	public AdvancedDatastore getAdvancedDatastore() {
		return (AdvancedDatastore)datastore;
	}
	/**
	 * {@link #getAdvancedDatastore()}
	 * @return
	 */
	public AdvancedDatastore ads() {
		return getAdvancedDatastore();
	}
	@AfterClass
	public static void close() {
		client.close();
		client = null;
	}
	public void dropDatabase() {
		getDatabase().drop();
	}
	protected void mapClasses(@SuppressWarnings("rawtypes") Class...classes) {
		morphia.map(classes);
	}
	public <E> Iterable<E> iterable(@SuppressWarnings("unchecked") E...es){
		return new Iterable<E>() {
			@Override
			public Iterator<E> iterator() {
				return new Iterator<E>() {
					int index = 0;

					@Override
					public boolean hasNext() {
						return index < es.length;
					}
					@Override
					public E next() {
						return es[index++];
					}
				};
			}
		};
	}
}
