// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template const_serializer.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver15;

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
import org.projectfloodlight.openflow.protocol.OFBsnVrfCounter;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFBsnVrfCounterSerializerVer15 {

    public final static byte BSN_VRF_COUNTER_BYTES_VAL = (byte) 0x0;
    public final static byte BSN_VRF_COUNTER_PACKETS_VAL = (byte) 0x1;

    public static OFBsnVrfCounter readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readByte());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFBsnVrfCounter e) {
        bb.writeByte(toWireValue(e));
    }

    public static void putTo(OFBsnVrfCounter e, PrimitiveSink sink) {
        sink.putByte(toWireValue(e));
    }

    public static OFBsnVrfCounter ofWireValue(byte val) {
        switch(val) {
            case BSN_VRF_COUNTER_BYTES_VAL:
                return OFBsnVrfCounter.BSN_VRF_COUNTER_BYTES;
            case BSN_VRF_COUNTER_PACKETS_VAL:
                return OFBsnVrfCounter.BSN_VRF_COUNTER_PACKETS;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFBsnVrfCounter in version 1.5: " + val);
        }
    }


    public static byte toWireValue(OFBsnVrfCounter e) {
        switch(e) {
            case BSN_VRF_COUNTER_BYTES:
                return BSN_VRF_COUNTER_BYTES_VAL;
            case BSN_VRF_COUNTER_PACKETS:
                return BSN_VRF_COUNTER_PACKETS_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFBsnVrfCounter in version 1.5: " + e);
        }
    }

}