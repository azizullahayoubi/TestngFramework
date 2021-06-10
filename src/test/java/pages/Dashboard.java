package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class Dashboard extends CommonMethods {
    @FindBy (id="welcome")
    public WebElement welcomeMassage;

    @FindBy (id ="menu_pim_viewPimModule")
    public WebElement PimOption;

    @FindBy(id="menu_pim_addEmployee")
    public WebElement addEmployeeBtn;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeList;



    public Dashboard(){
        PageFactory.initElements(driver,this);



    }

}
