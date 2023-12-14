package pages;


import driver.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage {
    public OverviewPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }


}
