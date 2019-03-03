package org.geekhub.lesson19.listener;

import org.geekhub.lesson19.db.persistence.User;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

public class UserListener {
    @PrePersist
    public void prePersist(User user) {
        System.out.println("username: " + user.getUsername() + ", id: " + user.getId());
    }

    @PostPersist
    public void postPersist(User user) {
        System.out.println("username: " + user.getUsername() + ", id: " + user.getId());
    }
}
