
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.util.*;


public class StepImplementation extends BaseTest{
    Random rand = new Random();
    String beforeDepartureTime, afterDepartureTime, beforeArrivalTime, afterArrivalTime;

    @Step("<id> 'li id elementi ekranda görünüyorsa tıkla")
    public void checkThenClickById(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        if(element.isDisplayed()){
            element.click();
        }
    }
    @Step("<xpath> 'li xpath elementi ekranda görünüyorsa tıkla")
    public void checkThenClickByXPath(String xpath) {
        MobileElement element = appiumDriver.findElement(By.xpath(xpath));
        if(element.isDisplayed()){
            element.click();
        }
    }

    @Step("<id> 'li id elementine tıkla, <text> değerini gir")
    public void sendKeyById(String id, String text) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        element.click();
        element.sendKeys(text);
    }

    @Step("<id> 'li id elementin görünür olup olmadığını kontrol et")
    public void isDisplay(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        Assert.assertTrue(element.getText()+" elementi görünmüyor",element.isDisplayed()==true);
        logger.info("Element basarili bir sekilde goruldu.");

    }

    @Step("Şuanki günden 7 gün sonrasına tarih seçilir")
    public void plus7Days() {
            new Actions(appiumDriver)
                    .sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                    .sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                    .sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                    .sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                    .sendKeys(Keys.ENTER).perform();
        logger.info("Takvimden tarih secimi basarili.");

    }
    @Step("<xpath> 'li uçuşlardan rastgele seç ve <departure> ile <arrival> değerlerini kaydet")
    public void randomSelectFly(String xpath, String departurePath, String arrivalPath) {

        int random = rand.nextInt(4);
        if (random==0){
            random = random+1;
        }
        System.out.println("random: "+random);
        MobileElement departure = appiumDriver.findElement(By.xpath("("+xpath+")["+random+"]/"+departurePath+""));
        beforeDepartureTime = departure.getAttribute("text");
        MobileElement arrival = appiumDriver.findElement(By.xpath("("+xpath+")["+random+"]/"+arrivalPath+""));
        beforeArrivalTime = arrival.getAttribute("text");

        MobileElement element = appiumDriver.findElement(By.xpath("("+xpath+")["+random+"]"));
        element.click();
        logger.info("Rastgele ucus secimi basarili.");
    }

    @Step("<departurePath> ile <arrivalPath> sondaki uçuş saatleri verileri")
    public void lastDataFlyHours(String departurePath, String arrivalPath) {
        MobileElement departure = appiumDriver.findElement(By.xpath(departurePath));
        afterDepartureTime = departure.getAttribute("text");

        MobileElement arrival = appiumDriver.findElement(By.xpath(arrivalPath));
        afterArrivalTime = arrival.getAttribute("text");
        logger.info("Detaylı ucus listesindeki ucus saati verilerin kaydi basarili.");
    }

    @Step("Uçuş ve varış saatlerinin uyuşması kontrolü")
    public void compareFlyHours() {
        Assert.assertEquals("Uçuş saatlerinin önceki ve sonraki halleri eşit değil!",beforeDepartureTime,afterDepartureTime);
        Assert.assertEquals("Varış saatlerinin önceki ve sonraki halleri eşit değil!",beforeArrivalTime,afterArrivalTime);
    }

}
