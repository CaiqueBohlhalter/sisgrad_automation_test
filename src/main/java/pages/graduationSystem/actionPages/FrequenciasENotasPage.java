package pages.graduationSystem.actionPages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FrequenciasENotasPage {
    private static final String PAGE_TITLE = ":: UNESP : Câmpus de Rio Claro ::";
    private static final String PAGE_URL = "https://sistemas.unesp.br/academico/aluno/cadastro.frequenciasNotas.action";
    private static final String DEFAULT_TITLE = "Frequências e Notas";
    private static final String USER_FULL_NAME = Dotenv.load().get("PERSONAL_FULL_NAME");
    private static final String USER_RA = Dotenv.load().get("PERSONAL_RA");

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"ct\"]/h2")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"cc\"]/div[1]")
    WebElement infoMsg;

    @FindBy(xpath = "//*[@id=\"tabelaNotas\"]")
    WebElement tabelaNotasTable;

    public FrequenciasENotasPage(WebDriver driver){
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
        if(getPageTitle().equals(PAGE_TITLE)){
            return title.getText().contains(DEFAULT_TITLE);
        }
        return false;
    }

    public Boolean isCorrectIdentificationShown() {
        return (infoMsg.getText().contains(USER_FULL_NAME) && infoMsg.getText().contains(USER_RA));
    }

    public Boolean isTabelaNotasTableShown() {
        return tabelaNotasTable.isDisplayed();
    }


    public Boolean areRowsDisplayed() {
        List<WebElement> tabelaNotasRows = By.xpath("//*[@id=\"tabelaNotas\"]/tbody/tr").findElements(driver);

        return tabelaNotasRows.size() > 1;
    }
}
