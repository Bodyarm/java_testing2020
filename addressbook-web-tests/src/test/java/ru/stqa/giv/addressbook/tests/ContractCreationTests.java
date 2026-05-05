package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContractCreationTests extends TestBase {

    @Test
    public void testContractCreation()  {

        app.goTo().homePage();
        Contracts before = app.contract().all();

        app.contract().initContractCreation();
        ContractData contractToCreate = new ContractData().withFirstname("check").withMiddleName("test2").withLastName("test3").withNickName("test4").withTitle("Good title").withCompany("Dreamworks").withGroup("test1");
        app.contract().create(contractToCreate);
        app.goTo().homePage();

        Contracts after = app.contract().all();

        ContractData newContract = new ContractData()
                .withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())
                .withFirstname(contractToCreate.getFirstname())
                .withLastName(contractToCreate.getLastname());

        assertThat(after, equalTo(before.withAdded(newContract)));
    }

}
