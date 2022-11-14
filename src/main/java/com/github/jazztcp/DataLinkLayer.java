package com.github.jazztcp;

import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapPacket;

public class DataLinkLayer implements PacketListener {

    @Override
    public void gotPacket(PcapPacket pcapPacket) {
        System.out.println(pcapPacket);
    }
}
