package br.com.maekawa.leaderelection.leadership;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class Leadership {

    private HazelcastInstance hazelcastInstance;

    private Boolean leader = false;

    public Leadership(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Scheduled(fixedDelay = 1000L)
    public void amILeader() throws Exception {
        ILock lock = hazelcastInstance.getLock("leader");
        lock.tryLock(100, MILLISECONDS,2, SECONDS);
        if(lock.isLocked()) {
            System.out.println("I'm Leader");
            Thread.sleep(5000);
            lock.unlock();
            System.out.println("unlocked");
        }else {
            System.out.println("Not locked");
        }
    }

}
