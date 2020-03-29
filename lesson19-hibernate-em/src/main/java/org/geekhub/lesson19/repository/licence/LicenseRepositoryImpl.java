package org.geekhub.lesson19.repository.licence;

import org.geekhub.lesson19.db.persistence.License;
import org.geekhub.lesson19.repository.GeneralRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
class LicenseRepositoryImpl extends GeneralRepositoryImpl<License, Integer> implements LicenseRepository {
    LicenseRepositoryImpl() {
        super(License.class);
    }
}
