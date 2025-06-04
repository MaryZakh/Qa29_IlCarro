package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Rehovot", "6/10/2025","6/27/2025");
        app.getHelperCar().getScreen("src/test/screenshots/currentmonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Rehovot", "7/3/2025","11/15/2025");
        app.getHelperCar().getScreen("src/test/screenshots/currentyear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Rehovot", "10/9/2025","3/8/2026");
        app.getHelperCar().getScreen("src/test/screenshots/any.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("Rehovot", "3/9/2025","7/18/2026");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("You can't pick date before today"));

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperCar().navigateByLogo();
    }
}
