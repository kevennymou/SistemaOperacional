package strategies;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import process.SOProcess;
import process.SubProcess;
import process.SystemCallType;
import process.SystemOperation;
import scheduler.Scheduler;

public class Lottery extends Scheduler {
	private LinkedList<SOProcess> listOfProcesses;
	private LinkedList<SubProcess> listOfSubProcess;
    public Lottery() {
		this.listOfProcesses = new LinkedList<SOProcess>();
		this.listOfSubProcess = new LinkedList<SubProcess>();
	}

	public void addSOProcess(SOProcess process) {
        this.listOfProcesses.add(process);
    }

    public SubProcess execute() {
    	randomFirstSOProcess();
        if (listOfSubProcess != null) {
        	
        	
        	SubProcess element = this.listOfSubProcess.poll();
        	
        	if (element != null) {
        		return element;
        	}
        }
        return null;
    }

	@Override
	public void close(SOProcess process) {
		this.listOfProcesses = removeProcessById(this.listOfProcesses, process.getId());

        this.listOfSubProcess = removeSubProcessByProcessId(this.listOfSubProcess, process.getId());
	}
	
    private void randomFirstSOProcess() {
    	if (listOfProcesses != null && !listOfProcesses.isEmpty()) {
    		
    		int randomIndex = (int) (Math.random() * this.listOfProcesses.size());
    		SOProcess process = this.listOfProcesses.get(randomIndex);
    		
    		if (process != null) {
    			List<SubProcess> subProcesses = (List<SubProcess>) SystemOperation.systemCall(SystemCallType.READ, process);
    			
    			for (SubProcess value : subProcesses) {
    				this.listOfSubProcess.add(value);
    			}
    			
    			this.listOfProcesses.removeIf(p -> p.getId() == process.getId());
    		}
    	}
    }
	
	private LinkedList<SOProcess> removeProcessById(LinkedList<SOProcess> secondProcessQueue, String id) {
		LinkedList<SOProcess> result = new LinkedList<SOProcess>();
        for (SOProcess p : secondProcessQueue) {
            if (p.getId() != id) {
                result.add(p);
            }
        }

        return result;
    }

    private LinkedList<SubProcess> removeSubProcessByProcessId(LinkedList<SubProcess> secondSubProcessesQueue, String id) {
		LinkedList<SubProcess> result = new LinkedList<SubProcess>();
        for (SubProcess p : secondSubProcessesQueue) {
            if (p.getId() != id) {
                result.add(p);
            }
        }

        return result;
    }

	@Override
	public Map<String, Integer> getQuantumTable() {
		return null;
	}
}