import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.json.simple.parser.JSONParser;
import java.io.*;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

/**
 * Класс страницы для хранения методов и элементов страниц со свойствами <b>usernameLocator</b>, <b>passwordLocator</b>,
 * <b>makeNewResumeLocator</b>, <b>firstNameHolderLocator</b>, <b>lastNameHolderLocator</b>, <b>cityHolderLocator</b>,
 * <b>birthDayHolderLocator</b>, <b>birthMonthSelectorLocator</b>, <b>birthYearHolderLocator</b>,
 * <b>buttonPushLocator</b>, <b>driver</b>, <b>jo</b>, <b>obj</b>, <b>obj</b> и <b>myLogPassPath</b>.
 * @author Набиев Азамат Ильдусович
 * @version 1.1
 */
public class ForPageObject {
    /** Переменная объекта логина */
    By usernameLocator = By.name("username");

    /** Переменная объекта пароля*/
    By passwordLocator = By.name("password");

    /** Переменная объекта кнопки "Войти в личный кабинет" */
    By loginButtonLocator = By.xpath("//input[@value='Войти в личный кабинет']");

    /** Переменная ссылки на форму создания резюме */
    By makeNewResumeLocator = By.xpath("//a[text()='Создать резюме']");

    /** Переменная поля ввода имени */
    By firstNameHolderLocator = By.xpath("//input[@placeholder='Имя']");

    /** Переменная поля ввода фамилии */
    By lastNameHolderLocator = By.xpath("//input[@placeholder='Фамилия']");

    /** Переменная поля ввода города*/
    By cityHolderLocator = By.xpath("//input[@data-qa='suggestCity resume-city'" +
            " and @type='text']");

    /** Переменная поля ввода дня рождения*/
    By birthDayHolderLocator = By.xpath("//input[@placeholder='День']");

    /** Переменная списка выбора месяца рождения*/
    By birthMonthSelectorLocator = By.xpath("//span[text()='Месяц']");

    /** Переменная поля ввода года рождения*/
    By birthYearHolderLocator = By.xpath("//input[@placeholder='Год']");

    /** Переменная кнопки "Сохранить и опубликовать" */
    By buttonPushLocator = By.xpath("//button[text()='Сохранить и опубликовать']");

    /** Переменная объекта кнопки "Удалить резюме" */
    By deleteResume = By.xpath("//button[@data-qa='resume-delete']");

    /** Переменная объекта кнопки "Удалить" */
    By deleteResumeAccept = By.xpath("//button[text()='Удалить']");

    /** Переменная сообщения ошибки "Обязательное поле" */
    By necessaryFieldErr = By.xpath("//div[@class='bloko-form-error " +
            "bloko-form-error_entered' and text()='Обязательное поле']");

    /** Переменная сообщения ошибки "Обязательное поле" у имени*/
    By necessaryFirstNameFieldErr = By.xpath("//div[@class='bloko-control-group__vertical-item' and" +
            " .//input[@placeholder='Имя']]//div[@class='bloko-form-error " +
            "bloko-form-error_entered' and text()='Обязательное поле']");

    /** Переменная сообщения ошибки "Обязательное поле" у фамилии */
    By necessaryLastNameFieldErr = By.xpath("//div[@class='bloko-control-group__vertical-item' and" +
            " .//input[@placeholder='Фамилия']]//div[@class='bloko-form-error " +
            "bloko-form-error_entered' and text()='Обязательное поле']");

    /** Переменная сообщения ошибки "Обязательное поле" у города */
    By necessaryCityFieldErr = By.xpath("//div[@class='bloko-column" +
            " bloko-column_container bloko-column_xs-4 bloko-column_s-8" +
            " bloko-column_m-12 bloko-column_l-16' and " +
            ".//input[@data-qa='suggestCity resume-city']]" +
            "//div[@class='bloko-form-error bloko-form-error_entered' " +
            "and text()='Обязательное поле']");

    /** Переменная сообщения ошибки "Обязательное поле" у опыта работы */
    By necessaryJobExpFieldErr = By.xpath("//div[@class='bloko-column bloko-column_xs-0 bloko-column_s-8" +
            " bloko-column_m-6 bloko-column_l-6']//div [@class='bloko-form-error" +
            " bloko-form-error_entered' and text()= 'Обязательное поле']");

    /** Переменная сообщения ошибки "Только буквы и дефис" у имени*/
    By onlyABCandDefFirstNameErr = By.xpath("//div[@class='bloko-control-group__vertical-item' and" +
            " .//input[@placeholder='Имя']]//div[@class='bloko-form-error " +
            "bloko-form-error_entered' and text()='Только буквы и дефис']");

    /** Переменная сообщения ошибки "Только буквы и дефис" у фамилии*/
    By onlyABCandDefLastNameErr = By.xpath("//div[@class='bloko-control-group__vertical-item' and" +
            " .//input[@placeholder='Фамилия']]//div[@class='bloko-form-error " +
            "bloko-form-error_entered' and text()='Только буквы и дефис']");

    /** Переменная сообщения ошибки "Некорректная дата" */
    By notCorrectDate = By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Некорректная дата']");

    /** Переменная сообщения ошибки "Слишком рано" */
    By tooEarlyErr = By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Слишком рано']");

    /** Переменная сообщения ошибки "Вы слишком молоды" */
    By tooYoungErr = By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Вы слишком молоды']");

    /** Переменная сообщения ошибки "Вы не выбрали пол" */
    By sexLessErr = By.xpath("//div [@class='bloko-form-error bloko-form-error_entered' and text()= 'Вы не выбрали пол']");

    /** Переменная объекта WebDriver */
    WebDriver driver;

    /** Переменная времени задержки, сек */
    Integer timeForPause = 8;

    /** Переменная объекта JSON */
    private JSONObject jo;

    /** Переменная объекта */
    private Object obj;

    /** Переменная пути к JSON-файлу, где хранятся логин и пароль для входа в систему */
    private String myLogPassPath;

    /**
     * Конструктор - создание новой страницы с определенными значениями
     * @param driver  экземпляр WebDriver
     * @see ForPageObject#ForPageObject(WebDriver)
     */
    public ForPageObject(WebDriver driver) {
        this.driver = driver;
        // Вызов функции неявного ожидания
        driver.manage().timeouts().
                implicitlyWait(11, TimeUnit.SECONDS);
        // Открываем страницу входа в систему, используя драйвер
        driver.get("https://ufa.hh.ru/account/login");
    }

    /**
     * Функция ввода логина
     * @param username логин
     * @return возвращает страницу
     */
    public ForPageObject typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    /**
     * Функция ввода пароля
     * @param password пароль
     * @return возвращает страницу
     */
    public ForPageObject typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    /**
     * Функция нажатия кнопки "Войти в личный кабинет"
     * @return возвращает страницу
     */
    public ForPageObject submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return this;
    }

    /**
     * Функция осуществления входа в систему
     * @throws IOException Исключение, которое выдается при возникновении ошибки ввода-вывода
     * @throws ParseException Исключение, которое выдается, если синтаксический анализ вызывает ошибку
     * @return возвращает страницу
     */
    public ForPageObject loginAs() throws IOException, ParseException{
        myLogPassPath = (new File("src\\main\\resources\\config.json").getAbsolutePath());
        Object obj = new JSONParser().parse(new FileReader(myLogPassPath));
        JSONObject jo = (JSONObject) obj;
        typeUsername((String)jo.get("username"));
        typePassword((String)jo.get("password"));
        submitLogin();
        return this;
    }

    /**
     * Функция ввода имени
     * @param firstName имя
     * @return возвращает страницу
     */
    public ForPageObject typeFirstname(String firstName) {
        driver.findElement(firstNameHolderLocator).clear();
        driver.findElement(firstNameHolderLocator).sendKeys(firstName);
        return this;
    }
    /**
     * Функция ввода фамилии
     * @param lastName фамилия
     * @return возвращает страницу
     */
    public ForPageObject typeLastname(String lastName) {
        driver.findElement(lastNameHolderLocator).clear();
        driver.findElement(lastNameHolderLocator).sendKeys(lastName);
        return this;
    }
    /**
     * Функция ввода города
     * @param city город
     * @return возвращает страницу
     */
    public ForPageObject typeCity(String city) {
        driver.findElement(cityHolderLocator).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE + city + Keys.ENTER);
        waitTime(3);
        return this;
    }
    /**
     * Функция ввода дня рождения
     * @param day день рождения
     * @return возвращает страницу
     */
    public ForPageObject typeBirthdayDateDay(String day) {
        driver.findElement(birthDayHolderLocator).sendKeys(day);
        return this;
    }
    /**
     * Функция выбора месяца рождения
     * @param month месяц рождения
     * @return возвращает страницу
     */
    public ForPageObject typeBirthdayDateMonth(String month) {
        driver.findElement(birthMonthSelectorLocator).submit();
        if (month == "null") {
            driver.findElement(By.xpath("//select[@data-qa='resume__birthday__month-select']//option[1]")).click();
        } else {
            driver.findElement(By.xpath("//option [@value='" + month + "']")).click();
        }
        return this;
    }
    /**
     * Функция ввода года рождения
     * @param year год рождения
     * @return возвращает страницу
     */
    public ForPageObject typeBirthdayDateYear(String year) {
        driver.findElement(birthYearHolderLocator).sendKeys(year);
        return this;
    }
    /**
     * Функция выбора пола
     * @param sex пол
     * @return возвращает страницу
     */
    public ForPageObject chooseSex(String sex) {
        By sexCheckBoxLocator = By.xpath("//span [@class='bloko-radio__text' and text() ='" + sex + "']");
        driver.findElement(sexCheckBoxLocator).click();
        return this;
    }
    /**
     * Функция выбора опыта работы
     * @param experienceSkill опыт работы
     * @return возвращает страницу
     */
    public ForPageObject chooseJobExperience(String experienceSkill) {
        By chooseJobExperienceOpt = By.xpath("//span [text() ='" + experienceSkill +"']");
        driver.findElement(chooseJobExperienceOpt).click();
        return this;
    }
    /**
     * Функция нажатия кнопки "Сохранить и опубликовать"
     * @return возвращает страницу
     */
    public ForPageObject submitPushResume() {
        driver.findElement(buttonPushLocator).submit();
        return this;
    }
    /**
     * Функция нажатия на гиперссылку на форму создания резюме
     * @return возвращает страницу
     */
    public ForPageObject submitStartMakeResume() {
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
    public ForPageObject doIt(String firstName, String lastName,
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
    public ForPageObject doIt(String firstName, String lastName,
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
    public ForPageObject doIt(String firstName, String lastName,
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
    public ForPageObject doIt(String firstName, String lastName,
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
    public ForPageObject doIt(String firstName, String lastName,
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
     * Функция создания резюме без указания месяца, опыта работы и пола
     * @param firstName имя
     * @param lastName фамилия
     * @param city город
     * @param day день рождения
     * @param year год рождения
     * @return возвращает страницу
     */
    public ForPageObject doIt(String firstName, String lastName,
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
