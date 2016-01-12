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

    static HazelcastInstance hazelcastInstance;
    
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();        
        SerializationConfig serializationConfig = clientConfig.getSerializationConfig();
        
        serializationConfig.addDataSerializableFactory(UserIdConstants.FACTORY_ID, new ExampleObjectFactory());
        
      //  serializationConfig.setAllowUnsafe(true); //speeds the IO serialization
     //   serializationConfig.setUseNativeByteOrder(true); //we assume everyone is using intel.
    //    serializationConfig.setEnableCompression(true); //not sure this is enabled unless the server supports it as well.
                
        clientConfig.addAddress("172.17.42.1"); //My docker address, that does not change no matter where I go.
        
        hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
        
        UserId[] users = buildFakeUserSet();
        
        ISet<UserId> isOnLineSet = hazelcastInstance.getSet(UserIdConstants.ON_LINE_SET_NAME);
        
        System.out.println("starting to run test");
        long startTime = System.currentTimeMillis();
        
        int i = users.length;
        while (--i >= 0) {            
            isOnLineSet.add(users[i]);
        }
        
        long duration = System.currentTimeMillis()-startTime;
        
        System.out.println("duration "+duration);
        System.out.println("msg/sec  "+((1000*users.length)/duration ));
        
        //need online and offline
        
        
        
        
        
        
        
        
        
    }

    private static UserId[] buildFakeUserSet() {
        //My Fake Users
        int userCount = 2000_000;
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
