package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContractModificationTests extends TestBase{

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if (app.contract().list().isEmpty()){
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

        Set<ContractData> before = app.contract().all();
        ContractData contractToModify = before.iterator().next();


        before.remove(contractToModify);
        System.out.println("id to modify = "+ contractToModify.getId());
        System.out.println(before.size());


        app.contract().selectContractById(contractToModify.getId());
        app.contract().initContractModification();
        ContractData contractChanged = contractToModify.withFirstname("mod2").withLastName("mod3");

        app.contract().fillContractForm(contractChanged,false);
        app.contract().submitContractModification();
        app.goTo().homePage();

        before.add(contractChanged);
        Set<ContractData> after = app.contract().all();
        Assert.assertEquals(before, after);


    }
}
