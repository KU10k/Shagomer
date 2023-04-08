package org.example.dao;

import org.example.model.MonthData;
import org.example.model.PurposeUser;
import org.example.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAO {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int userInfo;


    public void addNewUser(String userName, String userSurname, String login, String password, int purpose) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        PurposeUser purposeUser = new PurposeUser(purpose);
        User user = new User(userName, userSurname, login, password);

        user.setPurposeUser(purposeUser);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public int userLogin(String username, String password) {
        User userFromLogin = findUserByUsername(username);

        if (userFromLogin == null) {
            System.out.println("Такого пользователя не существует");

        } else {
            if (correctPassword(password, userFromLogin)) {
                System.out.println("Поздравляю ты вошёл в личный кабинет");
                System.out.println("Добро пожаловать в личный кабинет: " + userFromLogin.getName() +
                        " " + userFromLogin.getSurname());
                userInfo = userFromLogin.getId();
                return userInfo;

            } else {
                System.out.println("Пароль неверный");
            }
        }
        return 0;
    }

    public void saveMonth(String monthScan, int day, int step) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, userInfo);

        ShagomerDAO shagomerDAO = new ShagomerDAO(sessionFactory);

        MonthData monthData = shagomerDAO.stepOfDay(monthScan, day, step);


        user.addMonth(monthData);

        session.persist(user);

        session.getTransaction().commit();
        session.close();
    }

    public void newPurpose(int x) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, userInfo);

        PurposeUser purposeUser1 = new PurposeUser(x, user);

        user.addPur(purposeUser1);
        session.persist(user);


        session.getTransaction().commit();
        session.close();
    }

    public void exceedingTheGoal() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, userInfo);
        System.out.println("Ваша цель = " + user.getPurposeUser().getPurpose() + " в день");
        for (MonthData monthLine : user.getMonthData()) {
                if (user.getPurposeUser().getPurpose() < monthLine.getStep()) {
                    System.out.println(
                            monthLine.getDay() + " " + monthLine.getMonth() + " Вы " + user.getName() + " прошли " +
                                    monthLine.getStep() + " " +
                                    " Вы молодец! Вы справились с поставленной задачей");
                } else {
                    System.out.println(
                            monthLine.getDay() + " " + monthLine.getMonth() + " Вы " + user.getName() + " прошли " +
                                    monthLine.getStep() + " " +
                                    " Вы недостигли цели");
                }
            }
        session.getTransaction().commit();
        session.close();
    }


    public List<MonthData> monthDay(String monthName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, userInfo);

        List<MonthData> monthDataList = new ArrayList<>();
        for (MonthData monthLine : user.getMonthData()) {
            if (monthLine.getMonth().equals(monthName)) {
                MonthData month = new MonthData(monthLine.getId(), monthLine.getMonth(),
                        monthLine.getDay(), monthLine.getStep(), user);
                monthDataList.add(month);
            }
        }
        session.getTransaction().commit();
        session.close();
        return monthDataList;
    }

//    public List<MonthData> monthDay2(String monthName) {
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        User user = session.get(User.class, userInfo);
//
//        Query<User> getAll = session.createQuery("from User where id= ?2 ");
//        Query<MonthData> getAll2 = session.createQuery("from MonthData where id= ?1 ");
//        getAll2.setParameter(1, monthName);
//        getAll.setParameter(2, user.getId());
//        getAll2.getResultList();
//
//
//        List<MonthData> monthDataList = new ArrayList<>();
////        for (MonthData monthLine :  getAll.getResultList()) {
////            MonthData month = new MonthData(monthLine.getId(), monthLine.getMonth(),
////                    monthLine.getDay(), monthLine.getStep(), user);
////            monthDataList.add(month);
////        }
//
//        return monthDataList;
//
//    }


    public void sumOfMonth(String monthName) {
        int result = 0;
        Session session;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, userInfo);

        for (MonthData monthLine : user.getMonthData()) {
            if (monthLine.getMonth().equals(monthName)) {
                result += monthLine.getStep();
            }
        }
        session.getTransaction().commit();
        session.close();
        System.out.println("В месяц : " + monthName + " в сумме вы прошли : " + result + "\n");
    }

    public List<User> userList() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User").getResultList();

        session.getTransaction().commit();
        session.close();
        return users;
    }

    private User findUserByUsername(String username) {
        for (User user : userList()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private boolean correctPassword(String password, User userFromLogin) {
        return userFromLogin.getPassword().equals(password);
    }


}
