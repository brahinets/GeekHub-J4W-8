package org.geekhub.lesson4.task2.authentication;

import org.geekhub.lesson4.task2.authentication.exception.UserNotFoundException;
import org.geekhub.lesson4.task2.authentication.exception.WrongCredentialsException;
import org.geekhub.lesson4.task2.authentication.exception.WrongPasswordException;
import org.geekhub.lesson4.task2.user.InMemoryUserSource;
import org.geekhub.lesson4.task2.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @BeforeMethod
    public void setUp() {
        authenticationService = new AuthenticationService(
            new InMemoryUserSource(
                new User(1, "Username1", "qwerty"),
                new User(2, "Username2", "123456")
            ));
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void shouldThrowUserNotFoundException_whenUserWithSuchUsernameDoesNotExist() {
        authenticationService.auth("Username0", "zzzzzz");
    }

    @Test(expectedExceptions = WrongCredentialsException.class, dataProvider = "emptyString")
    public void shouldThrowWrongCredentialsException_whenUsernameIsEmpty(String emptyString) {
        authenticationService.auth(emptyString, "qwerty");
    }

    @Test(expectedExceptions = WrongCredentialsException.class, dataProvider = "emptyString")
    public void shouldThrowWrongCredentialsException_whenPasswordIsEmpty(String emptyString) {
        authenticationService.auth("Username1", emptyString);
    }

    @Test(expectedExceptions = WrongPasswordException.class)
    public void shouldThrowWrongPasswordException_whenPasswordIsIncorrect() {
        authenticationService.auth("Username1", "wrong password");
    }

    @Test
    public void shouldReturnUser_whenUsernameAndPasswordAreCorrect() {
        User user = authenticationService.auth("Username2", "123456");
        Assert.assertEquals(user.getId(), 2, "Wrong user is authenticated");
    }

    @DataProvider
    public static Object[][] emptyString() {
        return new Object[][]{
            {null},
            {""},
            {"     "},
            {"\n\n"},
            {"\n\t\r"},
            {"\t\r"},
        };
    }
}
