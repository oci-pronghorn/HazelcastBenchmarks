package com.ociweb.benchmarks.cluster;


import com.hazelcast.config.Config;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.ociweb.benchmarks.cluster.factory.ExampleFlyweightFactory;
import com.ociweb.benchmarks.cluster.factory.ExampleObjectFactory;
import com.ociweb.benchmarks.domain.UserIdConstants;

public class StartHZClusterMember {

    HazelcastInstance hazelcastInstance;
    
    static StartHZClusterMember instance;
    
    private StartHZClusterMember(int id, DataSerializableFactory factory) {
        
        System.setProperty("hazelcast.slow.operation.detector.enabled", "false");
//        System.setProperty("hazelcast.slow.operation.detector.log.purge.interval.seconds", "5");
//        System.setProperty("hazelcast.slow.operation.detector.log.retention.seconds", "10");
//        System.setProperty("hazelcast.slow.operation.detector.stacktrace.logging.enabled", "true");
        
   //     System.setProperty("hazelcast.operation.thread.count","2");
  //      System.setProperty("hazelcast.operation.generic.thread.count","2");
  //      System.setProperty("hazelcast.event.queue.capacity", "100");
  //      System.setProperty("hazelcast.event.thread.count",5);
//        System.setProperty("hazelcast.socket.receive.buffer.size","64");
//        System.setProperty("hazelcast.socket.send.buffer.size","64");
        
        Config config = new Config();
        
        SerializationConfig serializationConfig = config.getSerializationConfig();
        serializationConfig.addDataSerializableFactory(id, factory);    
   //     serializationConfig.setAllowUnsafe(true); //speeds the IO serialization
   //     serializationConfig.setUseNativeByteOrder(true); //we assume everyone is using intel.
   //     serializationConfig.setEnableCompression(true); //breaks but not sure why
        
        
        
        
//        MapConfig mapConfig = config.getMapConfig("object constant id");
//        mapConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
//        
//        mapConfig.setAsyncBackupCount(1);
//        mapConfig.setBackupCount(1);
        
        hazelcastInstance = Hazelcast.newHazelcastInstance(config );
        
        
    }
    
    
    public static void main(String[] args) {
       
        boolean arrayBacked = true;
        
        
        startClusterMember(arrayBacked);

        System.out.println("instance is started");
    }


    public static HazelcastInstance startClusterMember(boolean arrayBacked) {
        
        DataSerializableFactory factory;
        
        if (arrayBacked) {
            factory = new ExampleFlyweightFactory();            
        } else {
            factory = new ExampleObjectFactory();
        }
        
        instance = new StartHZClusterMember(UserIdConstants.FACTORY_ID, factory);
        
        
        return instance.hazelcastInstance;
    }


    


}
