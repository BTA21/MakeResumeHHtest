import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.io.*;
import org.json.simple.parser.*;
import java.util.concurrent.TimeUnit;


public class MakeResume {
    By makeNewResumeLocator = By.xpath("//a[text()='Создать резюме']");
    By firstNameHolderLocator = By.xpath("//input[@placeholder='Имя']");
    By lastNameHolderLocator = By.xpath("//input[@placeholder='Фамилия']");
    By cityHolderLocator = By.xpath("//input[@data-qa='suggestCity resume-city' and @type='text']");
    By birthDayHolderLocator = By.xpath("//input[@placeholder='День']");
    By birthMonthSelectorLocator = By.xpath("//span[text()='Месяц']");
    By birthYearHolderLocator = By.xpath("//input[@placeholder='Год']");
    By buttonPushLocator = By.xpath("//button[text()='Сохранить и опубликовать']");
    private WebDriver driver;
    private JSONObject jo;
    private Object obj;

    public MakeResume(WebDriver driver) {
        this.driver = driver;

        driver.manage().timeouts().
                implicitlyWait(11, TimeUnit.SECONDS);
    }

    public MakeResume typeFirstname(String firstName) {
        driver.findElement(firstNameHolderLocator).clear();
        driver.findElement(firstNameHolderLocator).sendKeys(firstName);
        return this;
    }

    public MakeResume typeLastname(String lastName) {
        driver.findElement(lastNameHolderLocator).clear();
        driver.findElement(lastNameHolderLocator).sendKeys(lastName);
        return this;
    }

    public MakeResume typeCity(String city) {
        driver.findElement(cityHolderLocator).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE + city + Keys.ENTER);
        waitTime(3);
        return this;
    }

    public MakeResume typeBirthdayDateDay(String day) {
        driver.findElement(birthDayHolderLocator).sendKeys(day);
        return this;
    }

    public MakeResume typeBirthdayDateMonth(String month) {
        driver.findElement(birthMonthSelectorLocator).submit();
        driver.findElement(By.xpath("//option [@value='"+ month +"']")).click();
        return this;
    }

    public MakeResume typeBirthdayDateYear(String year) {
        driver.findElement(birthYearHolderLocator).sendKeys(year);
        return this;
    }

    public MakeResume chooseSex(String gender) {
        By sexCheckBoxLocator = By.xpath("//span [@class='bloko-radio__text' and text() ='" + gender + "']");
        driver.findElement(sexCheckBoxLocator).click();
        return this;
    }

    public MakeResume chooseJobExperience(String experienceSkill) {
        By chooseJobExperienceOpt = By.xpath("//span [text() ='" + experienceSkill +"']");
        driver.findElement(chooseJobExperienceOpt).click();
        return this;
    }

    public MakeResume submitPushResume() {
        driver.findElement(buttonPushLocator).submit();
        return this;
    }

    public MakeResume submitStartMakeResume() {
        driver.findElement(makeNewResumeLocator).click();
        return this;
    }

    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, String month,
                           Integer year, String sex, String jobExp) throws IOException, FileNotFoundException, ParseException{
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

    public MakeResume doIt(String firstName, String lastName,
                           String city, String day, String month,
                           Integer year, String sex, String jobExp) throws IOException, FileNotFoundException, ParseException{
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

    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, String month,
                           String year, String sex, String jobExp) throws IOException, FileNotFoundException, ParseException{
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

    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day,
                           Integer year, String sex,
                           String jobExp) throws IOException, FileNotFoundException, ParseException{
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

    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, String month,
                           Integer year, String sex) throws IOException, FileNotFoundException, ParseException{
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

    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day, Integer year,
                           String sex) throws IOException, FileNotFoundException, ParseException{
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

    public MakeResume doIt(String firstName, String lastName,
                           String city, Integer day,
                           Integer year) throws IOException, FileNotFoundException, ParseException{
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

    public static void waitTime(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }


}
