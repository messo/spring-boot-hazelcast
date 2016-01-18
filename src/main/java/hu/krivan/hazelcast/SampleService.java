package hu.krivan.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.aggregation.Aggregations;
import com.hazelcast.mapreduce.aggregation.Supplier;
import hu.krivan.hazelcast.model.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleService {

    @Autowired
    private HazelcastInstance hazelcastInstance;


    @SuppressWarnings("unchecked")
    private IMap<Long, Sample> getSampleMap() {
        return hazelcastInstance.getMap("samples");
    }


    public void save(Sample sample) {
        getSampleMap().put(sample.getId(), sample);
    }

    public Long sum() {
        return getSampleMap().aggregate(Supplier.all(Sample::getValue), Aggregations.longSum());
    }
}
