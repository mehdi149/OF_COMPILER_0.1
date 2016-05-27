// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template const_serializer.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver14;

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
import org.projectfloodlight.openflow.protocol.OFMeter;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFMeterSerializerVer14 {

    public final static int MAX_VAL = (int) 0xffff0000;
    public final static int SLOWPATH_VAL = (int) 0xfffffffd;
    public final static int CONTROLLER_VAL = (int) 0xfffffffe;
    public final static int ALL_VAL = (int) 0xffffffff;

    public static OFMeter readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readInt());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFMeter e) {
        bb.writeInt(toWireValue(e));
    }

    public static void putTo(OFMeter e, PrimitiveSink sink) {
        sink.putInt(toWireValue(e));
    }

    public static OFMeter ofWireValue(int val) {
        switch(val) {
            case MAX_VAL:
                return OFMeter.MAX;
            case SLOWPATH_VAL:
                return OFMeter.SLOWPATH;
            case CONTROLLER_VAL:
                return OFMeter.CONTROLLER;
            case ALL_VAL:
                return OFMeter.ALL;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFMeter in version 1.4: " + val);
        }
    }


    public static int toWireValue(OFMeter e) {
        switch(e) {
            case MAX:
                return MAX_VAL;
            case SLOWPATH:
                return SLOWPATH_VAL;
            case CONTROLLER:
                return CONTROLLER_VAL;
            case ALL:
                return ALL_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFMeter in version 1.4: " + e);
        }
    }

}
