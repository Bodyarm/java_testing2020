package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.giv.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
        //wd.findElement(By.linkText("Logout")).click();
    }


}
