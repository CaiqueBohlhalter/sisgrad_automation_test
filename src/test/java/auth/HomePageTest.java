package auth;

import pages.authPages.AuthUnespPage;
import pages.authPages.HomePage;
import pages.authPages.AuthReitoriaPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {
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
    public void shouldRedirectToAuthPage() throws InterruptedException {
        HomePage page = new HomePage(driver);
        page.clickCentralButton();
        AuthUnespPage authUnespPage = new AuthUnespPage(driver);
        assertTrue(authUnespPage.isCorrectPage());
    }

    @Test
    public void shouldRedirectToReitoriaAuthPage() throws InterruptedException {
        HomePage page = new HomePage(driver);
        page.clickPortalReitoriaButton();
        AuthReitoriaPage reitoriaAuthPage = new AuthReitoriaPage(driver);
        assertTrue(reitoriaAuthPage.isCorrectPage());
    }
}
