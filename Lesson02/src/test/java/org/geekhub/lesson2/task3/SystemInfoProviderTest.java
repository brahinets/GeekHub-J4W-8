package org.geekhub.lesson2.task3;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class SystemInfoProviderTest {

    private SystemInfoProvider systemInfoProvider;

    @BeforeMethod
    public void setUp() {
        systemInfoProvider = new SystemInfoProvider();
    }

    @Test
    public void shouldReturnPositiveCountOfProcessors() {
        int actualProcessorsCount = systemInfoProvider.getProcessorsCount();

        assertPositive(actualProcessorsCount, "Processor does not have cores");
    }

    @Test
    public void shouldReturnFreeRAM() {
        long freeRam = systemInfoProvider.getFreeRam();
        long freeRamForJvm = Runtime.getRuntime().freeMemory();

        Assert.assertTrue(freeRam > freeRamForJvm, "JVM Can not use all RAM");
    }

    @Test
    public void shouldReturnTotalRAM() {
        long totalRam = systemInfoProvider.getTotalRam();
        long ramUsedByJvm = Runtime.getRuntime().totalMemory();

        assertPositive(totalRam, "PC can not have 0 of total RAM");
        Assert.assertTrue(totalRam > ramUsedByJvm, "JVM Can not use all RAM");
    }

    @Test
    public void shouldReturnJava11() {
        int javaVersion = systemInfoProvider.getJavaVersion();

        Assert.assertTrue(javaVersion >= 11, "Java should be at least 11");
    }

    @Test
    public void shouldReturnNonEmptyUserName() {
        String actualUserName = systemInfoProvider.getUserName();

        assertNotEmpty(actualUserName, "User does not have name");
    }

    @Test
    public void shouldReturnNonEmptyUserHomeDirectoryPath() {
        String actualUserHomeDirectory = systemInfoProvider.getUserHomeDirectory();

        assertNotEmpty(actualUserHomeDirectory, "User does not have directory");
    }

    @Test
    public void shouldReturnNonEmptyOSName() {
        String actualOSName = systemInfoProvider.getOSName();

        assertNotEmpty(actualOSName, "Operating system does not have name");
    }

    private void assertPositive(long val, String message) {
        Assert.assertTrue(val > 0, message);
    }

    private void assertNotEmpty(String val, String message) {
        Assert.assertTrue(nonEmpty(val), message);
    }

    private boolean nonEmpty(String val) {
        return Objects.nonNull(val) && !val.matches("^[\\s]*$");
    }
}
