package org.geekhub.lesson19.service;

import org.geekhub.lesson19.db.persistence.License;
import org.geekhub.lesson19.db.persistence.User;
import org.geekhub.lesson19.repository.licence.LicenseRepository;
import org.geekhub.lesson19.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserLicenseServiceImpl implements UserLicenseService {
    private final UserRepository userRepository;
    private final LicenseRepository licenseRepository;

    @Autowired
    public UserLicenseServiceImpl(UserRepository userRepository, LicenseRepository licenseRepository) {
        this.userRepository = userRepository;
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void save(User user, License license) {
        final User savedUser = userRepository.save(user);
        license.setUser(savedUser);
        licenseRepository.save(license);
    }

    @Override
    public void delete(User user) {
        final List<License> licenses = user.getLicenses();
        userRepository.delete(user);
        if (true) {
            throw new RuntimeException();
        }
        licenses.forEach(licenseRepository::delete);
    }
}
