package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContractDeletionTests extends TestBase{

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if (app.contract().all().isEmpty()){
            app.contract().create(new ContractData().withFirstname("Check1").withMiddleName("Kus").withLastName("Dog lover").withNickName("LegNeck").withTitle("HOHOHO").withCompany("ClosedWay").withGroup("test1"));
        };
        app.goTo().homePage();

    }


    @Test
    public void testContractDeletion() throws Exception {

        Set<ContractData> before = app.contract().all();

        ContractData contractToDelete = before.iterator().next();

        app.contract().selectContractById(contractToDelete.getId());
        app.contract().deleteSelectedContracts();
        app.contract().confirmContractDeletion();

        app.goTo().homePage();

        before.remove(contractToDelete);
        Set<ContractData> after = app.contract().all();

        Assert.assertEquals(before, after);

    }
}
