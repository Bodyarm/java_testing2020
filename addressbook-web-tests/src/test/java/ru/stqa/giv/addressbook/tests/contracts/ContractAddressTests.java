package ru.stqa.giv.addressbook.tests.contracts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractAddressTests extends TestBase {

    @BeforeMethod
    public void prepare(){
        app.goTo().homePage();
         Contracts before = app.contract().all();

        int amount = (int) before
                .stream()
                .filter((s) -> !s.getPostAddress().equals(""))
                .count();

        System.out.println("Count"+ amount);

        if (amount ==0 ){
            app.contract().create(new ContractData()
                    .withFirstname("Check1")
                    .withLastName("Dog lover")
                    .withPostAddress("Улица крутых чуваков 47, 54")
            );
        };
        app.goTo().homePage();
    }



    @Test
    public void addressTest1(){
        Contracts after = app.contract().all();
        ContractData contractWithAddress = after.stream().filter(s->!s.getPostAddress().equals("")).iterator().next();
        ContractData contractWithAddressFromDetails = app.contract().getContractFullDataByID(contractWithAddress.getId());

        assertThat(contractWithAddress.getPostAddress(), equalTo(contractWithAddressFromDetails.getPostAddress()));

    }



}
