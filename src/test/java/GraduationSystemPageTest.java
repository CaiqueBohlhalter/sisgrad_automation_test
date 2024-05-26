import Pages.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GraduationSystemPageTest {
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
    public void shouldAccessGraduationSystemTest() {
        accessGraduationSystemPage();
    }

    @Test
    public void shouldAccessHorarioDeAulasPageTest() {
        GraduationSystemPage graduationSystemPage = accessGraduationSystemPage();

        graduationSystemPage.clickHorarioAula();

        HorarioAulasPage horarioAulasPage = new HorarioAulasPage(driver);
        Assertions.assertTrue(horarioAulasPage.isCorrectPage());
    }

    @Test
    public void shouldAccessFrequenciaENotasPageTest() {
        GraduationSystemPage graduationSystemPage = accessGraduationSystemPage();

        graduationSystemPage.clickFrequenciaNota();

        FrequenciasENotasPage frequenciasENotasPage = new FrequenciasENotasPage(driver);
        Assertions.assertTrue(frequenciasENotasPage.isCorrectPage());
    }

    @Test
    public void shouldAccessHistoricoEscolarPageTest() {
        GraduationSystemPage graduationSystemPage = accessGraduationSystemPage();

        graduationSystemPage.clickHistoricoEscolar();

        HistoricoEscolarPage historicoEscolarPage = new HistoricoEscolarPage(driver);
        Assertions.assertTrue(historicoEscolarPage.isCorrectPage());
    }

    @Test
    public void shouldAccessNovaSolicitacaoDocumentosPageTest() {
        GraduationSystemPage graduationSystemPage = accessGraduationSystemPage();

        graduationSystemPage.clickSolicitacoesDocumentosNovaSolicitacao();

        SolicitacoesDocumentosPage solicitacoesDocumentosPage = new SolicitacoesDocumentosPage(driver);
        Assertions.assertTrue(solicitacoesDocumentosPage.isCorrectPage());
    }

    @Test
    public void shouldAccessMensagensRecebidasPageTest() {
        GraduationSystemPage graduationSystemPage = accessGraduationSystemPage();

        graduationSystemPage.clickMensagensRecebidas();

        MensagensRecebidasPage mensagensRecebidasPage = new MensagensRecebidasPage(driver);
        Assertions.assertTrue(mensagensRecebidasPage.isCorrectPage());
    }

    @Test
    public void shouldAccessMensagensEnviadasPageTest() {
        GraduationSystemPage graduationSystemPage = accessGraduationSystemPage();

        graduationSystemPage.clickMensagensEnviadas();

        MensagensEnviadasPage mensagensEnviadasPage = new MensagensEnviadasPage(driver);
        Assertions.assertTrue(mensagensEnviadasPage.isCorrectPage());
    }

    private static void login() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isCorrectPage());

        homePage.clickCentralButton();

        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword(UNESP_PASS);
        authPage.clickLoginButton();
    }

    private static void InitializeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private GraduationSystemPage accessGraduationSystemPage() {
        AccessCentralPage accessCentralPage = new AccessCentralPage(driver);
        Assertions.assertTrue(accessCentralPage.isCorrectPage());

        accessCentralPage.clickSisgradItem();

        GraduationSystemPage graduationSystemPage = new GraduationSystemPage(driver);
        Assertions.assertTrue(graduationSystemPage.isCorrectPage());

        return graduationSystemPage;
    }
}
