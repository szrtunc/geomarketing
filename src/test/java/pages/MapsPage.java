package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class MapsPage {
    public MapsPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='MuiBox-root css-178yklu']/div/div/div/div/h6")
    public List<WebElement> maps;

    @FindBy(xpath = "//div[@class='MuiBox-root css-69i1ev']/button")
    public WebElement newMapButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-mjbegz']/div[1]/div/input")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@class='MuiBox-root css-mjbegz']/div[1]/div/button")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@style=\"width: 100%; height: 100px;\"]")
    public WebElement pageEnd;
}
