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
import org.projectfloodlight.openflow.protocol.OFOxsClass;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFOxsClassSerializerVer15 {

    public final static short OPENFLOW_BASIC_VAL = (short) 0x8002;
    public final static short EXPERIMENTER_VAL = (short) 0xffff;

    public static OFOxsClass readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readShort());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFOxsClass e) {
        bb.writeShort(toWireValue(e));
    }

    public static void putTo(OFOxsClass e, PrimitiveSink sink) {
        sink.putShort(toWireValue(e));
    }

    public static OFOxsClass ofWireValue(short val) {
        switch(val) {
            case OPENFLOW_BASIC_VAL:
                return OFOxsClass.OPENFLOW_BASIC;
            case EXPERIMENTER_VAL:
                return OFOxsClass.EXPERIMENTER;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFOxsClass in version 1.5: " + val);
        }
    }


    public static short toWireValue(OFOxsClass e) {
        switch(e) {
            case OPENFLOW_BASIC:
                return OPENFLOW_BASIC_VAL;
            case EXPERIMENTER:
                return EXPERIMENTER_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFOxsClass in version 1.5: " + e);
        }
    }

}
