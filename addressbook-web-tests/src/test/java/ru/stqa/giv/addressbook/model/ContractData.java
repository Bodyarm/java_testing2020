package ru.stqa.giv.addressbook.model;

import java.util.Objects;

public class ContractData {
    private int id;
    private  String firstname;
    private final String middlename;
    private  String lastname;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private final String nickname;
    private final String title;
    private final String company;
    private String group;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ContractData(String firstname, String middlename, String lastname, String nickname, String title, String company, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.group = group;
        this.id = Integer.MAX_VALUE;
    }

    public ContractData(int id, String firstname, String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
        this.middlename = null;
        this.nickname = null;
        this.title = null;
        this.company = null;
        this.group = null;
        this.id = id;
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


}
