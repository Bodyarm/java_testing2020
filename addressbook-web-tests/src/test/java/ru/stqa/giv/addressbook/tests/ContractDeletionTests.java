package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;

public class ContractDeletionTests extends TestBase{

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        if (app.contract().list().isEmpty()){
            app.contract().create(new ContractData("Check1", "Kus", "DOG_KILLER", "Tiiitle","DDDD", "OOO Lopata", "test1"));
        };
        app.goTo().homePage();

    }


    @Test
    public void testContractDeletion() throws Exception {

        List<ContractData> before = app.contract().list();
        System.out.println("Amount of contracts before deletion: "+ before.size());

        app.contract().selectContract();
        app.contract().deleteSelectedContracts();
        app.contract().confirmContractDeletion();
        app.goTo().homePage();

        before.remove(0);
        List<ContractData> after = app.contract().list();
        System.out.println("Amount of contract after deletion: "+after.size());

        Comparator<? super ContractData> byID= (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);

    }
}
