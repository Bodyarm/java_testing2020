package ru.stqa.giv.addressbook.tests.db.contracts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContractAddToGroup extends TestBase {

    @BeforeMethod
    public void preChecks(){

        //At least one group exist
        if(app.db().groups().isEmpty()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Friends").withHeader("Ho-ho-ho").withFooter("Leg"));
            app.goTo().homePage();
        }

        //At least one contract without group exist
        if(app.db().contracts().stream().filter(s->s.getGroups().isEmpty()).count()==0){
            app.contract().create(new ContractData().withFirstname("Одинойкий").withLastName("Джо"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContractToGroup(){
        ContractData contractWithNoGroup = app.db().contracts().stream().filter(s->s.getEmail3().isEmpty()).iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.contract().addContractToGroup(contractWithNoGroup,group);

        ContractData contractAfterTest = app.db().contracts().stream().filter(s->s.getId()==contractWithNoGroup.getId()).iterator().next();
        assertThat(contractAfterTest.getGroups().iterator().next(),equalTo(contractWithNoGroup.inGroup(group).getGroups().iterator().next()));

    }
}
