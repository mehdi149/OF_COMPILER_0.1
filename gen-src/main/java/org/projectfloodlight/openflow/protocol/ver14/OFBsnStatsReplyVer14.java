// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_virtual_class.java
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
import io.netty.buffer.ByteBuf;

abstract class OFBsnStatsReplyVer14 {
    // version: 1.4
    final static byte WIRE_VERSION = 5;
    final static int MINIMUM_LENGTH = 24;


    public final static OFBsnStatsReplyVer14.Reader READER = new Reader();

    static class Reader implements OFMessageReader<OFBsnStatsReply> {
        @Override
        public OFBsnStatsReply readFrom(ByteBuf bb) throws OFParseError {
            if(bb.readableBytes() < MINIMUM_LENGTH)
                return null;
            int start = bb.readerIndex();
            // fixed value property version == 5
            byte version = bb.readByte();
            if(version != (byte) 0x5)
                throw new OFParseError("Wrong version: Expected=OFVersion.OF_14(5), got="+version);
            // fixed value property type == 19
            byte type = bb.readByte();
            if(type != (byte) 0x13)
                throw new OFParseError("Wrong type: Expected=OFType.STATS_REPLY(19), got="+type);
            int length = U16.f(bb.readShort());
            if(length < MINIMUM_LENGTH)
                throw new OFParseError("Wrong length: Expected to be >= " + MINIMUM_LENGTH + ", was: " + length);
            U32.f(bb.readInt());
            // fixed value property statsType == 65535
            short statsType = bb.readShort();
            if(statsType != (short) 0xffff)
                throw new OFParseError("Wrong statsType: Expected=OFStatsType.EXPERIMENTER(65535), got="+statsType);
            OFStatsReplyFlagsSerializerVer14.readFrom(bb);
            // pad: 4 bytes
            bb.skipBytes(4);
            // fixed value property experimenter == 0x5c16c7L
            int experimenter = bb.readInt();
            if(experimenter != 0x5c16c7)
                throw new OFParseError("Wrong experimenter: Expected=0x5c16c7L(0x5c16c7L), got="+experimenter);
            int subtype = bb.readInt();
            bb.readerIndex(start);
            switch(subtype) {
               case 0xd:
                   // discriminator value 0xdL=0xdL for class OFBsnDebugCounterDescStatsReplyVer14
                   return OFBsnDebugCounterDescStatsReplyVer14.READER.readFrom(bb);
               case 0xc:
                   // discriminator value 0xcL=0xcL for class OFBsnDebugCounterStatsReplyVer14
                   return OFBsnDebugCounterStatsReplyVer14.READER.readFrom(bb);
               case 0xa:
                   // discriminator value 0xaL=0xaL for class OFBsnFlowChecksumBucketStatsReplyVer14
                   return OFBsnFlowChecksumBucketStatsReplyVer14.READER.readFrom(bb);
               case 0x10:
                   // discriminator value 0x10L=0x10L for class OFBsnGenericStatsReplyVer14
                   return OFBsnGenericStatsReplyVer14.READER.readFrom(bb);
               case 0x5:
                   // discriminator value 0x5L=0x5L for class OFBsnGentableBucketStatsReplyVer14
                   return OFBsnGentableBucketStatsReplyVer14.READER.readFrom(bb);
               case 0x4:
                   // discriminator value 0x4L=0x4L for class OFBsnGentableDescStatsReplyVer14
                   return OFBsnGentableDescStatsReplyVer14.READER.readFrom(bb);
               case 0x2:
                   // discriminator value 0x2L=0x2L for class OFBsnGentableEntryDescStatsReplyVer14
                   return OFBsnGentableEntryDescStatsReplyVer14.READER.readFrom(bb);
               case 0x3:
                   // discriminator value 0x3L=0x3L for class OFBsnGentableEntryStatsReplyVer14
                   return OFBsnGentableEntryStatsReplyVer14.READER.readFrom(bb);
               case 0x7:
                   // discriminator value 0x7L=0x7L for class OFBsnGentableStatsReplyVer14
                   return OFBsnGentableStatsReplyVer14.READER.readFrom(bb);
               case 0xe:
                   // discriminator value 0xeL=0xeL for class OFBsnImageDescStatsReplyVer14
                   return OFBsnImageDescStatsReplyVer14.READER.readFrom(bb);
               case 0x1:
                   // discriminator value 0x1L=0x1L for class OFBsnLacpStatsReplyVer14
                   return OFBsnLacpStatsReplyVer14.READER.readFrom(bb);
               case 0x8:
                   // discriminator value 0x8L=0x8L for class OFBsnPortCounterStatsReplyVer14
                   return OFBsnPortCounterStatsReplyVer14.READER.readFrom(bb);
               case 0x6:
                   // discriminator value 0x6L=0x6L for class OFBsnSwitchPipelineStatsReplyVer14
                   return OFBsnSwitchPipelineStatsReplyVer14.READER.readFrom(bb);
               case 0xb:
                   // discriminator value 0xbL=0xbL for class OFBsnTableChecksumStatsReplyVer14
                   return OFBsnTableChecksumStatsReplyVer14.READER.readFrom(bb);
               case 0x9:
                   // discriminator value 0x9L=0x9L for class OFBsnVlanCounterStatsReplyVer14
                   return OFBsnVlanCounterStatsReplyVer14.READER.readFrom(bb);
               case 0xf:
                   // discriminator value 0xfL=0xfL for class OFBsnVrfCounterStatsReplyVer14
                   return OFBsnVrfCounterStatsReplyVer14.READER.readFrom(bb);
               default:
                   throw new OFParseError("Unknown value for discriminator subtype of class OFBsnStatsReplyVer14: " + subtype);
            }
        }
    }
}
