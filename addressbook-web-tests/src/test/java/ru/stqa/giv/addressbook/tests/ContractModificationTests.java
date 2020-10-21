package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

public class ContractModificationTests extends TestBase{

    @Test
    public void testContractModification() throws Exception {

        app.getNavigationHelper().returnToHomePage();
        app.getContractHelper().selectContract();
        app.getContractHelper().initContractModification();
        app.getContractHelper().fillContractForm(new ContractData("mod2", "mod3", "m0d14", "Vasiliii", "Mr", "OOO Piff Paff",  null),false);
        app.getContractHelper().submitContractModification();
        app.getNavigationHelper().returnToHomePage();


    }
}
