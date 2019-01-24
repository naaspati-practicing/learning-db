package sam.pkg;

import java.util.stream.Collectors;

import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SqlObjectGetHandleDemo extends SqlObject {

	default void createTable() {
		useHandle(h -> h.execute("create table name(user_name varchar(255)) "));
	}

	@SqlUpdate("insert into name(user_name) values(?)")
	void insert(@Bind String user_name);

	default String getNames() {
		return withHandle(h -> {
			return h.createQuery("select * from name")
					.mapTo(String.class)
					.stream()
					.collect(Collectors.joining(", "));
		});
	}

}
