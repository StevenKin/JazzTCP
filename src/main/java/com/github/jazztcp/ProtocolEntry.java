package com.github.jazztcp;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;

import static com.github.jazztcp.NetworkUtils.getHardwareNetworkInterface;

public class ProtocolEntry {
    private static final int COUNT = 10;
    private static final int READ_TIMEOUT = 101;
    private static final int SNAPLEN = 65536;

    public static void main(String[] args) throws Exception {
        PcapNetworkInterface anInterface = getHardwareNetworkInterface();
        PcapHandle handle = anInterface.openLive(SNAPLEN, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);
        handle.loop(-1, new DataLinkLayer());
        handle.close();
    }
}
