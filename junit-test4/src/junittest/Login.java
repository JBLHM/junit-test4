package junittest;

public class Login {
public static boolean  login(String username,String password)
{
	return("admin".equals(username)&&"111".equals(password)?true:false);
}
}
