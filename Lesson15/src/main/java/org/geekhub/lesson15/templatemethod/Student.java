package org.geekhub.lesson15.templatemethod;

import org.geekhub.lesson15.templatemethod.templates.PassExamTemplate;

public class Student {
    private PassExamTemplate template;

    public Student(PassExamTemplate template) {
        this.template = template;
    }

    public PassExamTemplate getTemplate() {
        return template;
    }
}
