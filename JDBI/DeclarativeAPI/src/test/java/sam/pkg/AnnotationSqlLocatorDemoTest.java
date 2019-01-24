package sam.pkg;

import java.util.Arrays;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.*;

public class AnnotationSqlLocatorDemoTest extends JdbiInit {
	
	ClasspathSqlLocatorDemo dao;
	Handle handle;
	
	@BeforeEach
	void init_self() {
		handle = jdbi.open();
		dao = handle.attach(ClasspathSqlLocatorDemo.class);
	}
	
	@BeforeEach
	void destroy_self() {
		if(handle != null) 
			handle.close();
	}
	
	@Test
	void main_test() {
		dao.createTable();
		List<String> expected = Arrays.asList("ram", "naam", "satya", "hai");
		for (String s : expected) 
			dao.insert(s);
		
		List<String> actual = dao.getNames();
		
		assertThat(actual).containsExactlyElementsIn(expected);
	}
}
