package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods {


    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setUp() throws IOException {
        ConfigReader.readProperties(Constants.configuration_FilePath);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                // System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Fairfax":
                //System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid name of browser");
        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    public static void waitForClickablity(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickablity(element);
        element.click();
    }

    public static JavascriptExecutor getJsExicutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJsExicutor().executeScript("arguments[0].click()", element);

    }

    public static void takeScreeshot(String fileName) {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH +
                    fileName+" "+getTimeStamp("YYYY-MM-dd-HH-mm-ss")+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTimeStamp(String pattern){
        Date date =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        return  simpleDateFormat.format(date);
    }


        @AfterMethod(alwaysRun = true)
        public void closBrowser () {

            driver.quit();
        }

}