package process;


public class SubProcess {
    private String id;
    private int instructions;
    private SOProcess process;
    private boolean isDone;

    public SubProcess(String id, SOProcess process) {
        this.id = id;
        this.instructions = 7;
        this.process = process;
        this.isDone = false;
    }

    public void finish() {
        this.isDone = true;
    }

    public String getId() {
        return this.id;
    }

    public int getInstructions() {
        return this.instructions;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public SOProcess getProcess() {
        return this.process;
    }
}