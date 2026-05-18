package ru.stqa.giv.addressbook.tests;
import org.openqa.selenium.remote.Browser;
import org.slf4j.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.giv.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static final ApplicationManager app;
    protected Logger logger = LoggerFactory.getLogger(TestBase.class);

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser",Browser.CHROME.browserName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] parms){

        logger.info("Start Test " + method.getName() +" with parms " + Arrays.asList(parms));
    }

    @AfterMethod(alwaysRun = true)
    public void LogTestEnd(Method method){
        logger.info("End test "+ method.getName());
    }

}
