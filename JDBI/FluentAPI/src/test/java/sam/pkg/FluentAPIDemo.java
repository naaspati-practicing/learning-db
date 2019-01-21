package sam.pkg;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static sam.pkg.UserDao.CREATE_TABLE_SQL;
import static sam.pkg.UserDao.DROP_TABLE_SQL;
import static sam.pkg.UserDao.INSERT_USER_BY_NAMED;
import static sam.pkg.UserDao.INSERT_USER_BY_POSITION;
import static sam.pkg.UserDao.SELECT_ALL_SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FluentAPIDemo extends JdbiInit {
	private static final Logger LOGGER = Logger.getLogger(FluentAPIDemo.class.getName());

	private boolean table_deleted = true;

	@BeforeEach
	void init_this() {
		table_deleted = false;
	}

	private void droptable(Handle h) {
		h.execute(DROP_TABLE_SQL);
		table_deleted = true;
		LOGGER.fine(() -> "dropped user table");
	}

	@AfterEach
	void drop_table() {
		if(!table_deleted) 
			jdbi.useHandle(this::droptable);
	}

	@Test
	void main_test() throws SQLException {
		assertFalse(table_deleted);
		
		User[] expected = users();

		List<User> users = jdbi.withHandle(handle -> {
			handle.execute(CREATE_TABLE_SQL);

			// Inline positional parameters
			handle.execute(INSERT_USER_BY_POSITION, expected[0].getId(), expected[0].getName());

			// Positional parameters
			handle.createUpdate(INSERT_USER_BY_POSITION)
			.bind(0, expected[1].getId())
			.bind(1, expected[1].getName())
			.execute();

			// Named parameters

			handle.createUpdate(INSERT_USER_BY_NAMED)
			.bind("id", expected[2].getId())
			.bind("name", expected[2].getName())
			.execute();

			// Named parameters from bean properties
			handle.createUpdate(INSERT_USER_BY_NAMED)
			.bindBean(expected[3])
			.execute();

			List<User> ret = handle.createQuery(SELECT_ALL_SQL).mapToBean(User.class).list(); 

			droptable(handle);
			return ret;
		});
		
		assertThat(users)
		.containsExactlyElementsIn(expected);
	}
}
