package hu.krivan.hazelcast.config;

import com.hazelcast.config.*;
import hu.krivan.hazelcast.SampleMapStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config config(SampleMapStore sampleMapStore) {
        return new Config()
                .setNetworkConfig(
                        new NetworkConfig()
                                .setJoin(
                                        new JoinConfig()
                                                .setMulticastConfig(new MulticastConfig().setEnabled(false))
                                                .setTcpIpConfig(
                                                        new TcpIpConfig()
                                                                .setEnabled(true)
                                                                .addMember("localhost")
                                                )
                                )
                )
                .setProperty("hazelcast.logging.type", "slf4j")
                .addMapConfig(
                        new MapConfig("samples")
                                .setMapStoreConfig(
                                        new MapStoreConfig()
                                                .setImplementation(sampleMapStore)
                                                .setWriteDelaySeconds(0) // write-through
                                )
                );
    }
}
