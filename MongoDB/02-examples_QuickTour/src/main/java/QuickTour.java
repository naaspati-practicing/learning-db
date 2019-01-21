import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.MongoClient;

public class QuickTour implements AutoCloseable {

	public static void main(String[] args) throws Exception {
		MyLoggerFactory.logger( "org.mongodb.driver" ).setLevel(Level.ALL);
		System.setProperty("DEBUG.MONGO", "true");

		try(QuickTour q = new QuickTour()) {
			q.countEmployees();
			q.underpaidEmployees();
			q.increaseSalaryunderpaidEmployees();
			q.deleteoverpaidEmployees();
		}
	}

	private final MongoClient client = new MongoClient();
	private final Datastore datastore;

	public QuickTour() {
		Morphia morphia = new Morphia();
		morphia.map(Employee.class);

		datastore = morphia.createDatastore(client, "morphia_example");
		datastore.getDB().dropDatabase();
		datastore.ensureIndexes();

		fillData();
	}

	private void fillData() {
		Employee elmer = new Employee("Elmer Fudd", 24, 50000.0);
		Employee daffy = new Employee("Daffy Duck", 25,  40000.0);
		Employee pepe = new Employee("Pepé Le Pew", 26, 25000.0);

		datastore.save(Arrays.asList(elmer, daffy, pepe));
		elmer.addDirectReports(daffy, pepe);

		datastore.save(elmer);
	}

	private Query<Employee> find(){
		return datastore.find(Employee.class);
	}
	private UpdateOperations<Employee> updateOperation(){
		return datastore.createUpdateOperations(Employee.class);
	}
	private void countEmployees() {
		List<Employee> employees = find().asList();
		assert employees.size() == 3;
	}

	private void underpaidEmployees() {
		List<Employee> list = find()
				.field("salary").lessThanOrEq(30000)
				.asList();

		assert list.size() == 1;

		list = find().filter("salary <=", 30000).asList();

		assert list.size() == 1;
	}
	private void increaseSalaryunderpaidEmployees() {
		Query<Employee> underpaidQuery = find()
				.field("salary").lessThanOrEq(30000);

		UpdateOperations<Employee> increaseSalaryby10K = updateOperation()
				.inc("salary", 10_000);
		
		UpdateResults result = datastore.update(underpaidQuery, increaseSalaryby10K);
		assert 1 == result.getUpdatedCount();
	}
	
	private void deleteoverpaidEmployees() {
		Query<Employee> query = find()
				.field("salary").greaterThanOrEq(100_000);
		
		datastore.delete(query);
	}
	@Override
	public void close() throws Exception {
		client.close();
	}
}
