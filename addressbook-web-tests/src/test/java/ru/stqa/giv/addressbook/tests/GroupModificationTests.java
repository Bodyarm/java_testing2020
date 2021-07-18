package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{


    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1",null,null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ before.size() );
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size()-1).getId(),"mod1","mod2", "mod3");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ after.size() );
        Assert.assertEquals(after.size(), before.size());

        before.remove((before.size()-1));
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }
}
