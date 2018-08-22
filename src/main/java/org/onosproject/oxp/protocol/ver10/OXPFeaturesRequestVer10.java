package org.onosproject.oxp.protocol.ver10;

import com.google.common.hash.PrimitiveSink;
import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.oxp.exceptions.OXPParseError;
import org.onosproject.oxp.protocol.OXPFeaturesRequest;
import org.onosproject.oxp.protocol.OXPMessageReader;
import org.onosproject.oxp.protocol.OXPMessageWriter;
import org.onosproject.oxp.protocol.OXPType;
import org.onosproject.oxp.protocol.OXPVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cr on 16-7-19.
 */
public class OXPFeaturesRequestVer10 implements OXPFeaturesRequest{
    private static final Logger logger = LoggerFactory.getLogger(OXPFeaturesRequestVer10.class);

    // version: 1.0
    static final byte WIRE_VERSION = 1;
    static final int LENGTH = 8;

    private static final long DEFAULT_XID = 0x0L;

    // OXP msg fields:
    private final long xid;

    // Immutable default instance
    static final OXPFeaturesRequestVer10 DEFAULT = new OXPFeaturesRequestVer10(DEFAULT_XID);

    OXPFeaturesRequestVer10(long xid) {
        this.xid = xid;
    }

    @Override
    public OXPVersion getVersion() {
        return OXPVersion.OXP_10;
    }

    @Override
    public OXPType getType() {
        return OXPType.OXPT_FEATURES_REQUEST;
    }

    @Override
    public long getXid() {
        return xid;
    }

    static final Reader READER = new Reader();
    static class Reader implements OXPMessageReader<OXPFeaturesRequest> {
        @Override
        public OXPFeaturesRequest readFrom(ChannelBuffer bb) throws OXPParseError {
            int startIndex = bb.readerIndex();
            // version
            byte version = bb.readByte();
            // type
            byte type = bb.readByte();
            // length
            int length = bb.readShort();
            // xid
            long xid = bb.readInt();
            return new OXPFeaturesRequestVer10(xid);
        }
    }

    @Override
    public void writeTo(ChannelBuffer bb) {
        WRITER.write(bb, this);
    }

    static final Writer WRITER = new Writer();
    static class Writer implements OXPMessageWriter<OXPFeaturesRequestVer10> {
        @Override
        public void write(ChannelBuffer bb, OXPFeaturesRequestVer10 message){
            //version
            bb.writeByte(WIRE_VERSION);
            //type
            bb.writeByte(OXPType.OXPT_FEATURES_REQUEST.value());
            //length
            bb.writeShort(LENGTH);
            //xid
            bb.writeInt((int) message.xid);
        }
    }

    @Override
    public Builder createBuilder() {
        return null;
    }

    static class Builder implements OXPFeaturesRequest.Builder {
        // OXP msg fileds
        private boolean xidSet;
        private long xid;

        @Override
        public OXPFeaturesRequest build() {
            long xid = this.xidSet ? this.xid : DEFAULT_XID;
            return new OXPFeaturesRequestVer10(xid);
        }

        @Override
        public OXPVersion getVersion() {
            return OXPVersion.OXP_10;
        }

        @Override
        public OXPType getType() {
            return OXPType.OXPT_FEATURES_REQUEST;
        }

        @Override
        public long getXid() {
            return xid;
        }

        @Override
        public OXPFeaturesRequest.Builder setXid(long xid) {
            this.xidSet = true;
            this.xid = xid;
            return this;
        }
    }

    @Override
    public void putTo(PrimitiveSink sink) {
        // TODO: 16-7-20
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OXPFeaturesRequestVer10 other = (OXPFeaturesRequestVer10) obj;

        if( xid != other.xid)
            return false;
        return true;
    }
}
