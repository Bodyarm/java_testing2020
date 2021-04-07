package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("del1",null,null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    System.out.println("Groups Amount is "+ before.size() );
    app.getGroupHelper().selectGroup(before.size() -1 );
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    System.out.println("Groups Amount is "+ after.size() );
    Assert.assertEquals(after.size(), before.size() -1);


  }

}
