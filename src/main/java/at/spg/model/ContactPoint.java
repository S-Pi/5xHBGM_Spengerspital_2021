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

    
}
