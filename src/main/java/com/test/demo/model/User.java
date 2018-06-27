package com.test.demo.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    //@GeneratedValue( strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String name;
    @Column
    private String username;
    @Column
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "street", referencedColumnName = "street"),
            @JoinColumn(name = "suite", referencedColumnName = "suite"),
            @JoinColumn(name = "city", referencedColumnName = "city"),
            @JoinColumn(name = "zipcode", referencedColumnName = "zipcode")
    })
    private Address address;
    @Column
    private String phone;
    @Column
    private String website;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "company_name", referencedColumnName = "name"),
            @JoinColumn(name = "catchPhrase", referencedColumnName = "catchPhrase"),
            @JoinColumn(name = "bs", referencedColumnName = "bs")
    })
    private Company company;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}
