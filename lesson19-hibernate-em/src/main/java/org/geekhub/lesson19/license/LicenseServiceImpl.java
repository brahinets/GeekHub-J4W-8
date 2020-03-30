package org.geekhub.lesson19.license;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
    public License save(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public void delete(License license) {
        licenseRepository.delete(license);
    }
}
