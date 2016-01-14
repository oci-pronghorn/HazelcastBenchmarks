package com.ociweb.benchmarks;

import java.util.Random;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ISet;
import com.ociweb.benchmarks.cluster.factory.ExampleObjectFactory;
import com.ociweb.benchmarks.domain.UserId;
import com.ociweb.benchmarks.domain.UserIdConstants;

public class StartHZClient {

    private HazelcastInstance hazelcastInstance;
    private UserId[] users;
    
    public static void main(String[] args) {
        StartHZClient instance = new StartHZClient();
        
        instance.setup(200_000, null);
        instance.run();
        instance.shutdown();
                
    }

    
    public void setup(int testSize, String clusterAddr) {
        users = buildFakeUserSet(testSize);
        
        ClientConfig clientConfig = new ClientConfig();        
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        
        serializationConfig.addDataSerializableFactory(UserIdConstants.FACTORY_ID, new ExampleObjectFactory());
        
        //  serializationConfig.setAllowUnsafe(true); //speeds the IO serialization
        //   serializationConfig.setUseNativeByteOrder(true); //we assume everyone is using intel.
        //    serializationConfig.setEnableCompression(true); //not sure this is enabled unless the server supports it as well.
       
        if (null != clusterAddr) {
            clientConfig.getNetworkConfig().addAddress(clusterAddr);
        }
        
        //     clientConfig.addAddress(); //My docker address, that does not change no matter where I go.
        
        hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
    }
    
    public void run() {
        
        
        ISet<UserId> isOnLineSet = hazelcastInstance.getSet(UserIdConstants.ON_LINE_SET_NAME);
        
        UserId[] localUser = users;
        int i = localUser.length;
        while (--i >= 0) {            
            isOnLineSet.add(localUser[i]);
        }
        
     
    }
    
    public void shutdown() {
        hazelcastInstance.shutdown();
    }

    private static UserId[] buildFakeUserSet(int userCount) {
        //My Fake Users
        int seed = 123;
        Random r = new Random(seed);
        UserId[] users = new UserId[userCount];
        int j = userCount;
        long familyId = r.nextLong();
        while (--j>=0) {
            if (0==(0x3)) {
                familyId = r.nextLong();
            }
            users[j] = new UserId(j, familyId, (short)r.nextInt(2000));
        }
        return users;
    }
    
    
}
