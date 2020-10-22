package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.giv.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }


}
