package ru.stqa.giv.addressbook.tests.db.contracts;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContractEmailTests extends TestBase {

    Logger logger = LoggerFactory.getLogger(ContractEmailTests.class);

    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().homePage();
        Contracts before = app.db().contracts();

        int amount = (int) before
                .stream()
                .filter((s) -> s.getEmail1().length() >1 & s.getEmail2().length()>1 & s.getEmail3().length()>1)
                .count();

        if (amount ==0 ){
            app.contract().create(new ContractData()
                    .withFirstname("Check1")
                    .withMiddleName("Kus")
                    .withLastName("Dog lover")
                    .withNickName("LegNeck")
                    .withTitle("HOHOHO")
                    .withCompany("ClosedWay")
                    //.withGroup("test1")
                    .withEmail1("pub@mail.ru")
                    .withEmail2("pub@rambler.ru")
                    .withEmail3("kusmadan@narod.org")

            );
        };
        app.goTo().homePage();

    }


    @Test
    public void emailTest1(){

        Contracts before = app.db().contracts();
        ContractData contractRandom = before.stream().filter(s->s.getEmail1().length()>1).iterator().next();
        ContractData contractFromDetails = app.contract().getContractFullDataByID(contractRandom.getId());

        logger.info("Assert E-mail checks for contract with ID="+contractRandom.getId());
        assertThat(contractRandom.getEmail1(),equalTo(contractFromDetails.getEmail1()));
        assertThat(contractRandom.getEmail2(),equalTo(contractFromDetails.getEmail2()));
        assertThat(contractRandom.getEmail3(),equalTo(contractFromDetails.getEmail3()));

   }

    private String mergeEmails(ContractData contract){
        return Arrays.asList(contract.getEmail1(), contract.getEmail2(), contract.getEmail3())
                .stream()
                .filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }


}
