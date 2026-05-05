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
        app.goTo().groupPage();
        if (app.group().list().isEmpty()){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification(){

        List<GroupData> before = app.group().list();
        System.out.println("Groups Amount is "+ before.size() );
        int index = before.size()-1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("mod1").withFooter("mod2").withHeater("mod3");
        app.group().modify(index, group);

        List<GroupData> after = app.group().list();
        System.out.println("Groups Amount is "+ after.size() );

        before.remove((index));
        before.add(group);

        Comparator<? super GroupData> byID= (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byID);
        after.sort(byID);

        Assert.assertEquals(before,after);


    }


}
