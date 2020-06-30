package junittest;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

	@Test
	public void testLogin() {
		//fail("Not yet implemented");
	boolean actual=Login.login("admin","111");
	boolean except=true;
	assertEquals(actual,except);
	}

}
