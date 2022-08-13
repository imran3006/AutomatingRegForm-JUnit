import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class RegistrationForm {
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headed");
        driver =new ChromeDriver(options);

//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void practiceForm() throws InterruptedException, IOException, ParseException {
  // initialize json file
        String fileName = "./src/test/resources/students.json";
// json parsing
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
      JSONObject studentsObj = new JSONObject();
        JSONArray studentsArray = (JSONArray) obj;

//Automate registration form
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement txtFirstName = driver.findElement(By.id("firstName"));
        txtFirstName.sendKeys("Imran");
        Thread.sleep(1000);



        WebElement txtLastName = driver.findElement(By.id("lastName"));
        txtLastName.sendKeys("Sarker");
        Thread.sleep(1000);


        WebElement txtMail = driver.findElement(By.id("userEmail"));
        txtMail.sendKeys("imranabc@gmail.com");
        Thread.sleep(1000);


        WebElement r1 = driver.findElement(By.xpath("//label[contains(text(),'Male')]"));
        WebElement r2 = driver.findElement(By.xpath("//label[contains(text(),'Female')]"));
        WebElement r3 = driver.findElement(By.xpath("//label[contains(text(),'Other')]"));
        r1.click();
        Thread.sleep(1000);



        WebElement NnumberAdd = driver.findElement(By.id("userNumber"));
        NnumberAdd.sendKeys("0183356448");
        Thread.sleep(1000);



        Actions actions = new Actions(driver);
        WebElement txtDate = driver.findElement(By.id("dateOfBirthInput"));
        txtDate.click();
        txtDate.sendKeys(Keys.CONTROL + "a");
//        txtDate.sendKeys(Keys.BACK_SPACE);
//        driver.findElement(By.id("datePickerMonthYearInput")).clear();
        Thread.sleep(3000);
        txtDate.sendKeys("01 Dec 1996");
        txtDate.sendKeys(Keys.ENTER);
        Thread.sleep(1000);


        WebElement subject = driver.findElement(By.id("subjectsInput"));
        subject.sendKeys("CSE");
        Thread.sleep(1000);





        WebElement h1 = driver.findElement(By.xpath("//label[contains(text(),'Sports')]"));
        WebElement h2 = driver.findElement(By.xpath("//label[contains(text(),'Reading')]"));
        WebElement h3 = driver.findElement(By.xpath("//label[contains(text(),'Music')]"));
        h2.click();
        Thread.sleep(1000);



        WebElement upldpic = driver.findElement(By.id("uploadPicture"));
        upldpic.sendKeys("E:\\aa.png");
        Thread.sleep(1000);


        WebElement address = driver.findElement(By.id("currentAddress"));
        address.sendKeys("East Nasirabad, Chittagong-4209");
        Thread.sleep(1000);


               /*---- dropdown menu*------*/

      WebElement dropDown= driver.findElement(By.id("react-select-3-input"));
//      actions.moveToElement(dropDown);
//      actions.keyDown(Keys.SHIFT);
//      actions.sendKeys(Keys.ARROW_DOWN).perform();

//        dropDown.sendKeys(Keys.ARROW_DOWN);
//        dropDown.sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//
//
//
//
//        WebElement dropDown2= driver.findElement(By.id("react-select-4-input"));
//        dropDown2.sendKeys(Keys.ARROW_DOWN);
//      dropDown.sendKeys(Keys.ENTER);
//        Thread.sleep(3000);



        WebElement btn = driver.findElement(By.id("submit"));
//       btn.click();
        btn.sendKeys(Keys.ENTER);
//        String txt=driver.findElement(By.className("modal-title h4")).getText();
//        Assert.assertTrue(txt.contains("Thanks for submitting the form"));



/*-----scraping data from web table and put it to the json file---- */

        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        int i = 0;
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                i++;
                //JSONObject studentsObj = new JSONObject();
                studentsObj.put(cells.get(0).getText(),cell.getText());


            }

        }
        studentsArray.add(studentsObj);
        System.out.print(studentsArray);
        FileWriter file = new FileWriter(fileName);
        file.write(studentsArray.toJSONString());
        file.flush();
        file.close();



        Thread.sleep(3000);
    }

    @After
    public void closeDriver(){
//        //driver.close();
//        driver.quit();
    }




}
