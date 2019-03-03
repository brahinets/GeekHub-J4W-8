package org.geekhub.lesson14.action;

import org.geekhub.lesson14.source.Source;

public interface Action {
    void apply(Source source, String name, String value);
}