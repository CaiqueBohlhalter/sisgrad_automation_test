package graduationSystem.action;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.authPages.AuthUnespPage;
import pages.authPages.HomePage;
import pages.centralPage.AccessCentralPage;
import pages.graduationSystem.GraduationSystemPage;
import pages.graduationSystem.actionPages.MensagensEnviadasPage;
import pages.graduationSystem.actionPages.MensagensRecebidasPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MensagensRecebidasPageTest {
    private static final String ACCESS_PAGE_URL = "https://sistemas.unesp.br/central/#/sistemas";
    private static final String UNESP_ID = Dotenv.load().get("UNESP_PERSONAL_ID");
    private static final String UNESP_PASS = Dotenv.load().get("UNESP_PERSONAL_PASS");
    private static WebDriver driver;

    @BeforeAll
    public static void setup() throws InterruptedException {
        InitializeDriver();
        login();
    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }

    @AfterEach
    public void goBackToAccessPage(){
        driver.get(ACCESS_PAGE_URL);
    }

    @Test
    public void shouldShowTableContentTest() {
        MensagensRecebidasPage mensagensRecebidasPage = accessPage();
        assertTrue(mensagensRecebidasPage.isCorrectIdentificationShown(), "Info Message is not Displayed");
        assertTrue(mensagensRecebidasPage.isRegistersCountDisplayed(), "Messages Counter is not Displayed");
        assertTrue(mensagensRecebidasPage.isReceivedMessagesTableShown(), "Messages Table is not Shown");
    }

    @Test
    public void shouldSwitchTablePagesTest() {
        MensagensRecebidasPage mensagensRecebidasPage = accessPage();
        assertFalse(mensagensRecebidasPage.isPreviousPageFormEnabled());
        assertTrue(mensagensRecebidasPage.isNextPageFormEnabled());
        mensagensRecebidasPage.clickNextPageForm();
        assertTrue(mensagensRecebidasPage.isPreviousPageFormClickable());
        mensagensRecebidasPage.clickPreviousPageForm();
        assertFalse(mensagensRecebidasPage.isPreviousPageFormEnabled());
    }

    private static void InitializeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static void login() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isCorrectPage());

        homePage.clickCentralButton();

        AuthUnespPage authPage = new AuthUnespPage(driver);
        assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword(UNESP_PASS);
        authPage.clickLoginButton();
    }

    private MensagensRecebidasPage accessPage() {
        AccessCentralPage accessCentralPage = new AccessCentralPage(driver);
        assertTrue(accessCentralPage.isCorrectPage());

        accessCentralPage.clickSisgradItem();

        GraduationSystemPage graduationSystemPage = new GraduationSystemPage(driver);
        assertTrue(graduationSystemPage.isCorrectPage());

        graduationSystemPage.clickMensagensEnviadas();

        MensagensRecebidasPage mensagensRecebidasPage = new MensagensRecebidasPage(driver);
        assertTrue(mensagensRecebidasPage.isCorrectPage());

        return mensagensRecebidasPage;
    }
}
