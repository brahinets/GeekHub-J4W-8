package org.geekhub.lesson19.license;

import org.geekhub.lesson19.config.GeneralRepository;
import org.geekhub.lesson19.config.GeneralRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
class LicenseRepository extends GeneralRepositoryImpl<License, Integer> implements GeneralRepository<License, Integer> {
    LicenseRepository() {
        super(License.class);
    }
}
