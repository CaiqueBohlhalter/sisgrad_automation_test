package auth;

import pages.centralPage.AccessCentralPage;
import pages.authPages.AuthUnespPage;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class AuthUnespPageTest {
    private static final String UNESP_ID = Dotenv.load().get("UNESP_PERSONAL_ID");
    private static final String UNESP_PASS = Dotenv.load().get("UNESP_PERSONAL_PASS");
    private static final String WRONG_ID = "WRONG_ID";
    private static final String WRONG_PASS = "WRONG_PASS";
    private static WebDriver driver;

    @BeforeAll
    public static void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }

    @Test
    public void shouldLoginWithRightCredentials() throws InterruptedException {
        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword(UNESP_PASS);
        authPage.clickLoginButton();

        AccessCentralPage accessCentralPage = new AccessCentralPage(driver);
        Assertions.assertTrue(accessCentralPage.isCorrectPage());

        accessCentralPage.logoutFromProfile();

        Assertions.assertTrue(authPage.isCorrectPage());
        Assertions.assertTrue(authPage.isLogoutMessageShown());
    }

    @Test
    public void shouldFailLoginWithWrongId() throws InterruptedException {
        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(WRONG_ID);
        authPage.insertUnespPassword(UNESP_PASS);
        authPage.clickLoginButton();

        Assertions.assertTrue(authPage.isCorrectPage());
        Assertions.assertTrue(authPage.isInvalidCredentialMessageShown());
    }

    @Test
    public void shouldFailLoginWithWrongPassword() throws InterruptedException {
        AuthUnespPage authPage = new AuthUnespPage(driver);
        Assertions.assertTrue(authPage.isCorrectPage());

        authPage.insertUnespId(UNESP_ID);
        authPage.insertUnespPassword(WRONG_PASS);
        authPage.clickLoginButton();

        Assertions.assertTrue(authPage.isCorrectPage());
        Assertions.assertTrue(authPage.isInvalidCredentialMessageShown());
    }
}
