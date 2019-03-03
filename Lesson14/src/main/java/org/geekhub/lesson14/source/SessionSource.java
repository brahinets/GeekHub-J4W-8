package org.geekhub.lesson14.source;

import javax.servlet.http.HttpSession;

public class SessionSource implements Source {
    private final HttpSession session;

    public SessionSource(HttpSession session) {
        this.session = session;
    }

    public void put(String name, String value) {
        session.setAttribute(name, value);
    }

    @Override
    public void delete(String name) {
        session.removeAttribute(name);
    }
}