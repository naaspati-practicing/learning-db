package sam.pkg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.jdbi.v3.core.Handle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SqlObjectGetHandleDemoTest extends JdbiInit {
	
	SqlObjectGetHandleDemo dao;
	Handle handle;
	
	@BeforeEach
	void init_self() {
		handle = jdbi.open();
		dao = handle.attach(SqlObjectGetHandleDemo.class);
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
		
		String actual = dao.getNames();
		assertEquals(String.join(", ", expected), actual);
	}
}
