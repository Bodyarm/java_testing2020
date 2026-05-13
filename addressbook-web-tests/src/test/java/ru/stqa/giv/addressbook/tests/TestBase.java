package ru.stqa.giv.addressbook.tests;
import org.openqa.selenium.remote.Browser;
import org.slf4j.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.giv.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser",Browser.CHROME.browserName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        logger.info("Start Test " + logger.getClass());
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
        logger.info("End test "+ logger.getClass());

    }

}
