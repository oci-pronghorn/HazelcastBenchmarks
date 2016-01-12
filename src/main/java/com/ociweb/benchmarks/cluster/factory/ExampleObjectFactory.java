package com.ociweb.benchmarks.cluster.factory;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.ociweb.benchmarks.domain.UserId;
import com.ociweb.benchmarks.domain.UserIdConstants;

public class ExampleObjectFactory implements DataSerializableFactory {

    @Override
    public IdentifiedDataSerializable create(int typeId) {
        switch(typeId) {
            case UserIdConstants.USERID_ID:
                return new UserId();
            default:
                throw new UnsupportedOperationException();
        }
    }

}
