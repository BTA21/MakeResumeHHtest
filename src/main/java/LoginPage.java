import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.json.simple.parser.JSONParser;
import java.io.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");
    By loginButtonLocator = By.xpath("//input[@value='Войти в личный кабинет']");
    private WebDriver driver;
    private JSONObject jo;
    private Object obj;
    private String myLogPassPath;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().
                implicitlyWait(11, TimeUnit.SECONDS);
        driver.get("https://ufa.hh.ru/account/login");
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public LoginPage submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return this;
    }

    public LoginPage loginAs() throws IOException, ParseException{
        myLogPassPath = (new File("src\\main\\resources\\config.json").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", myLogPassPath);
        Object obj = new JSONParser().parse(new FileReader(myLogPassPath));
        JSONObject jo = (JSONObject) obj;
        typeUsername((String)jo.get("username"));
        typePassword((String)jo.get("password"));
        return submitLogin();
    }
}
