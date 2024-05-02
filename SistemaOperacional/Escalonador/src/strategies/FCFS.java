package strategies;

import java.util.Comparator;
import java.util.List;

import process.SystemOperation;
import scheduler.SchedulerQueue;
import process.SOProcess;
import process.SubProcess;
import process.SystemCallType;

public class FCFS extends SchedulerQueue {
    public FCFS() {
		super(new Comparator<SOProcess>() {
			@Override
			public int compare(SOProcess process1, SOProcess process2) {
				return -1;
			}
			
		});
	}

	public void addSOProcess(SOProcess process) {
        this.soProcessQueue.add(process);

        List<SubProcess> subProcesses = SystemOperation.systemCall(SystemCallType.READ, process);

        for (SubProcess sp : subProcesses) {
            this.soSubProcessQueue.add(sp);
        }
    }

    public SubProcess execute() {
    	if (this.soProcessQueue != null && this.soSubProcessQueue != null) {
    		
    		this.soProcessQueue.poll();
    		SubProcess element = this.soSubProcessQueue.poll();
    		
    		if (element != null) {
    			return element;
    		} 
    	}
    	return null;
    }
}