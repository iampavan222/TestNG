package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testlibrary.LoginApp;
import utils.LaunchApp;

public class LoginWithValidData 
{	
	@Test(dataProvider = "users")
	public void AppLaunch(String uid,String pwd) {
		LoginApp lp = new LoginApp();
		lp.loginpage(uid, pwd);
		
		LaunchApp.closeApp();
   }
	
	@DataProvider(name="users")
	public String[][] datainf() {
		String data[][]={ {"admin","Qedge123!@#"},{"adm","qedge"},{"admin","qwedkffjdvnck"}};
		return data;
		
	}

}
