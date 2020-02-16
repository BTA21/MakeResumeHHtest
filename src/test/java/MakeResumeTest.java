import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для тестирования валидных данных при создании резюме со свойствами
 * <b>driver</b>, <b>makeResumeNow</b>, <b>firstName</b>,
 * <b>LastName</b>, <b>phoneNumber</b>, <b>city</b>,
 * <b>day</b>, <b>month</b>, <b>year</b>,
 * <b>sex</b>, <b>jobExp</b> и <b>expected</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.3
 */
@RunWith(Parameterized.class)
public class MakeResumeTest {
    public WebDriver driver;
    public ForPageObject makeResumeNow;

    @Parameterized.Parameter
    public String firstName;

    @Parameterized.Parameter(1)
    public String LastName;

    @Parameterized.Parameter(2)
    public String phoneNumber;

    @Parameterized.Parameter(3)
    public String city;

    @Parameterized.Parameter(4)
    public Integer day;

    @Parameterized.Parameter(5)
    public String month;

    @Parameterized.Parameter(6)
    public Integer year;

    @Parameterized.Parameter(7)
    public String sex;

    @Parameterized.Parameter(8)
    public String jobExp;

    @Parameterized.Parameter(9)
    public Boolean expected;

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

    @Step("Проверка идеального сценария")
    public void checkValidDataStep(String firstName, String LastName, String phoneNumber,
                                          String city, Integer day, String month,
                                          Integer year, String sex, String jobExp,
                                          Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, phoneNumber, city, day, month, year, sex, jobExp);
        Boolean actual = driver.findElements(makeResumeNow.necessaryFieldErr).size() > 0;
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        driver.findElement(makeResumeNow.resumesForDelete).click();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        driver.findElement(makeResumeNow.resumeForDelete).click();
        Assert.assertTrue("Резюме не было создано, были допущены ошибки." +
                " Удалите резюме с названием 'Начинающий специалист' и попробуйте снова.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод всех данных")
    @Story(value = "Случаи, где ошибка не ожидается")
    @Test
    public void checkValidDataTest(){
        checkValidDataStep(firstName, LastName, phoneNumber, city, day, month, year, sex, jobExp, expected);
    }

    @Parameterized.Parameters(name = "firstName = {0} | LastName = {1}" +
            " |phoneNumber = {2} |city = {3} |day = {4} |month = {5}" +
            " |year = {6}| sex = {7} | jobExp = {8}| expected = {9}")
    public static List<Object[]> dataProvider() {
        return Arrays.asList(new Object[][] {
                {"Иван", "Иванов", "+12345678901", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы", false}
        });
    }

}