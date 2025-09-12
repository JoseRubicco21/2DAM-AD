package menu.actions;
import menu.MenuAction;
import models.Worker;

public class AugmentSalaryMenuAction implements MenuAction{
    private Worker worker;
    
    public AugmentSalaryMenuAction(Worker worker){
        this.worker = worker;
    };

    @Override
    public void execute() {
        if(this.worker.getSalary() < 1000){
            this.worker.setSalary(this.worker.getSalary()*0.15+this.worker.getSalary());
        } else {
            this.worker.setSalary(this.worker.getSalary()*0.12+this.worker.getSalary());
        }

        System.out.println(this.worker.getSalary());
    }

}