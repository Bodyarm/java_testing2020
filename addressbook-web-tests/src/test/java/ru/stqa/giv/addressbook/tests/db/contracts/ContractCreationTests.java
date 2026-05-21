package ru.stqa.giv.addressbook.tests.db.contracts;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;
import ru.stqa.giv.addressbook.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContractCreationTests extends TestBase {

    @BeforeMethod
    public void preparations(){
        //Create group if there are no groups
        if(app.db().groups().isEmpty()){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewCreatedGroup").withFooter("Footeer Lol").withHeader("Header Lol"));
            app.goTo().homePage();
        }
    }


    @DataProvider
    public Iterator<Object[]> groupFromXML() throws IOException {
        List<Object[]> list = new ArrayList<>();
        String xml ="";
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contract.xml")));
        String line = reader.readLine();
        while(line !=null){
            xml +=line;
            line = reader.readLine();
        }
        reader.close();
        XStream xstream = new XStream();
        xstream.processAnnotations(ContractData.class);
        xstream.allowTypesByWildcard(new String[] {
                "ru.stqa.giv.addressbook.model.*"
        });
        List<ContractData> contracts;
        contracts = (List<ContractData>) xstream.fromXML(xml);
        return  contracts.stream().map(g-> new Object[] {g}).toList().iterator();
    }

    @Test(dataProvider = "groupFromXML")
    public void testFromXML(ContractData contractToCreate){
        app.goTo().homePage();
        Contracts before = app.db().contracts();

        app.contract().initContractCreation();
        app.contract().create(contractToCreate);
        app.goTo().homePage();

        assertThat(app.contract().count(), equalTo(before.size()+1));
        Contracts after = app.db().contracts();

        int maxId = after.stream().mapToInt((g)->g.getId()).max().getAsInt();

        contractToCreate = after.stream()
                .filter(c -> c.getId() == maxId)
                .findFirst()
                .orElse(null);

        assertThat(after, equalTo(before.withAdded(contractToCreate)));
        ContractData contractFull =  app.contract().getContractFullDataByID(contractToCreate.getId());
        app.goTo().homePage();

        assertThat(contractToCreate.getFirstname(),equalTo(contractFull.getFirstname()));
        assertThat(contractToCreate.getLastname(),equalTo(contractFull.getLastname()));
        assertThat(contractToCreate.getPostAddress(),equalTo(contractFull.getPostAddress()));
    }






    @Test
    public void testContractCreation()  {

        Groups groups = app.db().groups();
        Contracts before = app.db().contracts();

        app.contract().initContractCreation();
        ContractData contractToCreate = new ContractData()
                .withFirstname("Kusman")
                .withMiddleName("test2")
                .withLastName("test3")
                .withNickName("test4")
                .withTitle("Good title")
                .withCompany("Dreamworks")
                .withPhoneHome("+79117661844")
                .withPhoneMobile("8(921)969-51-40")
                .withPhoneWork("27-93")
                .inGroup(groups.iterator().next())
                ;
        app.contract().create(contractToCreate);
        app.goTo().homePage();

        assertThat(app.contract().count(), equalTo(before.size()+1));
        Contracts after = app.db().contracts();

        int maxId = after.stream().mapToInt((g)->g.getId()).max().getAsInt();

        contractToCreate = after.stream()
                .filter(c -> c.getId() == maxId)
                .findFirst()
                .orElse(null);

        assertThat(after, equalTo(before.withAdded(contractToCreate)));
        ContractData contractFull =  app.contract().getContractFullDataByID(contractToCreate.getId());
        app.goTo().homePage();

        assertThat(contractToCreate.getPhoneHome(),equalTo(contractFull.getPhoneHome()));
        assertThat(contractToCreate.getPhoneMobile(),equalTo(contractFull.getPhoneMobile()));
        assertThat(contractToCreate.getPhoneWork(),equalTo(contractFull.getPhoneWork()));

    }



    @Test
    public void testPhoto(){

        app.goTo().homePage();
        Contracts before = app.contract().all();
        app.contract().initContractCreation();

        File photo = new File("src/test/resources/havok.jpg");


        ContractData contractToCreate = new ContractData()
                .withFirstname("check")
                .withLastName("test3")
                .withPhoto(photo);
                
                ;
        app.contract().create(contractToCreate);
        app.goTo().homePage();
        
        
    }



    public String cleanPhone(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");

    }

}
