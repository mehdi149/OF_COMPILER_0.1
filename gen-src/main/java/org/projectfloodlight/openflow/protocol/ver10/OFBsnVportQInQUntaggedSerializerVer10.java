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
import org.projectfloodlight.openflow.protocol.OFBsnVportQInQUntagged;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFBsnVportQInQUntaggedSerializerVer10 {

    public final static short BSN_VPORT_Q_IN_Q_UNTAGGED_VAL = (short) 0xffff;

    public static OFBsnVportQInQUntagged readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readShort());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFBsnVportQInQUntagged e) {
        bb.writeShort(toWireValue(e));
    }

    public static void putTo(OFBsnVportQInQUntagged e, PrimitiveSink sink) {
        sink.putShort(toWireValue(e));
    }

    public static OFBsnVportQInQUntagged ofWireValue(short val) {
        switch(val) {
            case BSN_VPORT_Q_IN_Q_UNTAGGED_VAL:
                return OFBsnVportQInQUntagged.BSN_VPORT_Q_IN_Q_UNTAGGED;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFBsnVportQInQUntagged in version 1.0: " + val);
        }
    }


    public static short toWireValue(OFBsnVportQInQUntagged e) {
        switch(e) {
            case BSN_VPORT_Q_IN_Q_UNTAGGED:
                return BSN_VPORT_Q_IN_Q_UNTAGGED_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFBsnVportQInQUntagged in version 1.0: " + e);
        }
    }

}
