package ru.stqa.giv.addressbook.tests.db.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;
import ru.stqa.giv.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTests extends TestBase {


    @BeforeMethod
    public void checkPreconditions(){
        if(app.db().groups().isEmpty()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData().withId(groupToModify.getId()).withName("mod1").withFooter("mod2").withHeader("mod3");
        app.group().modify(group);

        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.db().groups();

        assertThat(after,equalTo(before.withOut(groupToModify).withAdded(group)));


    }


}
