package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ReitoriaAuthPage {
    private static final String PAGE_TITLE = "UNESP : Sistemas";
    WebDriver driver;

    public ReitoriaAuthPage(WebDriver driver){
        this.driver = driver;
        driver.get("https://sistemas.unesp.br/acesso/");
    }

    private String getPageTitle(){
        return driver.getTitle();
    }

    public Boolean isCorrectPage(){
        return getPageTitle().equals(PAGE_TITLE) ;
    }
}
