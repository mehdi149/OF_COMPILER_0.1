// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template unit_test.java
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
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import com.google.common.collect.ImmutableList;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.hamcrest.CoreMatchers;



public class OFIndividualFlowStatsReplyVer15Test {
    OFFactory factory;

    final static byte[] INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED =
        new byte[] { 0x6, 0x13, 0x0, 0x48, 0x12, 0x34, 0x56, 0x78, 0x0, 0x11, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x38, 0x0, 0x0, 0x1, 0x0, 0x0, 0x1, 0x0, 0x1, 0x0, 0x10, (byte) 0x80, 0x0, 0x1, 0x8, 0x0, 0x0, 0x0, 0x4, 0x0, 0x0, 0x0, 0x5, 0x0, 0x0, 0x0, 0x1c, (byte) 0x80, 0x2, 0x0, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x4, (byte) 0x80, 0x2, 0x2, 0x8, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x6, 0x0, 0x0, 0x0, 0x0 };

    @Before
    public void setup() {
        factory = OFFactoryVer15.INSTANCE;
    }

    @Test
    public void testWrite() {
        OFIndividualFlowStatsReply.Builder builder = factory.buildIndividualFlowStatsReply();
        builder
        .setXid(0x12345678)
        .setFlags(ImmutableSet.<OFStatsReplyFlags>of(OFStatsReplyFlags.REPLY_MORE))
        .setEntries(ImmutableList.<OFIndividualFlowStatsEntry>of(factory.buildIndividualFlowStatsEntry().setTableId(TableId.of(1))
                                                                .setReason(OFFlowStatsReason.STATS_REQUEST)
                                                                .setPriority(1)
                                                                .setMatch(factory.buildMatch()
                                                                                .setMasked(MatchField.IN_PORT, OFPort.of(4), OFPort.of(5))
                                                                                .build())
                                                                .setStats(factory.buildStatV6()
                                                                                .setOxsFields(OFOxsList.of(factory.oxss().buildDuration().setValue(U64.of(4)).build(),
                                                                                factory.oxss().buildIdleTime().setValue(U64.of(6)).build()))
                                                                                .build())
                                                                .build()))
        .build();;
        OFIndividualFlowStatsReply individualFlowStatsReply = builder.build();
        ByteBuf bb = Unpooled.buffer();
        individualFlowStatsReply.writeTo(bb);
        byte[] written = new byte[bb.readableBytes()];
        bb.readBytes(written);

        assertThat(written, CoreMatchers.equalTo(INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED));
    }

    @Test
    public void testRead() throws Exception {
        OFIndividualFlowStatsReply.Builder builder = factory.buildIndividualFlowStatsReply();
        builder
        .setXid(0x12345678)
        .setFlags(ImmutableSet.<OFStatsReplyFlags>of(OFStatsReplyFlags.REPLY_MORE))
        .setEntries(ImmutableList.<OFIndividualFlowStatsEntry>of(factory.buildIndividualFlowStatsEntry().setTableId(TableId.of(1))
                                                                .setReason(OFFlowStatsReason.STATS_REQUEST)
                                                                .setPriority(1)
                                                                .setMatch(factory.buildMatch()
                                                                                .setMasked(MatchField.IN_PORT, OFPort.of(4), OFPort.of(5))
                                                                                .build())
                                                                .setStats(factory.buildStatV6()
                                                                                .setOxsFields(OFOxsList.of(factory.oxss().buildDuration().setValue(U64.of(4)).build(),
                                                                                factory.oxss().buildIdleTime().setValue(U64.of(6)).build()))
                                                                                .build())
                                                                .build()))
        .build();;
        OFIndividualFlowStatsReply individualFlowStatsReplyBuilt = builder.build();

        ByteBuf input = Unpooled.copiedBuffer(INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED);

        // FIXME should invoke the overall reader once implemented
        OFIndividualFlowStatsReply individualFlowStatsReplyRead = OFIndividualFlowStatsReplyVer15.READER.readFrom(input);
        assertEquals(INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED.length, input.readerIndex());

        assertEquals(individualFlowStatsReplyBuilt, individualFlowStatsReplyRead);
   }

   @Test
   public void testReadWrite() throws Exception {
       ByteBuf input = Unpooled.copiedBuffer(INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED);

       // FIXME should invoke the overall reader once implemented
       OFIndividualFlowStatsReply individualFlowStatsReply = OFIndividualFlowStatsReplyVer15.READER.readFrom(input);
       assertEquals(INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED.length, input.readerIndex());

       // write message again
       ByteBuf bb = Unpooled.buffer();
       individualFlowStatsReply.writeTo(bb);
       byte[] written = new byte[bb.readableBytes()];
       bb.readBytes(written);

       assertThat(written, CoreMatchers.equalTo(INDIVIDUAL_FLOW_STATS_REPLY_SERIALIZED));
   }

}
