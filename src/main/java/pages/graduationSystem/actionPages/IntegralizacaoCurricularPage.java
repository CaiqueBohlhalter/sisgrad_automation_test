package pages.graduationSystem.actionPages;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IntegralizacaoCurricularPage {
    private static final String PAGE_TITLE = ":: UNESP : Câmpus de Rio Claro ::";
    private static final String PAGE_URL = "https://sistemas.unesp.br/academico/aluno/cadastro.integralizacaoCurricular.action";
    private static final String DEFAULT_TITLE = "Integralização Curricular";
    private static final String USER_FULL_NAME = Dotenv.load().get("PERSONAL_FULL_NAME");
    private static final String USER_RA = Dotenv.load().get("PERSONAL_RA");

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"ct\"]/h2")
    WebElement title;

    @FindBy(xpath = "//*[@id=\"cc\"]/div")
    WebElement infoMsg;

    @FindBy(xpath = "//*[@id=\"cc\"]/table[1]")
    WebElement completedClassesTable;

    @FindBy(xpath = "//*[@id=\"cc\"]/table[2]")
    WebElement currentClassesTable;

    @FindBy(xpath = "//*[@id=\"cargaHorariaWrapper\"]")
    WebElement workloadTable;

    @FindBy(xpath = "//*[@id=\"cc\"]/table[4]")
    WebElement summaryTable;

    public IntegralizacaoCurricularPage(WebDriver driver){
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

    public Boolean areAllTablesBeingShown() {
        return (isCompletedClassesTableShown() &&
                isCurrentClassesTableShown() &&
                isWorkloadTableShown() &&
                isSummaryTableShown());
    }

    private Boolean isCompletedClassesTableShown() {
        return completedClassesTable.isDisplayed();
    }

    private Boolean isCurrentClassesTableShown() {
        return currentClassesTable.isDisplayed();
    }

    private Boolean isWorkloadTableShown() {
        return workloadTable.isDisplayed();
    }

    private Boolean isSummaryTableShown() {
        return summaryTable.isDisplayed();
    }
}
