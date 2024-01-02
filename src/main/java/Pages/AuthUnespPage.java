package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthUnespPage {
    private static final String PAGE_TITLE = "Sistemas Unesp - Autenticação";
    private static final String PAGE_URL = "https://auth.unesp.br/login";
    private static final String LOGOUT_MESSAGE = "Logout efetuado com sucesso.";
    private static final String INVALID_CREDENTIAL_MESSAGE = "Identificação e/ou senha inválido(s).";

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"input_0\"]")
    WebElement unespIdInput;
    @FindBy(xpath = "//*[@id=\"input_1\"]")
    WebElement unespPasswordInput;
    @FindBy(name = "button_entrar")
    WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"form\"]/md-card/md-card-title-text/div")
    WebElement reportMessage;

    public AuthUnespPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void insertUnespId(String id){
        unespIdInput.clear();
        unespIdInput.sendKeys(id);
    }

    public void insertUnespPassword(String password){
        unespPasswordInput.clear();
        unespPasswordInput.sendKeys(password);
    }

    public void clickLoginButton() throws InterruptedException {
        loginButton.click();
    }

    public boolean isLogoutMessageShown(){
        String message = reportMessage.getText();
        return message.equals(LOGOUT_MESSAGE);
    }

    public boolean isInvalidCredentialMessageShown(){
        String message = reportMessage.getText();
        return message.equals(INVALID_CREDENTIAL_MESSAGE);
    }

    private String getPageTitle(){
        return driver.getTitle();
    }

    public Boolean isCorrectPage(){
        wait.until(ExpectedConditions.titleContains(PAGE_TITLE));
        return getPageTitle().equals(PAGE_TITLE);
    }

}
