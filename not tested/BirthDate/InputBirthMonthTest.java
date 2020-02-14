import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.junit.*;
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
public class InputBirthMonthTest {
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

    @Step("Проверка сценария, где дата рождения принимает значение {day}.null.{year}")
    public void checkEmptyMonthBDStep(String firstName, String LastName,
                                 String city, Integer day, String month,
                                 Integer year, String sex, String jobExp, Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day,month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.notCorrectDate).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод даты рождения")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkEmptyMonthBDTest() {
        checkEmptyMonthBDStep("Иван", "Иванов", "Омск", 12,"null", 1996, "Мужской", "Нет опыта работы", true);
    }
}