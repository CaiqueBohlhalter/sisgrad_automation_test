package pages.authPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private static final String PAGE_TITLE = "Sistemas.Unesp";
    private static final String PAGE_URL = "https://sistemas.unesp.br/";

    WebDriver driver;

    @FindBy(id = "wrapper-central-de-acessos")
    WebElement centralDeAcessosWrapper;
    @FindBy(id = "lista-portais")
    WebElement portalReitoriaButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void clickCentralButton(){
        centralDeAcessosWrapper.click();
    }

    public void clickPortalReitoriaButton() {
        portalReitoriaButton.click();
    }

    private String getPageTitle(){
       return driver.getTitle();
    }

    public Boolean isCorrectPage(){
        return getPageTitle().equals(PAGE_TITLE) ;
    }
}
