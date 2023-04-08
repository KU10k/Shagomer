package org.example;

import org.example.configuration.ApplicationConfiguration;
import org.example.dao.ShagomerDAO;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    ///данные пользователся
    private static final String POSSIBLE_ANSWER1 = "логин";
    private static final String POSSIBLE_ANSWER2 = "регистрация";
    private static final String POSSIBLE_ANSWER3 = "выход";

    ///ввод месяца
    private static final String ANSWER1 = "ввести данные";
    private static final String ANSWER2 = "вывести данные за месяц";
    private static final String ANSWER3 = "сумма шагов";
    private static final String ANSWER5 = "изменить цель";
    private static final String ANSWER4 = "выход";
    private static final String ANSWER6 = "вывести все данные";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);


        Scanner scanner = new Scanner(System.in);

        ///данные пользователся
        int purpose;
        String name;
        String surname;
        String login;
        String password;
        String answerToDoing;
        boolean exit = false;

        ///ввод месяца
        String toDoing;
        String monthScan;
        String stepOfDay;
        int day;
        String sum;
        int step;


        while (!exit) {
            System.out.print("\n" + "Что будем делать (логин || регистрация): ");
            answerToDoing = scanner.nextLine().toLowerCase();
            switch (answerToDoing) {
                case POSSIBLE_ANSWER1:
                    System.out.println("Хорошо, чтобы войти в личный кабинет" +
                            " нам нужно от вас логин ");
                    System.out.print("Введите логин: ");
                    login = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    password = scanner.nextLine();
                    if (userDAO.userLogin(login, password) != 0) {

                    }else break;
/////////////////////////////ввод месяца/////////////////////////
                    boolean exitData = false;
                    while (!exitData) {
                        System.out.print("ввести данные | вывести данные за месяц | вывести все данные | " +
                                "сумма шагов | изменить цель :");
                        toDoing = scanner.nextLine();
                        switch (toDoing) {
                            case ANSWER1:
                                System.out.print("введите месяц: " + "\n"
                                        + "JAN, " + "FEB, " + "MAR, " + "APR, " + "MAY, " + "JUNE, " + "JULY, " +
                                        "AUG, " + "SEP, " + "OCT ," + "NOV, " + "DEC." + "\n");
                                Scanner scanMonth = new Scanner(System.in);
                                try {
                                    monthScan = scanMonth.nextLine().toUpperCase();
                                    System.out.print("Введите день начала тренировки : ");
                                    day = scanMonth.nextInt();
                                    System.out.print("Введите число шагов: ");
                                    step = scanMonth.nextInt();
                                    userDAO.saveMonth(monthScan, day, step);
                                } catch (IllegalArgumentException exc) {
                                    System.out.println("не понятные данные!" + "\n");
                                }

                                break;
                            case ANSWER2:
                                System.out.print("\n" + "Введите месяц для вывода информации о нем :");
                                Scanner scanStepOfDay = new Scanner(System.in);
                                stepOfDay = scanStepOfDay.nextLine().toUpperCase();
                                userDAO.monthDay(stepOfDay).forEach(System.out::println);
                                break;
                            case ANSWER6:
                                System.out.print("\n" + "Введите все данные где пользователь привысил цель :");
                                userDAO.exceedingTheGoal();
                                break;
                            case ANSWER3:
                                System.out.print("\n" + "Введите месяц чтобы узнать количество шагов :");
                                Scanner sumOfSteps = new Scanner(System.in);
                                sum = sumOfSteps.nextLine().toUpperCase();
                                userDAO.sumOfMonth(sum);
                                break;
                            case ANSWER4:
                                exitData = true;
                                break;
                            case ANSWER5:
                                System.out.print("\n" + "Изменить цель :");
                                int newPurpose;
                                Scanner purposeNew = new Scanner(System.in);
                                newPurpose = purposeNew.nextInt();
                                userDAO.newPurpose(newPurpose);
                                break;
                            default:
                                System.out.println("Не верные данные!" + "\n");
                        }

                    }
//////////////////////////////////////////////////////////////////////////////////////////
                    break;
                case POSSIBLE_ANSWER2:
                    System.out.println("Хорошо, чтобы зарегистрироваться в личный кабинет" +
                            " нам нужно от вас логин ");
                    System.out.print("Введите Имя: ");
                    name = scanner.nextLine();
                    System.out.print("Введите Фамилию: ");
                    surname = scanner.nextLine();
                    System.out.print("Введите логин: ");
                    login = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    password = scanner.nextLine();
                    System.out.print("Введите цель: ");
                    purpose = scanner.nextInt();

                    userDAO.addNewUser(name, surname, login, password, purpose);
                    break;

                case POSSIBLE_ANSWER3:
                    exit = true;
                    break;
                default:
                    System.out.println("Я вас не понял попробуйте ещё раз");
                    break;
            }

        }
        SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
        sessionFactory.close();
        context.close();
    }

}
