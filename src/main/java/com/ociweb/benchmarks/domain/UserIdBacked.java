package com.ociweb.benchmarks.domain;

import com.ociweb.pronghorn.columns.BackingData;
import static com.ociweb.benchmarks.domain.UserIdBase.ShortFields.*;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import static com.ociweb.benchmarks.domain.UserIdBase.LongFields.*;

public class UserIdBacked extends UserIdBase implements IdentifiedDataSerializable{
    
    private int recordIdx;
    private final BackingData<UserIdBase> backing;
    
    public UserIdBacked(BackingData<UserIdBase> backing) {
        this.backing = backing;
    }

    public void selectRecord(int recordIdx) {
        this.recordIdx = recordIdx;
    }
    
    public final void setMemberId(short value) {
        BackingData.setShort(MemberId, value, recordIdx, backing);      
    }
    
    public final short getMemberId() {
        return BackingData.getShort(MemberId, recordIdx, backing);      
    }
    
    public final void setFamilyId(long value) {
        BackingData.setLong(FamilyId, value, recordIdx, backing);
    }
    
    public final long getFamilyId() {
        return BackingData.getLong(FamilyId, recordIdx, backing);  
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        
        out.writeInt(recordIdx);
        out.writeLong(BackingData.getLong(FamilyId, recordIdx, backing));
        out.writeShort(BackingData.getShort(MemberId, recordIdx, backing));
        //backing.write(recordIdx, 1, out);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        recordIdx = in.readInt(); 
        BackingData.setLong(FamilyId, in.readLong(), recordIdx, backing);
        BackingData.setShort(MemberId, in.readShort(), recordIdx, backing);   
        //backing.read(recordIdx, 1, in);
    }

    @Override
    public int getFactoryId() {
        return UserIdConstants.FACTORY_ID;
    }
    @Override
    public int getId() {
        return UserIdConstants.USERID_ID;
    }
    
}
