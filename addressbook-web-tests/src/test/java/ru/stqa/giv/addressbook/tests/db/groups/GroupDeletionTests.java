package ru.stqa.giv.addressbook.tests.db.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;
import ru.stqa.giv.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions(){

    app.goTo().groupPage();
    if (app.db().groups().isEmpty()){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData groupToDelete = before.iterator().next();
    app.group().delete(groupToDelete);
    assertThat(app.group().count(),equalTo(before.size()-1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withOut(groupToDelete)));


  }



}
