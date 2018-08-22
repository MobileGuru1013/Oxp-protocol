package org.onosproject.oxp.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.oxp.types.IPAddress;
import org.onosproject.oxp.types.IPAddressWithMask;
import org.onosproject.oxp.types.MacAddress;
import org.onosproject.oxp.types.OXPHost;

import java.util.List;

/**
 * Created by cr on 16-7-21.
 */
public interface OXPHostReply extends OXPObject, OXPMessage  {
    OXPVersion getVersion();
    OXPType getType();
    long getXid();
    List<OXPHost> getHosts();


    void writeTo(ChannelBuffer bb);

    Builder createBuilder();
    public interface Builder extends OXPMessage.Builder {
        OXPHostReply build();
        OXPVersion getVersion();
        OXPType getType();
        long getXid();
        Builder setXid(long xid);
        List<OXPHost> getHosts();
        Builder setHosts(List<OXPHost> hosts);
    }
}
