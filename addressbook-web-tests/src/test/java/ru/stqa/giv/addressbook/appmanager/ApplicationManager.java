package ru.stqa.giv.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class ApplicationManager {

    private Properties properties;
    WebDriver wd;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContractHelper contracthelper;
    private String browser;

    public ApplicationManager(String browser) throws IOException {
        this.browser = browser;

    }


    public void init() throws IOException {

        String target =System.getProperty("target", "local");
        File checkpath = new File(".");
        System.out.println(checkpath.getAbsolutePath());
        System.out.println(String.format("src/test/resources/%s.properties",target));
        properties = new Properties();
        properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));


             if (browser.equals(Browser.FIREFOX.browserName())) wd = new FirefoxDriver();
        else if (browser.equals(Browser.CHROME.browserName()))  wd = new ChromeDriver();
        else if (browser.equals(Browser.IE.browserName()))      wd = new InternetExplorerDriver();

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper      = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper    = new SessionHelper(wd);
        contracthelper   = new ContractHelper(wd);
        sessionHelper.login(properties.getProperty("web.login"), properties.getProperty("web.password"));
    }



    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContractHelper contract() {
        return contracthelper;
    }


}
