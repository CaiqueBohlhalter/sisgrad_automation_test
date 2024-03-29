import Pages.AuthUnespPage;
import Pages.HomePage;
import Pages.AuthReitoriaPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

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
        Assertions.assertTrue(authUnespPage.isCorrectPage());
    }

    @Test
    public void shouldRedirectToReitoriaAuthPage() throws InterruptedException {
        HomePage page = new HomePage(driver);
        page.clickPortalReitoriaButton();
        AuthReitoriaPage reitoriaAuthPage = new AuthReitoriaPage(driver);
        Assertions.assertTrue(reitoriaAuthPage.isCorrectPage());
    }
}
