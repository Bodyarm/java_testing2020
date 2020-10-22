package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

public class ContractCreationTests extends TestBase {

    @Test
    public void testContractCreation() throws Exception {


        app.getContractHelper().initContractCreation();
        app.getContractHelper().createContract(new ContractData("check4", "test2", "test3", "test4", "test5", "test6","test1"));
        app.getNavigationHelper().returnToHomePage();

    }

}
