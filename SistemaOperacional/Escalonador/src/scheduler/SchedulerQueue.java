package scheduler;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import process.SOProcess;
import process.SubProcess;

public abstract class SchedulerQueue extends Scheduler {
    protected PriorityQueue<SOProcess> soProcessQueue;
    protected LinkedList<SubProcess> soSubProcessQueue;

    public SchedulerQueue(Comparator comparatorSOProcess) {
        super();
        this.soProcessQueue = new PriorityQueue(comparatorSOProcess);
        this.soSubProcessQueue = new LinkedList();
    }

    public void close(SOProcess process) {
        this.soProcessQueue = removeProcessById(this.soProcessQueue, process.getId());

        this.soSubProcessQueue = removeSubProcessByProcessId(this.soSubProcessQueue, process.getId());
    }

    @Override
	public Map<String, Integer> getQuantumTable() {
		return null;
	}
    
    private PriorityQueue<SOProcess> removeProcessById(PriorityQueue<SOProcess> secondSOProcessQueue, String id) {
    	Comparator<SOProcess> comparator = new Comparator<SOProcess>() {
			@Override
			public int compare(SOProcess process1, SOProcess process2) {
				return 1;
			}
			
		};
		PriorityQueue<SOProcess> result = new PriorityQueue<SOProcess>(comparator);
        for (SOProcess p : secondSOProcessQueue) {
            if (p.getId() != id) {
                result.add(p);
            }
        }

        return result;
    }

    private LinkedList<SubProcess> removeSubProcessByProcessId(LinkedList<SubProcess> secondSOSubProcessQueue, String id) {
		LinkedList<SubProcess> result = new LinkedList<SubProcess>();
        for (SubProcess p : secondSOSubProcessQueue) {
            if (p.getId() != id) {
                result.add(p);
            }
        }

        return result;
    }
    
}