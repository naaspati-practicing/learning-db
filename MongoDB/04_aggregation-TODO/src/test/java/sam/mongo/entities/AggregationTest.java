package sam.mongo.entities;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.Query;

import com.mongodb.AggregationOptions;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationStrength;

import sam.mongo.commons.TestBase;

public class AggregationTest extends TestBase {
	@Test
	public void testCollection() {
		dropDatabase();
		mapClasses(User.class);
		ds().save(iterable(new User("john doe"), new User("John Doe")));

		Query<User> query = ds()
				.find(User.class)
				.field("name").equal("john doe");

		AggregationPipeline pipe = ds()
				.createAggregation(User.class)
				.match(query);

		assertEquals(1, count(pipe.aggregate(User.class)));

		Collation collation = Collation.builder().locale("en").collationStrength(CollationStrength.SECONDARY).build();
		assertEquals(2, count(pipe.aggregate(User.class, AggregationOptions.builder().collation(collation).build())));
	}
	private <E> int count(Iterator<E> aggregate) {
		int c = 0;
		while (aggregate.hasNext()) c++;
		return c;
	}

}
