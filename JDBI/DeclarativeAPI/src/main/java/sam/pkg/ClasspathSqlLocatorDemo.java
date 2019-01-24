package sam.pkg;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseAnnotationSqlLocator;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ClasspathSqlLocatorDemo {

	
	@UseClasspathSqlLocator
	@SqlUpdate
	// loacates sam\pkg\ClasspathSqlLocatorDemo\createTable.sql in classpath
	//default annotation @UseAnnotationSqlLocator
	public void createTable();
	
	@SqlUpdate("insert into name(user_name) values(?)")
	public void insert(@Bind String user_name);
	
	@SqlQuery("select * from name")
	public List<String> getNames();
}
