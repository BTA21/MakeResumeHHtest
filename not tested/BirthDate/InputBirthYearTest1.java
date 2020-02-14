import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

/**
 * Класс для тестирования ввода даты рождения при создании резюме со свойствами
 * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
 * <b>makeResumeNow</b>, <b>loginNow</b>,
 * <b>timeForPause</b> и <b>myChrDriPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.2
 */
public class InputBirthYearTest1 {
    public WebDriver driver;
    public ForPageObjects makeResumeNow;

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

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check1899YearBDStep(String firstName, String LastName,
                                    String city, Integer day, String month,
                                    Integer year, String sex, String jobExp, Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.tooEarlyErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Test
    public void check1899YearBDTest() {
        check1899YearBDStep("Иван", "Иванов", "Омск", 12, "09", 1899, "Мужской", "Нет опыта работы", true);
    }

    @Step("Проверка сценария, где дата рождения принимает значение {day}.{month}.{year}")
    public void check2006YearBDStep(String firstName, String LastName,
                                    String city, Integer day, String month,
                                    Integer year, String sex, String jobExp, Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Вы слишком молоды']")).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }
    @Step("Проверка сценария, где дата принимает значение '{day}.{month}.{year}'")
    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void check2006YearBDTest() {
        check2006YearBDStep("Иван", "Иванов", "Омск", 12, "09", 2006, "Мужской", "Нет опыта работы", true);
    }
}