package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UploadDataPage {
    public UploadDataPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(xpath = "//h6[@class='MuiTypography-root MuiTypography-subtitle1 css-1ikje50']")
    public WebElement loadDataScreenVisibility;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement loadToFileInput;

    @FindBy(xpath = "//input[@type='url']")
    public WebElement loadToUrlInput;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorError MuiIconButton-sizeSmall css-1evdsdt']")
    public WebElement fileDeleteButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1rr4qq7']")
    public WebElement fileVisibility;

    @FindBy(xpath = "//div[@class='css-ssb83w']/button[@tabindex='0'][2]")
    public WebElement loadContinueButton;

    @FindBy(xpath = "//div[@class='css-ssb83w']/button[@tabindex='0'][1]")
    public WebElement loadCancelButton;

    @FindBy(xpath = "//div[@class='css-1hwucl2']/h6")
    public WebElement percentValue;

    @FindBy(xpath = "//div[@class='css-ikzlcq']")
    public WebElement loadResultMenu;

    @FindBy(xpath = "//div[@class='MuiBox-root css-0']")
    public WebElement mapsColumnsVisibility;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div[1]/div[2]/select/option")
    public List<WebElement> tableWithUID;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/div/div[1]/div[2]")
    public WebElement tableWithUIDButton;

    @FindBy(xpath = "//div[@class='MuiFormControl-root MuiTextField-root css-i44wyl']/div/input")
    public WebElement aliasName;

    @FindBy(xpath = "//div[@class='MuiBox-root css-0']/div[2]/div[1]/div/div/select/option")
    public List<WebElement> latitudeColumn;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/div/div[3]/div[2]/div[1]/div/div")
    public WebElement latitudeColumnButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-0']/div[2]/div[2]/div/div/select/option")
    public List<WebElement> longitudeColumn;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/div/div[3]/div[2]/div[2]/div/div")
    public WebElement longitudeColumnButton;

    @FindBy(xpath = "//div[@class='MuiDialogActions-root MuiDialogActions-spacing css-1cz5dpq']/button[2]")
    public WebElement resultContinue;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/h6")
    public WebElement isLoaded;

    @FindBy(xpath = "//div[@class='MuiDialogActions-root MuiDialogActions-spacing css-1cz5dpq']/button")
    public WebElement completedButton;

    @FindBy(xpath = "//input[@class='MuiBox-root css-a88lvh' and @accept='.zip']")
    public WebElement uploadZipFile;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div")
    public WebElement columnsVisibility;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[1]/div/div[2]/select")
    public WebElement idColumnButton;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[1]/div/div[2]/select/option")
    public WebElement idColumn;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[2]/div/div[2]/select")
    public WebElement parentsColumnButton;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[2]/div/div[2]/select/option")
    public List<WebElement> parentsColumn;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[2]/div/div[2]/select")
    public WebElement displayNameColumnButton;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[3]/div/div[2]/select")
    public WebElement displayNameColumnButtonIL;

    @FindBy(xpath = "//div[@class=\"MuiDialogContent-root css-jkrf21\"]/div/div[2]/div/div[2]/select/option")
    public List<WebElement> displayNameColumn;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[3]/div/div[2]/select")
    public WebElement displayNameColumnButtonILCE;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[3]/div/div[2]/select/option")
    public List<WebElement> displayNameColumnILCE;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div")
    public List<WebElement> columnCount;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[2]/div/div[2]/select")
    public WebElement baseLayerColumn;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[3]/div/div[2]/select")
    public WebElement relatedUIDColumn;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[4]/div/div[2]/select")
    public WebElement surveyQuestionColumn;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[5]/div/div[2]/select")
    public WebElement surveyAnswerColumn;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div[6]/div/div[2]/select")
    public WebElement surveyGroupingColumn;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[3]/div/div[2]/select")
    public WebElement hierarchyColumnPoint;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[4]/div/div[2]/select")
    public WebElement hierarchyColumnRelationship;

    @FindBy(xpath = "//div[@class='css-u4p24i']/button")
    public WebElement hierarchyNewLevelButton;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[2]/div/div/select")
    public WebElement hierarchyLevel2Column;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[2]/button")
    public WebElement hierarchyLevel2Clear;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[3]/div/div/select")
    public WebElement hierarchyLevel3Column;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[3]/button")
    public WebElement hierarchyLevel3Clear;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[4]/div/div/select")
    public WebElement hierarchyLevel4Column;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[4]/button")
    public WebElement hierarchyLevel4Clear;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[5]/div/div/select")
    public WebElement hierarchyLevel5Column;

    @FindBy(xpath = "//div[@class='css-ikzlcq']/div/div[5]/div/div[5]/button")
    public WebElement hierarchyLevel5Clear;















}
