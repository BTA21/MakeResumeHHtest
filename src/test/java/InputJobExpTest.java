import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

public class InputJobExpTest {
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

    @Test
    public void chooseJobExperienceTest1()  throws IOException, ParseException {
        makeResumeNow.doIt("Иван", "Иванов", "Омск", 21, "09", 1996, "Мужской");
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div[@class='bloko-column bloko-column_xs-0 bloko-column_s-8" +
                " bloko-column_m-6 bloko-column_l-6']//div [@class='bloko-form-error" +
                " bloko-form-error_entered' and text()= 'Обязательное поле']")).size() > 0;
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