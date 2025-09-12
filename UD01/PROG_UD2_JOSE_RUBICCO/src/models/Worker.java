package models;

public class Worker extends Person {
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker [salary=" + salary + "]";
    }

    public Worker(){

    }

    public Worker(String name, int age, int height, double salary) {
        super(name, age, height);
        this.salary = salary;
    }

        

}
