import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
/**
 * Класс для работы.
 * @author Набиев Азамат Ильдусович
 * @version 1.1
 */
public class Run {
    /** Переменная пути к chrome-драйверу */
    private static String myChrDriPath;
    /**
     * Процедура являющаяся точкой входа для выполняемой программы
     * @param args аргументы командной строки
     * @throws IOException Исключение, которое выдается при возникновении ошибки ввода-вывода
     * @throws ParseException Исключение, которое выдается, если синтаксический анализ вызывает ошибку
     */
    public static void main(String[] args) throws IOException, ParseException {
        myChrDriPath = (new File("src\\main\\resources\\chromedriver_win32\\chromedriver.exe").getAbsolutePath());
        // Метод устанавливает системное свойство, указанное указанным ключом.
        System.setProperty("webdriver.chrome.driver", myChrDriPath);
        // Создаем экземпляр WebDriver
        WebDriver driver = new ChromeDriver();
        // Раскрываем браузер на всё окно
        driver.manage().window().maximize();
        LoginPage loginNow = new LoginPage(driver);
        MakeResume makeResumeNow = new MakeResume(driver);
        loginNow.loginAs();
        makeResumeNow.doIt("Азмат", "Набиев", "Уфа", 21, "09", 1996, "Мужской", "Нет опыта работы");
        waitTime(10);
        makeResumeNow.submitPushResume();
        // Закрываем браузер
        driver.quit();
    }
    /**
     * Функция создания задержки
     * @param sec время
     */
    public static void waitTime(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }
}