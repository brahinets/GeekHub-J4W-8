package org.geekhub.lesson19.license;

import java.util.List;

public interface LicenseService {
    List<License> findAll();

    License save(License license);

    void delete(License license);
}
