package org.geekhub.lesson19.userlicense;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.license.LicenseService;
import org.geekhub.lesson19.user.User;
import org.geekhub.lesson19.user.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class UserLicenseServiceImpl implements UserLicenseService {
    private final UserService userService;
    private final LicenseService licenseService;

    UserLicenseServiceImpl(UserService userService, LicenseService licenseService) {
        this.userService = userService;
        this.licenseService = licenseService;
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @Override
    public void save(User user, License license) {
        final User savedUser = userService.save(user);
        license.setUser(savedUser);
        licenseService.save(license);
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user = userService.findBy(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id=" + userId + " does not exist."));
        final List<License> licenses = user.getLicenses();
        licenses.forEach(licenseService::delete);
        if (true) {
            throw new RuntimeException("Test exception");
        }
        userService.deleteById(userId);
    }
}
