package org.geekhub.lesson19.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

public class UserListener {
    private static final Logger LOG = LoggerFactory.getLogger(UserListener.class);

    @PrePersist
    public void prePersist(User user) {
        LOG.info("username: {}, id: {}", user.getUsername(), user.getId());
    }

    @PostPersist
    public void postPersist(User user) {
        LOG.info("username: {}, id: {}", user.getUsername(), user.getId());
    }
}
