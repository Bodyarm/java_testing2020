package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContractCreationTests extends TestBase {

    @Test
    public void testContractCreation() throws Exception {


        List<ContractData> before = app.getContractHelper().getContractList();

        app.getContractHelper().initContractCreation();

        app.getContractHelper().createContract(new ContractData("check4", "test2", "test3", "test4", "test5", "test6","test1"));
        app.getNavigationHelper().returnToHomePage();

        List<ContractData> after = app.getContractHelper().getContractList();

        ContractData newContract = new ContractData(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId(),"check4","test3");
        before.add(newContract);

        Comparator<? super ContractData> byID= (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);

        // Отсортировать before и after
        // Сравнить их Assert'ом


    }

}
