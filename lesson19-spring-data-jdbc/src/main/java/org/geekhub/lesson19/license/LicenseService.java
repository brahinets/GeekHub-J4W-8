package org.geekhub.lesson19.license;

import java.util.List;

public interface LicenseService {
    List<License> findAll();

    List<License> findAllByUserId(int userId);

    License save(License license);

    void deleteAll(List<License> licenses);
}
