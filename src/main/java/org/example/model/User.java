package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purpose_id")
    private PurposeUser purposeUser;

    @OneToMany(cascade = CascadeType.ALL
            , mappedBy = "user")
    private List<MonthData> monthData;


    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;

    }

    public User() {
    }

    public void addMonth(MonthData month) {
        if (monthData == null) {
            monthData = new ArrayList<>();
        }
        monthData.add(month);
        month.setUsers(this);
    }

    public void addPur(PurposeUser purpose) {
        if (this.purposeUser != null) {
            purposeUser.setPurpose(purpose.getPurpose());
        } else this.purposeUser = purpose;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<MonthData> getMonthData() {
        return monthData;
    }

    public void setMonthData(List<MonthData> monthData) {
        this.monthData = monthData;
    }

    public PurposeUser getPurposeUser() {
        return purposeUser;
    }

    public void setPurposeUser(PurposeUser purposeUser) {
        this.purposeUser = purposeUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", purposeUser=" + purposeUser +
                ", monthData=" + monthData +
                '}';
    }
}
