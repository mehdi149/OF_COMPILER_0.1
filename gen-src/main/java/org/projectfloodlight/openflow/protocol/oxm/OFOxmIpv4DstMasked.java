// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_interface.java
// Do not modify

package org.projectfloodlight.openflow.protocol.oxm;

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

public interface OFOxmIpv4DstMasked extends OFObject, OFOxm<IPv4Address> {
    long getTypeLen();
    IPv4Address getValue();
    IPv4Address getMask();
    MatchField<IPv4Address> getMatchField();
    boolean isMasked();
    OFOxm<IPv4Address> getCanonical();
    OFVersion getVersion();


    void writeTo(ByteBuf channelBuffer);

    Builder createBuilder();
    public interface Builder extends OFOxm.Builder<IPv4Address> {
        OFOxmIpv4DstMasked build();
        long getTypeLen();
        IPv4Address getValue();
        Builder setValue(IPv4Address value);
        IPv4Address getMask();
        Builder setMask(IPv4Address mask);
        MatchField<IPv4Address> getMatchField();
        boolean isMasked();
        OFOxm<IPv4Address> getCanonical();
        OFVersion getVersion();
    }
}
