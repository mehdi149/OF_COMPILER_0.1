// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_factory_class.java
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
import java.util.Set;
import java.util.List;


public class OFBsnTlvsVer10 implements OFBsnTlvs {
    public final static OFBsnTlvsVer10 INSTANCE = new OFBsnTlvsVer10();




    public OFBsnTlvActorKey.Builder buildActorKey() {
        throw new UnsupportedOperationException("OFBsnTlvActorKey not supported in version 1.0");
    }
    public OFBsnTlvActorKey actorKey(int value) {
        throw new UnsupportedOperationException("OFBsnTlvActorKey not supported in version 1.0");
    }

    public OFBsnTlvActorPortNum.Builder buildActorPortNum() {
        throw new UnsupportedOperationException("OFBsnTlvActorPortNum not supported in version 1.0");
    }
    public OFBsnTlvActorPortNum actorPortNum(int value) {
        throw new UnsupportedOperationException("OFBsnTlvActorPortNum not supported in version 1.0");
    }

    public OFBsnTlvActorPortPriority.Builder buildActorPortPriority() {
        throw new UnsupportedOperationException("OFBsnTlvActorPortPriority not supported in version 1.0");
    }
    public OFBsnTlvActorPortPriority actorPortPriority(int value) {
        throw new UnsupportedOperationException("OFBsnTlvActorPortPriority not supported in version 1.0");
    }

    public OFBsnTlvActorState.Builder buildActorState() {
        throw new UnsupportedOperationException("OFBsnTlvActorState not supported in version 1.0");
    }
    public OFBsnTlvActorState actorState(Set<OFBsnLacpState> value) {
        throw new UnsupportedOperationException("OFBsnTlvActorState not supported in version 1.0");
    }

    public OFBsnTlvActorSystemMac.Builder buildActorSystemMac() {
        throw new UnsupportedOperationException("OFBsnTlvActorSystemMac not supported in version 1.0");
    }
    public OFBsnTlvActorSystemMac actorSystemMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvActorSystemMac not supported in version 1.0");
    }

    public OFBsnTlvActorSystemPriority.Builder buildActorSystemPriority() {
        throw new UnsupportedOperationException("OFBsnTlvActorSystemPriority not supported in version 1.0");
    }
    public OFBsnTlvActorSystemPriority actorSystemPriority(int value) {
        throw new UnsupportedOperationException("OFBsnTlvActorSystemPriority not supported in version 1.0");
    }

    public OFBsnTlvAnchor.Builder buildAnchor() {
        throw new UnsupportedOperationException("OFBsnTlvAnchor not supported in version 1.0");
    }
    public OFBsnTlvAnchor anchor(OFBsnAnchor value) {
        throw new UnsupportedOperationException("OFBsnTlvAnchor not supported in version 1.0");
    }

    public OFBsnTlvBroadcastQueryTimeout.Builder buildBroadcastQueryTimeout() {
        throw new UnsupportedOperationException("OFBsnTlvBroadcastQueryTimeout not supported in version 1.0");
    }
    public OFBsnTlvBroadcastQueryTimeout broadcastQueryTimeout(long value) {
        throw new UnsupportedOperationException("OFBsnTlvBroadcastQueryTimeout not supported in version 1.0");
    }

    public OFBsnTlvBroadcastRate.Builder buildBroadcastRate() {
        throw new UnsupportedOperationException("OFBsnTlvBroadcastRate not supported in version 1.0");
    }
    public OFBsnTlvBroadcastRate broadcastRate(long value) {
        throw new UnsupportedOperationException("OFBsnTlvBroadcastRate not supported in version 1.0");
    }

    public OFBsnTlvBucket.Builder buildBucket() {
        throw new UnsupportedOperationException("OFBsnTlvBucket not supported in version 1.0");
    }
    public OFBsnTlvBucket bucket(List<OFBsnTlv> value) {
        throw new UnsupportedOperationException("OFBsnTlvBucket not supported in version 1.0");
    }

    public OFBsnTlvCircuitId.Builder buildCircuitId() {
        throw new UnsupportedOperationException("OFBsnTlvCircuitId not supported in version 1.0");
    }
    public OFBsnTlvCircuitId circuitId(byte[] value) {
        throw new UnsupportedOperationException("OFBsnTlvCircuitId not supported in version 1.0");
    }

    public OFBsnTlvConvergenceStatus.Builder buildConvergenceStatus() {
        throw new UnsupportedOperationException("OFBsnTlvConvergenceStatus not supported in version 1.0");
    }
    public OFBsnTlvConvergenceStatus convergenceStatus(short value) {
        throw new UnsupportedOperationException("OFBsnTlvConvergenceStatus not supported in version 1.0");
    }

    public OFBsnTlvCpuLag cpuLag() {
        throw new UnsupportedOperationException("OFBsnTlvCpuLag not supported in version 1.0");
    }

    public OFBsnTlvCrcEnabled.Builder buildCrcEnabled() {
        throw new UnsupportedOperationException("OFBsnTlvCrcEnabled not supported in version 1.0");
    }
    public OFBsnTlvCrcEnabled crcEnabled(short value) {
        throw new UnsupportedOperationException("OFBsnTlvCrcEnabled not supported in version 1.0");
    }

    public OFBsnTlvData.Builder buildData() {
        throw new UnsupportedOperationException("OFBsnTlvData not supported in version 1.0");
    }
    public OFBsnTlvData data(byte[] value) {
        throw new UnsupportedOperationException("OFBsnTlvData not supported in version 1.0");
    }

    public OFBsnTlvDecap.Builder buildDecap() {
        throw new UnsupportedOperationException("OFBsnTlvDecap not supported in version 1.0");
    }
    public OFBsnTlvDecap decap(OFBsnDecap value) {
        throw new UnsupportedOperationException("OFBsnTlvDecap not supported in version 1.0");
    }

    public OFBsnTlvDisableSrcMacCheck disableSrcMacCheck() {
        throw new UnsupportedOperationException("OFBsnTlvDisableSrcMacCheck not supported in version 1.0");
    }

    public OFBsnTlvDrop drop() {
        throw new UnsupportedOperationException("OFBsnTlvDrop not supported in version 1.0");
    }

    public OFBsnTlvDscp.Builder buildDscp() {
        throw new UnsupportedOperationException("OFBsnTlvDscp not supported in version 1.0");
    }
    public OFBsnTlvDscp dscp(int value) {
        throw new UnsupportedOperationException("OFBsnTlvDscp not supported in version 1.0");
    }

    public OFBsnTlvEthDst.Builder buildEthDst() {
        throw new UnsupportedOperationException("OFBsnTlvEthDst not supported in version 1.0");
    }
    public OFBsnTlvEthDst ethDst(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvEthDst not supported in version 1.0");
    }

    public OFBsnTlvEthSrc.Builder buildEthSrc() {
        throw new UnsupportedOperationException("OFBsnTlvEthSrc not supported in version 1.0");
    }
    public OFBsnTlvEthSrc ethSrc(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvEthSrc not supported in version 1.0");
    }

    public OFBsnTlvExternalGatewayIp.Builder buildExternalGatewayIp() {
        throw new UnsupportedOperationException("OFBsnTlvExternalGatewayIp not supported in version 1.0");
    }
    public OFBsnTlvExternalGatewayIp externalGatewayIp(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvExternalGatewayIp not supported in version 1.0");
    }

    public OFBsnTlvExternalGatewayMac.Builder buildExternalGatewayMac() {
        throw new UnsupportedOperationException("OFBsnTlvExternalGatewayMac not supported in version 1.0");
    }
    public OFBsnTlvExternalGatewayMac externalGatewayMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvExternalGatewayMac not supported in version 1.0");
    }

    public OFBsnTlvExternalIp.Builder buildExternalIp() {
        throw new UnsupportedOperationException("OFBsnTlvExternalIp not supported in version 1.0");
    }
    public OFBsnTlvExternalIp externalIp(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvExternalIp not supported in version 1.0");
    }

    public OFBsnTlvExternalMac.Builder buildExternalMac() {
        throw new UnsupportedOperationException("OFBsnTlvExternalMac not supported in version 1.0");
    }
    public OFBsnTlvExternalMac externalMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvExternalMac not supported in version 1.0");
    }

    public OFBsnTlvExternalNetmask.Builder buildExternalNetmask() {
        throw new UnsupportedOperationException("OFBsnTlvExternalNetmask not supported in version 1.0");
    }
    public OFBsnTlvExternalNetmask externalNetmask(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvExternalNetmask not supported in version 1.0");
    }

    public OFBsnTlvGenerationId.Builder buildGenerationId() {
        throw new UnsupportedOperationException("OFBsnTlvGenerationId not supported in version 1.0");
    }
    public OFBsnTlvGenerationId generationId(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvGenerationId not supported in version 1.0");
    }

    public OFBsnTlvHashGtpHeaderMatch.Builder buildHashGtpHeaderMatch() {
        throw new UnsupportedOperationException("OFBsnTlvHashGtpHeaderMatch not supported in version 1.0");
    }
    public OFBsnTlvHashGtpHeaderMatch hashGtpHeaderMatch(short firstHeaderByte, short firstHeaderMask) {
        throw new UnsupportedOperationException("OFBsnTlvHashGtpHeaderMatch not supported in version 1.0");
    }

    public OFBsnTlvHashGtpPortMatch.Builder buildHashGtpPortMatch() {
        throw new UnsupportedOperationException("OFBsnTlvHashGtpPortMatch not supported in version 1.0");
    }

    public OFBsnTlvHashPacketField.Builder buildHashPacketField() {
        throw new UnsupportedOperationException("OFBsnTlvHashPacketField not supported in version 1.0");
    }
    public OFBsnTlvHashPacketField hashPacketField(Set<OFBsnHashPacketField> value) {
        throw new UnsupportedOperationException("OFBsnTlvHashPacketField not supported in version 1.0");
    }

    public OFBsnTlvHashPacketType.Builder buildHashPacketType() {
        throw new UnsupportedOperationException("OFBsnTlvHashPacketType not supported in version 1.0");
    }
    public OFBsnTlvHashPacketType hashPacketType(OFBsnHashPacketType value) {
        throw new UnsupportedOperationException("OFBsnTlvHashPacketType not supported in version 1.0");
    }

    public OFBsnTlvHashSeed.Builder buildHashSeed() {
        throw new UnsupportedOperationException("OFBsnTlvHashSeed not supported in version 1.0");
    }
    public OFBsnTlvHashSeed hashSeed(long seed1, long seed2) {
        throw new UnsupportedOperationException("OFBsnTlvHashSeed not supported in version 1.0");
    }

    public OFBsnTlvHashType.Builder buildHashType() {
        throw new UnsupportedOperationException("OFBsnTlvHashType not supported in version 1.0");
    }
    public OFBsnTlvHashType hashType(OFBsnHashType value) {
        throw new UnsupportedOperationException("OFBsnTlvHashType not supported in version 1.0");
    }

    public OFBsnTlvHeaderSize.Builder buildHeaderSize() {
        throw new UnsupportedOperationException("OFBsnTlvHeaderSize not supported in version 1.0");
    }
    public OFBsnTlvHeaderSize headerSize(long value) {
        throw new UnsupportedOperationException("OFBsnTlvHeaderSize not supported in version 1.0");
    }

    public OFBsnTlvIcmpCode.Builder buildIcmpCode() {
        throw new UnsupportedOperationException("OFBsnTlvIcmpCode not supported in version 1.0");
    }
    public OFBsnTlvIcmpCode icmpCode(short value) {
        throw new UnsupportedOperationException("OFBsnTlvIcmpCode not supported in version 1.0");
    }

    public OFBsnTlvIcmpId.Builder buildIcmpId() {
        throw new UnsupportedOperationException("OFBsnTlvIcmpId not supported in version 1.0");
    }
    public OFBsnTlvIcmpId icmpId(int value) {
        throw new UnsupportedOperationException("OFBsnTlvIcmpId not supported in version 1.0");
    }

    public OFBsnTlvIcmpType.Builder buildIcmpType() {
        throw new UnsupportedOperationException("OFBsnTlvIcmpType not supported in version 1.0");
    }
    public OFBsnTlvIcmpType icmpType(short value) {
        throw new UnsupportedOperationException("OFBsnTlvIcmpType not supported in version 1.0");
    }

    public OFBsnTlvIdleNotification idleNotification() {
        throw new UnsupportedOperationException("OFBsnTlvIdleNotification not supported in version 1.0");
    }

    public OFBsnTlvIdleTime.Builder buildIdleTime() {
        throw new UnsupportedOperationException("OFBsnTlvIdleTime not supported in version 1.0");
    }
    public OFBsnTlvIdleTime idleTime(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvIdleTime not supported in version 1.0");
    }

    public OFBsnTlvIdleTimeout.Builder buildIdleTimeout() {
        throw new UnsupportedOperationException("OFBsnTlvIdleTimeout not supported in version 1.0");
    }
    public OFBsnTlvIdleTimeout idleTimeout(long value) {
        throw new UnsupportedOperationException("OFBsnTlvIdleTimeout not supported in version 1.0");
    }

    public OFBsnTlvIgmpSnooping igmpSnooping() {
        throw new UnsupportedOperationException("OFBsnTlvIgmpSnooping not supported in version 1.0");
    }

    public OFBsnTlvInternalGatewayMac.Builder buildInternalGatewayMac() {
        throw new UnsupportedOperationException("OFBsnTlvInternalGatewayMac not supported in version 1.0");
    }
    public OFBsnTlvInternalGatewayMac internalGatewayMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvInternalGatewayMac not supported in version 1.0");
    }

    public OFBsnTlvInternalMac.Builder buildInternalMac() {
        throw new UnsupportedOperationException("OFBsnTlvInternalMac not supported in version 1.0");
    }
    public OFBsnTlvInternalMac internalMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvInternalMac not supported in version 1.0");
    }

    public OFBsnTlvInterval.Builder buildInterval() {
        throw new UnsupportedOperationException("OFBsnTlvInterval not supported in version 1.0");
    }
    public OFBsnTlvInterval interval(long value) {
        throw new UnsupportedOperationException("OFBsnTlvInterval not supported in version 1.0");
    }

    public OFBsnTlvIpProto.Builder buildIpProto() {
        throw new UnsupportedOperationException("OFBsnTlvIpProto not supported in version 1.0");
    }
    public OFBsnTlvIpProto ipProto(short value) {
        throw new UnsupportedOperationException("OFBsnTlvIpProto not supported in version 1.0");
    }

    public OFBsnTlvIpv4.Builder buildIpv4() {
        throw new UnsupportedOperationException("OFBsnTlvIpv4 not supported in version 1.0");
    }
    public OFBsnTlvIpv4 ipv4(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvIpv4 not supported in version 1.0");
    }

    public OFBsnTlvIpv4Dst.Builder buildIpv4Dst() {
        throw new UnsupportedOperationException("OFBsnTlvIpv4Dst not supported in version 1.0");
    }
    public OFBsnTlvIpv4Dst ipv4Dst(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvIpv4Dst not supported in version 1.0");
    }

    public OFBsnTlvIpv4Netmask.Builder buildIpv4Netmask() {
        throw new UnsupportedOperationException("OFBsnTlvIpv4Netmask not supported in version 1.0");
    }
    public OFBsnTlvIpv4Netmask ipv4Netmask(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvIpv4Netmask not supported in version 1.0");
    }

    public OFBsnTlvIpv4Src.Builder buildIpv4Src() {
        throw new UnsupportedOperationException("OFBsnTlvIpv4Src not supported in version 1.0");
    }
    public OFBsnTlvIpv4Src ipv4Src(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvIpv4Src not supported in version 1.0");
    }

    public OFBsnTlvIpv6.Builder buildIpv6() {
        throw new UnsupportedOperationException("OFBsnTlvIpv6 not supported in version 1.0");
    }
    public OFBsnTlvIpv6 ipv6(IPv6Address value) {
        throw new UnsupportedOperationException("OFBsnTlvIpv6 not supported in version 1.0");
    }

    public OFBsnTlvKnownMulticastRate.Builder buildKnownMulticastRate() {
        throw new UnsupportedOperationException("OFBsnTlvKnownMulticastRate not supported in version 1.0");
    }
    public OFBsnTlvKnownMulticastRate knownMulticastRate(long value) {
        throw new UnsupportedOperationException("OFBsnTlvKnownMulticastRate not supported in version 1.0");
    }

    public OFBsnTlvL2MulticastLookup l2MulticastLookup() {
        throw new UnsupportedOperationException("OFBsnTlvL2MulticastLookup not supported in version 1.0");
    }

    public OFBsnTlvLoopbackPort.Builder buildLoopbackPort() {
        throw new UnsupportedOperationException("OFBsnTlvLoopbackPort not supported in version 1.0");
    }
    public OFBsnTlvLoopbackPort loopbackPort(OFPort value) {
        throw new UnsupportedOperationException("OFBsnTlvLoopbackPort not supported in version 1.0");
    }

    public OFBsnTlvMac.Builder buildMac() {
        throw new UnsupportedOperationException("OFBsnTlvMac not supported in version 1.0");
    }
    public OFBsnTlvMac mac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvMac not supported in version 1.0");
    }

    public OFBsnTlvMacMask.Builder buildMacMask() {
        throw new UnsupportedOperationException("OFBsnTlvMacMask not supported in version 1.0");
    }
    public OFBsnTlvMacMask macMask(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvMacMask not supported in version 1.0");
    }

    public OFBsnTlvMcgTypeVxlan mcgTypeVxlan() {
        throw new UnsupportedOperationException("OFBsnTlvMcgTypeVxlan not supported in version 1.0");
    }

    public OFBsnTlvMissPackets.Builder buildMissPackets() {
        throw new UnsupportedOperationException("OFBsnTlvMissPackets not supported in version 1.0");
    }
    public OFBsnTlvMissPackets missPackets(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvMissPackets not supported in version 1.0");
    }

    public OFBsnTlvMplsControlWord.Builder buildMplsControlWord() {
        throw new UnsupportedOperationException("OFBsnTlvMplsControlWord not supported in version 1.0");
    }
    public OFBsnTlvMplsControlWord mplsControlWord(short value) {
        throw new UnsupportedOperationException("OFBsnTlvMplsControlWord not supported in version 1.0");
    }

    public OFBsnTlvMplsLabel.Builder buildMplsLabel() {
        throw new UnsupportedOperationException("OFBsnTlvMplsLabel not supported in version 1.0");
    }
    public OFBsnTlvMplsLabel mplsLabel(long value) {
        throw new UnsupportedOperationException("OFBsnTlvMplsLabel not supported in version 1.0");
    }

    public OFBsnTlvMplsSequenced.Builder buildMplsSequenced() {
        throw new UnsupportedOperationException("OFBsnTlvMplsSequenced not supported in version 1.0");
    }
    public OFBsnTlvMplsSequenced mplsSequenced(short value) {
        throw new UnsupportedOperationException("OFBsnTlvMplsSequenced not supported in version 1.0");
    }

    public OFBsnTlvMulticastInterfaceId.Builder buildMulticastInterfaceId() {
        throw new UnsupportedOperationException("OFBsnTlvMulticastInterfaceId not supported in version 1.0");
    }
    public OFBsnTlvMulticastInterfaceId multicastInterfaceId(long value) {
        throw new UnsupportedOperationException("OFBsnTlvMulticastInterfaceId not supported in version 1.0");
    }

    public OFBsnTlvName.Builder buildName() {
        throw new UnsupportedOperationException("OFBsnTlvName not supported in version 1.0");
    }
    public OFBsnTlvName name(byte[] value) {
        throw new UnsupportedOperationException("OFBsnTlvName not supported in version 1.0");
    }

    public OFBsnTlvNegate negate() {
        throw new UnsupportedOperationException("OFBsnTlvNegate not supported in version 1.0");
    }

    public OFBsnTlvNextHopIpv4.Builder buildNextHopIpv4() {
        throw new UnsupportedOperationException("OFBsnTlvNextHopIpv4 not supported in version 1.0");
    }
    public OFBsnTlvNextHopIpv4 nextHopIpv4(IPv4Address value) {
        throw new UnsupportedOperationException("OFBsnTlvNextHopIpv4 not supported in version 1.0");
    }

    public OFBsnTlvNextHopMac.Builder buildNextHopMac() {
        throw new UnsupportedOperationException("OFBsnTlvNextHopMac not supported in version 1.0");
    }
    public OFBsnTlvNextHopMac nextHopMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvNextHopMac not supported in version 1.0");
    }

    public OFBsnTlvNexthopTypeVxlan nexthopTypeVxlan() {
        throw new UnsupportedOperationException("OFBsnTlvNexthopTypeVxlan not supported in version 1.0");
    }

    public OFBsnTlvOffset.Builder buildOffset() {
        throw new UnsupportedOperationException("OFBsnTlvOffset not supported in version 1.0");
    }
    public OFBsnTlvOffset offset(int value) {
        throw new UnsupportedOperationException("OFBsnTlvOffset not supported in version 1.0");
    }

    public OFBsnTlvParentPort.Builder buildParentPort() {
        throw new UnsupportedOperationException("OFBsnTlvParentPort not supported in version 1.0");
    }
    public OFBsnTlvParentPort parentPort(OFPort value) {
        throw new UnsupportedOperationException("OFBsnTlvParentPort not supported in version 1.0");
    }

    public OFBsnTlvPartnerKey.Builder buildPartnerKey() {
        throw new UnsupportedOperationException("OFBsnTlvPartnerKey not supported in version 1.0");
    }
    public OFBsnTlvPartnerKey partnerKey(int value) {
        throw new UnsupportedOperationException("OFBsnTlvPartnerKey not supported in version 1.0");
    }

    public OFBsnTlvPartnerPortNum.Builder buildPartnerPortNum() {
        throw new UnsupportedOperationException("OFBsnTlvPartnerPortNum not supported in version 1.0");
    }
    public OFBsnTlvPartnerPortNum partnerPortNum(int value) {
        throw new UnsupportedOperationException("OFBsnTlvPartnerPortNum not supported in version 1.0");
    }

    public OFBsnTlvPartnerPortPriority.Builder buildPartnerPortPriority() {
        throw new UnsupportedOperationException("OFBsnTlvPartnerPortPriority not supported in version 1.0");
    }
    public OFBsnTlvPartnerPortPriority partnerPortPriority(int value) {
        throw new UnsupportedOperationException("OFBsnTlvPartnerPortPriority not supported in version 1.0");
    }

    public OFBsnTlvPartnerState.Builder buildPartnerState() {
        throw new UnsupportedOperationException("OFBsnTlvPartnerState not supported in version 1.0");
    }
    public OFBsnTlvPartnerState partnerState(Set<OFBsnLacpState> value) {
        throw new UnsupportedOperationException("OFBsnTlvPartnerState not supported in version 1.0");
    }

    public OFBsnTlvPartnerSystemMac.Builder buildPartnerSystemMac() {
        throw new UnsupportedOperationException("OFBsnTlvPartnerSystemMac not supported in version 1.0");
    }
    public OFBsnTlvPartnerSystemMac partnerSystemMac(MacAddress value) {
        throw new UnsupportedOperationException("OFBsnTlvPartnerSystemMac not supported in version 1.0");
    }

    public OFBsnTlvPartnerSystemPriority.Builder buildPartnerSystemPriority() {
        throw new UnsupportedOperationException("OFBsnTlvPartnerSystemPriority not supported in version 1.0");
    }
    public OFBsnTlvPartnerSystemPriority partnerSystemPriority(int value) {
        throw new UnsupportedOperationException("OFBsnTlvPartnerSystemPriority not supported in version 1.0");
    }

    public OFBsnTlvPort.Builder buildPort() {
        throw new UnsupportedOperationException("OFBsnTlvPort not supported in version 1.0");
    }
    public OFBsnTlvPort port(OFPort value) {
        throw new UnsupportedOperationException("OFBsnTlvPort not supported in version 1.0");
    }

    public OFBsnTlvPortVxlanMode.Builder buildPortVxlanMode() {
        throw new UnsupportedOperationException("OFBsnTlvPortVxlanMode not supported in version 1.0");
    }
    public OFBsnTlvPortVxlanMode portVxlanMode(OFBsnPortVxlanMode value) {
        throw new UnsupportedOperationException("OFBsnTlvPortVxlanMode not supported in version 1.0");
    }

    public OFBsnTlvPriority.Builder buildPriority() {
        throw new UnsupportedOperationException("OFBsnTlvPriority not supported in version 1.0");
    }
    public OFBsnTlvPriority priority(long value) {
        throw new UnsupportedOperationException("OFBsnTlvPriority not supported in version 1.0");
    }

    public OFBsnTlvQosPriority.Builder buildQosPriority() {
        throw new UnsupportedOperationException("OFBsnTlvQosPriority not supported in version 1.0");
    }
    public OFBsnTlvQosPriority qosPriority(long value) {
        throw new UnsupportedOperationException("OFBsnTlvQosPriority not supported in version 1.0");
    }

    public OFBsnTlvQueueId.Builder buildQueueId() {
        throw new UnsupportedOperationException("OFBsnTlvQueueId not supported in version 1.0");
    }
    public OFBsnTlvQueueId queueId(long value) {
        throw new UnsupportedOperationException("OFBsnTlvQueueId not supported in version 1.0");
    }

    public OFBsnTlvQueueWeight.Builder buildQueueWeight() {
        throw new UnsupportedOperationException("OFBsnTlvQueueWeight not supported in version 1.0");
    }
    public OFBsnTlvQueueWeight queueWeight(long value) {
        throw new UnsupportedOperationException("OFBsnTlvQueueWeight not supported in version 1.0");
    }

    public OFBsnTlvRateLimit.Builder buildRateLimit() {
        throw new UnsupportedOperationException("OFBsnTlvRateLimit not supported in version 1.0");
    }
    public OFBsnTlvRateLimit rateLimit(long value) {
        throw new UnsupportedOperationException("OFBsnTlvRateLimit not supported in version 1.0");
    }

    public OFBsnTlvRateUnit.Builder buildRateUnit() {
        throw new UnsupportedOperationException("OFBsnTlvRateUnit not supported in version 1.0");
    }
    public OFBsnTlvRateUnit rateUnit(OFBsnRateUnit value) {
        throw new UnsupportedOperationException("OFBsnTlvRateUnit not supported in version 1.0");
    }

    public OFBsnTlvReference.Builder buildReference() {
        throw new UnsupportedOperationException("OFBsnTlvReference not supported in version 1.0");
    }
    public OFBsnTlvReference reference(int tableId, List<OFBsnTlv> key) {
        throw new UnsupportedOperationException("OFBsnTlvReference not supported in version 1.0");
    }

    public OFBsnTlvReplyPackets.Builder buildReplyPackets() {
        throw new UnsupportedOperationException("OFBsnTlvReplyPackets not supported in version 1.0");
    }
    public OFBsnTlvReplyPackets replyPackets(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvReplyPackets not supported in version 1.0");
    }

    public OFBsnTlvRequestPackets.Builder buildRequestPackets() {
        throw new UnsupportedOperationException("OFBsnTlvRequestPackets not supported in version 1.0");
    }
    public OFBsnTlvRequestPackets requestPackets(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvRequestPackets not supported in version 1.0");
    }

    public OFBsnTlvRxBytes.Builder buildRxBytes() {
        throw new UnsupportedOperationException("OFBsnTlvRxBytes not supported in version 1.0");
    }
    public OFBsnTlvRxBytes rxBytes(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvRxBytes not supported in version 1.0");
    }

    public OFBsnTlvRxPackets.Builder buildRxPackets() {
        throw new UnsupportedOperationException("OFBsnTlvRxPackets not supported in version 1.0");
    }
    public OFBsnTlvRxPackets rxPackets(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvRxPackets not supported in version 1.0");
    }

    public OFBsnTlvSamplingRate.Builder buildSamplingRate() {
        throw new UnsupportedOperationException("OFBsnTlvSamplingRate not supported in version 1.0");
    }
    public OFBsnTlvSamplingRate samplingRate(long value) {
        throw new UnsupportedOperationException("OFBsnTlvSamplingRate not supported in version 1.0");
    }

    public OFBsnTlvSetLoopbackMode setLoopbackMode() {
        throw new UnsupportedOperationException("OFBsnTlvSetLoopbackMode not supported in version 1.0");
    }

    public OFBsnTlvStatus.Builder buildStatus() {
        throw new UnsupportedOperationException("OFBsnTlvStatus not supported in version 1.0");
    }
    public OFBsnTlvStatus status(OFBsnStatus value) {
        throw new UnsupportedOperationException("OFBsnTlvStatus not supported in version 1.0");
    }

    public OFBsnTlvStripMplsL2OnIngress stripMplsL2OnIngress() {
        throw new UnsupportedOperationException("OFBsnTlvStripMplsL2OnIngress not supported in version 1.0");
    }

    public OFBsnTlvStripMplsL3OnIngress stripMplsL3OnIngress() {
        throw new UnsupportedOperationException("OFBsnTlvStripMplsL3OnIngress not supported in version 1.0");
    }

    public OFBsnTlvStripVlanOnEgress stripVlanOnEgress() {
        throw new UnsupportedOperationException("OFBsnTlvStripVlanOnEgress not supported in version 1.0");
    }

    public OFBsnTlvSubAgentId.Builder buildSubAgentId() {
        throw new UnsupportedOperationException("OFBsnTlvSubAgentId not supported in version 1.0");
    }
    public OFBsnTlvSubAgentId subAgentId(long value) {
        throw new UnsupportedOperationException("OFBsnTlvSubAgentId not supported in version 1.0");
    }

    public OFBsnTlvTcpDst.Builder buildTcpDst() {
        throw new UnsupportedOperationException("OFBsnTlvTcpDst not supported in version 1.0");
    }
    public OFBsnTlvTcpDst tcpDst(int value) {
        throw new UnsupportedOperationException("OFBsnTlvTcpDst not supported in version 1.0");
    }

    public OFBsnTlvTcpSrc.Builder buildTcpSrc() {
        throw new UnsupportedOperationException("OFBsnTlvTcpSrc not supported in version 1.0");
    }
    public OFBsnTlvTcpSrc tcpSrc(int value) {
        throw new UnsupportedOperationException("OFBsnTlvTcpSrc not supported in version 1.0");
    }

    public OFBsnTlvTtl.Builder buildTtl() {
        throw new UnsupportedOperationException("OFBsnTlvTtl not supported in version 1.0");
    }
    public OFBsnTlvTtl ttl(int value) {
        throw new UnsupportedOperationException("OFBsnTlvTtl not supported in version 1.0");
    }

    public OFBsnTlvTxBytes.Builder buildTxBytes() {
        throw new UnsupportedOperationException("OFBsnTlvTxBytes not supported in version 1.0");
    }
    public OFBsnTlvTxBytes txBytes(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvTxBytes not supported in version 1.0");
    }

    public OFBsnTlvTxPackets.Builder buildTxPackets() {
        throw new UnsupportedOperationException("OFBsnTlvTxPackets not supported in version 1.0");
    }
    public OFBsnTlvTxPackets txPackets(U64 value) {
        throw new UnsupportedOperationException("OFBsnTlvTxPackets not supported in version 1.0");
    }

    public OFBsnTlvUdfAnchor.Builder buildUdfAnchor() {
        throw new UnsupportedOperationException("OFBsnTlvUdfAnchor not supported in version 1.0");
    }
    public OFBsnTlvUdfAnchor udfAnchor(OFBsnUdfAnchor value) {
        throw new UnsupportedOperationException("OFBsnTlvUdfAnchor not supported in version 1.0");
    }

    public OFBsnTlvUdfId.Builder buildUdfId() {
        throw new UnsupportedOperationException("OFBsnTlvUdfId not supported in version 1.0");
    }
    public OFBsnTlvUdfId udfId(int value) {
        throw new UnsupportedOperationException("OFBsnTlvUdfId not supported in version 1.0");
    }

    public OFBsnTlvUdfLength.Builder buildUdfLength() {
        throw new UnsupportedOperationException("OFBsnTlvUdfLength not supported in version 1.0");
    }
    public OFBsnTlvUdfLength udfLength(int value) {
        throw new UnsupportedOperationException("OFBsnTlvUdfLength not supported in version 1.0");
    }

    public OFBsnTlvUdfOffset.Builder buildUdfOffset() {
        throw new UnsupportedOperationException("OFBsnTlvUdfOffset not supported in version 1.0");
    }
    public OFBsnTlvUdfOffset udfOffset(int value) {
        throw new UnsupportedOperationException("OFBsnTlvUdfOffset not supported in version 1.0");
    }

    public OFBsnTlvUdpDst.Builder buildUdpDst() {
        throw new UnsupportedOperationException("OFBsnTlvUdpDst not supported in version 1.0");
    }
    public OFBsnTlvUdpDst udpDst(int value) {
        throw new UnsupportedOperationException("OFBsnTlvUdpDst not supported in version 1.0");
    }

    public OFBsnTlvUdpSrc.Builder buildUdpSrc() {
        throw new UnsupportedOperationException("OFBsnTlvUdpSrc not supported in version 1.0");
    }
    public OFBsnTlvUdpSrc udpSrc(int value) {
        throw new UnsupportedOperationException("OFBsnTlvUdpSrc not supported in version 1.0");
    }

    public OFBsnTlvUint64List.Builder buildUint64List() {
        throw new UnsupportedOperationException("OFBsnTlvUint64List not supported in version 1.0");
    }
    public OFBsnTlvUint64List uint64List(List<U64> value) {
        throw new UnsupportedOperationException("OFBsnTlvUint64List not supported in version 1.0");
    }

    public OFBsnTlvUnicastQueryTimeout.Builder buildUnicastQueryTimeout() {
        throw new UnsupportedOperationException("OFBsnTlvUnicastQueryTimeout not supported in version 1.0");
    }
    public OFBsnTlvUnicastQueryTimeout unicastQueryTimeout(long value) {
        throw new UnsupportedOperationException("OFBsnTlvUnicastQueryTimeout not supported in version 1.0");
    }

    public OFBsnTlvUnicastRate.Builder buildUnicastRate() {
        throw new UnsupportedOperationException("OFBsnTlvUnicastRate not supported in version 1.0");
    }
    public OFBsnTlvUnicastRate unicastRate(long value) {
        throw new UnsupportedOperationException("OFBsnTlvUnicastRate not supported in version 1.0");
    }

    public OFBsnTlvUnknownMulticastRate.Builder buildUnknownMulticastRate() {
        throw new UnsupportedOperationException("OFBsnTlvUnknownMulticastRate not supported in version 1.0");
    }
    public OFBsnTlvUnknownMulticastRate unknownMulticastRate(long value) {
        throw new UnsupportedOperationException("OFBsnTlvUnknownMulticastRate not supported in version 1.0");
    }

    public OFBsnTlvUntagged untagged() {
        throw new UnsupportedOperationException("OFBsnTlvUntagged not supported in version 1.0");
    }

    public OFBsnTlvUsePacketState.Builder buildUsePacketState() {
        throw new UnsupportedOperationException("OFBsnTlvUsePacketState not supported in version 1.0");
    }
    public OFBsnTlvUsePacketState usePacketState(short value) {
        throw new UnsupportedOperationException("OFBsnTlvUsePacketState not supported in version 1.0");
    }

    public OFBsnTlvVfi.Builder buildVfi() {
        throw new UnsupportedOperationException("OFBsnTlvVfi not supported in version 1.0");
    }
    public OFBsnTlvVfi vfi(int value) {
        throw new UnsupportedOperationException("OFBsnTlvVfi not supported in version 1.0");
    }

    public OFBsnTlvVfpClassId.Builder buildVfpClassId() {
        throw new UnsupportedOperationException("OFBsnTlvVfpClassId not supported in version 1.0");
    }
    public OFBsnTlvVfpClassId vfpClassId(long value) {
        throw new UnsupportedOperationException("OFBsnTlvVfpClassId not supported in version 1.0");
    }

    public OFBsnTlvVlanMacList.Builder buildVlanMacList() {
        throw new UnsupportedOperationException("OFBsnTlvVlanMacList not supported in version 1.0");
    }
    public OFBsnTlvVlanMacList vlanMacList(List<OFBsnVlanMac> key) {
        throw new UnsupportedOperationException("OFBsnTlvVlanMacList not supported in version 1.0");
    }

    public OFBsnTlvVlanPcp.Builder buildVlanPcp() {
        throw new UnsupportedOperationException("OFBsnTlvVlanPcp not supported in version 1.0");
    }
    public OFBsnTlvVlanPcp vlanPcp(short value) {
        throw new UnsupportedOperationException("OFBsnTlvVlanPcp not supported in version 1.0");
    }

    public OFBsnTlvVlanVid.Builder buildVlanVid() {
        throw new UnsupportedOperationException("OFBsnTlvVlanVid not supported in version 1.0");
    }
    public OFBsnTlvVlanVid vlanVid(VlanVid value) {
        throw new UnsupportedOperationException("OFBsnTlvVlanVid not supported in version 1.0");
    }

    public OFBsnTlvVlanVidMask.Builder buildVlanVidMask() {
        throw new UnsupportedOperationException("OFBsnTlvVlanVidMask not supported in version 1.0");
    }
    public OFBsnTlvVlanVidMask vlanVidMask(int value) {
        throw new UnsupportedOperationException("OFBsnTlvVlanVidMask not supported in version 1.0");
    }

    public OFBsnTlvVni.Builder buildVni() {
        throw new UnsupportedOperationException("OFBsnTlvVni not supported in version 1.0");
    }
    public OFBsnTlvVni vni(long value) {
        throw new UnsupportedOperationException("OFBsnTlvVni not supported in version 1.0");
    }

    public OFBsnTlvVpnKey.Builder buildVpnKey() {
        throw new UnsupportedOperationException("OFBsnTlvVpnKey not supported in version 1.0");
    }
    public OFBsnTlvVpnKey vpnKey(long value) {
        throw new UnsupportedOperationException("OFBsnTlvVpnKey not supported in version 1.0");
    }

    public OFBsnTlvVrf.Builder buildVrf() {
        throw new UnsupportedOperationException("OFBsnTlvVrf not supported in version 1.0");
    }
    public OFBsnTlvVrf vrf(long value) {
        throw new UnsupportedOperationException("OFBsnTlvVrf not supported in version 1.0");
    }

    public OFBsnTlvVxlanEgressLag vxlanEgressLag() {
        throw new UnsupportedOperationException("OFBsnTlvVxlanEgressLag not supported in version 1.0");
    }

    public OFMessageReader<OFBsnTlv> getReader() {
        throw new UnsupportedOperationException("Reader<OFBsnTlv> not supported in version 1.0");
    }


    public OFVersion getVersion() {
            return OFVersion.OF_10;
    }
}
