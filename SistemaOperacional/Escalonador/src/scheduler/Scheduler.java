package scheduler;


import java.util.Map;

import process.SOProcess;
import process.SubProcess;
import cpu.CpuManager;

public abstract class Scheduler {
    private CpuManager cpuManager;

    public Scheduler() {
        this.cpuManager = new CpuManager(this);
    }

    public abstract void addSOProcess(SOProcess process);
    public abstract SubProcess execute();
    public abstract void close(SOProcess process);
    public abstract Map<String, Integer> getQuantumTable();
    public CpuManager getCpuManager() {
        return this.cpuManager;
    }
}