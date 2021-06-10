package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {
//       @Test
//    public void adminLogin(){
//           LoginPage loginPage= new LoginPage();
//           sendText(loginPage.usernamebox, ConfigReader.getPropertyValue("username"));
//           sendText(loginPage.passwordbox, ConfigReader.getPropertyValue("password"));
//           click(loginPage.loginBtn);
//
//
//           Dashboard dashboard=new Dashboard();
//           Assert.assertTrue(dashboard.welcomeMassage.isDisplayed(),"welcome message is not displayed");
//       }



       @Test(dataProvider = "invalidData",groups = "smoke")
    public  void  invalidLoginErrorMessageValidation(String userName, String password, String Message){
            LoginPage loginPage=new LoginPage();
            sendText(loginPage.usernamebox, userName);
            sendText(loginPage.passwordbox,password);
            click(loginPage.loginBtn);

            String actualeError = loginPage.errorMessage.getText();
            Assert.assertEquals(actualeError,Message, "Error is not matched");

       }

       @DataProvider
       public Object[] [] invalidData() {

           Object[][] data = {
                   {"test", "testone123", "Invalid credentials"},
                   {"Admin1", "Syntax123", "Invalid credentials"},
                   {"test", "", "Password cannot be empty"},
                   {"", "Syntax123", "Username cannot be empty"},

           };
           return data;
       }
}
