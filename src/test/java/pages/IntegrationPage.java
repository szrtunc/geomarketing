package pages;

import driver.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public class IntegrationPage {
    public IntegrationPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }



}
