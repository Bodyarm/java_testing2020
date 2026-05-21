package ru.stqa.giv.addressbook.tests.db.contracts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractDeletionTests extends TestBase {
    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if (app.db().contracts().isEmpty()){
            app.contract().create(new ContractData().withFirstname("Check1")
                    .withMiddleName("Kus")
                    .withLastName("Dog lover")
                    .withNickName("LegNeck")
                    .withTitle("HOHOHO")
                    .withCompany("ClosedWay"));
//                    .withGroup("test1"))

        };
        app.goTo().homePage();

    }


    @Test
    public void testContractDeletion() throws Exception {

        Contracts before = app.db().contracts();
        ContractData contractToDelete = before.iterator().next();

        app.contract().selectContractById(contractToDelete.getId());
        app.contract().deleteSelectedContracts();
        app.contract().confirmContractDeletion();
        app.goTo().homePage();

        assertThat(app.contract().count(),equalTo(before.size()-1));
        Contracts after = app.db().contracts();

        assertThat(after, equalTo(before.withOut(contractToDelete)));

    }
}
