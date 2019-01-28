package lesson14;

public enum ActionType {
    ADD, DELETE, UPDATE;

    public static ActionType recognize(String actionString) {
        for (ActionType action : values()) {
            if (action.name().equals(actionString)) {
                return action;
            }
        }

        throw new IllegalArgumentException("Action not recognized: " + actionString);
    }
}