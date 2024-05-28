package pages.graduationSystem.actionPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MensagensRecebidasPage {
    private static final String PAGE_TITLE = ":: UNESP : Câmpus de Rio Claro ::";
    private static final String PAGE_URL = "https://sistemas.unesp.br/academico/mensagem.listar.action?emailTipo=recebidas";
    private static final String DEFAULT_TITLE = "Mensagens recebidas";
    private static final String INFO_MSG = "Visualizar todas mensagens";

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"ct\"]/h2")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"destinatario\"]")
    WebElement receivedMessagesTable;

    @FindBy(xpath = "//*[@id=\"form_mensagens\"]/div/span")
    WebElement registersCount;

    @FindBy(xpath = "//*[@id=\"cc\"]/div")
    WebElement infoMsg;

    @FindBy(xpath = "//*[@id=\"form_mensagens\"]/div/span[2]/a[5]/img")
    WebElement nextPageForm;

    @FindBy(xpath = "//*[@id=\"form_mensagens\"]/div/span[2]/img[2]")
    WebElement previousPageForm;

    @FindBy(xpath = "//*[@id=\"form_mensagens\"]/div/span[2]/a[2]/img")
    WebElement previousPageFormClickable;

    public MensagensRecebidasPage(WebDriver driver){
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

    public Boolean isReceivedMessagesTableShown() {
        return receivedMessagesTable.isDisplayed();
    }

    public Boolean isRegistersCountDisplayed() {
        return registersCount.isDisplayed();
    }

    public Boolean isPreviousPageFormEnabled() {
        return !previousPageForm.getAttribute("class").contains("off");
    }

    public Boolean isPreviousPageFormClickable() {
        return !previousPageFormClickable.getAttribute("class").contains("off");
    }

    public Boolean isNextPageFormEnabled() {
        return !nextPageForm.getAttribute("class").contains("off");
    }

    public void clickPreviousPageForm() { previousPageFormClickable.click(); }

    public void clickNextPageForm() { nextPageForm.click(); }
}
