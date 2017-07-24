package Web.Webclient.Methods;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by tober on 31.05.17.
 * Calendar date selector
 */
public class SelectDateFromCalendar {

    private int year;
    private int month;
    private int day;

    public void setDate(int year, int month, int day, WebElement calendar) throws InterruptedException {
        this.year = year;
        this.month = month;
        this.day = day;

        $(calendar).hover().click();

        while (true) {
            wait(1);
            try {
                $(daysW).shouldBe(Condition.visible);
                if (!$(daysW).isDisplayed()) {
                    System.out.println("days is displayed");
                    $(By.xpath(daysS + "[.='" + day + "']")).click();
                    break;
                }
                if (!$(months).isDisplayed()) {
                    System.out.println("months is displayed");
                    $(By.xpath(months + "[" + month + "]")).click();
                    break;
                }
                if ($(years).isDisplayed()) {
                    System.out.println("years is displayed");
                    $(By.xpath(".//span[.='" + year + "'][contains(@class,'year')]")).hover().click();
                    break;
                }
            } catch (Throwable throwable) {
                $(yearsBackButton).click();
            }
        }

        $(By.xpath(months + "[" + this.month + "]")).click();
        $(By.xpath(daysS + "[.='" + this.day + "']")).click();


//        $(days).shouldBe(Condition.visible).click();
    }

    public void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    //======================================================================================================================
    private static final WebElement yearsBackButton = $(By.xpath(".//div[@class='datetimepicker-years']//i[@class='icon-arrow-left']"));
    private static final WebElement years = $(By.xpath("//div[@class='datetimepicker-years']"));
    private static final String months = "(.//div[@class='datetimepicker-months']//tbody//td/span)";
    private static final String daysW = "(.//div[@class='datetimepicker-days']//td[@class='day'])[1]";
    private static final WebElement daysS = $(By.xpath("(.//div[@class='datetimepicker-days']//td[@class='day'])[1]"));

}
