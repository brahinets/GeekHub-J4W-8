package org.geekhub.lesson19.license;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;

    LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public List<License> findAll() {
        return licenseRepository.findAll();
    }

    @Override
    public List<License> findAllByUserId(int userId) {
        return licenseRepository.findAllByUserId(userId);
    }

    @Override
    public License save(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public void deleteAll(List<License> licenses) {
        licenseRepository.deleteAll(licenses);
    }
}
