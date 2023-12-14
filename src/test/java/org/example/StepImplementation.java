package org.example;


import com.thoughtworks.gauge.Step;
import driver.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;

import java.time.Duration;

public class StepImplementation {

    WebDriver driver;
    LoginPage loginPage = new LoginPage();
    CreateMapPage createMapPage = new CreateMapPage();
    UploadDataPage uploadDataPage = new UploadDataPage();
    MapsPage mapsPage = new MapsPage();
    HeaderElementsPage headerPage = new HeaderElementsPage();
    DataRepositoryPage dataRepositoryPage = new DataRepositoryPage();
    MapPage mapPage = new MapPage();
    WebDriverWait wait;

    @Step("Go to GeoMarketing")
    public void gotoGetStartedPage() throws InterruptedException {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
        loginPage.signInText.isDisplayed();

    }

    @Step("Login")
    public void login() throws InterruptedException {
        loginPage.userNameBox.sendKeys(ConfigReader.getProperty("username"));
        loginPage.passwordBox.sendKeys(ConfigReader.getProperty("password"));
        loginPage.loginButton.click();
    }

    @Step("Sayfadaki tüm dilleri döndür.")
    public void languagesLoop() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        headerPage.languageButton.click();
        for (int i = 0; i < headerPage.languages.size(); i++) {
            try {
                //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement language = wait.until(ExpectedConditions.elementToBeClickable(headerPage.languages.get(i)));
                language.click();
                System.out.println(language.getText());
                //wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                wait.until(ExpectedConditions.elementToBeClickable(headerPage.languageButton)).click();
                Thread.sleep(1000);
            } catch (ElementClickInterceptedException e) {
                e.printStackTrace();
                System.out.println("Diller kısmı test edilirken hata olustu");
            }
        }
    }

    @Step("Sayfayı<dil> yap")
    public void selectLanguage(String dil) throws InterruptedException {
        for (WebElement x : headerPage.languages) {
            if (x.getText().equalsIgnoreCase(dil)) {
                x.click();
            }
        }
    }

    @Step("Point verilerini yükle," +
            " TableWithUID : <tableWithUID>" +
            " dataAliasName : Config Dosyasında Verildi " +
            " Latitude Column : <latitude>" +
            " Longitude Column : <longitude>")
    public void loadData(String tableWithUID, String latitude, String longitude) throws InterruptedException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        createMapPage.loadCustomerData.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        createMapPage.loadCustomerData.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOf(uploadDataPage.loadToUrlInput));
        uploadDataPage.loadToFileInput.sendKeys("C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\point_kadikoy.xlsx");
        wait.until(ExpectedConditions.visibilityOf(uploadDataPage.fileVisibility));
        uploadDataPage.loadContinueButton.click();
        //wait.until(ExpectedConditions.textToBePresentInElement(uploadDataPage.percentValue, "100%"));
        wait.until(ExpectedConditions.elementToBeClickable(uploadDataPage.tableWithUIDButton));

        uploadDataPage.tableWithUIDButton.click();
        for (WebElement uID : uploadDataPage.tableWithUID) {
            if (uID.getText().equalsIgnoreCase(tableWithUID)) {
                uID.click();
            }
        }
        uploadDataPage.aliasName.sendKeys(ConfigReader.getProperty("dataAliasName"));

        uploadDataPage.latitudeColumnButton.click();
        for (WebElement ltd : uploadDataPage.latitudeColumn) {
            if (ltd.getText().equalsIgnoreCase(latitude)) {
                ltd.click();
            }
        }
        uploadDataPage.longitudeColumnButton.click();
        for (WebElement lng : uploadDataPage.longitudeColumn) {
            if (lng.getText().equalsIgnoreCase(longitude)) {
                lng.click();
            }
        }
        uploadDataPage.resultContinue.click();

        try {
            wait.until(ExpectedConditions.visibilityOf(uploadDataPage.isLoaded));
            System.out.println("Veriseti Yüklendi");
        } catch (Exception e) {
            System.out.println("Veriseti yüklenemedi");
        }
        uploadDataPage.completedButton.click();
        wait.until(ExpectedConditions.visibilityOf(createMapPage.load100));
    }

    @Step("Haritalar sayfasına git haritayı kontrol et")
    public void checkMap() throws InterruptedException {
        goMapsPageAndClickMap();
        driver.navigate().back();
    }

    @Step("Logoya tıkla")
    public void clickLogo() throws InterruptedException {
        //String href = headerPage.geoMarketingLogo.getAttribute("href");
        //driver.get(href);
        headerPage.geoMarketingLogo.click();
    }

    @Step("Haritayı Aç")
    public void openMap() throws InterruptedException {
        goMapsPageAndClickMap();
    }

    @Step("Oluşturulan Veri Setini Bul Ve Aç")
    public void searchDataSetStep() throws InterruptedException {
        headerPage.dataRepositoryButton.click();
        searchDataSet();
    }

    @Step("İdari Alana Veri Setlerini Sırayla Yükle Ve Sorunsuz Şekilde Yüklendi Mi? Kontrol et")
    public void checkAndUploadDataSet() throws InterruptedException {
        dataRepositoryPage.fileAdministratorArea.click();
        dataRepositoryPage.fileUploadButton.isDisplayed();
        try {
            loadDataSetIL();
            System.out.println(ConfigReader.getProperty("dataSetAliasNameIL") + " verisi YUKLENDI");
            searchDataSet();
        } catch (Exception e) {
            System.out.println(ConfigReader.getProperty("dataSetAliasNameIL") + " verisi YÜKLENEMEDİ!!!");
        }
        wait.until(ExpectedConditions.visibilityOf(dataRepositoryPage.fileAdministratorArea));
        dataRepositoryPage.fileAdministratorArea.click();
        if (dataRepositoryPage.loadedFilesPage.isDisplayed()) {
            for (WebElement fileIL : dataRepositoryPage.loadedFiles) {
                if (fileIL.getText().equalsIgnoreCase(ConfigReader.getProperty("dataSetAliasNameIL"))) {
                    try {
                        loadDataSetILCE();
                        System.out.println(ConfigReader.getProperty("dataSetAliasNameILCE") + " verisi YUKLENDI");
                        searchDataSet();
                        //wait=new WebDriverWait(driver,Duration.ofSeconds(60));
                        //wait.until(ExpectedConditions.elementToBeClickable(dataRepositoryPage.fileAdministratorArea));
                        dataRepositoryPage.fileAdministratorArea.click();
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        System.out.println(ConfigReader.getProperty("dataSetAliasNameILCE") + " verisi YÜKLENEMEDİ!!!");
                    }
                    if (dataRepositoryPage.loadedFilesPage.isDisplayed()) {
                        for (WebElement fileILCE : dataRepositoryPage.loadedFiles) {
                            for (int i = 0; i < dataRepositoryPage.loadedFiles.size(); i++) {
                                if (fileILCE.getText().equalsIgnoreCase(ConfigReader.getProperty("dataSetAliasNameILCE"))) {
                                    loadDataSetMah();
                                    System.out.println(ConfigReader.getProperty("dataSetAliasNameMah") + " verisi YUKLENDI");
                                    searchDataSet();
                                    //wait=new WebDriverWait(driver,Duration.ofSeconds(60));
                                    wait.until(ExpectedConditions.elementToBeClickable(dataRepositoryPage.fileAdministratorArea));
                                    dataRepositoryPage.fileAdministratorArea.click();
                                    break;
                                } else {
                                    System.out.println(ConfigReader.getProperty("dataSetAliasNameMah") + " verisi YÜKLENEMEDİ!!!");
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }

    @Step("Survey Verisini Yükleme")
    public void surveyDataUpload() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        dataRepositoryPage.backDataFiles.click();
        dataRepositoryPage.fileSurveyData.click();
        dataRepositoryPage.fileUploadButton.click();
        uploadDataPage.loadDataScreenVisibility.isDisplayed();
        uploadDataPage.loadToFileInput.sendKeys("C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\survey_kadikoy.xlsx");
        uploadDataPage.loadContinueButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(uploadDataPage.aliasName)).sendKeys(ConfigReader.getProperty("surveyDataName"));

        selectByValue(new Select(uploadDataPage.baseLayerColumn), ConfigReader.getProperty("surveyBaseLayerValue"));
        selectByValue(new Select(uploadDataPage.relatedUIDColumn), ConfigReader.getProperty("surveyRelatedUidValue"));
        selectByValue(new Select(uploadDataPage.surveyQuestionColumn), ConfigReader.getProperty("surveyQuestionValue"));
        selectByValue(new Select(uploadDataPage.surveyAnswerColumn), ConfigReader.getProperty("surveyAnswerValue"));
        selectByValue(new Select(uploadDataPage.surveyGroupingColumn), ConfigReader.getProperty("surveyGroupingValue"));
        uploadDataPage.resultContinue.click();
        Thread.sleep(3000);

    }

    @Step("Survey Verisi Yüklendi Mi?")
    public void checkSurveyData() throws InterruptedException {
        while (true) {
            try {
                driver.navigate().refresh();
                searchDataSet();
                dataRepositoryPage.fileSurveyData.click();
                dataRepositoryPage.newDataSetVisibility.isDisplayed();
                Assert.assertTrue(dataRepositoryPage.newDataSetVisibility.getText().equalsIgnoreCase(ConfigReader.getProperty("surveyDataName")));
                System.out.println(ConfigReader.getProperty("surveyDataName") + " Survey datasi BULUNDU");
                break;
            } catch (Exception e) {
                System.out.println(ConfigReader.getProperty("surveyDataName") + ": Survey datasi BULUNAMADI!!!");
                Thread.sleep(20000);
            }
        }
    }

    @Step("Hierarchy Verisini Yükleme")
    public void hierarchyDataUpload() throws InterruptedException {
        dataRepositoryPage.backDataFiles.click();
        dataRepositoryPage.fileHierarchyData.click();
        dataRepositoryPage.fileUploadButton.click();
        createMapPage.loadHierarchyData.click();
        uploadDataPage.loadDataScreenVisibility.isDisplayed();
        uploadDataPage.loadToFileInput.sendKeys("C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\kadikoy_hiyerarsi.xlsx");
        uploadDataPage.loadContinueButton.click();
        uploadDataPage.hierarchyColumnRelationship.isDisplayed();

        selectByValue(new Select(uploadDataPage.hierarchyColumnRelationship), ConfigReader.getProperty("hierarchyRelationShipValue"));
        selectByValue(new Select(uploadDataPage.hierarchyColumnPoint), ConfigReader.getProperty("hierarchyPointValue"));
        uploadDataPage.hierarchyNewLevelButton.click();
        selectByValue(new Select(uploadDataPage.hierarchyLevel2Column), ConfigReader.getProperty("hierarchyLevel2"));
        uploadDataPage.hierarchyNewLevelButton.click();
        selectByValue(new Select(uploadDataPage.hierarchyLevel3Column), ConfigReader.getProperty("hierarchyLevel3"));
        uploadDataPage.hierarchyNewLevelButton.click();
        selectByValue(new Select(uploadDataPage.hierarchyLevel4Column), "şef");
        uploadDataPage.hierarchyNewLevelButton.click();
        selectByValue(new Select(uploadDataPage.hierarchyLevel5Column), ConfigReader.getProperty("hierarchyLevel5"));

        uploadDataPage.aliasName.sendKeys(ConfigReader.getProperty("hierarchyAliasName"));
        uploadDataPage.resultContinue.click();
        Thread.sleep(3000);
    }

    @Step("Hierarchy Verisi Yüklendi Mi?")
    public void checkHierarchyData() throws InterruptedException {
        String href = headerPage.geoMarketingLogo.getAttribute("href");
        driver.get(href);
        while (true) {
            try {
                driver.navigate().refresh();
                searchDataSet();
                dataRepositoryPage.fileHierarchyData.click();
                dataRepositoryPage.newDataSetVisibility.isDisplayed();
                Assert.assertTrue(dataRepositoryPage.newDataSetVisibility.getText().equalsIgnoreCase(ConfigReader.getProperty("hierarchyAliasName")));
                System.out.println(ConfigReader.getProperty("hierarchyAliasName") + " Hierarchy datasi BULUNDU");
                break;
            } catch (Exception e) {
                System.out.println(ConfigReader.getProperty("hierarchyAliasName") + ": Hierarchy datasi BULUNAMADI!!!");
                Thread.sleep(30000);
            }
        }
    }

    @Step("Katmanlar Sorunsuz Şekilde Yüklendi Mi?")
    public void checkLayers() throws InterruptedException {
        mapPage.mapTitle.isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(mapPage.secondButton).perform();
        mapPage.layerControlButton.click();
        checkLayerMethod(ConfigReader.getProperty("dataAliasName"));
        checkLayerMethod(ConfigReader.getProperty("dataSetAliasNameIL"));
        checkLayerMethod(ConfigReader.getProperty("dataSetAliasNameILCE"));
        checkLayerMethod(ConfigReader.getProperty("dataSetAliasNameMah"));
        mapPage.closeButton.click();
    }

    @Step("Katmanları Göster Ve Gizle")
    public void layersShow() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(mapPage.secondButton).perform();
        mapPage.layerControlButton.click();
        //Katmanları görünür yap
        layerShowClickMethod(ConfigReader.getProperty("dataAliasName"));
        layerShowClickMethod(ConfigReader.getProperty("dataSetAliasNameIL"));
        layerShowClickMethod(ConfigReader.getProperty("dataSetAliasNameILCE"));
        layerShowClickMethod(ConfigReader.getProperty("dataSetAliasNameMah"));

        //Katmanları gizle
        layerHidingClickMethod(ConfigReader.getProperty("dataAliasName"));
        layerHidingClickMethod(ConfigReader.getProperty("dataSetAliasNameIL"));
        layerHidingClickMethod(ConfigReader.getProperty("dataSetAliasNameILCE"));
        layerHidingClickMethod(ConfigReader.getProperty("dataSetAliasNameMah"));
    }

    @Step("Katman Silme")
    public void layersClear() throws InterruptedException {
        //Mahalle katmanını sil.
        layerDeleteMethod(ConfigReader.getProperty("dataSetAliasNameMah"));
    }

    @Step("<thematicType> Türünde Tematik Oluşturma")
    public void thematicCreate(String thematicType) throws InterruptedException {
        //clickLogo();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        layerOptionsClickMethod(ConfigReader.getProperty("dataSetAliasNameMah"));
        mapPage.styleChangeButton.click();
        mapPage.thematicButton.isDisplayed();
        mapPage.thematicButton.click();
        mapPage.thematicGeneralButton.click();
        try {
            mapPage.thematicTypeSelectButton.isDisplayed();
            mapPage.thematicTypeSelectButton.click();
        } catch (Exception e) {
            mapPage.thematicTypeVisibilityButton.click();
        }
        for (WebElement type : mapPage.thematicTypeList) {
            if (type.getText().equalsIgnoreCase(thematicType)) {
                type.click();
                break;
            }
        }

        if (thematicType.equalsIgnoreCase("Tekil Değerler")) {
            wait.until(ExpectedConditions.visibilityOf(mapPage.thematicColumnSelectButton));
            mapPage.thematicColumnSelectButton.click();
            for (WebElement columnValue : mapPage.thematicColumnList) {
                if (columnValue.getText().contains("MAHALLE")) {
                    columnValue.click();
                    break;
                }
            }
        }
        if (thematicType.equalsIgnoreCase("Eşit Aralıklı")) {
            wait.until(ExpectedConditions.visibilityOf(mapPage.thematicColumnSelectButton));
            mapPage.thematicColumnSelectButton.click();
            for (WebElement columnValue : mapPage.thematicColumnList) {
                if (columnValue.getText().contains("KAYIT_NO")) {
                    columnValue.click();
                    break;
                }
            }
        }
        mapPage.applyResultsMapButton.click();
    }

    @Step("Customize Layer")
    public void layerCustomize() {
        //burayı doldur...
    }

    @Step("Yeni Filtre Olusturmaya Git")
    public void createFilter() throws InterruptedException {
        Actions actionsFilter = new Actions(driver);
        actionsFilter.moveToElement(mapPage.filterButton).perform();
        mapPage.filters.click();
        mapPage.newFilterButton.click();
    }

    @Step("İdari Alan Filtresi Oluşturma")
    public void administrativeBorder() throws InterruptedException {
        filterSelectIL("İstanbul");
        filterSelectILCE("Kadıköy");
        filterSelectMAH("19 Mayıs Mh.");
        mapPage.filterOperationsButton.click();
        mapPage.filterPreview.click();
    }


    //METHODS
    public void filterSelectIL(String ilName) {
        mapPage.filterInputIL.click();
        for (WebElement filterIl : mapPage.filterSelectILILCEMAHColumn) {
            if (filterIl.getText().equalsIgnoreCase(ilName)) {
                filterIl.click();
                break;
            }
        }
    }

    public void filterSelectILCE(String ilceName) {
        mapPage.filterInputILCE.click();
        for (WebElement filterIlce : mapPage.filterSelectILILCEMAHColumn) {
            if (filterIlce.getText().equalsIgnoreCase(ilceName)) {
                filterIlce.click();
                break;
            }
        }
    }

    public void filterSelectMAH(String mahName) {
        mapPage.filterInputMah.click();
        for (WebElement filterMah : mapPage.filterSelectILILCEMAHColumn) {
            if (filterMah.getText().equalsIgnoreCase(mahName)) {
                filterMah.click();
                break;
            }
        }
    }

    public void loadDataSet(String idColumn, String displayNameColumn, String aliasNameColumn, String parentColumn, String path) throws InterruptedException {

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        dataRepositoryPage.fileUploadButton.click();
        uploadDataPage.loadDataScreenVisibility.isDisplayed();
        uploadDataPage.uploadZipFile.sendKeys(path);
        uploadDataPage.loadContinueButton.click();
        String storedAliasName = aliasNameColumn;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(uploadDataPage.idColumnButton));
        } catch (Exception e) {
            System.out.println("Dosya inceleniyor aşamasını geçemedi, 60 saniye bekledi. ");
        }

        Select idColumns = new Select(uploadDataPage.idColumnButton);
        idColumns.selectByValue(idColumn);
        uploadDataPage.idColumnButton.sendKeys(Keys.TAB);

        if (uploadDataPage.columnCount.size() == 4) {
            Select parentColumns = new Select(uploadDataPage.parentsColumnButton);
            parentColumns.selectByValue(parentColumn);
            uploadDataPage.parentsColumnButton.sendKeys(Keys.TAB);

            Select displayNameColumnsIL = new Select(uploadDataPage.displayNameColumnButtonIL);
            displayNameColumnsIL.selectByValue(displayNameColumn);
        } else {
            Select displayNameColumns = new Select(uploadDataPage.displayNameColumnButton);
            displayNameColumns.selectByValue(displayNameColumn);
        }
        uploadDataPage.aliasName.sendKeys(aliasNameColumn);
        uploadDataPage.resultContinue.click();
        dataRepositoryPage.notification.isDisplayed();
        Thread.sleep(35000);

    }

    public void loadDataSetIL() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdIL"), ConfigReader.getProperty("dataSetDisplayNameIL"), ConfigReader.getProperty("dataSetAliasNameIL"), null, "C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\IL_KADIKOY_region.zip");
    }

    public void loadDataSetILCE() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdILCE"), ConfigReader.getProperty("dataSetDisplayNameILCE"), ConfigReader.getProperty("dataSetAliasNameILCE"), ConfigReader.getProperty("parentColumnILCE"), "C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\ILCE_KADIKOY_region.zip");
    }

    public void loadDataSetMah() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdMah"), ConfigReader.getProperty("dataSetDisplayNameMah"), ConfigReader.getProperty("dataSetAliasNameMah"), ConfigReader.getProperty("parentColumnMah"), "C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\MAHALLE_KADIKOY_region.zip");
    }

    public void selectByValue(Select select, String value) {
        select.selectByValue(value);
    }

    public void refreshAndPerformSearch() throws InterruptedException {
        //driver.navigate().refresh();
        searchDataSet();
        //dataRepositoryPage.fileAdministratorArea.click();
    }

    public void goMapsPageAndClickMap() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        headerPage.mapsButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(mapsPage.maps));
        } catch (Exception e) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfAllElements(mapsPage.maps));
        }

        while (true) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String scroolheight = js.executeScript("return document.body.scrollHeight").toString();
            //System.out.println(scroolheight);

            for (WebElement map : mapsPage.maps) {
                if (map.getText().equalsIgnoreCase(ConfigReader.getProperty("dataAliasName"))) {
                    map.click();
                    System.out.println("----Map ACILDI----");
                    Thread.sleep(3000);
                    return;
                }
            }
            WebElement enAltElement = mapsPage.maps.get(mapsPage.maps.size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", enAltElement);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(scroolheight.equals( js.executeScript("return document.body.scrollHeight").toString()));
            if (scroolheight.equals(js.executeScript("return document.body.scrollHeight").toString())) {
                driver.navigate().refresh();
            }
        }

    }

    public void checkLayerMethod(String layerName) throws InterruptedException {
        boolean layerFound = false;
        for (WebElement layer : mapPage.layers) {
            if (layer.getText().equalsIgnoreCase(layerName)) {
                System.out.println(layerName + " KATMANI BULUNDU");
                layerFound = true;
                break;
            }
        }
        if (layerFound == false) {
            System.out.println(layerName + " KATMANI BULUNAMADI!!!");
        }
    }

    public void layerShowClickMethod(String layerName) throws InterruptedException {
        //dynamic Xpath
        driver.findElement(By.xpath("//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='" + layerName + "']/parent::div//button[1]"))
                .click();
        Thread.sleep(500);
        WebElement layerVisibilityNotification = driver.findElement(By.xpath("//div[text()='" + layerName + " katmanı gösterildi']"));
        String layerVMessage = (layerVisibilityNotification.isDisplayed()) ? layerName + " (Katmani Gosterildi)" : layerName + "Katmani GOSTERILEMEDI.....!!!";
        System.out.println(layerVMessage);
    }

    public void layerHidingClickMethod(String layerName) throws InterruptedException {
        driver.findElement(By.xpath("//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='" + layerName + "']/parent::div//button[1]"))
                .click();
        Thread.sleep(500);
        WebElement layerHidingNotification = driver.findElement(By.xpath("//div[text()='" + layerName + " katmanı gizlendi']"));
        String layerHMessage = (layerHidingNotification.isDisplayed()) ? layerName + " (Katmani Gizlendi)" : layerName + "Katmani GIZLENEMEDI.....!!!";
        System.out.println(layerHMessage);
    }

    public void layerOptionsClickMethod(String layerName) throws InterruptedException {
        driver.findElement(By.xpath("//div[contains(@class,'MuiBox-root')]//div[@draggable]//h6[text()='" + layerName + "']/parent::div//button[2]")).click();
    }

    public void layerDeleteMethod(String layerName) throws InterruptedException {
        layerOptionsClickMethod(layerName);
        mapPage.layerDelete.click();
        if (mapPage.layerDeletedNotification.isDisplayed()) {
            System.out.println(layerName + "Katmani Basariyla Silindi");
        } else {
            System.out.println(layerName + "Katmani Silinemedi");
        }
    }

    public void searchDataSet() throws InterruptedException {
        //headerPage.dataRepositoryButton.click();
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.navigate().refresh();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(dataRepositoryPage.dataSetVisibility));
        while (true) {
            for (WebElement dataset : dataRepositoryPage.dataSets) {
                if (dataset.getText().equalsIgnoreCase(ConfigReader.getProperty("dataAliasName"))) {
                    System.out.println("dataset bulundu :" + ConfigReader.getProperty("dataAliasName"));
                    dataset.click();
                    return;
                }
            }
            WebElement enAltElement = dataRepositoryPage.dataSets.get(dataRepositoryPage.dataSets.size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", enAltElement);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("GeoCode verisini yükle")
    public void loadGeoCode() throws InterruptedException {
        createMapPage.loadCustomerData.click();
        try {
            uploadDataPage.loadDataScreenVisibility.isDisplayed();
        }catch(Exception e){
            createMapPage.loadCustomerData.click();
            Thread.sleep(5000);
        }
        uploadDataPage.loadToFileInput.sendKeys("C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\point_kadikoy_0.xlsx");
        uploadDataPage.loadContinueButton.click();
        selectByValue(new Select(uploadDataPage.tableWithUIDGeoCode),"gvg_uid");



    }
/*
    @Step("debug")
    public void implementation1() {
        System.out.println(ConfigReader.getProperty("hierarchyLevel4"));

    }*/
}


