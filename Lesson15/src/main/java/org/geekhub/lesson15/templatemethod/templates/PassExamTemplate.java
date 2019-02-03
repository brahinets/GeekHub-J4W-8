package org.geekhub.lesson15.templatemethod.templates;

import org.geekhub.lesson15.Exam;

public abstract class PassExamTemplate {
    protected abstract void prepare(Exam exam);

    protected abstract void wakeup(Exam exam);

    protected abstract void pass(Exam exam);

    protected abstract void getMark(Exam exam);

    public final void passExam(Exam exam) {
        prepare(exam);
        wakeup(exam);
        pass(exam);
        getMark(exam);
    }
}
