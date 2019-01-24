package sam.pkg;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.logging.Logger;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeclarativeAPIDemo extends JdbiInit {
	private static final Logger LOGGER = Logger.getLogger(DeclarativeAPIDemo.class.getName());
	private boolean table_deleted = true;

	@BeforeEach
	void init_this() {
		table_deleted = false;
	}

	private void droptable(UserDao dao) {
		dao.dropTable();
		table_deleted = true;
		LOGGER.fine(() -> "dropped user table");
	}

	@AfterEach
	void drop_table() {
		if(!table_deleted) 
			jdbi.useExtension(UserDao.class, this::droptable);
	}
	@Test
	void main_test() {
		assertFalse(table_deleted);

		User[] expected = users();

		List<User> users = jdbi.withExtension(UserDao.class, dao -> {
			dao.createTable();

			// Positional parameters
			dao.insertUser_positional(expected[0].getId(), expected[0].getName());
			dao.insertUser_positional(expected[1].getId(), expected[1].getName());

			// Named parameters
			dao.insertUser_named(expected[2].getId(), expected[2].getName());

			// bean
			dao.insertUser_bean(expected[3]);

			List<User> ret = dao.listUsers(); 
			droptable(dao);
			return ret;
		});

		assertThat(users)
		.containsExactlyElementsIn(expected);	
	}
}
