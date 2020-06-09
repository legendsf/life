package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_03.me;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

public class DogCatQueue {
    @Data
    @AllArgsConstructor
    public static class Pet{
        String type;
    }

    public static class Dog extends Pet{

        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet{
        public Cat(){
            super("cat");
        }
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class PetEnter{
        Pet pet;
        long count;
    }
    @Data
    public static class CatDogQueue{
       Queue<PetEnter> cat=new LinkedList<>();
       Queue<PetEnter> dog=new LinkedList<>();
       long count;

       public void add(Pet pet){
          if(pet.type.equals("dog")){
              dog.add(new PetEnter(new Dog(),count++));
          }else if(pet.type.equals("cat")){
              cat.add(new PetEnter(new Cat(),count++));
          }else {
              throw new RuntimeException("error not cat or dog");
          }
       }

       public Pet pollAll(){
           if(!this.dog.isEmpty()&&!this.cat.isEmpty()){
              if(this.dog.peek().count<this.cat.peek().count){
                  return this.dog.poll().pet;
              }else {
                  return this.cat.poll().pet;
              }
           }else if(!this.dog.isEmpty()){
              return this.dog.poll().pet;
           }else if(!this.cat.isEmpty()){
               return this.cat.poll().pet;
           }else {
               return null;
           }
       }

       public Dog pollDog(){
           if(!this.dog.isEmpty()){
               return (Dog)this.dog.poll().pet;
           }else {
               return null;
           }
       }

       public Cat pollCat(){
           if(!this.cat.isEmpty()){
               return (Cat)this.cat.poll().pet;
           }
           return null;
       }
       public boolean isDogEmpty(){
           return this.dog.isEmpty();
       }

       public boolean isCatEmpty(){
           return this.cat.isEmpty();
       }
       public boolean isAllEmpty(){
           return  isCatEmpty()&&isDogEmpty();
       }

    }

    public static void main(String[] args) {
        CatDogQueue test = new CatDogQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogEmpty()) {
            System.out.println(test.pollDog().type);
        }
        while (!test.isAllEmpty()) {
            System.out.println(test.pollAll().type);
        }
    }
}
