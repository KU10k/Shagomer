package org.example.dao;

import java.util.InputMismatchException;
import org.example.model.Month;
import org.example.model.MonthData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ShagomerDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ShagomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MonthData stepOfDay(String monthScan, int day, int step) {
        MonthData monthData = null;
        int x = step;
        int a = day;
        try {
            Month month = Month.valueOf(monthScan);
            try {
                if (0 <= a && month.getDay().length >= a) {
                    for (int i = a; i < month.getDay().length; ) {
                        monthData = new MonthData(monthScan, i, x);
                        break;
                    }
                } else {
                    System.out.println("Такого дня нет");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Невернные данные");
            }
        } catch (InputMismatchException e) {
            System.out.println("Невернные данные");
        }
        return monthData;
    }

}
