package pages;

import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MapPage {
    public MapPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(xpath = "//h6[@class='MuiTypography-root MuiTypography-subtitle1 css-iwx1s8']")
    public WebElement mapTitle;

    @FindBy(xpath = "//div[@class='css-1841xz2']/button")
    public WebElement closeButton;

    @FindBy(xpath = "//div[@class='css-dvxtzn']/button[1]")
    public WebElement zoomIlkButton;

    @FindBy(xpath = "//div[@class='css-dvxtzn']/button[2]")
    public WebElement zoomInButton;

    @FindBy(xpath = "//div[@class='css-dvxtzn']/button[3]")
    public WebElement zoomOutButton;

    //Filteler kısmı
    @FindBy(xpath = "//div[@class='css-1i1hwqo']/div[3]")
    public WebElement filterButton;

    @FindBy(xpath = "//div[@class='css-1i1hwqo']/div[3]/div/div[1]")
    public WebElement filters;

    @FindBy(xpath = "//div[@class='css-1i1hwqo']/div[3]/div/div[2]")
    public WebElement filterResults;

    @FindBy(xpath = "//div[@class='css-1lb32d1']/button")
    public WebElement newFilterButton;

    @FindBy(xpath = "//div[@class='css-1plj7pc']/div[2]/div/div[1]/div/div")
    public WebElement filterInputIL;

    @FindBy(xpath = "//div[@class='css-1plj7pc']/div[2]/div/div[2]/div/div/div/input")
    public WebElement filterInputILCE;

    @FindBy(xpath = "//div[@class='css-1plj7pc']/div[2]/div/div[3]/div/div/div/input")
    public WebElement filterInputMah;

    @FindBy(xpath = "//div[@class='css-6vn6bn']/div[1]/button")
    public WebElement filterOperationsButton;

    @FindBy(xpath = "//div[@class='css-6vn6bn']/div[2]/button")
    public WebElement filterContinueButton;

    @FindBy(xpath = "//div[@class='css-12bftmg']/button[1]")
    public WebElement filterPreview;

    @FindBy(xpath = "//div[@class='css-12bftmg']/button[2]")
    public WebElement filterClear;

    @FindBy(xpath = "//div[@class='css-12bftmg']/button[3]")
    public WebElement filterSaveButton;

    @FindBy(xpath = "//div[@class='css-12bftmg']/button[4]")
    public WebElement filterShowResults;

    @FindBy(xpath = "//div[@class='css-12bftmg']/button[5]")
    public WebElement filterSaveResults;

    @FindBy(xpath = "//div[@class='MuiDialogContent-root css-jkrf21']/div/div/input")
    public WebElement filterSaveInputName;

    @FindBy(xpath = "//div[@class='MuiBox-root css-1wxgbrp']/h4")
    public WebElement numberOfPointsListed;


    //İkinci buton içeriği
    @FindBy(xpath = "//div[@class='css-1i1hwqo']/div[2]")
    public WebElement secondButton;

    @FindBy(xpath = "//div[@class='css-1i1hwqo']/div[2]/div/div[5]")
    public WebElement layerControlButton;

    @FindBy(xpath = "//div[@class='css-r67ann']/div/div/h6")
    public List<WebElement> layers;

    @FindBy(xpath = "//h6[text()='Adres Arama']")
    public WebElement addressSearchButton;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='Test1Sezer']/parent::div//button[1]")
    public WebElement dataVisibilityDataSet;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='SezerKadikoy2']/parent::div//button[2]")
    public WebElement dataOptionsDataSet;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='IL']/parent::div//button[1]")
    public WebElement dataSetVisibilityIL;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='IL']/parent::div//button[2]")
    public WebElement dataSetOptionsIL;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='ILCE']/parent::div//button[1]")
    public WebElement dataSetVisibilityILCE;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='ILCE']/parent::div//button[2]")
    public WebElement dataSetOptionsILCE;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='MAHALLE']/parent::div//button[1]")
    public WebElement dataSetVisibilityMah;

    @FindBy(xpath = "//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='MAHALLE']/parent::div//button[2]")
    public WebElement dataSetOptionsMah;

    @FindBy(xpath = "//div[@class='css-5kb3ol']/div[2]/div/button[1]")
    public WebElement centerTheMap;

    @FindBy(xpath = "//div[@class='css-5kb3ol']/div[2]/div/button[2]")
    public WebElement getPointInformation;

    @FindBy(xpath = "//div[@class='css-5kb3ol']/div[2]/div/button[3]")
    public WebElement fieldDrawing;

    @FindBy(xpath = "//div[@class='css-5kb3ol']/div[2]/div/button[4]")
    public WebElement ruler;

    @FindBy(xpath = "//div[@class='css-5kb3ol']/div[2]/div/button[5]")
    public WebElement addPoint;

    @FindBy(xpath = "//div[@class='css-5kb3ol']/div[2]/div/button[6]")
    public WebElement cleaning;


    //Katmanların yanındaki seçenekler kısmı
    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[1]")
    public WebElement zoningButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[2]")
    public WebElement centerOnMapButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[3]")
    public WebElement openEditsButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[4]")
    public WebElement styleChangeButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[5]")
    public WebElement optionsButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[6]")
    public WebElement dataButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[7]")
    public WebElement groupButton;

    @FindBy(xpath = "//div[@class='css-1v3f2am']/div[2]/ul/li[8]")
    public WebElement deleteButton;

    @FindBy(xpath = "//ul[@class='MuiList-root MuiList-dense css-1uzmcsd']/li[8]")
    public WebElement layerDelete;

    @FindBy(xpath = "//div[@role='tablist']/button[1]")
    public WebElement styleButton;

    @FindBy(xpath = "//div[@role='tablist']/button[2]")
    public WebElement thematicButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-aidsh6']/div[2]/div[2]/div/div[1]/button[1]")
    public WebElement thematicGeneralButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-aidsh6']/div[2]/div[2]/div/div[1]/button[2]")
    public WebElement thematicTicketButton;

    @FindBy(xpath = "//div[@class='MuiAutocomplete-endAdornment css-2iz2x6']")
    public WebElement thematicTypeSelectButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-184rbpn']//div/div[3]/div/button")
    public WebElement thematicTypeVisibilityButton;

    @FindBy(xpath = "//div[@role='presentation']/div/ul/li")
    public List<WebElement> thematicTypeList;

    @FindBy(xpath = "//div[@role='presentation']/div/ul/li")
    public List<WebElement> thematicColumnList;

    @FindBy(xpath = "//div[@class='MuiBox-root css-0']/div/div")
    public WebElement thematicColumnSelectButton;

    @FindBy(xpath = "//button[text()='Sonuçları Haritaya Uygula']")
    public WebElement applyResultsMapButton;

    @FindBy(xpath = "//div[@class='MuiListItemIcon-root css-oy1s0i']/following-sibling::div//span[text()='Veri Grupla']")
    public WebElement dataGroupOptionButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-hpgf8j']/button")
    public WebElement dataGroupButton;

    @FindBy(xpath = "//div[@class='css-3nm9yt']/button[3]")
    public WebElement applyGroupToMapButton;

    @FindBy(xpath = "//div[@class='css-1vg6agc']/button")
    public WebElement addNewGroupColumnButton;

    @FindBy(xpath = "//div[@class='css-1yjo05o']//input[@placeholder='Seçim yapınız']")
    public WebElement selectGroupColumnName;


    @FindBy(xpath = "//div[@class='MuiBox-root css-aidsh6']/following-sibling::div[2]")
    public WebElement dataGroupVisibility;

    @FindBy(xpath = "//div[@class='react-resizable-handle']")
    public WebElement dataInformationPageUp;

    //Filtreler Kısmı
    @FindBy(xpath = "//div[@role='presentation']/div/ul/li")
    public List<WebElement> filterSelectILILCEMAHColumn;


    @FindBy(xpath = "//div[text()='Successfully deleted.']")
    public WebElement layerDeletedNotification;










}