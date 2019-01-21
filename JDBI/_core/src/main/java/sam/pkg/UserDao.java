package sam.pkg;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDao {
	String DROP_TABLE_SQL = "DROP TABLE user IF EXISTS";
	String CREATE_TABLE_SQL = "CREATE TABLE user(id INTEGER PRIMARY KEY, name VARCHAR)";
	String INSERT_USER_BY_POSITION = "INSERT INTO user(id, name) VALUES (?,?)";
	String INSERT_USER_BY_NAMED = "INSERT INTO user(id, name) VALUES (:id,:name)";
	String SELECT_ALL_SQL = "SELECT * FROM user";

	@SqlUpdate(CREATE_TABLE_SQL)
	void createTable() ;
	
	@SqlUpdate(DROP_TABLE_SQL)
	void dropTable() ;
	
	@SqlUpdate(INSERT_USER_BY_POSITION)
	void insertUser_positional(int id, String name);
	
	@SqlUpdate(INSERT_USER_BY_NAMED)
	void insertUser_named(@Bind("id") int id, @Bind("name") String name);
	
	@SqlUpdate(INSERT_USER_BY_NAMED)
	void insertUser_bean(@BindBean User user);
	
	@SqlQuery(SELECT_ALL_SQL)
	@RegisterBeanMapper(User.class)
	List<User> listUsers();
}
