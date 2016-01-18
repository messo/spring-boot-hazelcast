package hu.krivan.hazelcast;

import com.hazelcast.core.MapStore;
import hu.krivan.hazelcast.model.Sample;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class SampleMapStore implements MapStore<Long, Sample> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void store(Long key, Sample value) {
        em.persist(value);
    }

    @Override
    public void storeAll(Map<Long, Sample> map) {
        map.forEach(this::store);
    }

    @Override
    public void delete(Long key) {
        em.remove(em.getReference(Sample.class, key));
    }

    @Override
    public void deleteAll(Collection<Long> keys) {
        em.createNamedQuery(Sample.DELETE_BY_KEYS)
                .setParameter("ids", keys)
                .executeUpdate();
    }

    @Override
    public Sample load(Long key) {
        return em.find(Sample.class, key);
    }

    @Override
    public Map<Long, Sample> loadAll(Collection<Long> keys) {
        return em.createNamedQuery(Sample.FIND_BY_KEYS, Sample.class)
                .setParameter("ids", keys)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(Sample::getId, Function.identity()));
    }

    @Override
    public Iterable<Long> loadAllKeys() {
        return em.createNamedQuery(Sample.FIND_ALL_KEYS, Long.class).getResultList();
    }
}
