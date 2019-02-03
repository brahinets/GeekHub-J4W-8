package org.geekhub.lesson15.strategy;

import org.geekhub.lesson15.strategy.strategies.PassExamStrategy;

public class Student {
    private PassExamStrategy strategy;

    public Student(PassExamStrategy strategy) {
        this.strategy = strategy;
    }

    public PassExamStrategy getStrategy() {
        return strategy;
    }
}
