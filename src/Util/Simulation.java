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
        int chance = r.nextInt(100);
        if (c.getPlay()!=1 && chance>30){int feed=0;
            int mood=0;
            if (c.getAge()>=1 && c.getAge()<=5){
                feed = 7;
                mood = 7;
            }else if(c.getAge()>=6 && c.getAge()<=10){
                feed = 5;
                mood = 5;
            } else if (c.getAge()>=11) {
                feed = 4;
                mood = 4;
            }
            System.out.printf("Вы покормили %s вискасом +%s к насыщению и +%s к настроению%n",c.getName(),feed,mood);
            int current = c.getSatiety();
            int currentMood = c.getMood();
            c.setMood(currentMood+mood);
            c.setSatiety(current+feed);
            c.setMid((c.getSatiety()+c.getMood()+c.getHp())/3);
            c.setPlay(1);}
        else if (chance<=30 && c.getPlay()!=1) {
            int mood = 4;
            int hp = 5;
            int current = c.getHp();
            int currentMood = c.getMood();
            System.out.printf("%s съел просроченный вискас, -%s к здоровью и -%s к настроению%n",c.getName(),hp,mood);
            c.setHp(current-hp);
            c.setMood(currentMood-mood);
            c.setMid((c.getSatiety()+c.getMood()+c.getHp())/3);
            c.setPlay(1);}
         else {
            System.out.println("Кот устал, действие недоступно");
        }

        return c;
    }

    @Override
    public Cat playWithCat(Cat c) {
        int chance = r.nextInt(100);
        if (c.getPlay()!=1 && chance>30){int fun = 0;
            int hp = 0;
            int sat = 0;
            if (c.getAge()>=1 && c.getAge()<=5){
                sat = 3;
                hp = 7;
                fun = 7;
            }else if(c.getAge()>=6 && c.getAge()<=10){
                sat = 5;
                hp = 5;
                fun = 5;
            } else if (c.getAge()>=11) {
                sat = 6;
                hp = 4;
                fun = 4;
            }

            System.out.printf("Вы поиграли с %s. +%s к настроению и +%s к здоровью. -%s к сытости%n",c.getName(),fun,hp,sat);
            int currentSat = c.getSatiety();
            int currentHp= c.getHp();
            int current = c.getMood();
            c.setMood(current+fun);
            c.setSatiety(currentSat-sat);
            c.setHp(currentHp+hp);
            c.setMid((c.getSatiety()+c.getMood()+c.getHp())/3);
            c.setPlay(1);}
        else if (chance<=30 && c.getPlay()!=1) {
            int mood = 4;
            int hp = 100;
            int current = c.getHp();
            int currentMood = c.getMood();
            System.out.printf("%s приземлился не на лапы, -%s к здоровью и -%s к настроению%n",c.getName(),hp,mood);
                c.setHp(current-hp);
                c.setMood(currentMood-mood);
                c.setMid((c.getSatiety()+c.getMood()+c.getHp())/3);
                c.setPlay(1);
            if (c.getHp()<=0){
                System.out.println("Кот умер");
                c.setHp(current-hp);
            }
            }
         else {
            System.out.println("Кот устал, действие недоступно");
        }

        return c;
    }

    @Override
    public Cat healCat(Cat c) {
        if (c.getPlay()!=1){ int hp = 0;
            int mood = 0;
            int satiety = 0;
            if (c.getAge()>=1 && c.getAge()<=5){
                satiety = 3;
                hp = 7;
                mood = 3;
            }else if(c.getAge()>=6 && c.getAge()<=10){
                satiety = 5;
                hp = 5;
                mood = 5;
            } else if (c.getAge()>=11) {
                satiety = 6;
                hp = 4;
                mood = 6;
            }
            System.out.printf("Вы отвели %s к ветеринару. +%s к здоровью%n -%s к настроению и -%s к сытости%n",c.getName(),hp,mood,satiety);
            int current = c.getHp();
            int currentMood = c.getMood();
            int currentSat= c.getSatiety();
            c.setMood(currentMood-mood);
            c.setSatiety(currentSat-satiety);
            c.setHp(current+hp);
            c.setMid((c.getSatiety()+c.getMood()+c.getHp())/3);
            c.setPlay(1);}
        else {
            System.out.println("Кот устал, действие недоступно");
        }

        return c;
    }

    public void chooseAction(List<Cat> c){
        GenerateCat g = new GenerateCat();
        int chooseAct = choice("Выберите действие с котами\n"+
                "1.Добавить кота\n"+
                "2.Покормить кота\n"+
                "3.Поиграть с котом\n"+
                "4.Отвести кота к ветеринару\n"+
                "5.Переключиться на следующий день");
        switch (chooseAct){
            case 1:
                g.addCat(c);
                g.printCats(c);
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
                c.removeIf(e->e.getHp()<0);
                g.printCats(c);
                chooseAction(c);
                break;
            case 4:
                int chooseCat3 = choice("Выберите кота");
                healCat(getPus(c,chooseCat3));
                g.printCats(c);
                chooseAction(c);
                break;
            case 5:
                nextDay(c);
                g.printCats(c);
                chooseAction(c);
                break;
            default:
                System.out.println("Данное действие недоступно. Выберите дейтвие повторно");
                chooseAction(c);
                break;
        }

    }
    public List<Cat> nextDay(List<Cat> c){
        for (var str:c) {
            int sat = r.nextInt(4)+1;
            int mood = r.nextInt(6)-3;
            int hp = r.nextInt(6)-3;
            int currentHp =str.getHp();
            int currentMood = str.getMood();
            int currentSat = str.getSatiety();
            if (str.getHp()>0){str.setHp(currentHp+hp);}
            str.setPlay(0);
            if(str.getMood()>0){str.setMood(currentMood+mood);}
            if (str.getSatiety()>0){str.setSatiety(currentSat+sat);}
            str.setMid((str.getHp()+str.getMood()+str.getSatiety())/3);
        }
       return c;
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
