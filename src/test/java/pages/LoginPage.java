package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(xpath ="//div[@class='MuiBox-root css-1qm1lh']")
    public WebElement signInText;
    @FindBy(xpath ="//body")
    public WebElement body;

    @FindBy(xpath = "//input[@placeholder='Kullanıcı Adı']")
    public WebElement userNameBox;

    @FindBy(xpath = "//input[@placeholder='Parolanızı giriniz']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[text()='Parolamı Unuttum']")
    public WebElement forgotPasswordBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@href='/auth/register']")
    public WebElement registerButton;


}
