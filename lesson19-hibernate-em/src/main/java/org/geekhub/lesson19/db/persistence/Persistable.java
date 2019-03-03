package org.geekhub.lesson19.db.persistence;

import java.io.Serializable;

public interface Persistable<PK extends Serializable> {
    PK getId();
}
