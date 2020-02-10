import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.io.*;
import org.json.simple.parser.*;
import java.util.concurrent.TimeUnit;
/**
 * Класс страницы создания резюме со свойствами <b>makeNewResumeLocator</b>, <b>firstNameHolderLocator</b>,
 * <b>lastNameHolderLocator</b>, <b>cityHolderLocator</b>, <b>birthDayHolderLocator</b>,
 * <b>birthMonthSelectorLocator</b>, <b>birthYearHolderLocator</b>,
 * <b>buttonPushLocator</b>, <b>driver</b>, <b>jo</b> и <b>obj</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.1
 */
public class MakeResume {
    /** Переменная ссылки на форму создания резюме */
    By makeNewResumeLocator = By.xpath("//a[text()='Создать резюме']");
    /** Переменная поля ввода имени */
    By firstNameHolderLocator = By.xpath("//input[@placeholder='Имя']");
    /** Переменная поля ввода фамилии */
    By lastNameHolderLocator = By.xpath("//input[@placeholder='Фамилия']");
    /** Переменная поля ввода города*/
    By cityHolderLocator = By.xpath("//input[@data-qa='suggestCity resume-city' and @type='text']");
    /** Переменная поля ввода дня рождения*/
    By birthDayHolderLocator = By.xpath("//input[@placeholder='День']");
    /** Переменная списка выбора месяца рождения*/
    By birthMonthSelectorLocator = By.xpath("//span[text()='Месяц']");
    /** Переменная поля ввода года рождения*/
    By birthYearHolderLocator = By.xpath("//input[@placeholder='Год']");
    /** Переменная кнопки "Сохранить и опубликовать" */
    By buttonPushLocator = By.xpath("//button[text()='Сохранить и опубликовать']");
    /** Переменная объекта WebDriver */
    private WebDriver driver;
    /** Переменная объекта JSON */
    private JSONObject jo;
    /** Переменная объекта */
    private Object obj;
    /**
     * Конструктор - создание новой страницы создания резюме с определенными значениями
     * @param driver  экземпляр WebDriver
     * @see MakeResume#MakeResume(WebDriver)
     */
    public MakeResume(WebDriver driver) {
        this.driver = driver;
        // Вызов функции неявного ожидания
        driver.manage().timeouts().
                implicitlyWait(11, TimeUnit.SECONDS);
    }
    /**
     * Функция ввода имени
     * @param firstName имя
     * @return возвращает страницу
     */
    public MakeResume typeFirstname(String firstName) {
        driver.findElement(firstNameHolderLocator).clear();
        driver.findElement(firstNameHolderLocator).sendKeys(firstName);
        return this;
    }
    /**
     * Функция ввода фамилии
     * @param lastName фамилия
     * @return возвращает страницу
     */
    public MakeResume typeLastname(String lastName) {
        driver.findElement(lastNameHolderLocator).clear();
        driver.findElement(lastNameHolderLocator).sendKeys(lastName);
        return this;
    }
    /**
     * Функция ввода города
     * @param city город
     * @return возвращает страницу
     */
    public MakeResume typeCity(String city) {
        driver.findElement(cityHolderLocator).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE + city + Keys.ENTER);
        waitTime(3);
        return this;
    }
    /**
     * Функция ввода дня рождения
     * @param day день рождения
     * @return возвращает страницу
     */
    public MakeResume typeBirthdayDateDay(String day) {
        driver.findElement(birthDayHolderLocator).sendKeys(day);
        return this;
    }
    /**
     * Функция выбора месяца рождения
     * @param month месяц рождения
     * @return возвращает страницу
     */
    public MakeResume typeBirthdayDateMonth(String month) {
        driver.findElement(birthMonthSelectorLocator).submit();
        driver.findElement(By.xpath("//option [@value='"+ month +"']")).click();
        return this;
    }
    /**
     * Функция ввода года рождения
     * @param year год рождения
     * @return возвращает страницу
     */
    public MakeResume typeBirthdayDateYear(String year) {
        driver.findElement(birthYearHolderLocator).sendKeys(year);
        return this;
    }
    /**
     * Функция выбора пола
     * @param sex пол
     * @return возвращает страницу
     */
    public MakeResume chooseSex(String sex) {
        By sexCheckBoxLocator = By.xpath("//span [@class='bloko-radio__text' and text() ='" + sex + "']");
        driver.findElement(sexCheckBoxLocator).click();
        return this;
    }
    /**
     * Функция выбора опыта работы
     * @param experienceSkill опыт работы
     * @return возвращает страницу
     */
    public MakeResume chooseJobExperience(String experienceSkill) {
        By chooseJobExperienceOpt = By.xpath("//span [text() ='" + experienceSkill +"']");
        driver.findElement(chooseJobExperienceOpt).click();
        return this;
    }
    /**
     * Функция нажатия кнопки "Сохранить и опубликовать"
     * @return возвращает страницу
     */
    public MakeResume submitPushResume() {
        driver.findElement(buttonPushLocator).submit();
        return this;
    }
    /**
     * Функция нажатия на гиперссылку на форму создания резюме
     * @return возвращает страницу
     */
    public MakeResume submitStartMakeResume() {
        driver.findElement(makeNewResumeLocator).click();
        return this;
    }
    /**
     * Функция создания резюме
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param month месяц рождения
     * @param year год рождения
     * @param sex пол
     * @param jobExp опыт работы
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, String month,
                           Integer year, String sex, String jobExp) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay((day.toString()));
        typeBirthdayDateMonth(month);
        typeBirthdayDateYear(year.toString());
        chooseSex(sex);
        chooseJobExperience(jobExp);
        waitTime(3);
        return this;
    }
    /**
     * Функция создания резюме
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param month месяц рождения
     * @param year год рождения
     * @param sex пол
     * @param jobExp опыт работы
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, String day, String month,
                           Integer year, String sex, String jobExp) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay(day);
        typeBirthdayDateMonth(month);
        typeBirthdayDateYear(year.toString());
        chooseSex(sex);
        chooseJobExperience(jobExp);
        waitTime(3);
        return this;
    }
    /**
     * Функция создания резюме
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param month месяц рождения
     * @param year год рождения
     * @param sex пол
     * @param jobExp опыт работы
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, String month,
                           String year, String sex, String jobExp) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay((day.toString()));
        typeBirthdayDateMonth(month);
        typeBirthdayDateYear(year);
        chooseSex(sex);
        chooseJobExperience(jobExp);
        waitTime(3);
        return this;
    }
    /**
     * Функция создания резюме без выбора месяца рождения
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param year год рождения
     * @param sex пол
     * @param jobExp опыт работы
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day,
                           Integer year, String sex,
                           String jobExp) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay((day.toString()));
        typeBirthdayDateYear(year.toString());
        chooseSex(sex);
        chooseJobExperience(jobExp);
        waitTime(3);
        return this;
    }
    /**
     * Функция создания резюме без указания опыта работы
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param month месяц рождения
     * @param year год рождения
     * @param sex пол
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, String month,
                           Integer year, String sex) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay(day.toString());
        typeBirthdayDateMonth(month);
        typeBirthdayDateYear(year.toString());
        chooseSex(sex);
        waitTime(3);
        return this;
    }
    /**
     * Функция создания резюме без указания месяца и опыта работы
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param year год рождения
     * @param sex пол
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, Integer year,
                           String sex) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay(day.toString());
        typeBirthdayDateYear(year.toString());
        chooseSex(sex);
        waitTime(3);
        return this;
    }
    /**
     * Функция создания резюме без указания месяца, опыта работы и пола
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param year год рождения
     * @return возвращает страницу
     */
    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day,
                           Integer year) {
        submitStartMakeResume();
        typeFirstname(firstName);
        typeLastname(lastName);
        waitTime(3);
        typeCity(city);
        typeBirthdayDateDay(day.toString());
        typeBirthdayDateYear(year.toString());
        waitTime(3);
        return this;
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
