// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_class.java
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
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.ImmutableSet;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

class OFSetConfigVer14 implements OFSetConfig {
    private static final Logger logger = LoggerFactory.getLogger(OFSetConfigVer14.class);
    // version: 1.4
    final static byte WIRE_VERSION = 5;
    final static int LENGTH = 12;

        private final static long DEFAULT_XID = 0x0L;
        private final static Set<OFConfigFlags> DEFAULT_FLAGS = ImmutableSet.<OFConfigFlags>of();
        private final static int DEFAULT_MISS_SEND_LEN = 0x0;

    // OF message fields
    private final long xid;
    private final Set<OFConfigFlags> flags;
    private final int missSendLen;
//
    // Immutable default instance
    final static OFSetConfigVer14 DEFAULT = new OFSetConfigVer14(
        DEFAULT_XID, DEFAULT_FLAGS, DEFAULT_MISS_SEND_LEN
    );

    // package private constructor - used by readers, builders, and factory
    OFSetConfigVer14(long xid, Set<OFConfigFlags> flags, int missSendLen) {
        if(flags == null) {
            throw new NullPointerException("OFSetConfigVer14: property flags cannot be null");
        }
        this.xid = xid;
        this.flags = flags;
        this.missSendLen = missSendLen;
    }

    // Accessors for OF message fields
    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_14;
    }

    @Override
    public OFType getType() {
        return OFType.SET_CONFIG;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public Set<OFConfigFlags> getFlags() {
        return flags;
    }

    @Override
    public int getMissSendLen() {
        return missSendLen;
    }



    public OFSetConfig.Builder createBuilder() {
        return new BuilderWithParent(this);
    }

    static class BuilderWithParent implements OFSetConfig.Builder {
        final OFSetConfigVer14 parentMessage;

        // OF message fields
        private boolean xidSet;
        private long xid;
        private boolean flagsSet;
        private Set<OFConfigFlags> flags;
        private boolean missSendLenSet;
        private int missSendLen;

        BuilderWithParent(OFSetConfigVer14 parentMessage) {
            this.parentMessage = parentMessage;
        }

    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_14;
    }

    @Override
    public OFType getType() {
        return OFType.SET_CONFIG;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public OFSetConfig.Builder setXid(long xid) {
        this.xid = xid;
        this.xidSet = true;
        return this;
    }
    @Override
    public Set<OFConfigFlags> getFlags() {
        return flags;
    }

    @Override
    public OFSetConfig.Builder setFlags(Set<OFConfigFlags> flags) {
        this.flags = flags;
        this.flagsSet = true;
        return this;
    }
    @Override
    public int getMissSendLen() {
        return missSendLen;
    }

    @Override
    public OFSetConfig.Builder setMissSendLen(int missSendLen) {
        this.missSendLen = missSendLen;
        this.missSendLenSet = true;
        return this;
    }


        @Override
        public OFSetConfig build() {
                long xid = this.xidSet ? this.xid : parentMessage.xid;
                Set<OFConfigFlags> flags = this.flagsSet ? this.flags : parentMessage.flags;
                if(flags == null)
                    throw new NullPointerException("Property flags must not be null");
                int missSendLen = this.missSendLenSet ? this.missSendLen : parentMessage.missSendLen;

                //
                return new OFSetConfigVer14(
                    xid,
                    flags,
                    missSendLen
                );
        }

    }

    static class Builder implements OFSetConfig.Builder {
        // OF message fields
        private boolean xidSet;
        private long xid;
        private boolean flagsSet;
        private Set<OFConfigFlags> flags;
        private boolean missSendLenSet;
        private int missSendLen;

    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_14;
    }

    @Override
    public OFType getType() {
        return OFType.SET_CONFIG;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public OFSetConfig.Builder setXid(long xid) {
        this.xid = xid;
        this.xidSet = true;
        return this;
    }
    @Override
    public Set<OFConfigFlags> getFlags() {
        return flags;
    }

    @Override
    public OFSetConfig.Builder setFlags(Set<OFConfigFlags> flags) {
        this.flags = flags;
        this.flagsSet = true;
        return this;
    }
    @Override
    public int getMissSendLen() {
        return missSendLen;
    }

    @Override
    public OFSetConfig.Builder setMissSendLen(int missSendLen) {
        this.missSendLen = missSendLen;
        this.missSendLenSet = true;
        return this;
    }
//
        @Override
        public OFSetConfig build() {
            long xid = this.xidSet ? this.xid : DEFAULT_XID;
            Set<OFConfigFlags> flags = this.flagsSet ? this.flags : DEFAULT_FLAGS;
            if(flags == null)
                throw new NullPointerException("Property flags must not be null");
            int missSendLen = this.missSendLenSet ? this.missSendLen : DEFAULT_MISS_SEND_LEN;


            return new OFSetConfigVer14(
                    xid,
                    flags,
                    missSendLen
                );
        }

    }


    final static Reader READER = new Reader();
    static class Reader implements OFMessageReader<OFSetConfig> {
        @Override
        public OFSetConfig readFrom(ByteBuf bb) throws OFParseError {
            int start = bb.readerIndex();
            // fixed value property version == 5
            byte version = bb.readByte();
            if(version != (byte) 0x5)
                throw new OFParseError("Wrong version: Expected=OFVersion.OF_14(5), got="+version);
            // fixed value property type == 9
            byte type = bb.readByte();
            if(type != (byte) 0x9)
                throw new OFParseError("Wrong type: Expected=OFType.SET_CONFIG(9), got="+type);
            int length = U16.f(bb.readShort());
            if(length != 12)
                throw new OFParseError("Wrong length: Expected=12(12), got="+length);
            if(bb.readableBytes() + (bb.readerIndex() - start) < length) {
                // Buffer does not have all data yet
                bb.readerIndex(start);
                return null;
            }
            if(logger.isTraceEnabled())
                logger.trace("readFrom - length={}", length);
            long xid = U32.f(bb.readInt());
            Set<OFConfigFlags> flags = OFConfigFlagsSerializerVer14.readFrom(bb);
            int missSendLen = U16.f(bb.readShort());

            OFSetConfigVer14 setConfigVer14 = new OFSetConfigVer14(
                    xid,
                      flags,
                      missSendLen
                    );
            if(logger.isTraceEnabled())
                logger.trace("readFrom - read={}", setConfigVer14);
            return setConfigVer14;
        }
    }

    public void putTo(PrimitiveSink sink) {
        FUNNEL.funnel(this, sink);
    }

    final static OFSetConfigVer14Funnel FUNNEL = new OFSetConfigVer14Funnel();
    static class OFSetConfigVer14Funnel implements Funnel<OFSetConfigVer14> {
        private static final long serialVersionUID = 1L;
        @Override
        public void funnel(OFSetConfigVer14 message, PrimitiveSink sink) {
            // fixed value property version = 5
            sink.putByte((byte) 0x5);
            // fixed value property type = 9
            sink.putByte((byte) 0x9);
            // fixed value property length = 12
            sink.putShort((short) 0xc);
            sink.putLong(message.xid);
            OFConfigFlagsSerializerVer14.putTo(message.flags, sink);
            sink.putInt(message.missSendLen);
        }
    }


    public void writeTo(ByteBuf bb) {
        WRITER.write(bb, this);
    }

    final static Writer WRITER = new Writer();
    static class Writer implements OFMessageWriter<OFSetConfigVer14> {
        @Override
        public void write(ByteBuf bb, OFSetConfigVer14 message) {
            // fixed value property version = 5
            bb.writeByte((byte) 0x5);
            // fixed value property type = 9
            bb.writeByte((byte) 0x9);
            // fixed value property length = 12
            bb.writeShort((short) 0xc);
            bb.writeInt(U32.t(message.xid));
            OFConfigFlagsSerializerVer14.writeTo(bb, message.flags);
            bb.writeShort(U16.t(message.missSendLen));


        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("OFSetConfigVer14(");
        b.append("xid=").append(xid);
        b.append(", ");
        b.append("flags=").append(flags);
        b.append(", ");
        b.append("missSendLen=").append(missSendLen);
        b.append(")");
        return b.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OFSetConfigVer14 other = (OFSetConfigVer14) obj;

        if( xid != other.xid)
            return false;
        if (flags == null) {
            if (other.flags != null)
                return false;
        } else if (!flags.equals(other.flags))
            return false;
        if( missSendLen != other.missSendLen)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime *  (int) (xid ^ (xid >>> 32));
        result = prime * result + ((flags == null) ? 0 : flags.hashCode());
        result = prime * result + missSendLen;
        return result;
    }

}