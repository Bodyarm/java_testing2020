package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{


    @BeforeMethod
    public void checkPreconditions(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1",null,null));
        }
    }

    @Test
    public void testGroupModification(){

        List<GroupData> before = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ before.size() );
        int index = before.size()-1;

        GroupData group = new GroupData(before.get(index).getId(),"mod1","mod2", "mod3");
        app.getGroupHelper().modifyGroup(index, group);

        List<GroupData> after = app.getGroupHelper().getGroupList();
        System.out.println("Groups Amount is "+ after.size() );

        before.remove((index));
        before.add(group);

        Comparator<? super GroupData> byID= (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before,after);


    }


}
