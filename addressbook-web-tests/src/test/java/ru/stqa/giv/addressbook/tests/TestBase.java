package ru.stqa.giv.addressbook.tests;
import org.openqa.selenium.remote.Browser;
import org.slf4j.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.giv.addressbook.appmanager.ApplicationManager;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    protected void validataGroupsUI(){

        if(Boolean.getBoolean("checks.ui.groups")
                ||app.properties.getProperty("checks.ui.groups","false").equals("true")
        ) {
            logger.info("checks.ui.groups=true");
            logger.info("Assert groups db vs UI");
            app.goTo().groupPage();
            Groups groupsDB = app.db().groups();
            Groups groupsUI = app.group().all();

            assertThat(groupsUI, equalTo(groupsDB.stream().map(g -> new GroupData()
                    .withId(g.getId())
                    .withName(g.getName()))
                    .collect(Collectors.toSet())
                     ));
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
