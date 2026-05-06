package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

        Contracts before = app.contract().all();

        ContractData contractToDelete = before.iterator().next();

        app.contract().selectContractById(contractToDelete.getId());
        app.contract().deleteSelectedContracts();
        app.contract().confirmContractDeletion();
        app.goTo().homePage();

        assertThat(app.contract().count(),equalTo(before.size()-1));
        Contracts after = app.contract().all();

        assertThat(after, equalTo(before.withOut(contractToDelete)));

    }
}
