//import io.qameta.allure.Step;
//import org.junit.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.WebDriver;
//import java.io.File;
//import java.io.IOException;
///**
// * Класс для тестирования валидных данных при создании резюме со свойствами
// * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
// * <b>makeResumeNow</b>, <b>loginNow</b>,
// * <b>timeForPause</b> и <b>myChrDriPath</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.2
// */
//public class MakeResumeTest {
//    public By deleteResume = By.xpath("//button[@data-qa='resume-delete']");
//    public By deleteResumeAccept = By.xpath("//button[text()='Удалить']");
//    public WebDriver driver;
//    public ForPageObject makeResumeNow;
//    public int timeForPause;
//    private String myChrDriPath;
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
//    } catch(NoSuchElementException e) {
//
//    }
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
//        waitTime(timeForPause);
//        Assert.assertTrue("Резюме не было создано, были допущены ошибки." +
//                " Удалите резюме с названием 'Начинающий специалист' и попробуйте снова.",actual == expected);
//    }
//
//    @Test
//    public void checkValidDataTest(){
//        checkValidDataStep("Иван", "Иванов", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы", false);
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