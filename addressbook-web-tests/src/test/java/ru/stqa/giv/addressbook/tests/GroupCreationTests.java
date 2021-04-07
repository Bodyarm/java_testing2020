package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ before.size() );

        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ after.size() );
        Assert.assertEquals(after.size(),before.size()+1);

    }


}
