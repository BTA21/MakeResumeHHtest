//import io.qameta.allure.Step;
//import org.junit.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.WebDriver;
//import java.io.File;
//import java.io.IOException;
//
///**
// * Класс для тестирования ввода даты рождения при создании резюме со свойствами
// * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
// * <b>makeResumeNow</b>, <b>loginNow</b>,
// * <b>timeForPause</b> и <b>myChrDriPath</b>.
// * @author Набиев Азамат Ильдусович
// * @version 1.2
// */
//public class InputBirthDateTest {
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
//        } catch(NoSuchElementException e) {
//
//        }
//        driver.quit();
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void checkTestDayBDStep(String firstName, String LastName,
//                                 String city, String day, String month,
//                                 Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void checkTestDayBDTest() {
//        checkTestDayBDStep("Иван", "Иванов", "Омск", "Test", "09", 1996, "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void check123DayBDStep(String firstName, String LastName,
//                                   String city, Integer day, String month,
//                                   Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void check123DayBDTest() {
//        check123DayBDStep("Иван", "Иванов", "Омск", 123, "09", 1996, "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void checkTest12DayBDStep(String firstName, String LastName,
//                                  String city, String day, String month,
//                                  Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void checkTest12DayBDTest() {
//        checkTest12DayBDStep("Иван", "Иванов", "Омск", "Test12", "09", 1996, "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void check12DayBDStep(String firstName, String LastName,
//                                     String city, Integer day, String month,
//                                     Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
//    }
//
//    @Test
//    public void check12DayBDTest() {
//        check12DayBDStep("1Иван", "Иванов", "Омск", 12, "09", 1996, "Мужской", "Нет опыта работы",false);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}. .{year}")
//    public void checkEmptyMonthBDStep(String firstName, String LastName,
//                                 String city, Integer day,
//                                 Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void checkEmptyMonthBDTest() {
//        checkEmptyMonthBDStep("Иван", "Иванов", "Омск", 12, 1996, "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void checkTestYearBDStep(String firstName, String LastName,
//                                 String city, Integer day, String month,
//                                 String year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void checkTestYearBDTest() {
//        checkTestYearBDStep("Иван", "Иванов", "Омск", 12, "09", "Test", "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void checkTest12YearBDStep(String firstName, String LastName,
//                                    String city, Integer day, String month,
//                                    String year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void checkTest12YearBDTest() {
//        checkTest12YearBDStep("Иван", "Иванов", "Омск", 12, "09", "Test12", "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void check12YearBDStep(String firstName, String LastName,
//                                      String city, Integer day, String month,
//                                      Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void check12YearBDTest() {
//        check12YearBDStep("Иван", "Иванов", "Омск", 12, "09", 12, "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void check1899YearBDStep(String firstName, String LastName,
//                                  String city, Integer day, String month,
//                                  Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.tooEarlyErr).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void check1899YearBDTest() {
//        check1899YearBDStep("Иван", "Иванов", "Омск", 12, "09", 1899, "Мужской", "Нет опыта работы", true);
//    }
//
//    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
//    public void check2006YearBDStep(String firstName, String LastName,
//                                    String city, Integer day, String month,
//                                    Integer year, String sex, String jobExp, Boolean expected) {
//        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
//        makeResumeNow.submitPushResume();
//        waitTime(timeForPause);
//        Boolean actual = driver.findElements(makeResumeNow.tooYoungErr).size() > 0;
//        Assert.assertTrue("Данные оказались корректными.",actual == expected);
//    }
//
//    @Test
//    public void check2006YearBDTest() {
//        check2006YearBDStep("Иван", "Иванов", "Омск", 12, "09", 2006, "Мужской", "Нет опыта работы", true);
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