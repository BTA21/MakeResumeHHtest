//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Step;
//import io.qameta.allure.Story;
//import org.junit.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.WebDriver;
//import java.io.File;
//import java.io.IOException;
//
///**
// * Класс для тестирования ввода имени при создании резюме со свойствами
// * <b>driver</b> и <b>makeResumeNow</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.3
// */
//public class InputFirstNameTest {
//    public WebDriver driver;
//    public ForPageObject makeResumeNow;
//
//    @Before
//    public void start() throws IOException, ParseException {
//        System.setProperty("webdriver.chrome.driver", new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath());
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        makeResumeNow = new ForPageObject(driver);
//        makeResumeNow.loginAs();
//    }
//
//    @After
//    public void end() {
//        try {
//            makeResumeNow.waitTime(makeResumeNow.timeForPause);
//            driver.findElement(makeResumeNow.deleteResume).click();
//            makeResumeNow.waitTime(makeResumeNow.timeForPause);
//            driver.findElement(makeResumeNow.deleteResumeAccept).click();
//        } catch(NoSuchElementException e) {}
//        driver.quit();
//    }
//
//    @Step("Проверка сценария, где имя принимает значение '{firstName}'")
//    public void check1TestFNStep(String firstName, Boolean expected) {
//        makeResumeNow.inputFirstName(firstName);
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        makeResumeNow.submitPushResume();
//        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefFirstNameErr).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Ввод имени")
//    @Story(value = "Случаи с ожидаемой ошибкой")
//    @Test
//    public void check1TestFNTest() {
//        check1TestFNStep("1Тест", true);
//    }
//
//    @Step("Проверка сценария, где имя принимает значение '{firstName}'")
//    public void checkTestDefFNStep(String firstName, Boolean expected) {
//        makeResumeNow.inputFirstName(firstName);
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        makeResumeNow.submitPushResume();
//        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefFirstNameErr).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Ввод имени")
//    @Story(value = "Случаи с ожидаемой ошибкой")
//    @Test
//    public void checkTestDefFNTest() {
//        checkTestDefFNStep("Тест-", true);
//    }
//
//    @Step("Проверка сценария, где имя принимает значение '{firstName}'")
//    public void checkTestDefTestFNStep(String firstName, Boolean expected) {
//        makeResumeNow.inputFirstName(firstName);
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        makeResumeNow.submitPushResume();
//        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefFirstNameErr).size() > 0;
//        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Ввод имени")
//    @Story(value = "Случаи, где ошибка не ожидается")
//    @Test
//    public void checkTestDefTestFNTest() {
//        checkTestDefTestFNStep("Test-Test", false);
//    }
//
//    @Step("Проверка сценария, где имя принимает значение 'null'")
//    public void checkEmptyFNStep(String firstName, Boolean expected) {
//        makeResumeNow.inputFirstName(firstName);
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        makeResumeNow.submitPushResume();
//        Boolean actual = driver.findElements(makeResumeNow.necessaryFirstNameFieldErr).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Ввод имени")
//    @Story(value = "Случаи с ожидаемой ошибкой")
//    @Test
//    public void checkEmptyFNTest() {
//        checkEmptyFNStep("", true);
//    }
//}