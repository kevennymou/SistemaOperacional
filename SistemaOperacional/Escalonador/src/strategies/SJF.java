package strategies;

import java.util.Comparator;
import java.util.List;

import process.SOProcess;
import process.SubProcess;
import process.SystemCallType;
import process.SystemOperation;
import scheduler.SchedulerQueue;

public class SJF extends SchedulerQueue {
    public SJF() {
		super(new Comparator<SOProcess>() {
			@Override
			public int compare(SOProcess process1, SOProcess process2) {
				return process1.getTimeToExecute() - process2.getTimeToExecute();
			}
			
		});
	}

    public void addSOProcess(SOProcess process) {
        this.soProcessQueue.add(process);
    }

    public SubProcess execute() {
    	orderToExecute();
        
        if (soSubProcessQueue != null) {
        	
        	SubProcess element = this.soSubProcessQueue.poll();
        	
        	if (element != null) {
        		return element;
        	}         
        }
        return null;
    }

    private void orderToExecute() {
        if (soProcessQueue != null) {
        	
        	SOProcess process = this.soProcessQueue.poll();
        	
        	if (process != null) {
        		List<SubProcess> subProcesses = (List<SubProcess>) SystemOperation.systemCall(SystemCallType.READ, process);
        		
        		for (SubProcess value : subProcesses) {
        			this.soSubProcessQueue.add(value);
        		}
        	}
        }
    }
}