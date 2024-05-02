package strategies;

import java.util.Comparator;
import java.util.List;

import process.SystemOperation;
import scheduler.SchedulerQueue;
import process.SOProcess;
import process.SubProcess;
import process.SystemCallType;



public class Priority extends SchedulerQueue {
	public Priority() {
		super(new Comparator<SOProcess>() {
			@Override
			public int compare(SOProcess process1, SOProcess process2) {
				return process2.getPriority() - process1.getPriority();
			}
			
		});
	}
    public void addSOProcess(SOProcess process) {
        this.soProcessQueue.add(process);
    }

    public SubProcess execute() {
    	orderByPriority();
        
        if (soSubProcessQueue != null) {
        	
        	SubProcess element = this.soSubProcessQueue.poll();
        	
        	if (element != null) {
        		return element;
        	}         
        }
        return null;
    }

    private void orderByPriority() {
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