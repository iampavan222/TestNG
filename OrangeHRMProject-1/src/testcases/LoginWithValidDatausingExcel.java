package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import testlibrary.LoginApp;
import utils.LaunchApp;
import utils.XLUtils;

public class LoginWithValidDatausingExcel extends LaunchApp 
{

	public static String xlfile="D:\\selenium\\OrangeHRMProject-1\\src\\Datainf\\SampleUserData.xlsx";
	public static String xlsheet="InfoData";

	@Test
	public static void AdminLogin() throws IOException 
	{
		LoginApp lp= new LoginApp();
		String uid,pwd;

		int rcount=XLUtils.getRowCount(xlfile, xlsheet);

		for(int i=1;i<=rcount;i++) 
		{
			uid=XLUtils.getStringData(xlfile, xlsheet, i, 0);
			pwd=XLUtils.getStringData(xlfile, xlsheet, i, 1);
			lp.loginpage(uid, pwd);
			try {
				boolean res=lp.isAdminornot();
				Assert.assertTrue(res);

				if(res) 
				{
					XLUtils.setCellData(xlfile, xlsheet, i, 2, "Pass");
					XLUtils.setGreenColour(xlfile, xlsheet, i, 2);
					lp.logout();
				}else
				{
					XLUtils.setCellData(xlfile, xlsheet, i, 2, "Fail");
					XLUtils.setRedColour(xlfile, xlsheet, i, 2);	
				}
			}catch(Exception E) 
			{
				boolean res=lp.errmsgdisplay();
				if(res) {
					XLUtils.setCellData(xlfile, xlsheet, i, 2, "Fail");
					XLUtils.setRedColour(xlfile, xlsheet, i, 2);
				}
			}
		}

	}

}
