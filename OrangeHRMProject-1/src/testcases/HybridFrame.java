package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import testlibrary.EmployeeRegistration;
import testlibrary.LoginApp;
import testlibrary.UserRegistration;
import utils.LaunchApp;
import utils.XLUtils;

public class HybridFrame extends LaunchApp {
	
	String datafile="D:\\selenium\\OrangeHRMProject-1\\src\\Datainf\\OrangeHRM.xlsx";
	String sheet1="TestCases";
	String sheet2="TestSteps";
	@Test
	public void hybrid() throws IOException, InterruptedException
	{
		String tcid,tsid,keyword;
		String uid,pwd,fname,lname;
		String role,empname,uname,pword;
		String stepresult,tcresult;
		boolean res=false;
		
		int s1count=XLUtils.getRowCount(datafile,sheet1);
		int s2count=XLUtils.getRowCount(datafile, sheet2);
		
		LoginApp lp = new LoginApp();
		EmployeeRegistration emprg = new EmployeeRegistration();
		UserRegistration userrg= new UserRegistration();
		
		for(int i=1;i<=s1count;i++) 
		{
			String exec=XLUtils.getStringData(datafile, sheet1, i, 2);
			if(exec.equalsIgnoreCase("y")) {
				tcid=XLUtils.getStringData(datafile, sheet1, i, 0);
				for(int j=1;j<=s2count;j++) {
					tsid=XLUtils.getStringData(datafile, sheet2, j, 0);
					if(tsid.equalsIgnoreCase(tcid)) {
						keyword=XLUtils.getStringData(datafile, sheet2, j, 4);
						switch(keyword.toLowerCase()) {
						case "adminlogin":
							uid=XLUtils.getStringData(datafile, sheet2, j, 5);
							pwd=XLUtils.getStringData(datafile, sheet2, j, 6);
							lp.loginpage(uid, pwd);
							res=lp.isAdminornot();
							break;
						case "logout":
							lp.logout();
							break;
						case "newempreg":
							fname=XLUtils.getStringData(datafile, sheet2, j, 5);
							lname=XLUtils.getStringData(datafile, sheet2, j, 6);
							res=emprg.empreg(fname, lname);
							break;
						case "newuserreg":
							role=XLUtils.getStringData(datafile, sheet2, j, 5);
							empname=XLUtils.getStringData(datafile, sheet2, j, 6);
							uname=XLUtils.getStringData(datafile, sheet2, j, 7);
							pword=XLUtils.getStringData(datafile, sheet2, j, 8);
							res=userrg.addUser(role, empname, uname, pword);
							break;
						case "emplogin":
							uname=XLUtils.getStringData(datafile, sheet2, j, 5);
							pword=XLUtils.getStringData(datafile, sheet2, j, 6);
							lp.loginpage(uname, pword);
							res=lp.isEmpModuleDisplayed();
							break;
						case "invalid login":
							uid=XLUtils.getStringData(datafile, sheet2, j, 5);
							pwd=XLUtils.getStringData(datafile, sheet2, j, 6);
							lp.loginpage(uid, pwd);
							res=lp.errmsgdisplay();
							break;
						}

						if(res) {
							stepresult="Pass";
							XLUtils.setCellData(datafile, sheet2, j, 3, stepresult);
							XLUtils.setGreenColour(datafile, sheet2, j, 3);
						}
						else {
							stepresult="fail";
							XLUtils.setCellData(datafile, sheet2, j, 3, stepresult);
							XLUtils.setRedColour(datafile, sheet2, j, 3);
						}
						
						//update the data in test case
						tcresult=XLUtils.getStringData(datafile, sheet1, i, 3);
						if(!tcresult.equalsIgnoreCase("fail")) 
						{
							XLUtils.setCellData(datafile, sheet1, i, 3, stepresult);
							
						}
						//set the fill colour
						tcresult=XLUtils.getStringData(datafile, sheet1, i, 3);
						if(tcresult.equalsIgnoreCase("Pass")) {
							XLUtils.setGreenColour(datafile, sheet1, i, 3);
						}
						else {
							XLUtils.setRedColour(datafile, sheet1, i, 3);
						}
						
					}
				}
			}
			else {
				XLUtils.setCellData(datafile, sheet1, i, 3, "Fail");
				XLUtils.setRedColour(datafile, sheet1, i, 3);
			}
		}
	}

}
