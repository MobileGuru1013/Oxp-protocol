package org.onosproject.oxp.protocol.errormsg;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.oxp.protocol.OXPObject;
import org.onosproject.oxp.protocol.OXPType;
import org.onosproject.oxp.protocol.OXPVersion;
import org.onosproject.oxp.protocol.OXPDomainConfigFaliedCode;
import org.onosproject.oxp.protocol.OXPErrorMsg;
import org.onosproject.oxp.protocol.OXPErrorType;
import org.onosproject.oxp.types.OXPErrorCauseData;

/**
 * Created by cr on 16-7-17.
 */
public interface OXPDomainConfigFailedErrorMsg extends OXPObject, OXPErrorMsg {
    OXPVersion getVersion();
    OXPType getType();
    long getXid();
    OXPErrorType getErrType();
    OXPDomainConfigFaliedCode getCode();
    OXPErrorCauseData getData();

    void writeTo(ChannelBuffer channelBuffer);

    public interface Builder extends OXPErrorMsg.Builder {
        OXPDomainConfigFailedErrorMsg build();
        OXPVersion getVersion();
        OXPType getType();
        long getXid();
        Builder setXid(long xid);
        OXPErrorType getErrType();
        OXPDomainConfigFaliedCode getCode();
        Builder setCode(OXPDomainConfigFaliedCode code);
        OXPErrorCauseData getData();
        Builder setData(OXPErrorCauseData data);
    }
}
