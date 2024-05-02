package cpu;

import process.SubProcess;

public class Core {
	private int id;
	private int numberOfInstructionsPerClock;
	private SubProcess subProcess;

	public Core(int id, int numberOfInstructionsPerClock) {
		this.id = id;
		this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
		this.subProcess = null;
	}

	private void finish() {
		if (this.subProcess != null && this.subProcess.getProcess().getInstructions() > this.subProcess.getProcess().getInstructionsExecuted()) {
			this.subProcess.finish();
			this.subProcess.getProcess().setInstructionsExecuted(this.subProcess.getInstructions());
			this.subProcess.getProcess().checkIfSubProcessIsDone();
			this.subProcess = null;
		}
	}
	
	public void run() {
		if (this.subProcess != null) {
			System.out.println(" " + this.subProcess.getId());
			finish();
		}
	}

	public int getId() {
		return this.id;
	}

	public SubProcess getSubProcess() {
		return this.subProcess;
	}

	public void setSubProcess(SubProcess subProcess) {
		this.subProcess = subProcess;
	}
}