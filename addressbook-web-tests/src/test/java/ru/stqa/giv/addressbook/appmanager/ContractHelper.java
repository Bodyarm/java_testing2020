package ru.stqa.giv.addressbook.appmanager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.*;

public class ContractHelper  extends HelperBase{

    private Contracts contractsCache = null;

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
        type(By.name("home"),contractData.getPhoneHome());
        type(By.name("mobile"),contractData.getPhoneMobile());
        type(By.name("work"),contractData.getPhoneWork());
        type(By.name("email"),contractData.getEmail1());
        type(By.name("email2"),contractData.getEmail2());
        type(By.name("email3"),contractData.getEmail3());
        type(By.name("address"),contractData.getPostAddress());
        attachFile(By.name("photo"), contractData.getPhoto());

        if (creation) {
            if (!contractData.getGroups().isEmpty()) {
                    new Select(wd.findElement(By.name("new_group"))).selectByValue(new Integer(contractData.getGroups().iterator().next().getId()).toString());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContractCreation() {
        click(By.name("submit"));
        cleanCache();
    }


    public void selectContractById(int id) {
        try {click(By.cssSelector(String.format("input[value='%s']", id)));
        }
        catch (NoSuchElementException e ){
            logger.error("No contract with id {} founded", id);
        }
    }

    public void deleteSelectedContracts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContractDeletion() {
        wd.switchTo().alert().accept();
        cleanCache();
    }

    public void submitContractModification() {
        click(By.name("update"));
        cleanCache();
    }

    public boolean isContractThere() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContractData contract) {
        logger.info("Start creating contract:{}", contract.toString());
        initContractCreation();
        fillContractForm(contract,true);
        submitContractCreation();
        logger.info("Contract {} was created", contract.toString());
    }

    public void cleanCache(){
        contractsCache = null;
    }

    public Contracts all() {
        if (contractsCache !=null){
            return new Contracts(contractsCache);
        }
        contractsCache = new Contracts();
        List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr[name='entry']"));

        for (WebElement element : elements){

            List<WebElement> columns = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = columns.get(1).getText();
            String firstName = columns.get(2).getText();
            ContractData contract = new ContractData().withId(id).withFirstname(firstName).withLastName(lastName);
            String[] allPhones = columns.get(5).getText().split("\n");
            String allPhonesTogether = columns.get(5).getText();


            switch (allPhones.length){
                case 1: contract = contract.withPhoneHome(allPhones[0]);break;
                case 2: contract = contract.withPhoneHome(allPhones[0]).withPhoneMobile(allPhones[1]);break;
                case 3: contract = contract.withPhoneHome(allPhones[0]).withPhoneMobile(allPhones[1]).withPhoneWork(allPhones[2]);break;
            }
            contract = contract.withAllPhones(allPhonesTogether);

            String[] allEmails = columns.get(4).getText().split("\n");
            String allEmailsUnite = columns.get(4).getText();

            switch (allEmails.length){
                case 1: contract = contract.withEmail1(allEmails[0]); break;
                case 2: contract = contract.withEmail1(allEmails[0]).withEmail2(allEmails[1]); break;
                case 3: contract = contract.withEmail1(allEmails[0]).withEmail2(allEmails[1]).withEmail3(allEmails[2]); break;
            }
            contract = contract.withAllEmail(allEmailsUnite);


            String contractAddress = columns.get(3).getText();
            contract =contract.withPostAddress(contractAddress);

            contractsCache.add(contract);
        }
        return new Contracts(contractsCache);
    }


    public int count() {
        return wd.findElements(By.cssSelector("input[name='selected[]']")).size();
    }

    public ContractData getContractFullDataByID(int id) {
        initContractModificationById(id);
        return new ContractData()
                .withFirstname(wd.findElement(By.name("firstname")).getAttribute("value"))
                .withMiddleName(wd.findElement(By.name("middlename")).getAttribute("value"))
                .withLastName(wd.findElement(By.name("lastname")).getAttribute("value"))
                .withNickName(wd.findElement(By.name("nickname")).getAttribute("value"))
                .withTitle(wd.findElement(By.name("title")).getAttribute("value"))
                .withCompany(wd.findElement(By.name("company")).getAttribute("value"))
                .withPhoneHome(wd.findElement(By.name("home")).getAttribute("value"))
                .withPhoneMobile(wd.findElement(By.name("mobile")).getAttribute("value"))
                .withPhoneWork(wd.findElement(By.name("work")).getAttribute("value"))
                .withEmail1(wd.findElement(By.name("email")).getAttribute("value"))
                .withEmail2(wd.findElement(By.name("email2")).getAttribute("value"))
                .withEmail3(wd.findElement(By.name("email3")).getAttribute("value"))
                .withPostAddress(wd.findElement(By.name("address")).getText());
    }

    public void filterContractsByGroup(GroupData group) {
        try {new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(group.getId()));}
            catch (NoSuchElementException e){
                logger.error("No group with id {} founded in filter list", group.getId());
            }

    }

    public void removeContractFromGroup(ContractData contract, GroupData group) {
        filterContractsByGroup(group);
        selectContractById(contract.getId());
        click(By.name("remove"));
        logger.info("Contract {} was removed from group {}", contract.toString(), group.toString());
    }


    public void addContractToGroup(ContractData contract, GroupData group){
        selectContractById(contract.getId());
        selectGroupToAdd(group);
        click(By.name("add"));
        logger.info("Contract {} added to group {}", contract.getId(), group.toString());
    }

    private void selectGroupToAdd(GroupData group) {
        try {new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));}
        catch (NoSuchElementException e){
            logger.error("No group with id {} founded in add list", group.getId());
        }
    }


}
