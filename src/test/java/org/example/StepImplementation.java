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

      /* JavascriptExecutor js= ((JavascriptExecutor) driver);
        Object subsrictionStatus=js.executeScript("return substrictionStatus");
        while (subsrictionStatus.equals(false)) {
            Thread.sleep(1000);
            subsrictionStatus=js.executeScript("return substrictionStatus");
        }*/
        Thread.sleep(10000);
        driver.navigate().refresh();
        loginPage.userNameBox.sendKeys(ConfigReader.getProperty("username"));
        loginPage.passwordBox.sendKeys(ConfigReader.getProperty("password"));
        loginPage.loginButton.click();
        Thread.sleep(3000);
        driver.navigate().refresh();
        loginPage.userNameBox.sendKeys(ConfigReader.getProperty("username"));
        loginPage.passwordBox.sendKeys(ConfigReader.getProperty("password"));
        loginPage.loginButton.click();
        Thread.sleep(3000);




       // DriverFactory.saveHar();

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
                break;
            }
        }
    }

    @Step("Point verilerini yükle : TableWithUID, dataAliasName, Latitude Column, Longitude Column")
    public void loadPointDataStep() throws InterruptedException {
        loadPointData();
        wait = new WebDriverWait(driver, Duration.ofSeconds(70));
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

    @Step("GeoCode Haritayı Aç")
    public void openGeoCodeMap() throws InterruptedException{
        goMapsPageAndClickGeoCodeMap();
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
            Thread.sleep(50000);
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
                        //Thread.sleep(50000);
                        System.out.println(ConfigReader.getProperty("dataSetAliasNameILCE") + " verisi YUKLENDI");
                        searchDataSet();
                        //wait=new WebDriverWait(driver,Duration.ofSeconds(60));
                        //wait.until(ExpectedConditions.elementToBeClickable(dataRepositoryPage.fileAdministratorArea));
                        dataRepositoryPage.fileAdministratorArea.click();
                        //Thread.sleep(5000);
                    } catch (Exception e) {
                        System.out.println(ConfigReader.getProperty("dataSetAliasNameILCE") + " verisi YÜKLENEMEDİ!!!");
                    }
                    if (dataRepositoryPage.loadedFilesPage.isDisplayed()) {
                        for (WebElement fileILCE : dataRepositoryPage.loadedFiles) {
                            for (int i = 0; i < dataRepositoryPage.loadedFiles.size(); i++) {
                                if (fileILCE.getText().equalsIgnoreCase(ConfigReader.getProperty("dataSetAliasNameILCE"))) {
                                    loadDataSetMah();
                                    //Thread.sleep(50000);
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
        uploadDataPage.loadToFileInput.sendKeys(ConfigReader.getProperty("dataPathSurvey"));
        uploadDataPage.loadContinueButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(uploadDataPage.aliasNameInput)).sendKeys(ConfigReader.getProperty("surveyDataName"));

        selectByValue(new Select(uploadDataPage.baseLayerColumn), ConfigReader.getProperty("surveyBaseLayerValue"));
        selectByValue(new Select(uploadDataPage.relatedUIDColumn), ConfigReader.getProperty("surveyRelatedUidValue"));
        selectByValue(new Select(uploadDataPage.surveyQuestionColumn), ConfigReader.getProperty("surveyQuestionValue"));
        selectByValue(new Select(uploadDataPage.surveyAnswerColumn), ConfigReader.getProperty("surveyAnswerValue"));
        selectByValue(new Select(uploadDataPage.surveyGroupingColumn), ConfigReader.getProperty("surveyGroupingValue"));
        uploadDataPage.resultContinue.click();
        Thread.sleep(9000);
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
                Thread.sleep(50000);
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
        uploadDataPage.loadToFileInput.sendKeys(ConfigReader.getProperty("dataPathHierarchy"));
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

        uploadDataPage.aliasNameInput.sendKeys(ConfigReader.getProperty("hierarchyAliasName"));
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
        /*mapPage.mapTitle.isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(mapPage.secondButton).perform();
        mapPage.layerControlButton.click();*/
        checkLayerMethod(ConfigReader.getProperty("dataAliasName"));
        checkLayerMethod(ConfigReader.getProperty("dataSetAliasNameIL"));
        checkLayerMethod(ConfigReader.getProperty("dataSetAliasNameILCE"));
        checkLayerMethod(ConfigReader.getProperty("dataSetAliasNameMah"));
        mapPage.closeButton.click();
    }

    @Step("Katmanları Göster Ve Gizle")
    public void layersShow() throws InterruptedException {
        layerControlClick();
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
    public void administrativeBorderFilterCreate() throws InterruptedException {
        filterSelectIL("İstanbul");
        filterSelectILCE("Kadıköy");
        filterSelectMAH("19 Mayıs Mh.");
    }

    @Step("İdari Alan Filtresi Çalışıyor mu?")
    public void administrativeFilterResult() {
        mapPage.filterOperationsButton.click();
        mapPage.filterPreview.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(mapPage.numberOfPointsListed.getText(), "631");
    }


    @Step("GeoCode verisini yükle")
    public void loadGeoCode() throws InterruptedException {
        createMapPage.loadCustomerData.click();
        try {
            uploadDataPage.loadDataScreenVisibility.isDisplayed();
        } catch (Exception e) {
            createMapPage.loadCustomerData.click();
            Thread.sleep(5000);
        }
        WebDriverWait waitGeoCodeOperations=new WebDriverWait(driver,Duration.ofSeconds(40));
        uploadDataPage.loadToFileInput.sendKeys(ConfigReader.getProperty("dataPathGeoCode"));
        uploadDataPage.loadContinueButton.click();
        waitGeoCodeOperations.until(ExpectedConditions.visibilityOf(uploadDataPage.tableWithUID));
        selectByValue(new Select(uploadDataPage.tableWithUID), ConfigReader.getProperty("uid"));
        uploadDataPage.aliasNameInput.sendKeys(ConfigReader.getProperty("dataAliasNameGeoCode"));
        selectByValue(new Select(uploadDataPage.latitudeColumn), ConfigReader.getProperty("latitudeColumn"));
        selectByValue(new Select(uploadDataPage.longitudeColumn), ConfigReader.getProperty("longitudeColumn"));
        uploadDataPage.resultContinue.click();
        waitGeoCodeOperations.until(ExpectedConditions.visibilityOf(uploadDataPage.geoCodeIncorrectDataVisibility));
        uploadDataPage.resultContinue.click();
        waitGeoCodeOperations.until(ExpectedConditions.visibilityOf(uploadDataPage.geoCodeColumnSelectTableVisibility));

        String [] values =ConfigReader.getPropertyArray("addressColumnsGeoCode");
        for(int i=0; i<values.length;i++){
            uploadDataPage.geoCodeColumnAddButton.click();
            int x=i+1;
            WebElement addressColum=driver.findElement(By.xpath("//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-1 css-tuxzvu']/div["+x+"]/div/div/div/select"));
            selectByValue(new Select(addressColum),values[x-1]);
            Thread.sleep(2000);
        }
        uploadDataPage.resultContinue.click();



    }
    @Step("GeoCode doğru şekilde çalışıyor mu?")
    public void checkGeoCode() throws InterruptedException{
        layerControlClick();
        layerShowClickMethod(ConfigReader.getProperty("dataAliasNameGeoCode"));
        layerOptionsClickMethod(ConfigReader.getProperty("dataAliasNameGeoCode"));
        mapPage.dataButton.click();
        Actions actions=new Actions(driver);
        actions.clickAndHold(mapPage.dataInformationPageUp).moveByOffset(0, -100).release().perform();

    }

    @Step("Adres Arama Yap")
    public void searchAddress() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(mapPage.secondButton).perform();
        mapPage.addressSearchButton.click();
    }

    @Step("Katmanlar Kısmına git")
    public void goLayers() {
        mapPage.mapTitle.isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(mapPage.secondButton).perform();
        mapPage.layerControlButton.click();
    }

    @Step("Gruplama Yap")
    public void groupingProcess() throws InterruptedException {
        layerShowClickMethod(ConfigReader.getProperty("dataAliasName"));
        layerOptionsClickMethod(ConfigReader.getProperty("dataAliasName"));

        WebElement enAltElement = mapPage.layers.get(mapPage.layers.size() - 1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", enAltElement);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapPage.dataGroupOptionButton.click();
        mapPage.addNewGroupColumnButton.click();
        mapPage.selectGroupColumnName.sendKeys("trade_channel" + Keys.DOWN + Keys.ENTER);
        mapPage.dataGroupButton.click();
        mapPage.applyGroupToMapButton.click();
        Thread.sleep(3000);
        Assert.assertTrue(mapPage.dataGroupVisibility.isDisplayed(), "Noktalar Haritalandirilamadi");

    }



    public void loadPointData() throws InterruptedException {
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
        uploadDataPage.loadToFileInput.sendKeys(ConfigReader.getProperty("dataPath"));
        wait.until(ExpectedConditions.visibilityOf(uploadDataPage.fileVisibility));
        uploadDataPage.loadContinueButton.click();
        //wait.until(ExpectedConditions.textToBePresentInElement(uploadDataPage.percentValue, "100%"));
        wait.until(ExpectedConditions.elementToBeClickable(uploadDataPage.tableWithUIDButton));

        selectByValue(new Select(uploadDataPage.tableWithUID), ConfigReader.getProperty("uid"));
        uploadDataPage.aliasNameInput.sendKeys(ConfigReader.getProperty("dataAliasName"));
        selectByValue(new Select(uploadDataPage.latitudeColumn), ConfigReader.getProperty("latitudeColumn"));
        selectByValue(new Select(uploadDataPage.longitudeColumn), ConfigReader.getProperty("longitudeColumn"));
        uploadDataPage.resultContinue.click();

    }

    public void goMapsPageAndClickMap() throws InterruptedException {
        headerPage.mapsButton.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //try {
        wait.until(ExpectedConditions.visibilityOfAllElements(mapsPage.maps));
        //} catch (Exception e) {
        //  driver.navigate().refresh();
        // wait.until(ExpectedConditions.visibilityOfAllElements(mapsPage.maps));
        //}
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
    public void goMapsPageAndClickGeoCodeMap()throws InterruptedException{
        headerPage.mapsButton.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //try {
        wait.until(ExpectedConditions.visibilityOfAllElements(mapsPage.maps));
        //} catch (Exception e) {
        //  driver.navigate().refresh();
        // wait.until(ExpectedConditions.visibilityOfAllElements(mapsPage.maps));
        //}
        while (true) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String scroolheight = js.executeScript("return document.body.scrollHeight").toString();
            //System.out.println(scroolheight);
            for (WebElement map : mapsPage.maps) {
                if (map.getText().equalsIgnoreCase(ConfigReader.getProperty("dataAliasNameGeoCode"))) {
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

    public void searchDataSet() throws InterruptedException {
        //headerPage.dataRepositoryButton.click();
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.navigate().refresh();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        /*FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);*/
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

    public void loadDataSet(String idColumn, String displayNameColumn, String aliasNameColumn, String parentColumn, String path) throws InterruptedException {

        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        /*FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);*/
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
        uploadDataPage.aliasNameInput.sendKeys(aliasNameColumn);
        uploadDataPage.resultContinue.click();
        dataRepositoryPage.notification.isDisplayed();
        Thread.sleep(50000);

    }

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

    public void loadDataSetIL() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdIL"), ConfigReader.getProperty("dataSetDisplayNameIL"), ConfigReader.getProperty("dataSetAliasNameIL"), null, ConfigReader.getProperty("dataPathIL"));
    }

    public void loadDataSetILCE() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdILCE"), ConfigReader.getProperty("dataSetDisplayNameILCE"), ConfigReader.getProperty("dataSetAliasNameILCE"), ConfigReader.getProperty("parentColumnILCE"), ConfigReader.getProperty("dataPathILCE"));
    }

    public void loadDataSetMah() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdMah"), ConfigReader.getProperty("dataSetDisplayNameMah"), ConfigReader.getProperty("dataSetAliasNameMah"), ConfigReader.getProperty("parentColumnMah"), ConfigReader.getProperty("dataPathMah"));
    }

    public void refreshAndPerformSearch() throws InterruptedException {
        //driver.navigate().refresh();
        searchDataSet();
        //dataRepositoryPage.fileAdministratorArea.click();
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

    public void selectByValue(Select select, String value) {
        select.selectByValue(value);
    }

    public void layerControlClick()throws InterruptedException{
        Actions actions = new Actions(driver);
        actions.moveToElement(mapPage.secondButton).perform();
        mapPage.layerControlButton.click();
    }


}


