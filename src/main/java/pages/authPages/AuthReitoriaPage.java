package pages.authPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AuthReitoriaPage {
    private static final String PAGE_TITLE = "UNESP : Sistemas";
    private static final String PAGE_URL = "https://sistemas.unesp.br/acesso/";
    WebDriver driver;

    public AuthReitoriaPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    private String getPageTitle(){
        return driver.getTitle();
    }

    public Boolean isCorrectPage(){
        return getPageTitle().equals(PAGE_TITLE) ;
    }
}
