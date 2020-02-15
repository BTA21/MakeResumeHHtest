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
// * Класс для тестирования валидных данных при создании резюме со свойствами
// * <b>driver</b> и <b>makeResumeNow</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.3
// */
//public class MakeResumeTest {
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
//    } catch(NoSuchElementException e) {}
//        driver.quit();
//    }
//
//    @Step("Проверка идеального сценария")
//    public void checkValidDataStep(String firstName, String LastName,
//                                          String city, Integer day, String month,
//                                          Integer year, String sex, String jobExp,
//                                          Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        Boolean actual = driver.findElements(makeResumeNow.necessaryFieldErr).size() > 0;
//        makeResumeNow.submitPushResume();
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        driver.findElement(makeResumeNow.resumesForDelete).click();
//        makeResumeNow.waitTime(makeResumeNow.timeForPause);
//        driver.findElement(makeResumeNow.resumeForDelete).click();
//        Assert.assertTrue("Резюме не было создано, были допущены ошибки." +
//                " Удалите резюме с названием 'Начинающий специалист' и попробуйте снова.",actual == expected);
//    }
//
//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Выбор опыта работы")
//    @Story(value = "Случаи ожидаемой ошибкой")
//    @Test
//    public void checkValidDataTest(){
//        checkValidDataStep("Иван", "Иванов", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы", false);
//    }
//}