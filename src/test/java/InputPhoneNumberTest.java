import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.apache.commons.lang.StringUtils;
import org.json.simple.parser.ParseException;
import org.junit.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Класс для тестирования ввода номера телефона при создании резюме со свойствами
 * <b>driver</b> и <b>makeResumeNow</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.3
 */

@RunWith(Theories.class)
public class InputPhoneNumberTest {
    public WebDriver driver;
    public ForPageObject makeResumeNow;

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

    @DataPoints
    public static Object[][] isEmptyData = new Object[][] {
            { "Test", true },
            { "Test12", true },
            { "12", true },
            { "+712", true },
            { "+123456789012", true },
    };

    @Step("Проверка сценария, где телефонный номер принимает значение '{phoneNumber}'")
    public void checkPhoneNumberStep(String phoneNumber, Boolean expected) {
        String message = "Данные оказались корректными.";
        makeResumeNow.inputPhoneNumber(phoneNumber);
        makeResumeNow.waitTime(makeResumeNow.timeForPause);
        makeResumeNow.submitPushResume();
        Boolean actual = driver.findElements(makeResumeNow.notCorrectPhoneNumber).size() > 0;
        if (expected == false)
            message = "Данные оказались некорректными.";
        Assert.assertTrue(message,actual == expected);
    }

    @Epic(value = "Тесты на ввод данных при создании резюме")
    @Feature(value = "Ввод номера телефона")
    @Story(value = "Случаи с ожидаемой ошибкой")
    @Theory
    public void checkPhoneNumberTest(final Object... testData) {
        checkPhoneNumberStep((String) testData[0], (Boolean) testData[1]);
    }

    @Ignore
    @Test
    public void checkPhoneNumberTest() {
        checkPhoneNumberStep("1Тест", true);
    }
}