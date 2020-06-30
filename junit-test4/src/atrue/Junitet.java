package atrue;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import junittest.Mainusetest;
public class Junitet {
 
	@Test
 public void testsum()
 {
	 int ab=Mainusetest.addtwo(10,10);
	
	 int except=10;
	 assertEquals(except,ab);
	 
 }


}
