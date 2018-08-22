package org.onosproject.oxp.protocol.ver10;

import com.google.common.collect.ImmutableSet;
import com.google.common.hash.PrimitiveSink;
import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.oxp.exceptions.OXPParseError;
import org.onosproject.oxp.protocol.OXPConfigFlags;
import org.onosproject.oxp.protocol.OXPGetConfigReply;
import org.onosproject.oxp.protocol.OXPMessage;
import org.onosproject.oxp.protocol.OXPMessageReader;
import org.onosproject.oxp.protocol.OXPMessageWriter;
import org.onosproject.oxp.protocol.OXPType;
import org.onosproject.oxp.protocol.OXPVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by cr on 16-7-21.
 */
public class OXPGetConfigReplyVer10 implements OXPGetConfigReply {
    private static final Logger logger = LoggerFactory.getLogger(OXPGetConfigReplyVer10.class);

    // version: 1.0
    static final byte WIRE_VERSION = 1;
    static final int LENGTH = 12;

    private static final long DEFAULT_XID = 0x0L;
    private static final Set<OXPConfigFlags> DEFAULT_FLAGS = ImmutableSet.<OXPConfigFlags>of();
    private static final byte DEFAULT_PERIIOD = 5;
    private static final short DEFAULT_MISS_SEND_LENGTH = 0;

    // OXP msg fields:
    private final long xid;
    private final Set<OXPConfigFlags> flags;
    private final byte period;
    private final short missSendLength;

    // Immutable default instance
    static final OXPGetConfigReplyVer10 DEFAULT = new OXPGetConfigReplyVer10(DEFAULT_XID, DEFAULT_FLAGS, DEFAULT_PERIIOD, DEFAULT_MISS_SEND_LENGTH);

    OXPGetConfigReplyVer10(long xid, Set<OXPConfigFlags> flags, byte period, short missSendLength) {
        if ( flags == null) {
            throw new IllegalArgumentException("OXPGetConfigReplyVer10: property flags cannot be null");
        }
        this.xid = xid;
        this.flags = flags;
        this.period = period;
        this.missSendLength = missSendLength;
    }

    @Override
    public OXPVersion getVersion() {
        return OXPVersion.OXP_10;
    }

    @Override
    public OXPType getType() {
        return OXPType.OXPT_GET_CONFIG_REPLY;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public Set<OXPConfigFlags> getFlags() {
        return flags;
    }

    @Override
    public byte getPeriod() {
        return period;
    }

    @Override
    public short getMissSendLength() {
        return missSendLength;
    }

    static final Reader READER = new Reader();
    static class Reader implements OXPMessageReader<OXPGetConfigReply> {
        @Override
        public OXPGetConfigReply readFrom(ChannelBuffer bb) throws OXPParseError {
            int startIndex = bb.readerIndex();
            // version
            byte version = bb.readByte();
            // type
            byte type = bb.readByte();
            // length
            int length = bb.readShort();
            if (bb.readableBytes() + (bb.readableBytes() - startIndex) < length) {
                bb.readerIndex(startIndex);
                return null;
            }
            // xid
            long xid = bb.readInt();
            // flags
            Set<OXPConfigFlags> flags = OXPConfigFlagsSerializerVer10.readFrom(bb);
            // period
            byte period = bb.readByte();
            // miss_send_length
            short missSendLength = bb.readShort();
            return new OXPGetConfigReplyVer10(xid, flags, period, missSendLength);
        }
    }

    @Override
    public void writeTo(ChannelBuffer bb) {
        WRITER.write(bb, this);
    }

    static final Writer WRITER = new Writer();
    static class Writer implements OXPMessageWriter<OXPGetConfigReplyVer10> {
        @Override
        public void write(ChannelBuffer bb, OXPGetConfigReplyVer10 message) {
            // version
            bb.writeByte(OXPVersion.OXP_10.getWireVersion());
            // type
            bb.writeByte(OXPType.OXPT_GET_CONFIG_REPLY.value());
            // length
            bb.writeShort(LENGTH);
            // xid
            bb.writeInt((int) message.xid);
            // flags
            OXPConfigFlagsSerializerVer10.writeTo(bb, message.flags);
            // period
            bb.writeByte(message.period);
            // miss_send_length
            bb.writeShort(message.missSendLength);
        }
    }

    @Override
    public OXPMessage.Builder createBuilder() {
        return null;
    }

    static class Builder implements OXPGetConfigReply.Builder {
        private boolean xidSet;
        private  long xid;
        private boolean flagsSet;
        private  Set<OXPConfigFlags> flags;
        private boolean periodSet;
        private  byte period;
        private boolean missSendLengthSet;
        private  short missSendLength;

        @Override
        public OXPGetConfigReply build() {
            long xid = this.xidSet ? this.xid : DEFAULT_XID;
            Set<OXPConfigFlags> flags= this.flagsSet ? this.flags : DEFAULT_FLAGS;
            if (flags == null)
                throw new NullPointerException("Property flags must not be null");
            byte period = this.periodSet ? this.period : DEFAULT_PERIIOD;
            short missSendLength = this.missSendLengthSet ? this.missSendLength : DEFAULT_MISS_SEND_LENGTH;
            return new OXPGetConfigReplyVer10(xid, flags, period, missSendLength);
        }

        @Override
        public OXPVersion getVersion() {
            return OXPVersion.OXP_10;
        }

        @Override
        public OXPType getType() {
            return OXPType.OXPT_GET_CONFIG_REPLY;
        }

        @Override
        public long getXid() {
            return xid;
        }

        @Override
        public OXPGetConfigReply.Builder setXid(long xid) {
            this.xidSet = true;
            this.xid = xid;
            return this;
        }

        @Override
        public Set<OXPConfigFlags> getFlags() {
            return flags;
        }

        @Override
        public OXPGetConfigReply.Builder setFlags(Set<OXPConfigFlags> flags) {
            this.flagsSet = true;
            this.flags = flags;
            return this;
        }

        @Override
        public byte getPeriod() {
            return period;
        }

        @Override
        public OXPGetConfigReply.Builder setPeriod(byte period) {
            this.periodSet = true;
            this.period = period;
            return this;
        }

        @Override
        public short getMissSendLength() {
            return missSendLength;
        }

        @Override
        public OXPGetConfigReply.Builder setMissSendLength(short missSendLength) {
            this.missSendLengthSet = true;
            this.missSendLength = missSendLength;
            return this;
        }
    }

    @Override
    public void putTo(PrimitiveSink sink) {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OXPGetConfigReplyVer10 other = (OXPGetConfigReplyVer10) obj;

        if( xid != other.xid)
            return false;
        if (flags == null) {
            if (other.flags != null)
                return false;
        } else if (!flags.equals(other.flags))
            return false;
        if (period != other.period)
            return false;
        if( missSendLength != other.missSendLength)
            return false;
        return true;
    }
}
