package org.geekhub.lesson4.task2.user;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class InMemoryUserSourceTest {

    @Test
    public void shouldReturnEmpty_whenUserNotFoundByUsername() {
        UserSource emptyUserSource = new InMemoryUserSource();

        Optional<User> user = emptyUserSource.find("Username");

        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void shouldReturnUser_whenUserFoundByUsername() {
        UserSource userSource = new InMemoryUserSource(
            new User(1, "Username", "Password")
        );

        Optional<User> user = userSource.find("Username");

        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(user.get().getId(), 1);
    }
}