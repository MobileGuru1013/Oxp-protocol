package org.onosproject.oxp.protocol.errormsg;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.oxp.protocol.OXPErrorMsg;
import org.onosproject.oxp.protocol.OXPErrorType;
import org.onosproject.oxp.protocol.OXPHelloFailedCode;
import org.onosproject.oxp.protocol.OXPObject;
import org.onosproject.oxp.protocol.OXPType;
import org.onosproject.oxp.protocol.OXPVersion;
import org.onosproject.oxp.types.OXPErrorCauseData;

/**
 * Created by cr on 16-7-17.
 */
public interface OXPHelloFailedErrorMsg extends OXPObject, OXPErrorMsg {
    OXPVersion getVersion();
    OXPType getType();
    long getXid();
    OXPErrorType getErrType();
    OXPHelloFailedCode getCode();
    OXPErrorCauseData getData();

    void writeTo(ChannelBuffer channelBuffer);

    public interface Builder extends OXPErrorMsg.Builder {
        OXPHelloFailedErrorMsg build();
        OXPVersion getVersion();
        OXPType getType();
        long getXid();
        Builder setXid(long xid);
        OXPErrorType getErrType();
        OXPHelloFailedCode getCode();
        Builder setCode(OXPHelloFailedCode code);
        OXPErrorCauseData getData();
        Builder setData(OXPErrorCauseData data);
    }
}
