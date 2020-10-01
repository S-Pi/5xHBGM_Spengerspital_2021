package at.spg.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class Period {

	public Period() {
	}

	public Period(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	@Column(name="pp_start")
	private LocalDateTime start;
	
	@Column(name="pp_end")
	private LocalDateTime end;
	
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Period period = (Period) o;
		return Objects.equals(start, period.start) &&
				Objects.equals(end, period.end);
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, end);
	}
}
