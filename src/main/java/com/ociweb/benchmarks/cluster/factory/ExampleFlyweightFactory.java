package com.ociweb.benchmarks.cluster.factory;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.ociweb.benchmarks.domain.UserIdBacked;
import com.ociweb.benchmarks.domain.UserIdBase;
import com.ociweb.benchmarks.domain.UserIdConstants;
import com.ociweb.pronghorn.columns.BackingData;

public class ExampleFlyweightFactory implements DataSerializableFactory {

    public final static int maxUsers = 10_000_000;
    private final static BackingData<UserIdBase> backing = new BackingData<UserIdBase>(UserIdBase.typeDef, maxUsers);
    private final UserIdBacked userFlyweight = new UserIdBacked(backing);
    
    @Override
    public IdentifiedDataSerializable create(int typeId) {
        switch(typeId) {
            case UserIdConstants.USERID_ID:
                return userFlyweight; //TODO: where do we set the messageId?????
            default:
                throw new UnsupportedOperationException();
        }
    }

}
