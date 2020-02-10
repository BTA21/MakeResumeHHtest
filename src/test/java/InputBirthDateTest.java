import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

public class InputBirthDateTest {
    public By deleteResume = By.xpath("//button[@data-qa='resume-delete']");
    public By deleteResumeAccept = By.xpath("//button[text()='Удалить']");
    public WebDriver driver;
    public MakeResume makeResumeNow;
    public LoginPage loginNow;
    public int timeForPause;
    private String myChrDriPath;

    @Before
    public void start() throws IOException, ParseException {
        myChrDriPath = (new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", myChrDriPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginNow = new LoginPage(driver);
        loginNow.loginAs();
        makeResumeNow = new MakeResume(driver);
        timeForPause = 8;
    }

    @After
    public void end() {
        try {
            driver.findElement(deleteResume).click();
            driver.findElement(deleteResumeAccept).click();
        } catch(NoSuchElementException e) {

        }
        driver.quit();
    }
    
    @Step("Вводим Test день рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateDayTest1() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", "Test", "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }
    @Step("Вводим 123 день рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateDayTest2() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 123, "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Step("Вводим Test12 день рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateDayTest3() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", "Test12", "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Step("Вводим корректную дату рождения, чтобы проверить отсутствие ошибки некорректной даты")
    @Test
    public void typeBirthdayDateDayTest4() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = false;
        assertEquals(expected,actual);
    }

    @Step("Пропускаем ввод месяца рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateMonthTest1()  throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Step("Вводим Test год рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateYearTest1() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, "09", "Test", "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Step("Вводим Test12 год рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateYearTest2() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, "09", "Test12", "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }
    @Step("Вводим 12 год рождения, чтобы проверить появление ошибки некорректной даты")
    @Test
    public void typeBirthdayDateYearTest3() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, "09", 12, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }
    @Step("Вводим 1899 год рождения, чтобы проверить появление ошибки возраста")
    @Test
    public void typeBirthdayDateYearTest4() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, "09", 1899, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Слишком рано']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Step("Вводим 2006 год рождения, чтобы проверить появление ошибки возраста")
    @Test
    public void typeBirthdayDateYearTest5() throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 12, "09", 2006, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Вы слишком молоды']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Ignore
    public void waitTime() {
    }
    public static void waitTime(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }

}