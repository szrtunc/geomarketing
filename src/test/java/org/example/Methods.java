package org.example;

import driver.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

public class Methods {

    WebDriver driver;
    LoginPage loginPage = new LoginPage();
    CreateMapPage createMapPage = new CreateMapPage();
    UploadDataPage uploadDataPage = new UploadDataPage();
    MapsPage mapsPage = new MapsPage();
    HeaderElementsPage headerPage = new HeaderElementsPage();
    DataRepositoryPage dataRepositoryPage = new DataRepositoryPage();
    MapPage mapPage = new MapPage();
   // WebDriverWait wait;

    public void loadPointData() throws InterruptedException{
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);

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
        //wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOf(uploadDataPage.loadToUrlInput));
        uploadDataPage.loadToFileInput.sendKeys("C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\point_kadikoy_0.xlsx");
        wait.until(ExpectedConditions.visibilityOf(uploadDataPage.fileVisibility));
        uploadDataPage.loadContinueButton.click();
        //wait.until(ExpectedConditions.textToBePresentInElement(uploadDataPage.percentValue, "100%"));
        wait.until(ExpectedConditions.elementToBeClickable(uploadDataPage.tableWithUIDButton));

        selectByValue(new Select(uploadDataPage.tableWithUID), ConfigReader.getProperty("uid"));
        uploadDataPage.aliasNameInput.sendKeys(ConfigReader.getProperty("dataAliasName"));
        selectByValue(new Select(uploadDataPage.latitudeColumn),ConfigReader.getProperty("latitudeColumn"));
        selectByValue(new Select(uploadDataPage.longitudeColumn),ConfigReader.getProperty("longitudeColumn"));
        uploadDataPage.resultContinue.click();

    }
    public void goMapsPageAndClickMap() throws InterruptedException {
        //wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        headerPage.mapsButton.click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

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
    public void searchDataSet() throws InterruptedException {
        //headerPage.dataRepositoryButton.click();
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.navigate().refresh();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
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

        //wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
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
        Thread.sleep(35000);

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
        loadDataSet(ConfigReader.getProperty("dataSetIdIL"), ConfigReader.getProperty("dataSetDisplayNameIL"), ConfigReader.getProperty("dataSetAliasNameIL"), null, "C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\IL_KADIKOY_region.zip");
    }
    public void loadDataSetILCE() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdILCE"), ConfigReader.getProperty("dataSetDisplayNameILCE"), ConfigReader.getProperty("dataSetAliasNameILCE"), ConfigReader.getProperty("parentColumnILCE"), "C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\ILCE_KADIKOY_region.zip");
    }
    public void loadDataSetMah() throws InterruptedException {
        loadDataSet(ConfigReader.getProperty("dataSetIdMah"), ConfigReader.getProperty("dataSetDisplayNameMah"), ConfigReader.getProperty("dataSetAliasNameMah"), ConfigReader.getProperty("parentColumnMah"), "C:\\Users\\Geovision\\Desktop\\geoMarketingDosyalar2\\MAHALLE_KADIKOY_region.zip");
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





}
