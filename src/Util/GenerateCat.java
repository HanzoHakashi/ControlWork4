package Util;

import Model.Cat;

import java.util.*;

public class GenerateCat {
    private List<String> names = new ArrayList<>();
    List<Cat> cats = new ArrayList<>();
    private  Random r = new Random();

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }
    public List<String> catName(){
        List<String> namesC = new ArrayList<>();
        namesC.add("Neko");
        namesC.add("Kitsune");
        namesC.add("Nana");
        namesC.add("Chopper");
        setNames(namesC);
        return namesC;
    }

   public List<Cat> createCat(){
       List<Cat> cats1 = getCats();
       List<String> names = catName();

       for (int i = 0; i < names.size(); i++) {
           Cat c = new Cat();
           int age = r.nextInt(17)+1;
           int random1 = r.nextInt(100);
           int random2 = r.nextInt(100);
           int random3 = r.nextInt(100);
           c.setName(names.get(i));
           c.setHp(random1);
           c.setMid(((random1+random2+random3)/3));
           c.setMood(random2);
           c.setSatiety(random3);
           c.setAge(age);
           cats1.add(c);
       }
       return cats1;
   }

   public void printCats(List<Cat> cats){
       System.out.printf("\t|%-4s\t|%-7s\t|%-4s\t|%-4s\t|%-4s\t|%-2s\t|%-7s|%n","#","Name","Age","Hp","Mood","Satiety","Mid");
       cats.sort(Comparator.comparing(Cat::getMid));
       Collections.reverse(cats);
       int i = 1;
       for (var str:cats) {
           System.out.printf("\t|%-4s\t|%-7s\t|%-4s\t|%-4s\t|%-4s\t|%-7s\t|%-7s|%n",i,str.getName(),str.getAge(),str.getHp(),str.getMood(),str.getSatiety(),str.getMid());
           i++;
       }
   }

   public void addCat(List<Cat> c){
       String name = addCatName();
       Cat cat = new Cat();
       cat.setName(name);
       int age = addCatAge();
       cat.setAge(age);
       int random1 = r.nextInt(100);
       int random2 = r.nextInt(100);
       int random3 = r.nextInt(100);
       cat.setHp(random1);
       cat.setMid(((random1+random2+random3)/3));
       cat.setMood(random2);
       cat.setSatiety(random3);
       c.add(cat);
       printCats(c);
   }
   private static String addCatName() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Введите имя нового кота");
            try {
                return input.nextLine();
            }
            catch (java.util.InputMismatchException e) {
                input.nextLine();
            }
        }
    }
    private static int addCatAge() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Укажите возраст");
            try {
                return input.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Неверное значение, укажите верный параметр");
                input.nextLine();
            }
        }
    }
}
