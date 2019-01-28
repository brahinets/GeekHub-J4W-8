package lesson14;

import org.geekhub.lesson14.action.Action;
import org.geekhub.lesson14.action.AddAction;
import org.geekhub.lesson14.action.DeleteAction;
import org.geekhub.lesson14.action.UpdateAction;

import java.util.Map;

import static java.util.Objects.nonNull;

public class ActionFactory {
    private static final Map<ActionType, Action> actions = Map.of(
            ActionType.ADD, new AddAction(),
            ActionType.DELETE, new DeleteAction(),
            ActionType.UPDATE, new UpdateAction()
    );

    public static Action createAction(ActionType actionType) {
        final Action action = actions.get(actionType);

        if (nonNull(action)) {
            return action;
        }

        throw new IllegalArgumentException("Action nt supported: " + actionType);
    }
}
