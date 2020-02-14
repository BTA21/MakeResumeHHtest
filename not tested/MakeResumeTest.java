import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
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
 * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
 * <b>makeResumeNow</b>, <b>loginNow</b>,
 * <b>timeForPause</b> и <b>myChrDriPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.2
 */
@RunWith(Parameterized.class)
public class MakeResumeTest {
    public By deleteResume = By.xpath("//button[@data-qa='resume-delete']");
    public By deleteResumeAccept = By.xpath("//button[text()='Удалить']");
    public WebDriver driver;
    public ForPageObjects makeResumeNow;
    public int timeForPause;

    @Parameterized.Parameter
    public String firstName;

    @Parameterized.Parameter(1)
    public String LastName;

    @Parameterized.Parameter(2)
    public String city;

    @Parameterized.Parameter(3)
    public Integer day;

    @Parameterized.Parameter(4)
    public String month;

    @Parameterized.Parameter(5)
    public Integer year;

    @Parameterized.Parameter(6)
    public String sex;

    @Parameterized.Parameter(7)
    public String jobExp;

    @Parameterized.Parameter(8)
    public Boolean expected;

    @Before
    public void start() throws IOException, ParseException {
        System.setProperty("webdriver.chrome.driver", (new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath()));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        makeResumeNow = new ForPageObjects(driver);
        makeResumeNow.loginAs();
        timeForPause = 8;
    }

    @After
    public void end() {
        try {
            driver.findElement(deleteResume).click();
            driver.findElement(deleteResumeAccept).click();
    } catch(NoSuchElementException e) {

    }
        driver.quit();
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Тест с валидными данными")
    @Test
    public void checkValidDataTest(){
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        Boolean actual = driver.findElements(makeResumeNow.necessaryFieldErr).size() > 0;
        makeResumeNow.submitPushResume();
        waitTime(timeForPause);
        Assert.assertTrue("Резюме не было создано, были допущены ошибки." +
                " Удалите резюме с названием 'Начинающий специалист' и попробуйте снова.",actual == expected);
    }

    @Parameterized.Parameters(name = "firstName = {0} | LastName = {1}" +
            " |city = {2} |day = {3} |month = {4}" +
            " |year = {5}| sex = {6} | jobExp = {7}| expected = {8}")
    public static List<Object[]> dataProvider() {
        return Arrays.asList(new Object[][] {
                {"Иван", "Иванов", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы", false}
        });
    }

    @Ignore
    public static void waitTime(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }
}