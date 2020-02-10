import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.json.simple.parser.JSONParser;
import java.io.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;
/**
 * Класс страницы для входа в систему со свойствами <b>usernameLocator</b>, <b>passwordLocator</b>,
 * <b>driver</b>, <b>jo</b>, <b>obj</b>, <b>obj</b> и <b>myLogPassPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.1
 */
public class LoginPage {
    /** Переменная объекта логина */
    By usernameLocator = By.name("username");
    /** Переменная объекта пароля*/
    By passwordLocator = By.name("password");
    /** Переменная объекта кнопки "Войти в личный кабинет" */
    By loginButtonLocator = By.xpath("//input[@value='Войти в личный кабинет']");
    /** Переменная объекта WebDriver */
    private WebDriver driver;
    /** Переменная объекта JSON */
    private JSONObject jo;
    /** Переменная объекта */
    private Object obj;
    /** Переменная пути к JSON-файлу, где хранятся логин и пароль для входа в систему */
    private String myLogPassPath;
    /**
     * Конструктор - создание новой страницы входа в систему с определенными значениями
     * @param driver  экземпляр WebDriver
     * @see LoginPage#LoginPage(WebDriver)
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Вызов функции неявного ожидания
        driver.manage().timeouts().
                implicitlyWait(11, TimeUnit.SECONDS);
        // Открываем страницу входа в систему, используя драйвер
        driver.get("https://ufa.hh.ru/account/login");
    }
    /**
     * Функция ввода логина
     * @param username логин
     * @return возвращает страницу
     */
    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }
    /**
     * Функция ввода пароля
     * @param password пароль
     * @return возвращает страницу
     */
    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }
    /**
     * Функция нажатия кнопки "Войти в личный кабинет"
     * @return возвращает страницу
     */
    public LoginPage submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return this;
    }
    /**
     * Функция осуществления входа в систему
     * @throws IOException Исключение, которое выдается при возникновении ошибки ввода-вывода
     * @throws ParseException Исключение, которое выдается, если синтаксический анализ вызывает ошибку
     * @return возвращает страницу
     */
    public LoginPage loginAs() throws IOException, ParseException{
        myLogPassPath = (new File("src\\main\\resources\\config.json").getAbsolutePath());
        Object obj = new JSONParser().parse(new FileReader(myLogPassPath));
        JSONObject jo = (JSONObject) obj;
        typeUsername((String)jo.get("username"));
        typePassword((String)jo.get("password"));
        return submitLogin();
    }
}
