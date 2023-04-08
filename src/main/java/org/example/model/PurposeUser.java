package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "purposes")
public class PurposeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private int id;
    @Column(name = "purpose")
    private int purpose;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.DETACH, CascadeType.REFRESH},
            mappedBy = "purposeUser")
    private User user;

    public PurposeUser(int purpose) {
        this.purpose = purpose;
    }

    public PurposeUser(int purpose, User user) {
        this.purpose = purpose;
        this.user = user;
    }

    public PurposeUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PurposeUser{" +
                "id=" + id +
                ", purpose=" + purpose +
                ", user=" + user.getName() +
                '}';
    }
}
