package lambda;

import java.util.Comparator;
import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private double height;
    private double weight;
    private Person father;
    private Person mother;
    private Gender gender;

    public static Person of(String name, int age, double height, double weight, Gender gender) {
        return new Person(name, age, height, weight, gender);
    }
    
    private Person(String name, int age, double height, double weight, Gender gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    enum Gender {
        MALE, FEMALE
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int compare(Person other, Comparator<Person> comparator) {
        return comparator.compare(this, other);
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public boolean isKid() {
        return age <= 12;
    }
    
    public boolean isChild(Person other) {
        return this.father.equals(other) || this.mother.equals(other);
    }

    public boolean isParent(Person other) {
        return this.equals(other.getFather()) || this.equals(other.getMother());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.height, height) == 0 &&
                Double.compare(person.weight, weight) == 0 &&
                Objects.equals(name, person.name) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height, weight, gender);
    }
}
