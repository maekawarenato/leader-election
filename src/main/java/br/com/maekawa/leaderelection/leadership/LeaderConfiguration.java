package br.com.maekawa.leaderelection.leadership;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeaderConfiguration {

    private Members members;

    public LeaderConfiguration(Members members) {
        this.members = members;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config){
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public Config hazelcastConfig(){
        Config config = new Config();
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20); //ports from 5701 to 5721
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);

        //Adding cluster members
        members.getUrl().forEach(s ->
                join.getTcpIpConfig()
                        .addMember(s).setEnabled(true)
        );
        config.getNativeMemoryConfig();

        return config;
    }

}
