package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

        Set<GroupData> before = app.group().all();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData().withId(groupToModify.getId()).withName("mod1").withFooter("mod2").withHeater("mod3");
        app.group().modify(group);

        Set<GroupData> after = app.group().all();
        before.remove(groupToModify);
        before.add(group);

        Assert.assertEquals(before,after);


    }


}
