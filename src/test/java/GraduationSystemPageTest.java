import Pages.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GraduationSystemPageTest {
    private static final String UNESP_ID = Dotenv.load().get("UNESP_PERSONAL_ID");
    private static final String UNESP_PASS = Dotenv.load().get("UNESP_PERSONAL_PASS");;
    private static WebDriver driver;

    @BeforeAll
    public static void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }

    @Test
    public void shouldAccessGraduationSystemTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isCorrectPage());

        homePage.clickCentralButton();

        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword(UNESP_PASS);
        authPage.clickLoginButton();

        AccessCentralPage accessCentralPage = new AccessCentralPage(driver);
        Assertions.assertTrue(accessCentralPage.isCorrectPage());

        accessCentralPage.clickSisgradItem();

        GraduationSystemPage graduationSystemPage = new GraduationSystemPage(driver);
        Assertions.assertTrue(graduationSystemPage.isCorrectPage());
    }

    @Test
    public void shouldAccessHorarioDeAulasPageTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isCorrectPage());

        homePage.clickCentralButton();

        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword(UNESP_PASS);
        authPage.clickLoginButton();

        AccessCentralPage accessCentralPage = new AccessCentralPage(driver);
        Assertions.assertTrue(accessCentralPage.isCorrectPage());

        accessCentralPage.clickSisgradItem();

        GraduationSystemPage graduationSystemPage = new GraduationSystemPage(driver);
        Assertions.assertTrue(graduationSystemPage.isCorrectPage());

        graduationSystemPage.hoverOverMeusDadosMenuAndClickHorarioAula();

        HorarioAulasPage horarioAulasPage = new HorarioAulasPage(driver);
        Assertions.assertTrue(horarioAulasPage.isCorrectPage());
    }
}
