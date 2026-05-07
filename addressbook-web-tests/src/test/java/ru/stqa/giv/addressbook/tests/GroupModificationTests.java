package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTests extends TestBase{


    @BeforeMethod
    public void checkPreconditions(){
        app.goTo().groupPage();
        if (app.group().all().isEmpty()){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){

        Groups before = app.group().all();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData().withId(groupToModify.getId()).withName("mod1").withFooter("mod2").withHeader("mod3");
        app.group().modify(group);

        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after,equalTo(before.withOut(groupToModify).withAdded(group)));


    }


}
