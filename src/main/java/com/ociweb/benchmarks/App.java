package com.ociweb.benchmarks;

import com.hazelcast.core.HazelcastInstance;
import com.ociweb.benchmarks.cluster.StartHZClusterMember;

public class App {

    public static void main(String[] args) {

        boolean arrayBacked = false;
        int clusterMemberCount = 3;
        int testSize = 200_000;

        
        HazelcastInstance[] clusterMembers = new HazelcastInstance[clusterMemberCount];
        
        int i = clusterMemberCount; 
        while (--i >= 0) {        
            clusterMembers[i] = StartHZClusterMember.startClusterMember(arrayBacked);
        }
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("////Cluster is up and ready for testing//////////");
        System.out.println("/////////////////////////////////////////////////");
        
        String clusterAddr = clusterMembers[0].getCluster().getLocalMember().getAddress().getHost();
        long duration = -1;
        boolean standardDriver = true;
        
        if (standardDriver) {
            StartHZClient testInstance = new StartHZClient();
            
            testInstance.setup(testSize, clusterAddr);
            
            long startTime = System.currentTimeMillis();        
            
            testInstance.run();
            
            duration = System.currentTimeMillis()-startTime;
                    
            testInstance.shutdown();
        } else {
            
            
            //put our driver here.
            
            
        }

        
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("////The testing has now finished/////////////////");
        System.out.println("/////////////////////////////////////////////////");
        
       
        //ready to shut down the cluster, do it cleanly
        i = clusterMemberCount;
        while (--i >= 0) {
            clusterMembers[i].shutdown();
        }
        
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("////Results//////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("duration "+duration);
        System.out.println("msg/sec  "+((1000*testSize)/duration ));
        
        
        
        
        
    }

}
