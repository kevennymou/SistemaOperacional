package process;

import java.util.List;
import memory.MemoryManager;
import scheduler.Scheduler;
import strategies.FCFS;
import strategies.Lottery;
import strategies.Priority;
import strategies.SJF;

public class SystemOperation {
    public static MemoryManager memoryManager = new MemoryManager();
    public static Scheduler scheduler = new Lottery(); //FCFS Lottery Priority SJF
    
    public static SOProcess systemCall(int size, int priority, int timeToExecute) {
    	return new SOProcess(size, priority, timeToExecute);
    }

    public static List<SubProcess> systemCall(SystemCallType typeCall, SOProcess process) {
        if (typeCall == SystemCallType.WRITE && process != null) {
            boolean checkWrite = memoryManager.checkWrite(process);

            if (checkWrite) {
                memoryManager.write(process);
                scheduler.addSOProcess(process);
            } else {
            	System.out.println(" ");
                System.out.println("!!!!!!!!!!!!!!!   P A GE  F A U L T   !!!!!!!!!!!!!!!");
                System.out.println(" ");
            }
        }

        if (typeCall == SystemCallType.READ && process != null) {
            return memoryManager.read(process);
        }

        if (typeCall == SystemCallType.DELETE && process != null) {
            scheduler.close(process);
            memoryManager.delete(process);
        }

        if (typeCall == SystemCallType.STOP && process != null) {
            scheduler.close(process);
        }

        return null;
    }
}