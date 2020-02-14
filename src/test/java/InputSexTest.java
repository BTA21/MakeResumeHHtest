//import io.qameta.allure.Step;
//import org.junit.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.WebDriver;
//import java.io.File;
//import java.io.IOException;
///**
// * Класс для тестирования выбора пола при создании резюме со свойствами
// * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
// * <b>makeResumeNow</b>, <b>loginNow</b>,
// * <b>timeForPause</b> и <b>myChrDriPath</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.2
// */
//public class InputSexTest {
//    public By deleteResume = By.xpath("//button[@data-qa='resume-delete']");
//    public By deleteResumeAccept = By.xpath("//button[text()='Удалить']");
//    public WebDriver driver;
//    public ForPageObject makeResumeNow;
//    public String myChrDriPath;
//    public int timeForPause;
//
//    @Before
//    public void start() throws IOException, ParseException {
//        myChrDriPath = (new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath());
//        System.setProperty("webdriver.chrome.driver", myChrDriPath);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        makeResumeNow = new ForPageObject(driver);
//        makeResumeNow.loginAs();
//        timeForPause = 8;
//    }
//
//    @After
//    public void end() {
//        try {
//            driver.findElement(deleteResume).click();
//            driver.findElement(deleteResumeAccept).click();
//        } catch(NoSuchElementException e) {
//
//        }
//        driver.quit();
//    }
//
//    @Step("Проверка сценария, где пол принимает значение '{sex}'")
//    public void checkMaleSexStep(String firstName, String LastName,
//                                  String city, Integer day, String month,
//                                  Integer year, String sex, String jobExp,
//                                  Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.sexLessErr).size() > 0;
//        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
//    }
//
//    @Test
//    public void checkMaleSexTest() {
//        checkMaleSexStep("1Иван", "Иванов", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы",false);
//    }
//
//    @Ignore
//    public void waitTime() {
//    }
//    public static void waitTime(Integer sec) {
//        try {
//            Thread.sleep(sec * 1000);
//        } catch (InterruptedException e) {
//        }
//    }
//
//}