package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContractCreationTests extends TestBase {

    @Test
    public void testContractCreation()  {

        app.goTo().homePage();
        Set<ContractData> before = app.contract().all();

        app.contract().initContractCreation();
        ContractData contractToCreate = new ContractData().withFirstname("check").withMiddleName("test2").withLastName("test3").withNickName("test4").withTitle("Good title").withCompany("Dreamworks").withGroup("test1");
        app.contract().create(contractToCreate);
        app.goTo().homePage();

        Set<ContractData> after = app.contract().all();

        //ContractData newContract = new ContractData().withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId()).withFirstname("check").withLastName("test3");
        ContractData newContract = new ContractData()
                .withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())
                .withFirstname(contractToCreate.getFirstname())
                .withLastName(contractToCreate.getLastname());
        before.add(newContract);

//        Comparator<? super ContractData> byID= (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
//        before.sort(byID);
//        after.sort(byID);

        Assert.assertEquals(before, after);

    }

}
