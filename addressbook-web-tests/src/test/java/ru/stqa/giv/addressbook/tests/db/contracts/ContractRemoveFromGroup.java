package ru.stqa.giv.addressbook.tests.db.contracts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;
import ru.stqa.giv.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContractRemoveFromGroup extends TestBase {

    @BeforeMethod
    public void preparations(){

        //At least one group exist
        if(app.db().groups().isEmpty()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Friends").withHeader("Ho-ho-ho").withFooter("Leg"));
        }

        //At least one contract exist
        app.goTo().homePage();
        if(app.contract().count()==0){
            app.contract().create(new ContractData().withFirstname("James").withCompany("Daily Bugle"));
            app.goTo().homePage();
        }

        //At least one contract with group exist
        if(!app.db().contracts().stream().anyMatch(s -> !s.getGroups().isEmpty())){
            GroupData group = app.db().groups().iterator().next();
            app.contract().addContractToGroup(app.db().contracts().iterator().next(), group);
        }

        //Проверка что контракт уже состоит в группе
    }


    @Test
    public void testRemoveContractFromGroup(){
        app.goTo().homePage();
        Contracts contractsBefore = app.db().contracts().stream().filter(s->!s.getGroups().isEmpty()).collect(Collectors.collectingAndThen(Collectors.toSet(),Contracts::new));
        ContractData contractGroupRemove = contractsBefore.iterator().next();
        GroupData group = contractGroupRemove.getGroups().iterator().next();

        app.contract().removeContractFromGroup(contractGroupRemove, group);
        app.goTo().homePage();

        Contracts contractsAfter = app.db().contracts();
        ContractData contractAfterGroupRemoved = contractsAfter.stream().filter(s-> s.getId()==contractGroupRemove.getId()).iterator().next();

        assertThat(contractAfterGroupRemoved.inGroup(group),equalTo(contractGroupRemove));
    }
}
