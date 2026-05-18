package ru.stqa.giv.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contract")
@Entity
@Table(name="addressbook")
public class ContractData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name="firstname")
    private String firstname;
    @XStreamOmitField
    @Column(name="middlename")
    private String middlename;
    @Expose
    @Column(name="lastname")
    private String lastname;
    @XStreamOmitField
    @Transient
    private String nickname;
    @XStreamOmitField
    @Transient
    private String title;
    @XStreamOmitField
    @Transient
    private String company;
    @XStreamOmitField
    @Transient
    private String group;
    @XStreamOmitField
    @Column(name="home", columnDefinition = "TEXT")
    private String phoneHome;
    @XStreamOmitField
    @Column(name="mobile",columnDefinition = "TEXT")
    private String phoneMobile ="";
    @XStreamOmitField
    @Column(name="work",columnDefinition = "TEXT")
    private String phoneWork ="";
    @XStreamOmitField
    @Transient
    private String allPhones ="";
    @XStreamOmitField
    @Column(name="email")
    private String email1 = "";
    @XStreamOmitField
    @Column(name="email2")
    private String email2 = "";
    @XStreamOmitField
    @Column(name = "email3")
    private String email3 = "";
    @XStreamOmitField
    @Transient
    private String allEmails ="";
    @Expose
    @Column(name="address")
    private String postaddress = "";
    @XStreamOmitField
    @Column(name="photo", columnDefinition = "TEXT")
    private String photo="";

    public File getPhoto() {
        return new File(Objects.requireNonNullElse(photo,""));
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
        this.photo = photo.getPath();
        return this;
    }


    @Override
    public String toString() {
        return "ContractData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractData that = (ContractData) o;
        return id == that.id
                && Objects.equals(firstname, that.firstname)
                && Objects.equals(middlename, that.middlename)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(phoneHome, that.phoneHome)
                && Objects.equals(phoneMobile, that.phoneMobile)
                && Objects.equals(phoneWork, that.phoneWork)
                && Objects.equals(email1, that.email1)
                && Objects.equals(email2, that.email2)
                && Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(firstname);
        result = 31 * result + Objects.hashCode(middlename);
        result = 31 * result + Objects.hashCode(lastname);
        result = 31 * result + Objects.hashCode(phoneHome);
        result = 31 * result + Objects.hashCode(phoneMobile);
        result = 31 * result + Objects.hashCode(phoneWork);
        result = 31 * result + Objects.hashCode(email1);
        result = 31 * result + Objects.hashCode(email2);
        result = 31 * result + Objects.hashCode(email3);


        return result;
    }

}