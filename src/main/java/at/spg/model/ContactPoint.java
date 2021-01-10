package at.spg.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContactPoint extends Element{

    public ContactPoint() {
    }

    public ContactPoint(SystemCode systemEnum, String value, UseCode useEnum, int rank, Period period) {
        this.systemEnum = systemEnum;
        this.value = value;
        this.useEnum = useEnum;
        this.rank = rank;
        this.period = period;
    }

    public enum SystemCode{
        phone, fax, email, pager, url, sms, other
    }

    public enum UseCode{
        home, work, temp, old, mobile
    }

    @Enumerated(EnumType.STRING)
    private SystemCode systemEnum;
    
    @Column(name="cp_value")
    private String value;
    @Enumerated(EnumType.STRING)
    private UseCode useEnum;
    @Column(name="cp_rank")
    private int rank;
    @Embedded
    private Period period;
	public SystemCode getSystemEnum() {
		return systemEnum;
	}

	public void setSystemEnum(SystemCode systemEnum) {
		this.systemEnum = systemEnum;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UseCode getUseEnum() {
		return useEnum;
	}

	public void setUseEnum(UseCode useEnum) {
		this.useEnum = useEnum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

    
}
