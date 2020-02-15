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
// * Класс для тестирования выбора опыта работы при создании резюме со свойствами
// * <b>driver</b> и <b>makeResumeNow</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.3
// */
//public class InputJobExpTest {
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
//    @Step("Проверка сценария, где опыт работы не выбирается")
//    public void checkEmptyJEStep(Boolean expected) {
//        makeResumeNow.doIt();
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        makeResumeNow.submitPushResume();
//        Boolean actual = driver.findElements(makeResumeNow.necessaryJobExpFieldErr).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Выбор опыта работы")
//    @Story(value = "Случаи ожидаемой ошибкой")
//    @Test
//    public void checkEmptyJETest() {
//        checkEmptyJEStep(true);
//    }
//}