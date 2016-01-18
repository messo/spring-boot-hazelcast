package hu.krivan.hazelcast.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = Sample.FIND_ALL_KEYS, query = "SELECT sample.id FROM Sample sample"),
        @NamedQuery(name = Sample.FIND_BY_KEYS, query = "SELECT sample FROM Sample sample WHERE sample.id IN :ids"),
        @NamedQuery(name = Sample.DELETE_BY_KEYS, query = "DELETE FROM Sample sample WHERE sample.id in :ids")
})
public class Sample implements Serializable {

    public static final String FIND_ALL_KEYS = "Sample.findAllKeys";
    public static final String FIND_BY_KEYS = "Sample.findByKeys";
    public static final String DELETE_BY_KEYS = "Sample.deleteByKeys";

    @Id
    private Long id;
    private long value;

    public Sample() {
    }

    public Sample(long id, long value) {
        this.id = id;
        this.value = value;
    }


    public Long getId() {
        return id;
    }

    public long getValue() {
        return value;
    }
}
