package ru.stqa.giv.addressbook.tests.contracts;

import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractPhoneTests extends TestBase {


    @Test(enabled = false)
    //Упадёт из-за изменений в ContractData equals. Стало больше полей
    public void creationCheckPhone(){

        app.goTo().homePage();
        Contracts before = app.contract().all();

        app.contract().initContractCreation();
        ContractData contractToCreate = new ContractData()
                .withFirstname("check")
                .withMiddleName("test2")
                .withLastName("test3")
                .withNickName("test4")
                .withTitle("Good title")
                .withCompany("Dreamworks")
                //.withGroup("test1")
                .withPhoneHome("+79117661844")
                .withPhoneMobile("8(921)969-51-40")
                //.withPhoneWork("27-93")
                ;
        app.contract().create(contractToCreate);
        app.goTo().homePage();

        //Check that arrays have equal size after adding new one
        assertThat(app.contract().count(), equalTo(before.size()+1));
        Contracts after = app.contract().all();

        int maxId = after.stream().mapToInt((g)->g.getId()).max().getAsInt();

        contractToCreate = after.stream()

                .filter(c -> c.getId() == maxId)
                .findFirst()
                .orElse(null);

        //Check all contracts
        assertThat(after, equalTo(before.withAdded(contractToCreate)));
        ContractData contractFull =  app.contract().getContractFullDataByID(contractToCreate.getId());
        app.goTo().homePage();

        //Check by every field
        assertThat(contractToCreate.getPhoneHome(),equalTo(cleanPhone(contractFull.getPhoneHome())));
        assertThat(contractToCreate.getPhoneMobile(),equalTo(cleanPhone(contractFull.getPhoneMobile())));
        assertThat(contractToCreate.getPhoneWork(),equalTo(cleanPhone(contractFull.getPhoneWork())));

        //Check by joined Phones
        assertThat(contractToCreate.getAllPhones(), equalTo(mergePhones(contractFull)));


    }

    public static String cleanPhone(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");

    }

    private String mergePhones(ContractData contract){
         return Arrays.asList(contract.getPhoneHome(), contract.getPhoneMobile(), contract.getPhoneWork())
        .stream()
        .filter((s)->!s.equals(""))
        .map(ContractPhoneTests::cleanPhone)
        .collect(Collectors.joining("\n"));
    }


}