package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;

import java.util.Comparator;
import java.util.List;

public class ContractCreationTests extends TestBase {

    @Test
    public void testContractCreation()  {

        app.goTo().homePage();

        List<ContractData> before = app.contract().list();

        app.contract().initContractCreation();

        app.contract().create(new ContractData().withFirstname("check").withMiddleName("test2").withLastName("test3").withNickName("test4").withTitle("Good title").withCompany("Dreamworks").withGroup("test1"));
        app.goTo().homePage();

        List<ContractData> after = app.contract().list();

        ContractData newContract = new ContractData().withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId()).withFirstname("check").withLastName("test3");
        before.add(newContract);

        Comparator<? super ContractData> byID= (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        System.out.println(before.toString());
        System.out.println("----------------------");
        System.out.println(after.toString());

        Assert.assertEquals(before, after);



        // Отсортировать before и after
        // Сравнить их Assert'ом


    }

}
