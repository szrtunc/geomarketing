package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DataRepositoryPage {
    public DataRepositoryPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='MuiBox-root css-1rgljgn']/div/button/div/h6")
    public List<WebElement> dataSets;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1rgljgn']")
    public WebElement dataSetVisibility;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1ngs2t9']/h6[text()='Customer Data']")
    public WebElement fileCustomerData;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-1ngs2t9\"]/h6[text()='Releated To Base Layer']")
    public WebElement fileRelatedToBaseLayer;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1ngs2t9']/h6[text()='Administrator Area']")
    public WebElement fileAdministratorArea;

    @FindBy(xpath = "//div[@class=\"css-11ywqt1\"]/img[@src='/images/file-upload.svg']")
    public WebElement fileUploadButton;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-1ngs2t9\"]/h6[text()='Customer Poligonal Spatial Data']")
    public WebElement fileCustomerPoligonalSpatialData;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-1ngs2t9\"]/h6[text()='Customer Point Spatial Data']")
    public WebElement fileCustomerPointSpatialData;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-1ngs2t9\"]/h6[text()='Hierarchy Data']")
    public WebElement fileHierarchyData;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-1ngs2t9\"]/h6[text()='Survey Data']")
    public WebElement fileSurveyData;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-1rgljgn\"]")
    public WebElement dataSetScroll;

    @FindBy(xpath = "//div[@id=\"notistack-snackbar\"]")
    public WebElement notification;

    @FindBy(xpath = "//div[@class='MuiBox-root css-4z6nar']")
    public WebElement loadedFilesPage;

    @FindBy(xpath = "//div[@class='css-11ywqt1']/div[2]/div/div/div/div[1]/div[1]/div/h6")
    public List <WebElement> loadedFiles;


    @FindBy(xpath = "//div[@class=\"css-11ywqt1\"]/div[2]/div/div/div/div[1]/div[1]/div")
    public WebElement loadedFilesX;

    @FindBy(xpath = "//div[@class=\"MuiBox-root css-4z6nar\"]/div/div")
    public List<WebElement> files;

    @FindBy(xpath = "//div[@class=\"css-crx196\"]/nav/ol/li[1]/p")
    public WebElement backDataFiles;

    @FindBy(xpath = "//div[@class=\"css-11ywqt1\"]/div[2]/div/div/div/div[1]/div[1]/div/h6")
    public WebElement newDataSetVisibility;

}
