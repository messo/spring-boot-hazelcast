package hu.krivan.hazelcast;

import hu.krivan.hazelcast.model.Sample;
import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Autowired
    SampleService service;

    @Autowired
    SampleMapStore store;


    @Test
    public void contextLoads() {
    }

    @Test
    public void sampleTest() {
        // given we have samples
        service.save(new Sample(1L, 5L));
        service.save(new Sample(2L, 7L));

        // when
        long sum = service.sum();

        // then
        // verify hazelcast is working
        Assert.assertEquals(5 + 7, sum);
        // verify persistence layer is working
        Assert.assertThat(store.loadAllKeys(), IsIterableWithSize.iterableWithSize(2));
    }
}
