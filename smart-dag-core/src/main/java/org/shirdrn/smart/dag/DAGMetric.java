package org.shirdrn.smart.dag;

import java.util.Date;
import java.util.List;

@InterfaceAudience.Private
public abstract class DAGMetric extends AbstractNameableComponent<DAGMetric> {

	protected Date startDate;
	protected Date finishDate;
	protected long timeTaken;
	protected Status status = Status.INITIALIZATION;
	protected List<Throwable> causes;
	
	public DAGMetric() {
		super();
	}
	
	public DAGMetric(String name) {
		super(name);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Throwable> getCauses() {
		return causes;
	}

	public void setCauses(List<Throwable> causes) {
		this.causes = causes;
	}

	public enum Status {
		INITIALIZATION, 
		SUCCESS, 
		FAILURE
	}
}
