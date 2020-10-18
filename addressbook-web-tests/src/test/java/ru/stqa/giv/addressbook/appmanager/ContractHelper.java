package ru.stqa.giv.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.giv.addressbook.model.ContractData;

public class ContractHelper  extends HelperBase{

    public ContractHelper(WebDriver wd) {
        super(wd);
    }

    public void initContractCreation() {
        click(By.linkText("add new"));
    }

    public void fillContractForm(ContractData contractData) {
        type(By.name("firstname"),contractData.getFirstname());
        type(By.name("middlename"),contractData.getMiddlename());
        type(By.name("lastname"),contractData.getLastname());
        type(By.name("nickname"),contractData.getNickname());
        type(By.name("title"),contractData.getTitle());
        type(By.name("company"),contractData.getCompany());
    }

    public void submitContractCreation() {
        click(By.name("submit"));
    }



}