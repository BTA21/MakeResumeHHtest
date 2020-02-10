import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Run {

    private static String myChrDriPath;

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        myChrDriPath = (new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath());
        System.setProperty("webdriver.chrome.driver", myChrDriPath);
        // Создаем экземпляр WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        LoginPage loginNow = new LoginPage(driver);
        MakeResume makeResumeNow = new MakeResume(driver);
        loginNow.loginAs();
        makeResumeNow.doIt("Азмат", "Набиев", "Уфа", 21, "09", 1996, "Мужской", "Нет опыта работы");
        waitTime(10);
        makeResumeNow.submitPushResume();
        driver.quit();
    }

    public static void waitTime(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }
}