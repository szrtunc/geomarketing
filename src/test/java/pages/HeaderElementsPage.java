package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HeaderElementsPage {
    public HeaderElementsPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(xpath = "//button[@tabindex='0' and @role='button']")
    public WebElement languageButton;

    @FindBy(xpath = "//div[@tabindex='-1']/li")
    public List<WebElement> languages;

    @FindBy(xpath = "//img[@alt='user']")
    public WebElement userImage;

    @FindBy(xpath = "//button[@aria-label='Ayarlar']")
    public WebElement settingsButton;

    @FindBy(xpath = "//ul[@role='menu']")
    public List<WebElement> userMenu;

    //@FindBy(xpath = "//a[@class='MuiBox-root css-150reob']")
    @FindBy(xpath = "//div[@class='MuiContainer-root MuiContainer-maxWidthXl css-1ekb41w']/div/a[1]")
    public WebElement geoMarketingLogo;

    @FindBy(xpath = "//a[ @tabindex='0' and @href='/overview']")
    public WebElement overviewButton;

    @FindBy(xpath = "//a[@href='/maps']")
    public WebElement mapsButton;

    @FindBy(xpath = "//a[@href='/repository']")
    public WebElement dataRepositoryButton;

    @FindBy(xpath = "//a[@href='/integration']")
    public WebElement integrationButton;





}
