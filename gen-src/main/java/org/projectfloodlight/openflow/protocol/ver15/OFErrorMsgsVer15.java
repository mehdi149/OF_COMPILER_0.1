// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_factory_class.java
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


public class OFErrorMsgsVer15 implements OFErrorMsgs {
    public final static OFErrorMsgsVer15 INSTANCE = new OFErrorMsgsVer15();

    private final XidGenerator xidGenerator = XidGenerators.global();



    public OFBadActionErrorMsg.Builder buildBadActionErrorMsg() {
        return new OFBadActionErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFBadRequestErrorMsg.Builder buildBadRequestErrorMsg() {
        return new OFBadRequestErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFFlowModFailedErrorMsg.Builder buildFlowModFailedErrorMsg() {
        return new OFFlowModFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFHelloFailedErrorMsg.Builder buildHelloFailedErrorMsg() {
        return new OFHelloFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFPortModFailedErrorMsg.Builder buildPortModFailedErrorMsg() {
        return new OFPortModFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFQueueOpFailedErrorMsg.Builder buildQueueOpFailedErrorMsg() {
        return new OFQueueOpFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFBadInstructionErrorMsg.Builder buildBadInstructionErrorMsg() {
        return new OFBadInstructionErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFBadMatchErrorMsg.Builder buildBadMatchErrorMsg() {
        return new OFBadMatchErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFGroupModFailedErrorMsg.Builder buildGroupModFailedErrorMsg() {
        return new OFGroupModFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFSwitchConfigFailedErrorMsg.Builder buildSwitchConfigFailedErrorMsg() {
        return new OFSwitchConfigFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFTableModFailedErrorMsg.Builder buildTableModFailedErrorMsg() {
        return new OFTableModFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFRoleRequestFailedErrorMsg.Builder buildRoleRequestFailedErrorMsg() {
        return new OFRoleRequestFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFBsnError.Builder buildBsnError() {
        return new OFBsnErrorVer15.Builder().setXid(nextXid());
    }

    public OFMeterModFailedErrorMsg.Builder buildMeterModFailedErrorMsg() {
        return new OFMeterModFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFTableFeaturesFailedErrorMsg.Builder buildTableFeaturesFailedErrorMsg() {
        return new OFTableFeaturesFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFAsyncConfigFailedErrorMsg.Builder buildAsyncConfigFailedErrorMsg() {
        return new OFAsyncConfigFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFBadPropertyErrorMsg.Builder buildBadPropertyErrorMsg() {
        return new OFBadPropertyErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFBundleFailedErrorMsg.Builder buildBundleFailedErrorMsg() {
        return new OFBundleFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFFlowMonitorFailedErrorMsg.Builder buildFlowMonitorFailedErrorMsg() {
        return new OFFlowMonitorFailedErrorMsgVer15.Builder().setXid(nextXid());
    }

    public OFMessageReader<OFErrorMsg> getReader() {
        return OFErrorMsgVer15.READER;
    }

    public long nextXid() {
        return xidGenerator.nextXid();
    }

    public OFVersion getVersion() {
            return OFVersion.OF_15;
    }
}
