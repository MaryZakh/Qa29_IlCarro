package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+1000;
        System.out.println(i);

        //=======================
        int z = (int) ((System.currentTimeMillis()/1000)%3600);

        System.out.println(System.currentTimeMillis());
        System.out.println(z);

        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow"+ z + "@gmail.com")
                .setPassword("Ssnow12345$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");

    }

    @Test
    public void registrationEmptyName(){
        int z = (int) ((System.currentTimeMillis()/1000)%3600);
        User user = new User()
                .setFirstName("")
                .setLastName("Snow")
                .setEmail("snowsnow"+z+"@gmail.com")
                .setPassword("Ssnow12345$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationEmptyLastName(){
        int z = (int) ((System.currentTimeMillis()/1000)%3600);
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("")
                .setEmail("snowsnow"+z+"@gmail.com")
                .setPassword("Ssnow12345$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationEmptyEmail(){
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("")
                .setPassword("Ssnow12345$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationEmptyPassword(){
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snowgmail.com")
                .setPassword("Ssnow12345$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test(enabled = false)
    public void registrationWrongPassword(){
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow@gmail.com")
                .setPassword("Ssnow123");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationExistsUser(){
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("margo@gmail.com")
                .setPassword("Mmar123456$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"User already exists\"");
    }

    @Test
    public void registrationWithoutCheckbox() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("opel@gmail.com")
                .setPassword("Osnow123456$");
        logger.info("Test start with test data --->" + user.toString());

        app.getHelperUser().openRegistrationFom();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
