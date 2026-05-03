package ru.stqa.giv.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;


public class ApplicationManager {

    WebDriver wd;
    private GroupHelper groupHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContractHelper contracthelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {
             if (browser.equals(Browser.FIREFOX.browserName())) wd = new FirefoxDriver();
        else if (browser.equals(Browser.CHROME.browserName()))  wd = new ChromeDriver();
        else if (browser.equals(Browser.IE.browserName()))      wd = new InternetExplorerDriver();

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wd.get("http://localhost/addressbook/");
        groupHelper      = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper    = new SessionHelper(wd);
        contracthelper   = new ContractHelper(wd);
        sessionHelper.login("admin", "secret");
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
