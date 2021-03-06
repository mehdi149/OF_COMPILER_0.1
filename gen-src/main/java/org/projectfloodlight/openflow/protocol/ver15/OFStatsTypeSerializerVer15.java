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
import org.projectfloodlight.openflow.protocol.OFStatsType;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFStatsTypeSerializerVer15 {

    public final static short DESC_VAL = (short) 0x0;
    public final static short FLOW_DESC_VAL = (short) 0x1;
    public final static short AGGREGATE_STATS_VAL = (short) 0x2;
    public final static short TABLE_STATS_VAL = (short) 0x3;
    public final static short PORT_STATS_VAL = (short) 0x4;
    public final static short QUEUE_STATS_VAL = (short) 0x5;
    public final static short GROUP_STATS_VAL = (short) 0x6;
    public final static short GROUP_DESC_VAL = (short) 0x7;
    public final static short GROUP_FEATURES_VAL = (short) 0x8;
    public final static short METER_STATS_VAL = (short) 0x9;
    public final static short METER_DESC_VAL = (short) 0xa;
    public final static short METER_FEATURES_VAL = (short) 0xb;
    public final static short TABLE_FEATURES_VAL = (short) 0xc;
    public final static short PORT_DESC_VAL = (short) 0xd;
    public final static short TABLE_DESC_VAL = (short) 0xe;
    public final static short QUEUE_DESC_VAL = (short) 0xf;
    public final static short FLOW_MONITOR_VAL = (short) 0x10;
    public final static short FLOW_STATS_VAL = (short) 0x11;
    public final static short CONTROLLER_STATUS_VAL = (short) 0x12;
    public final static short BUNDLE_FEATURES_VAL = (short) 0x13;
    public final static short EXPERIMENTER_VAL = (short) 0xffff;

    public static OFStatsType readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readShort());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFStatsType e) {
        bb.writeShort(toWireValue(e));
    }

    public static void putTo(OFStatsType e, PrimitiveSink sink) {
        sink.putShort(toWireValue(e));
    }

    public static OFStatsType ofWireValue(short val) {
        switch(val) {
            case DESC_VAL:
                return OFStatsType.DESC;
            case FLOW_DESC_VAL:
                return OFStatsType.FLOW_DESC;
            case AGGREGATE_STATS_VAL:
                return OFStatsType.AGGREGATE_STATS;
            case TABLE_STATS_VAL:
                return OFStatsType.TABLE_STATS;
            case PORT_STATS_VAL:
                return OFStatsType.PORT_STATS;
            case QUEUE_STATS_VAL:
                return OFStatsType.QUEUE_STATS;
            case GROUP_STATS_VAL:
                return OFStatsType.GROUP_STATS;
            case GROUP_DESC_VAL:
                return OFStatsType.GROUP_DESC;
            case GROUP_FEATURES_VAL:
                return OFStatsType.GROUP_FEATURES;
            case METER_STATS_VAL:
                return OFStatsType.METER_STATS;
            case METER_DESC_VAL:
                return OFStatsType.METER_DESC;
            case METER_FEATURES_VAL:
                return OFStatsType.METER_FEATURES;
            case TABLE_FEATURES_VAL:
                return OFStatsType.TABLE_FEATURES;
            case PORT_DESC_VAL:
                return OFStatsType.PORT_DESC;
            case TABLE_DESC_VAL:
                return OFStatsType.TABLE_DESC;
            case QUEUE_DESC_VAL:
                return OFStatsType.QUEUE_DESC;
            case FLOW_MONITOR_VAL:
                return OFStatsType.FLOW_MONITOR;
            case FLOW_STATS_VAL:
                return OFStatsType.FLOW_STATS;
            case CONTROLLER_STATUS_VAL:
                return OFStatsType.CONTROLLER_STATUS;
            case BUNDLE_FEATURES_VAL:
                return OFStatsType.BUNDLE_FEATURES;
            case EXPERIMENTER_VAL:
                return OFStatsType.EXPERIMENTER;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFStatsType in version 1.5: " + val);
        }
    }


    public static short toWireValue(OFStatsType e) {
        switch(e) {
            case DESC:
                return DESC_VAL;
            case FLOW_DESC:
                return FLOW_DESC_VAL;
            case AGGREGATE_STATS:
                return AGGREGATE_STATS_VAL;
            case TABLE_STATS:
                return TABLE_STATS_VAL;
            case PORT_STATS:
                return PORT_STATS_VAL;
            case QUEUE_STATS:
                return QUEUE_STATS_VAL;
            case GROUP_STATS:
                return GROUP_STATS_VAL;
            case GROUP_DESC:
                return GROUP_DESC_VAL;
            case GROUP_FEATURES:
                return GROUP_FEATURES_VAL;
            case METER_STATS:
                return METER_STATS_VAL;
            case METER_DESC:
                return METER_DESC_VAL;
            case METER_FEATURES:
                return METER_FEATURES_VAL;
            case TABLE_FEATURES:
                return TABLE_FEATURES_VAL;
            case PORT_DESC:
                return PORT_DESC_VAL;
            case TABLE_DESC:
                return TABLE_DESC_VAL;
            case QUEUE_DESC:
                return QUEUE_DESC_VAL;
            case FLOW_MONITOR:
                return FLOW_MONITOR_VAL;
            case FLOW_STATS:
                return FLOW_STATS_VAL;
            case CONTROLLER_STATUS:
                return CONTROLLER_STATUS_VAL;
            case BUNDLE_FEATURES:
                return BUNDLE_FEATURES_VAL;
            case EXPERIMENTER:
                return EXPERIMENTER_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFStatsType in version 1.5: " + e);
        }
    }

}
