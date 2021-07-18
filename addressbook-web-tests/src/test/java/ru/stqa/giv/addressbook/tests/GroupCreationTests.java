package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ before.size() );

        GroupData group = new GroupData("testZ", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ after.size() );
        Assert.assertEquals(after.size(),before.size()+1);

        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
