package Util;

import Model.Cat;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Simulation implements Actions{
    private Random r = new Random();
    public void run(){
        GenerateCat g = new GenerateCat();
        List<Cat> cats = g.createCat();
        g.printCats(cats);
        chooseAction(cats);
    }


    @Override
    public Cat feedCat(Cat c) {
        int feed = r.nextInt(10)+10;
        System.out.printf("Вы покормили %s вискасом + %s к насыщению%n",c.getName(),feed);
        int current = c.getSatiety();
        c.setSatiety(current+feed);
        return c;
    }

    @Override
    public Cat playWithCat(Cat c) {
        int fun = r.nextInt(15)+15;
        System.out.printf("Вы поиграли с %s. + %s к настроению.%n",c.getName(),fun);
        int current = c.getMood();
        c.setMood(current+fun);
        return c;
    }

    @Override
    public Cat healCat(Cat c) {
        int hp = r.nextInt(15)+25;
        System.out.printf("Вы отвели %s к ветеринару. + %s к здоровью%n",c.getName(),hp);
        int current = c.getHp();
        c.setHp(current+hp);
        return c;
    }

    public void chooseAction(List<Cat> c){
        GenerateCat g = new GenerateCat();
        int chooseAct = choice("Выберите действие с котами\n"+
                "1.Добавить кота\n"+
                "2.Покормить кота\n"+
                "3.Поиграть с котом\n"+
                "4.Отвести кота к ветеринару");
        switch (chooseAct){
            case 1:
                g.addCat(c);
                chooseAction(c);
                break;
            case 2:
                int chooseCat = choice("Выберите кота");
                feedCat(getPus(c,chooseCat));
                g.printCats(c);
                chooseAction(c);
                break;
            case 3:
                int chooseCat2 = choice("Выберите кота");
                playWithCat(getPus(c,chooseCat2));
                g.printCats(c);
                chooseAction(c);
                break;
            case 4:
                int chooseCat3 = choice("Выберите кота");
                healCat(getPus(c,chooseCat3));
                g.printCats(c);
                chooseAction(c);
                break;
            default:
                System.out.println("Данное действие недоступно. Выберите дейтвие повторно");
                chooseAction(c);
                break;
        }

    }

    public Cat getPus(List<Cat> c, int choice){
        return c.get(choice-1);
    }
    private static int choice(String message) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            try {
                return input.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Данное действие недоступно");
                input.nextLine();
            }
        }
    }
}
