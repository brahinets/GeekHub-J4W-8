package org.geekhub.lesson14;

import org.geekhub.lesson14.action.Action;
import org.geekhub.lesson14.source.SessionSource;
import org.geekhub.lesson14.source.Source;

import javax.servlet.http.HttpSession;

import static org.geekhub.lesson14.ActionFactory.createAction;

public class Main {
    public static void main(String[] args) {
        final HttpSession httpSession = null; // req.getSession(true)
        final Source source = new SessionSource(httpSession);

        String actionFromClient = "ADD"; // req.getParameter("action")
        ActionType actionType = ActionType.recognize(actionFromClient);
        Action action = createAction(actionType);
        action.apply(source, "a", "b");
    }
}