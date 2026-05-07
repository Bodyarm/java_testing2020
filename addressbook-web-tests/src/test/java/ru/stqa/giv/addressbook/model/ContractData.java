package ru.stqa.giv.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contract")
public class ContractData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @XStreamOmitField
    private String middlename;
    @Expose
    private String lastname;
    @XStreamOmitField
    private String nickname;
    @XStreamOmitField
    private String title;
    @XStreamOmitField
    private String company;
    @XStreamOmitField
    private String group;
    @XStreamOmitField
    private String phoneHome;
    @XStreamOmitField
    private String phoneMobile ="";
    @XStreamOmitField
    private String phoneWork ="";
    @XStreamOmitField
    private String allPhones ="";
    @XStreamOmitField
    private String email1 = "";
    @XStreamOmitField
    private String email2 = "";
    @XStreamOmitField
    private String email3 = "";
    @XStreamOmitField
    private String allEmails ="";
    @Expose
    private String postaddress = "";
    @XStreamOmitField
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public String getPostAddress(){
        return this.postaddress;
    }
    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails(){
        return allEmails;
    }

    public String getAllPhones() {
        return allPhones;
    }



    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public int getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getGroup() {return group;}

    public ContractData withId(int id) {
        this.id = id;
        return this;
    }

    public ContractData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContractData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContractData withMiddleName(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContractData withNickName(String nickname) {
        this.nickname = nickname;
        return  this;
    }

    public ContractData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContractData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContractData  withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContractData withPhoneHome(String phoneHome){
        this.phoneHome = phoneHome;
        return this;
    }

    public ContractData withPhoneMobile(String phoneMobile){
        this.phoneMobile = phoneMobile;
        return this;
    }

    public ContractData withPhoneWork(String phoneWork){
        this.phoneWork = phoneWork;
        return this;
    }

    public ContractData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContractData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }
    public ContractData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContractData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContractData withAllEmail(String allEmails){
        this.allEmails = allEmails;
        return this;
    }

    public ContractData withPostAddress(String postAddress){
        this.postaddress = postAddress;
        return this;
    }

    public ContractData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractData that = (ContractData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(middlename, that.middlename);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(firstname);
        result = 31 * result + Objects.hashCode(middlename);
        result = 31 * result + id;
        return result;
    }


    @Override
    public String toString() {
        return "ContractData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
