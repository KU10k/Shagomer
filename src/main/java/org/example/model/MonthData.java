package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "months")
public class MonthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true)
    private int id;
    @Column(name = "name")
    private String month;
    @Column(name = "date")
    private int day;
    @Column(name = "count")
    private int step;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public MonthData(String month, int day, int step) {
        this.month = month;
        this.day = day;
        this.step = step;
    }

    public MonthData(int id,String month, int day, int step, User user) {
        this.id=id;
        this.month = month;
        this.day = day;
        this.step = step;
        this.user = user;
    }

    public MonthData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }

    @Override
    public String toString() {
        return "MonthData{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", day=" + day +
                ", step=" + step +
                ", users=" + user.getName() + " " + user.getSurname() +
                '}';
    }
}
