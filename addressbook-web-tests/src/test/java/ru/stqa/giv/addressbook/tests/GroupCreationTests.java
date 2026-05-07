package ru.stqa.giv.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> groupList(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("Footer 1")});
        list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("Footer 2")});
        list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("Footer 3")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> groupList2() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.csv")));
        String line = reader.readLine();
        while(line !=null){
            String data[] = line.split(";");
            list.add(new Object[]{new GroupData().withName(data[0]).withHeader(data[1]).withFooter(data[2])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> groupFromXML() throws IOException {
        List<Object[]> list = new ArrayList<>();
        String xml ="";
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.xml")));
        String line = reader.readLine();
        while(line !=null){
            xml +=line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        xstream.allowTypesByWildcard(new String[] {
                "ru.stqa.giv.addressbook.model.*"
        });
        List<GroupData> groups;
        groups = (List<GroupData>) xstream.fromXML(xml);
        return  groups.stream().map(g-> new Object[] {g}).toList().iterator();
    }

    @DataProvider
    public Iterator<Object[]> groupFromJSON() throws IOException {
        List<Object[]> list = new ArrayList<>();
        String json ="";
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.json")));
        String line = reader.readLine();
        while(line !=null){
            json +=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return  groups.stream().map(g-> new Object[] {g}).toList().iterator();
    }



    @Test(dataProvider = "groupFromJSON")
    public void testGroupCreation(GroupData group) throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all();

        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size()+1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }

    



}
