package Util;

import Model.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
           c.setMood(random2);
           c.setSatiety(random3);
           c.setAge(age);
           cats1.add(c);
       }
       return cats1;
   }

   public void printCats(List<Cat> cats){
       System.out.printf("\t|%-4s\t|%-7s\t|%-4s\t|%-4s\t|%-4s\t|%-2s\t|%-7s|%n","#","Name","Age","Hp","Mood","Satiety","Mid");
       int i = 1;
       for (var str:cats) {
           int mid = str.getHp()+str.getMood()+str.getSatiety()/3;
           System.out.printf("\t|%-4s\t|%-7s\t|%-4s\t|%-4s\t|%-4s\t|%-7s\t|%-7s|%n",i,str.getName(),str.getAge(),str.getHp(),str.getMood(),str.getSatiety(),mid);
           i++;
       }
   }
}
