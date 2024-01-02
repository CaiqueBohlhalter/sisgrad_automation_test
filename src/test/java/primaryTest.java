import Pages.AccessCentralPage;
import Pages.AuthUnespPage;
import Pages.HomePage;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class primaryTest {

    private static final String UNESP_ID = Dotenv.load().get("UNESP_PERSONAL_ID");
    private static final String UNESP_PASS = Dotenv.load().get("UNESP_PERSONAL_PASS");
    private static WebDriver driver;

    @BeforeAll
    public static void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void shouldLoginWithSuccess() throws InterruptedException {
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
    }

    @Test
    public void shouldFailLoginWithWrongPassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isCorrectPage());

        homePage.clickCentralButton();

        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword("Test");
        authPage.clickLoginButton();

        Assertions.assertTrue(authPage.isCorrectPage());
        Assertions.assertTrue(authPage.isInvalidCredentialMessageShown());
    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }

}
