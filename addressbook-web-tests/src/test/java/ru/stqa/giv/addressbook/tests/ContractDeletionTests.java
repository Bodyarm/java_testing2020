package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;

public class ContractDeletionTests extends TestBase{



    @Test
    public void testContractDeletion() throws Exception {

        app.getNavigationHelper().returnToHomePage();
        if (!app.getContractHelper().isContractThere() ){
            app.getContractHelper().createContract(new ContractData("Check1", "Kus", "DOG_KILLER", "Tiiitle","DDDD", "OOO Lopata", "test1"));
        };
        app.getNavigationHelper().returnToHomePage();

        List<ContractData> before = app.getContractHelper().getContractList();
        System.out.println("Amount of contracts before deletion: "+ before.size());

        app.getContractHelper().selectContract();
        app.getContractHelper().deleteSelectedContracts();
        app.getContractHelper().confirmContractDeletion();
        app.getNavigationHelper().returnToHomePage();

        before.remove(0);
        List<ContractData> after = app.getContractHelper().getContractList();
        System.out.println("Amount of contract after deletion: "+after.size());

        Comparator<? super ContractData> byID= (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);

    }
}
