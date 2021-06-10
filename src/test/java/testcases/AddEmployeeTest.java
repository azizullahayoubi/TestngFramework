package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddEmployeePage;
import pages.Dashboard;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcellFileReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

public class AddEmployeeTest extends CommonMethods {



    @Test (groups = "regression")
    public void addEmployee(){
        LoginPage loginPage=new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        Dashboard dashboard=new Dashboard();
        click(dashboard.PimOption);
        click(dashboard.addEmployeeBtn);

        AddEmployeePage add=new AddEmployeePage();

        sendText(add.firstName,"test12345");
        sendText(add.lastName,"test1234567");

        click(add.saveBtn);

    }

    @Test(groups = "regression")
    public void addMultipleEmployee() throws InterruptedException {
        LoginPage loginPage=new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        Dashboard dashboard=new Dashboard();
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        List<Map<String,String>> newEmployees= ExcellFileReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,"Sheet1");
        System.out.println(Constants.TESTDATA_FILEPATH);

        SoftAssert softAssert=new SoftAssert();
        Iterator <Map<String,String>> it= newEmployees.iterator();

         while (it.hasNext()){
             click(dashboard.PimOption);
             click(dashboard.addEmployeeBtn);

             Map<String,String> mapNewEmployee=it.next();

             sendText(addEmployeePage.firstName, mapNewEmployee.get("firstName"));
             sendText(addEmployeePage.middleName, mapNewEmployee.get("middleName"));
             sendText(addEmployeePage.lastName, mapNewEmployee.get("lastName"));
             String employeeIDValue =new AddEmployeePage().employid.getAttribute("value");
             sendText(addEmployeePage.photographs,mapNewEmployee.get("photographs"));
             if (!addEmployeePage.checkBox.isSelected()){
                click(addEmployeePage.checkBox);
             }
             EmployeeListPage employeeListPage=new EmployeeListPage();
              sendText(addEmployeePage.username,mapNewEmployee.get("userName"));
             sendText(addEmployeePage.password,mapNewEmployee.get("password"));
             sendText(addEmployeePage.passwordConfirm,mapNewEmployee.get("password"));
             click(addEmployeePage.saveBtn);
             // vavaget to the employee list
             click(dashboard.PimOption);
             click(dashboard.employeeList);
               waitForClickablity(employeeListPage.empSearchID);
               sendText(employeeListPage.empSearchID,employeeIDValue);
               click(employeeListPage.empSearchBtn);

               List<WebElement>rowData=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
             for (int i = 0; i < rowData.size(); i++) {
                 System.out.println("I am inside the loop");
                 String rowText=rowData.get(i).getText();
                 System.out.println(rowText);

                 Thread.sleep(1000);
                 String expectedEmployeeeDetails=employeeIDValue + " " + mapNewEmployee.get("FirstName")+ " "+ mapNewEmployee.get("MiddleName")+ " "+mapNewEmployee.get("LastName");

             }
         }
         softAssert .assertAll();
    }
}
