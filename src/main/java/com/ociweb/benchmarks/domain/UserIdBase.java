package com.ociweb.benchmarks.domain;

import com.ociweb.pronghorn.columns.FieldsOf16Bits;
import com.ociweb.pronghorn.columns.FieldsOf32Bits;
import com.ociweb.pronghorn.columns.FieldsOf64Bits;
import com.ociweb.pronghorn.columns.FieldsOf8Bits;
import com.ociweb.pronghorn.columns.TypeDef;

public class UserIdBase {
    
    public static enum LongFields implements FieldsOf64Bits {
        FamilyId;
    }    
    public static enum IntFields implements FieldsOf32Bits {
    }
    public static enum ShortFields implements FieldsOf16Bits {        
        MemberId;
    }    
    public static enum ByteFields implements FieldsOf8Bits {
    }
    
    public final static TypeDef<LongFields,       IntFields,       ShortFields,       ByteFields> typeDef = 
                    new TypeDef<LongFields,       IntFields,       ShortFields,       ByteFields>(
                                LongFields.class, IntFields.class, ShortFields.class, ByteFields.class);
    
    

}
