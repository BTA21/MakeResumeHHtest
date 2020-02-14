import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

/**
 * Класс для тестирования ввода фамилии при создании резюме со свойствами
 * <b>driver</b> и <b>makeResumeNow</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.3
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
            makeResumeNow.waitTime(makeResumeNow.timeForPause);
            driver.findElement(makeResumeNow.deleteResume).click();
            makeResumeNow.waitTime(makeResumeNow.timeForPause);
            driver.findElement(makeResumeNow.deleteResumeAccept).click();
        } catch(NoSuchElementException e) {}
        driver.quit();
    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void check1TestLNStep(String LastName, Boolean expected) {
        makeResumeNow.inputLastName(LastName);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод фамилии")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void check1TestLNTest() {
        check1TestLNStep("1Тест", true);

    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkTestDefLNStep(String LastName, Boolean expected) {
        makeResumeNow.inputLastName(LastName);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод фамилии")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkTestDefLNTest() {
        checkTestDefLNStep("Тест-", true);
    }

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkTestDefTestLNStep(String LastName, Boolean expected) {
        makeResumeNow.inputLastName(LastName);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод фамилии")
    @Story(value = "Случаи, где ошибка не ожидается")
    @Test
    public void checkTestDefTestLNTest() {
        checkTestDefTestLNStep("Test-Test",false);
    }

    @Step("Проверка сценария, где фамилия принимает значение 'null'")
    public void checkEmptyLNStep(String LastName, Boolean expected) {
        makeResumeNow.inputLastName(LastName);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.necessaryLastNameFieldErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод фамилии")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkEmptyLNTest() {
        checkEmptyLNStep("", true);
    }
}