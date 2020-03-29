package org.geekhub.lesson19.userlicense;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.license.LicenseService;
import org.geekhub.lesson19.user.User;
import org.geekhub.lesson19.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<Integer, List<License>> findAllLicensesForUsers() {
        return licenseService.findAll().stream().collect(Collectors.groupingBy(License::getUserId));
    }

    @Override
    public void createUserWithLicense(String username, String firstName, String lastName) {
        final User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        final User savedUser = userService.save(user);

        final License license = new License();
        license.setName("Licence " + LocalTime.now());
        license.setUserId(savedUser.getId());
        licenseService.save(license);
    }

    @Override
    public void deleteByUserId(int userId) {
        final List<License> licenses = licenseService.findAllByUserId(userId);
        licenseService.deleteAll(licenses);
        userService.deleteById(userId);
    }
}
