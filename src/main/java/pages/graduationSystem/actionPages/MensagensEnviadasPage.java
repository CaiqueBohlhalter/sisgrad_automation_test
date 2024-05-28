package pages.graduationSystem.actionPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MensagensEnviadasPage {
    private static final String PAGE_TITLE = ":: UNESP : Câmpus de Rio Claro ::";
    private static final String PAGE_URL = "https://sistemas.unesp.br/academico/mensagem.listar.action?emailTipo=enviadas";
    private static final String DEFAULT_TITLE = "Mensagens enviadas";
    private static final String INFO_MSG = "Visualizar todas mensagens";

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"ct\"]/h2")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"mensagem\"]")
    WebElement sendMessagesTable;

    @FindBy(xpath = "//*[@id=\"form_mensagens\"]/div/span")
    WebElement registersCount;

    @FindBy(xpath = "//*[@id=\"cc\"]/div")
    WebElement infoMsg;

    public MensagensEnviadasPage(WebDriver driver){
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
        return infoMsg.getText().contains(INFO_MSG);
    }

    public Boolean isSendMessagesTableShown() {
        return sendMessagesTable.isDisplayed();
    }

    public Boolean isRegistersCountDisplayed() {
        return registersCount.isDisplayed();
    }

}
