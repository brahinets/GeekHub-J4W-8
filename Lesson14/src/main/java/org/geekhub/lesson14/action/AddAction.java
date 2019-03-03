package org.geekhub.lesson14.action;

import org.geekhub.lesson14.source.Source;

public class AddAction implements Action {
    @Override
    public void apply(Source source, String name, String value) {
        source.put(name, value);
    }
}