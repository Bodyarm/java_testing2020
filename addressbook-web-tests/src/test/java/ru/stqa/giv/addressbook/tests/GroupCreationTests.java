package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.giv.addressbook.model.GroupData;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ before.size() );

        GroupData group = new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ after.size() );
        Assert.assertEquals(after.size(),before.size()+1);

        int max = 0;
        for (GroupData g: after){
            if (g.getId()>max){
                max = g.getId();
            }
        }
        group.setId(max);

        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
