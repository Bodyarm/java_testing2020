package ru.stqa.giv.addressbook.appmanager;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;

import java.util.*;

public class ContractHelper  extends HelperBase{

    public ContractHelper(WebDriver wd) {
        super(wd);
    }

    public void initContractCreation() {
        click(By.linkText("add new"));
    }

    public void initContractModificationById(int id) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", id)));
    }

    public void fillContractForm(ContractData contractData, boolean creation) {
        type(By.name("firstname"),contractData.getFirstname());
        type(By.name("middlename"),contractData.getMiddlename());
        type(By.name("lastname"),contractData.getLastname());
        type(By.name("nickname"),contractData.getNickname());
        type(By.name("title"),contractData.getTitle());
        type(By.name("company"),contractData.getCompany());
        if (creation) {
            if ( contractData.getGroup() !=null) {
                    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contractData.getGroup());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContractCreation() {
        click(By.name("submit"));
    }


    public void selectContract() {
        click(By.name("selected[]"));

    }

    public void selectContractById(int id) {
        click(By.cssSelector(String.format("input[value='%s']", id)));
    }

    public void deleteSelectedContracts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContractDeletion() {
        wd.switchTo().alert().accept();
    }

    public void submitContractModification() {
        click(By.name("update"));
    }

    public boolean isContractThere() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContractData contractData) {
        initContractCreation();
        fillContractForm(contractData,true);
        submitContractCreation();
    }

    public List<ContractData> list() {
        List<ContractData> groups = new ArrayList<ContractData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr[name='entry']"));

        for (WebElement element : elements){

            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();
            ContractData contract = new ContractData().withId(id).withFirstname(firstName).withLastName(lastName);
            groups.add(contract);
        }
        return groups;
    }

    public Contracts all() {
        Contracts contracts = new Contracts();
        List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr[name='entry']"));

        for (WebElement element : elements){

            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();
            ContractData contract = new ContractData().withId(id).withFirstname(firstName).withLastName(lastName);
            contracts.add(contract);
        }
        return contracts;
    }



}
