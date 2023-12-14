package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateMapPage {
    public CreateMapPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }


    @FindBy(xpath = "//div[@class='css-7guhfj']/button")
    public WebElement loadCustomerData;

    @FindBy(xpath = "//div[@class='css-1plwyby'][1]/button")
    public WebElement loadAdministrativeAreaData;

    @FindBy(xpath = "//div[@class='css-1plwyby'][2]/button")
    public WebElement loadHierarchyData;

    @FindBy(xpath = "//div[@class='css-l5c1s3']/div[3]")
    public WebElement load100;





}
