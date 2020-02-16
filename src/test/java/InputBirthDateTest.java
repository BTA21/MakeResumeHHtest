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
 * Класс для тестирования ввода даты рождения при создании резюме со свойствами
 * <b>driver</b> и <b>makeResumeNow</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.3
 */
public class InputBirthDateTest {
    public WebDriver driver;
    public ForPageObject makeResumeNow;

    @Before
    public void start() throws IOException, ParseException {
        System.setProperty("webdriver.chrome.driver", new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath());
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

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void checkTestDayBDStep(String day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkTestDayBDTest() {
        checkTestDayBDStep("Test", "09", 1996, true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check123DayBDStep(Integer day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void check123DayBDTest() {
        check123DayBDStep(123, "09", 1996, true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void checkTest12DayBDStep(String day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkTest12DayBDTest() {
        checkTest12DayBDStep("Test12", "09", 1996, true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check12DayBDStep(Integer day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи, где ошибка не ожидается")
    @Test
    public void check12DayBDTest() {
        check12DayBDStep( 12, "09", 1996,false);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void checkEmptyMonthBDStep(Integer day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkEmptyMonthBDTest() {
        checkEmptyMonthBDStep(12, "null", 1996, true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void checkTestYearBDStep(Integer day, String month, String year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkTestYearBDTest() {
        checkTestYearBDStep(12, "09", "Test", true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void checkTest12YearBDStep(Integer day, String month, String year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkTest12YearBDTest() {
        checkTest12YearBDStep(12, "09", "Test12", true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check12YearBDStep(Integer day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void check12YearBDTest() {
        check12YearBDStep(12, "09", 12, true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check1899YearBDStep(Integer day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.tooEarlyErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void check1899YearBDTest() {
        check1899YearBDStep(12, "09", 1899,true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check2006YearBDStep(Integer day, String month, Integer year, Boolean expected) {
        makeResumeNow.doIt(day, month, year);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.tooYoungErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void check2006YearBDTest() {
        check2006YearBDStep(12, "09", 2006,true);
    }
}