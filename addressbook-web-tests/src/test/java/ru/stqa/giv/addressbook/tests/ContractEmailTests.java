package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContractEmailTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();

        Contracts before = app.contract().all();

        int amount = (int) before
                .stream()
                .filter((s) -> !s.getAllEmails().equals(""))
                .count();

        if (amount ==0 ){
            app.contract().create(new ContractData()
                    .withFirstname("Check1")
                    .withMiddleName("Kus")
                    .withLastName("Dog lover")
                    .withNickName("LegNeck")
                    .withTitle("HOHOHO")
                    .withCompany("ClosedWay")
                    .withGroup("test1")
                    .withEmail1("pub@mail.ru")
                    .withEmail2("pub@rambler.ru")
                    .withEmail3("kusmadan@narod.org")

            );
        };
        app.goTo().homePage();

    }


    @Test
    public void emailTest1(){

        Contracts before = app.contract().all();
        ContractData contractRandom = before.stream().filter(s->!s.getAllEmails().equals("")).iterator().next();

        ContractData contractFromDetails = app.contract().getContractFullDataByID(contractRandom.getId());


        System.out.println(contractRandom.getAllEmails());
        System.out.println("-----------");
        System.out.println(contractFromDetails.getEmail1() + contractFromDetails.getEmail2() + contractFromDetails.getEmail3());
        assertThat(contractRandom.getAllEmails(),equalTo(mergeEmails(contractFromDetails)));
   }

    private String mergeEmails(ContractData contract){
        return Arrays.asList(contract.getEmail1(), contract.getEmail2(), contract.getEmail3())
                .stream()
                .filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }


}
