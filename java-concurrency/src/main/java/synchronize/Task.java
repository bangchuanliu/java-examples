package synchronize;

public class Task {
    
    private int taskId;
    
    public Task(int taskId) {
        this.taskId = taskId;
    }
    
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " execute task " + taskId);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
