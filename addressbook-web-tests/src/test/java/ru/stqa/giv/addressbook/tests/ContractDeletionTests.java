package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

public class ContractDeletionTests extends TestBase{



    @Test
    public void testContractDeletion() throws Exception {

        app.getNavigationHelper().returnToHomePage();
        if (!app.getContractHelper().isContractThere() ){
            app.getContractHelper().createContract(new ContractData("Check1", "Kus", "DOG_KILLER", "Tiiitle","DDDD", "OOO Lopata", "test1"));
        };
        app.getNavigationHelper().returnToHomePage();
        app.getContractHelper().selectContract();
        app.getContractHelper().deleteSelectedContracts();
        app.getContractHelper().confirmContractDeletion();
        app.getNavigationHelper().returnToHomePage();


    }
}
