import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
/**
 * Класс для тестирования ввода фамилии при создании резюме со свойствами
 * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
 * <b>makeResumeNow</b>, <b>loginNow</b>,
 * <b>timeForPause</b> и <b>myChrDriPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.2
 */
public class InputLastNameTest {
    public WebDriver driver;
    public ForPageObject makeResumeNow;

    @Before
    public void start() throws IOException, ParseException {
        System.setProperty("webdriver.chrome.driver", (new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath()));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        makeResumeNow = new ForPageObject(driver);
        makeResumeNow.loginAs();
    }

    @After
    public void end() {
        try {
            driver.findElement(makeResumeNow.deleteResume).click();
            driver.findElement(makeResumeNow.deleteResumeAccept).click();
        } catch(NoSuchElementException e) {

        }
        driver.quit();
    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void check1TestLNStep(String firstName, String LastName,
                                 String city, Integer day, String month,
                                 Integer year, String sex, String jobExp,
                                 Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Test
    public void check1TestLNTest() {
        check1TestLNStep("Иван", "1Тест", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы", true);

    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkTestDefLNStep(String firstName, String LastName,
                                   String city, Integer day, String month,
                                   Integer year, String sex, String jobExp,
                                   Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Test
    public void checkTestDefLNTest() {
        checkTestDefLNStep("Иван", "Тест-", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы",true);
    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkTestDefTestLNStep(String firstName, String LastName,
                                       String city, Integer day, String month,
                                       Integer year, String sex, String jobExp,
                                       Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
    }

    @Test
    public void checkTestDefTestLNTest() {
        checkTestDefTestLNStep("Иван", "Test-Test", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы",false);
    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkEmptyLNStep(String firstName, String LastName,
                                 String city, Integer day, String month,
                                 Integer year, String sex, String jobExp,
                                 Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.necessaryLastNameFieldErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Test
    public void checkEmptyLNTest() {
        checkEmptyLNStep("Иван", "", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы",true);
    }
}