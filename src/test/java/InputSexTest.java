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
// * Класс для тестирования выбора пола при создании резюме со свойствами
// * <b>driver</b> и <b>makeResumeNow</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.3
// */
//public class InputSexTest {
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
//    @Step("Проверка сценария, где пол принимает значение '{sex}'")
//    public void checkMaleSexStep(String sex, Boolean expected) {
//        makeResumeNow.selectSex(sex);
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        makeResumeNow.submitPushResume();
//        Boolean actual = driver.findElements(makeResumeNow.sexLessErr).size() > 0;
//        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Выбор пола")
//    @Story(value = "Случаи, где ошибка не ожидается")
//    @Test
//    public void checkMaleSexTest() {
//        checkMaleSexStep("Мужской",false);
//    }
//}