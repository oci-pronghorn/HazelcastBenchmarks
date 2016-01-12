package com.ociweb.benchmarks.domain;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class UserId implements IdentifiedDataSerializable {

    private int  id;
    private long familyId;
    private short memberId;
    
    public UserId(){
    }
    
    public UserId(int id, long familyId, short memberId) {
        familyId = familyId;
        memberId = memberId;
    }
    
    
    public long getFamilyId() {
        return familyId;
    }
    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }
    public short getMemberId() {
        return memberId;
    }
    public void setMemberId(short memberId) {
        this.memberId = memberId;
    }
    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeInt(id);
        out.writeLong(familyId);
        out.writeShort(memberId);
    }
    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = in.readInt();
        familyId = in.readLong();
        memberId = in.readShort();  
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
