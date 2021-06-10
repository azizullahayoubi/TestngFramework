package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy (id="firstName")
    public WebElement firstName;


    @FindBy(id="middleName")
    public WebElement middleName;

    @FindBy(id="lastName")
    public WebElement lastName;

    @FindBy(id="employeeId")
    public WebElement employid;

    @FindBy(id="btnSave")
    public WebElement saveBtn;

    @FindBy(id="chkLogin")
    public WebElement checkBox;

    @FindBy(id="photofile")
    public WebElement photographs;

    @FindBy(id = "user_name")
    public WebElement username;

    @FindBy(id = "user_password")
    public WebElement password;

    @FindBy(id = "re_password")
    public WebElement passwordConfirm;


    public AddEmployeePage(){
        PageFactory.initElements(driver,this);
    }


}
