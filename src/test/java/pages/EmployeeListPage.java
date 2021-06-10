package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.CommonMethods;

public class EmployeeListPage extends CommonMethods {


    @FindBy(id="empsearch_id")
    public WebElement empSearchID;

    @FindBy(id="searchBtn")
    public WebElement empSearchBtn;

    public EmployeeListPage(){
        PageFactory.initElements(driver,this);
    }
}
