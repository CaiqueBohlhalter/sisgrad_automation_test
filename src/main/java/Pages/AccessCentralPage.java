package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccessCentralPage {
    private static final String PAGE_TITLE = "Central de Acessos";
    private static final String PAGE_URL = "https://sistemas.unesp.br/central/#/sistemas";

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "button_logout")
    WebElement logoutButton;
    @FindBy(xpath = "/html/body/div[4]/md-dialog/md-dialog-actions/button[2]")
    WebElement dialogAcceptButton;
    @FindBy(xpath = "/html/body/div[4]/md-dialog/md-dialog-actions/button[1]")
    WebElement dialogRefuseButton;
    @FindBy(xpath = "//*[@id=\"content\"]/md-content/div[2]/md-list/md-list-item[5]/div/a")
    WebElement sisgradButton;

    public AccessCentralPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logoutFromProfile(){
        logoutButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(dialogAcceptButton));
        dialogAcceptButton.click();
    }

    public void clickSisgradItem(){
        sisgradButton.click();
    }

    public void clickRefuseLogout(){
        dialogRefuseButton.click();
    }

    private String getPageTitle(){
        return driver.getTitle();
    }

    public Boolean isCorrectPage(){
        wait.until(ExpectedConditions.titleContains(PAGE_TITLE));
        return getPageTitle().equals(PAGE_TITLE) ;
    }
}
