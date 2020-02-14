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
 * Класс для тестирования ввода фамилии при создании резюме со свойствами
 * <b>deleteResume</b>, <b>deleteResumeAccept</b>, <b>driver</b>,
 * <b>makeResumeNow</b>, <b>loginNow</b>,
 * <b>timeForPause</b> и <b>myChrDriPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.2
 */
public class InputLastNameTest1 {
    public By deleteResume = By.xpath("//button[@data-qa='resume-delete']");
    public By deleteResumeAccept = By.xpath("//button[text()='Удалить']");
    public WebDriver driver;
    public ForPageObjects makeResumeNow;
    public int timeForPause;

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

    @Step("Проверка сценария, где фамилия принимает значение '{LastName}'")
    public void checkEmptyLNStep(String firstName, String LastName,
                                       String city, Integer day, String month,
                                       Integer year, String sex, String jobExp,
                                       Boolean expected) {
        makeResumeNow.doIt(firstName, LastName, city, day, month, year, sex, jobExp);
        makeResumeNow.submitPushResume();
        makeResumeNow.waitTime(timeForPause);
        Boolean actual = driver.findElements(makeResumeNow.necessaryLastNameFieldErr).size() > 0;
        Assert.assertTrue("Данные оказались корректными.",actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод фамилии")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Test
    public void checkEmptyLNTest() {
        checkEmptyLNStep("Иван", "", "Омск", 21, "09", 1996, "Мужской", "Нет опыта работы",true);
    }
}