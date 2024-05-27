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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolicitacoesDocumentosPage {
    private static final String PAGE_TITLE = ":: UNESP : Câmpus de Rio Claro ::";
    private static final String PAGE_URL = "https://sistemas.unesp.br/academico/documento.novaSolicitacao.action";
    private static final String DEFAULT_TITLE = "documentos";
    private static final String USER_FULL_NAME = Dotenv.load().get("PERSONAL_FULL_NAME");
    private static final String USER_RA = Dotenv.load().get("PERSONAL_RA");

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"ct\"]/h2")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"cc\"]/div")
    WebElement infoMsg;

    @FindBy(xpath = "//*[@id=\"cc\"]/table[1]")
    WebElement messageInfo;

    @FindBy(xpath = "//*[@id=\"documento\"]")
    WebElement docOptionsTable;

    public SolicitacoesDocumentosPage(WebDriver driver){
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

    public Boolean isMessageInfoShown() {
        return !messageInfo.getText().isEmpty();
    }

    public Boolean isDocumentOptionsTableShown() {
        return docOptionsTable.isDisplayed();
    }

    public Boolean isHistoricEscolarButtonEnabled() {
        return findDocTypeButton("Histórico Escolar");
    }

    public Boolean isAtestadoSimplesButtonEnabled() {
        return findDocTypeButton("Atestado de Matrícula Simples");
    }

    private Boolean findDocTypeButton(String textoDesejado){
        List<WebElement> linhas = driver.findElements(By.xpath("//*[@id='documento']/tbody/tr"));
        boolean encontrado = false;

        for (WebElement linha : linhas) {
            WebElement primeiraColuna = linha.findElement(By.xpath(".//td[1]"));
            String textoAtual = primeiraColuna.getText();

            if (textoAtual.equals(textoDesejado)) {
                WebElement fourthColumn = linha.findElement(By.xpath("td[4]"));
                List<WebElement> botoes = fourthColumn.findElements(By.tagName("a"));

                encontrado = !botoes.isEmpty();
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Texto desejado não foi encontrado em nenhuma linha.");
        }

        return encontrado;
    }
}
