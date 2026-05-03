package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;

public class ContractModificationTests extends TestBase{

    @Test
    public void testContractModification() throws Exception {


        //Зачитываем список до модификации
        //После модификации у первого в списке меняем fisrtname, lastname
        //Зачитываем обновлённый список
        //Добавляем компоратор
        //Сравниваем



        app.getNavigationHelper().returnToHomePage();
        if (!app.getContractHelper().isContractThere() ){
            app.getContractHelper().createContract(new ContractData("Check1", "Kus", "DOG_KILLER", "Tiiitle","DDDD", "OOO Lopata", "test1"));
        };

        app.getNavigationHelper().returnToHomePage();
        List<ContractData> before = app.getContractHelper().getContractList();
        System.out.println("Amount of contracts before modification: "+ before.size());

        app.getContractHelper().selectContract();
        app.getContractHelper().initContractModification();
        app.getContractHelper().fillContractForm(new ContractData("mod2", "mod3", "m0d14", "Vasiliii", "Mr", "OOO Piff Paff",  null),false);
        app.getContractHelper().submitContractModification();
        app.getNavigationHelper().returnToHomePage();

        before.get(0).setFirstname("mod2");
        before.get(0).setLastname("m0d14");

        List<ContractData> after = app.getContractHelper().getContractList();
        System.out.println("Amount of contracts after modification: "+ after.size());

        Comparator<? super ContractData> byID= (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before, after);


    }
}
