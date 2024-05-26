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
    WebElement meusDados;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[1]/ul/li[2]/a")
    WebElement meusDadosHorarioAula;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[1]/ul/li[3]/a")
    WebElement meusDadosFrequenciaNota;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[1]/ul/li[4]/a")
    WebElement meusDadosHistoricoEscolar;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[4]/a")
    WebElement solicitacoes;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[4]/ul/li[3]/a")
    WebElement solicitacoesDocumentos;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[4]/ul/li[3]/ul/li[1]/a")
    WebElement solicitacoesDocumentosNovaSolicitacao;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[9]/a")
    WebElement mensagens;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[9]/ul/li[1]/a")
    WebElement mensagensRecebidas;

    @FindBy(xpath = "//*[@id=\"menuesq\"]/li[9]/ul/li[2]/a")
    WebElement mensagensEnviadas;

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

    private void hoverOverMeusDadosMenu(){
        Actions action = new Actions(driver);
        action.moveToElement(meusDados).perform();
    }

    private void hoverOverSolicitacoesMenu(){
        Actions action = new Actions(driver);
        action.moveToElement(solicitacoes).perform();
    }

    private void hoverOverSolicitacoesDocumentosMenu(){
        Actions action = new Actions(driver);
        action.moveToElement(solicitacoes).perform();
        action.moveToElement(solicitacoesDocumentos).perform();
    }

    private void hoverOverMensagensMenu(){
        Actions action = new Actions(driver);
        action.moveToElement(mensagens).perform();
    }

    public void clickHorarioAula(){
        hoverOverMeusDadosMenu();
        meusDadosHorarioAula.click();
    }

    public void clickFrequenciaNota(){
        hoverOverMeusDadosMenu();
        meusDadosFrequenciaNota.click();
    }

    public void clickHistoricoEscolar(){
        hoverOverMeusDadosMenu();
        meusDadosHistoricoEscolar.click();
    }

    public void clickSolicitacoesDocumentosNovaSolicitacao(){
        hoverOverSolicitacoesDocumentosMenu();
        solicitacoesDocumentosNovaSolicitacao.click();
    }

    public void clickMensagensRecebidas(){
        hoverOverMensagensMenu();
        mensagensRecebidas.click();
    }

    public void clickMensagensEnviadas(){
        hoverOverMensagensMenu();
        mensagensEnviadas.click();
    }
}