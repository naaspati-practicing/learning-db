package sam.pkg;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class JdbiInit {
	private static final Logger LOGGER = Logger.getLogger(JdbiInit.class.getName());
	static final AtomicInteger order = new AtomicInteger(0);

	static Jdbi jdbi;

	@BeforeAll
	static void init() {
		jdbi = Jdbi.create("jdbc:h2:mem:test");
		LOGGER.fine("jdbi init( "+order.incrementAndGet()+")");
		Logger logger = Logger.getLogger("SQL");

		jdbi.setSqlLogger(new SqlLogger() {
			@Override
			public void logAfterExecution(StatementContext context) {
				logger.fine(() -> context.getParsedSql().toString());
			}
			@Override
			public void logException(StatementContext context, SQLException ex) {
				logger.log(Level.FINE, context.getParsedSql().toString(), ex);
			}
		});
	}

	@AfterAll
	static void stop() {
		LOGGER.fine("jdbi stopped( "+order.incrementAndGet()+")");
	}
	
	protected User[] users() {
		return new User[] {
				new User(0, "Alice"),
				new User(1, "Bob"),
				new User(2, "Clarice"),
				new User(3, "David")
		};
	}
}
