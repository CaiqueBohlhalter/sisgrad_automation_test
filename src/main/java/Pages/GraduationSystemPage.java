package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GraduationSystemPage {
    private static final String PAGE_TITLE = ":: UNESP : Câmpus de Rio Claro ::";
    private static final String PAGE_URL = "https://sistemas.unesp.br/academico/common.home.action";

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[1]/a")
    WebElement menuEsquerdo;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[1]/ul/li[2]/a")
    WebElement meusDadosHorarioAula;

    public GraduationSystemPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private String getPageTitle(){
        return driver.getTitle();
    }

    public Boolean isCorrectPage(){
        wait.until(ExpectedConditions.titleContains(PAGE_TITLE));
        return getPageTitle().equals(PAGE_TITLE);
    }

    public void hoverOverMeusDadosMenuAndClickHorarioAula(){
        Actions action = new Actions(driver);
        action.moveToElement(menuEsquerdo).perform();
        meusDadosHorarioAula.click();
    }


}
