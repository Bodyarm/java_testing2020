package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{


    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("del1",null,null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("mod1","mod2", "mod3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }
}
