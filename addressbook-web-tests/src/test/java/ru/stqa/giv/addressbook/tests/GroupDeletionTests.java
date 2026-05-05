package ru.stqa.giv.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    Groups before = app.group().all();
    GroupData groupToDelete = before.iterator().next();
    app.group().delete(groupToDelete);
    Groups after = app.group().all();

    assertThat(after.size(),equalTo(before.size()-1));
    assertThat(after, equalTo(before.withOut(groupToDelete)));


  }



}
