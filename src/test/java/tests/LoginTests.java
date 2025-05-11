package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess1() {
        User user = new User().setEmail("margo@gmail.com").setPassword("Mmar123456$");

//        user.setEmail("margo@gmail.com");
//        user.setPassword("Mmar123456$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }



    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margo@gmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margogmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

//    @Test
//    public void loginEmptyEmail() {
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("", "Mmar123456$");
//        app.getHelperUser().submitLogin();
//        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
//        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
//    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("margo@gmail.com", "Mmar123");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }

//    @Test
//    public void loginEmptyPassword() {
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("margo@gmail.com", "");
//        app.getHelperUser().submitLogin();
//        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
//        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
//    }

    @Test
    public void loginUnregistered() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dava@gmail.com", "Ddav123456$");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
