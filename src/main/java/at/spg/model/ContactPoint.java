package at.spg.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContactPoint extends Element{

    public ContactPoint() {
    }

    public ContactPoint(system systemEnum, String value, use useEnum, int rank, Period period) {
        this.systemEnum = systemEnum;
        this.value = value;
        this.useEnum = useEnum;
        this.rank = rank;
        this.period = period;
    }

    public enum system{
        phone, fax, email, pager, url, sms, other
    }

    public enum use{
        home, work, temp, old, mobile
    }

    @Enumerated(EnumType.STRING)
    private system systemEnum;
    @Column(name="cp_value")
    private String value;
    @Enumerated(EnumType.STRING)
    private use useEnum;
    @Column(name="cp_rank")
    private int rank;
    @Embedded
    private Period period;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPoint that = (ContactPoint) o;
        return rank == that.rank &&
                systemEnum == that.systemEnum &&
                Objects.equals(value, that.value) &&
                useEnum == that.useEnum &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemEnum, value, useEnum, rank, period);
    }
}
