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
        myChrDriPath = (new File("chromedriver_win32\\chromedriver.exe").getAbsolutePath());
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

    @Test
    public void typeBirthdayDateDayTest1() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", "Test", "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateDayTest2() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 123, "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateDayTest3() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", "Test12", "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateDayTest4() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, "09", 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateMonthTest1()  throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, 1996, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateYearTest1() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, "09", "Test", "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateYearTest2() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, "09", "Test12", "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateYearTest3() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, "09", 12, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateYearTest4() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, "09", 1899, "Мужской", "Нет опыта работы");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Слишком рано']")).size() > 0;
        Boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void typeBirthdayDateYearTest5() throws IOException, ParseException {
        makeResumeNow.doIt("Азамат", "Набиев", "Уфа", 12, "09", 2006, "Мужской", "Нет опыта работы");
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