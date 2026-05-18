package ru.stqa.giv.addressbook.tests.db.contracts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContractModificationTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if (app.db().contracts().isEmpty()){
            app.contract().create(new ContractData()
                    .withFirstname("Check1")
                    .withMiddleName("Kus")
                    .withLastName("Dog lover")
                    .withNickName("LegNeck")
                    .withTitle("HOHOHO")
                    .withCompany("ClosedWay")
                    .withGroup("test1")
            );
        };
        app.goTo().homePage();

    }

    @Test
    public void testContractModification() throws Exception {

        Contracts before = app.db().contracts();
        ContractData contractToModify = before.iterator().next();
        ContractData contractChanged = contractToModify.withFirstname("mod2").withLastName("mod3");

        app.contract().initContractModificationById(contractChanged.getId());
        app.contract().fillContractForm(contractChanged,false);
        app.contract().submitContractModification();
        app.goTo().homePage();

        assertThat(app.contract().count(),equalTo(before.size()));
        Contracts after = app.db().contracts();

        assertThat(after, equalTo(before.withOut(contractToModify).withAdded(contractChanged)));


    }
}
