package com.ociweb.benchmarks;

import com.ociweb.benchmarks.domain.UserId;
import com.ociweb.hazelcast.HZDataInput;
import com.ociweb.hazelcast.HazelcastClientConfig;
import com.ociweb.hazelcast.stage.HazelcastClient;
import com.ociweb.hazelcast.stage.ResponseCallBack;

public class PronghornHZClient {

    private HazelcastClient client;
    private ResponseCallBack callback;
    private UserId[] users;
    
    public void setup(int testSize, String clusterAddr) {
        users = ClassicHZClient.buildFakeUserSet(testSize);
        callback = new ResponseCallBack() {
            
            @Override
            public void send(int correlationId, short type, short flags, int partitionId, HZDataInput reader) {
                // TODO Auto-generated method stub
                
            }
        };
        HazelcastClientConfig config = new HazelcastClientConfig();
        client = new HazelcastClient(config, callback);
        
    }
    
    public void run() {
    
        //TODO: get the token for my new set 
        
        UserId[] localUser = users;
        int i = localUser.length;
        while (--i >= 0) {            
            
            UserId user = localUser[i];
            
            //TODO: add this user to the set.
            
            
        }
        
    }
    
    public void shutdown() {
        
    }
    
}
