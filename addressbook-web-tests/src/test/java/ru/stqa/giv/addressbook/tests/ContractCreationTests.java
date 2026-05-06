package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContractCreationTests extends TestBase {

    @Test
    public void testContractCreation()  {

        app.goTo().homePage();
        Contracts before = app.contract().all();


        // Метод зачитывания данных из страницы с редактирования
        // чё делать если телефонов указано меньше чем и как понять какой к какому полю относится?


        app.contract().initContractCreation();
        ContractData contractToCreate = new ContractData()
                .withFirstname("check")
                .withMiddleName("test2")
                .withLastName("test3")
                .withNickName("test4")
                .withTitle("Good title")
                .withCompany("Dreamworks")
                .withGroup("test1")
                .withPhoneHome("+79117661844")
                .withPhoneMobile("8(921)969-51-40")
                .withPhoneWork("27-93")
                ;
        app.contract().create(contractToCreate);
        app.goTo().homePage();

        assertThat(app.contract().count(), equalTo(before.size()+1));
        Contracts after = app.contract().all();

        int maxId = after.stream().mapToInt((g)->g.getId()).max().getAsInt();

        contractToCreate = after.stream()
                .filter(c -> c.getId() == maxId)
                .findFirst()
                .orElse(null);

        assertThat(after, equalTo(before.withAdded(contractToCreate)));
        ContractData contractFull =  app.contract().getContractFullDataByID(contractToCreate.getId());
        app.goTo().homePage();

        assertThat(contractToCreate.getPhoneHome(),equalTo(cleanPhone(contractFull.getPhoneHome())));
        assertThat(contractToCreate.getPhoneMobile(),equalTo(cleanPhone(contractFull.getPhoneMobile())));
        assertThat(contractToCreate.getPhoneWork(),equalTo(cleanPhone(contractFull.getPhoneWork())));

    }

    public String cleanPhone(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");

    }

}
