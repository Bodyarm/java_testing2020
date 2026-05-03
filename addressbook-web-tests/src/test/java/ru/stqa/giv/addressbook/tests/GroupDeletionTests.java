package ru.stqa.giv.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions(){
    app.goTo().groupPage();
    if (!app.group().isThereAGroup()){
      app.group().create(new GroupData("test1",null,null));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    List<GroupData> before = app.group().list();
    System.out.println("Groups Amount is "+ before.size() );
    int index = before.size() -1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    System.out.println("Groups Amount is "+ after.size() );
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);

  }



}
