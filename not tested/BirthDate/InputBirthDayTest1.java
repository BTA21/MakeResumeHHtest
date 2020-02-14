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

import static org.junit.Assert.*;
/**
 * Класс для тестирования ввода даты рождения при создании резюме со свойствами
 * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
 * <b>makeResumeNow</b>, <b>loginNow</b>,
 * <b>timeForPause</b> и <b>myChrDriPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.2
 */
@RunWith(Parameterized.class)
public class InputBirthDayTest1 {
    public WebDriver driver;
    public ForPageObjects makeResumeNow;


    @Parameterized.Parameter
    public String firstName;

    @Parameterized.Parameter(1)
    public String LastName;

    @Parameterized.Parameter(2)
    public String city;

    @Parameterized.Parameter(3)
    public String day;

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
    }

    @After
    public void end() {
        try {
            driver.findElement(makeResumeNow.deleteResume).click();
            driver.findElement(makeResumeNow.deleteResumeAccept).click();
        } catch(NoSuchElementException e) {

        }
        driver.quit();
    }

    @Step("Проверка сценария, где дата принимает значение {day}.{month}.{year}")
    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkBDTest() {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Parameterized.Parameters(name = "firstName = {0} | LastName = {1}" +
            " |city = {2} |day = {3} |month = {4}" +
            " |year = {5}| sex = {6} | jobExp = {7}| expected = {8}")
    public static List<Object[]> dataProvider() {
        return Arrays.asList(new Object[][] {
                {"Иван", "Иванов", "Омск", "Test", "09", 1996, "Мужской", "Нет опыта работы", true},
                {"Иван", "Иванов", "Омск", "Test12", "09", 1996, "Мужской", "Нет опыта работы", true}
        });
    }
}
