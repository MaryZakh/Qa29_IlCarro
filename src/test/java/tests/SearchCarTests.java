package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Rehovot", "5/28/2025","5/30/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Rehovot", "7/3/2025","11/15/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

//    @Test
//    public void searchAnyPeriodSuccess(){
//        app.getHelperCar().searchAnyPeriod("Rehovot", "10/9/2025","3/8/2026");
//        app.getHelperCar().submit();
//        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
//    }
}
