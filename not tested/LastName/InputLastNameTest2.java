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
 * Класс для тестирования ввода фамилии при создании резюме со свойствами
 * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
 * <b>makeResumeNow</b>, <b>loginNow</b>,
 * <b>timeForPause</b> и <b>myChrDriPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.2
 */
public class InputLastNameTest2 {
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

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkEmptyLNStep(String firstName, String LastName,
                                 String city, Integer day, String month,
                                 Integer year, String sex, String jobExp,
                                 Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.onlyABCandDefLastNameErr).size() > 0;
        Assert.assertTrue("Данные оказались некорректными.",actual == expected);
    }

//    @Epic(value = "Тесты на ввод данных при создании резюме")
//    @Feature(value = "Ввод фамилии")
//    @Story(value = "Случаи, где ошибка не ожидается")
    @Test
    public void checkEmptyLNTest() {
        checkEmptyLNStep("1Иван", "Test-Test", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы",false);
    }
}