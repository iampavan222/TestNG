package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import testlibrary.EmployeeRegistration;
import testlibrary.LoginApp;
import testlibrary.UserRegistration;
import utils.LaunchApp;
import utils.XLUtils;

public class OrangeHRMTestSuite extends LaunchApp {
	
	String datafile="D:\\selenium\\OrangeHRMProject-1\\src\\Datainf\\OrangeHRMKeywords.xlsx";
	String tcsheet="TestCases";
	String tssheet="TestSteps";
	
	String execute,tcid,tsid,keyword;
	String uid,pwd,fname,lname;
	String role,empname,uname,passwd;
	
	@Test
	public void checkTestCase() throws IOException, InterruptedException 
	{
		
		int ccount=XLUtils.getRowCount(datafile, tcsheet);
		int scount=XLUtils.getRowCount(datafile, tssheet);
		
		LoginApp lp = new LoginApp();
		EmployeeRegistration reg= new EmployeeRegistration();
		UserRegistration userreg= new UserRegistration();
		
		boolean res=false;
		String stepresult,testresult;
		
		for(int i=1;i<=ccount;i++) 
		{
			execute=XLUtils.getStringData(datafile, tcsheet, i, 2);
			if(execute.equalsIgnoreCase("y")) 
			{
				tcid=XLUtils.getStringData(datafile, tcsheet, i, 0);
			}
			for(int j=1;j<=scount;j++) 
			{
				tsid=XLUtils.getStringData(datafile, tssheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid)) {
					keyword=XLUtils.getStringData(datafile, tssheet, j, 4);
					switch(keyword.toLowerCase())
					{
					case "adminlogin":
						uid=XLUtils.getStringData(datafile, tssheet, j, 5);
						pwd=XLUtils.getStringData(datafile, tssheet, j, 6);
						lp.loginpage(uid, pwd);
						res=lp.isAdminornot();
						break;
					case "logout":
						lp.logout();
						break;
					case "newempreg":
						fname=XLUtils.getStringData(datafile, tssheet, j, 5);
						lname=XLUtils.getStringData(datafile, tssheet, j, 6);
						res=reg.empreg(fname, lname);
						break;
					case "newuserreg":
						role=XLUtils.getStringData(datafile, tssheet, j, 5);
						empname=XLUtils.getStringData(datafile, tssheet, j, 6);
						uname=XLUtils.getStringData(datafile, tssheet, j, 7);
						passwd=XLUtils.getStringData(datafile, tssheet, j, 8);
						res=userreg.addUser(role, empname, uname, passwd);
						break;
					case "emplogin":
						uname=XLUtils.getStringData(datafile, tssheet, j, 5);
						passwd=XLUtils.getStringData(datafile, tssheet, j,6);
						lp.loginpage(uname, passwd);
						res=lp.isEmpModuleDisplayed();
						break;
						
					case "invalid login":
						uid=XLUtils.getStringData(datafile, tssheet, j, 5);
						pwd=XLUtils.getStringData(datafile, tssheet, j, 6);
						lp.loginpage(uid, pwd);
						res=lp.errmsgdisplay();
						break;
					}
					//update the results in step results
					if(res) {
						stepresult="Pass";
						XLUtils.setCellData(datafile, tssheet, j, 3, stepresult);
						XLUtils.setGreenColour(datafile, tssheet, j, 3);
					}
					else {
						stepresult="fail";
						XLUtils.setCellData(datafile, tssheet, j, 3,stepresult);
						XLUtils.setRedColour(datafile, tssheet, j, 3);
					}
					
					//update the results in test cases
					
				}
				else {
					XLUtils.setCellData(datafile, tcsheet, i, 3, "Blocked");
					XLUtils.setRedColour(datafile, tcsheet, i, 3);
				}
				
			}
		}
	}
	

}
