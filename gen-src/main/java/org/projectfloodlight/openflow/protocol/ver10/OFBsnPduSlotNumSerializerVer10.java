// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template const_serializer.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver10;

import org.projectfloodlight.openflow.protocol.*;
import org.projectfloodlight.openflow.protocol.action.*;
import org.projectfloodlight.openflow.protocol.actionid.*;
import org.projectfloodlight.openflow.protocol.bsntlv.*;
import org.projectfloodlight.openflow.protocol.errormsg.*;
import org.projectfloodlight.openflow.protocol.meterband.*;
import org.projectfloodlight.openflow.protocol.instruction.*;
import org.projectfloodlight.openflow.protocol.instructionid.*;
import org.projectfloodlight.openflow.protocol.match.*;
import org.projectfloodlight.openflow.protocol.stat.*;
import org.projectfloodlight.openflow.protocol.oxm.*;
import org.projectfloodlight.openflow.protocol.oxs.*;
import org.projectfloodlight.openflow.protocol.queueprop.*;
import org.projectfloodlight.openflow.types.*;
import org.projectfloodlight.openflow.util.*;
import org.projectfloodlight.openflow.exceptions.*;
import org.projectfloodlight.openflow.protocol.OFBsnPduSlotNum;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFBsnPduSlotNumSerializerVer10 {

    public final static byte PDU_SLOT_NUM_ANY_VAL = (byte) 0xff;

    public static OFBsnPduSlotNum readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readByte());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFBsnPduSlotNum e) {
        bb.writeByte(toWireValue(e));
    }

    public static void putTo(OFBsnPduSlotNum e, PrimitiveSink sink) {
        sink.putByte(toWireValue(e));
    }

    public static OFBsnPduSlotNum ofWireValue(byte val) {
        switch(val) {
            case PDU_SLOT_NUM_ANY_VAL:
                return OFBsnPduSlotNum.PDU_SLOT_NUM_ANY;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFBsnPduSlotNum in version 1.0: " + val);
        }
    }


    public static byte toWireValue(OFBsnPduSlotNum e) {
        switch(e) {
            case PDU_SLOT_NUM_ANY:
                return PDU_SLOT_NUM_ANY_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFBsnPduSlotNum in version 1.0: " + e);
        }
    }

}