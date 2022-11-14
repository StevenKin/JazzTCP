package com.github.jazztcp;

import org.pcap4j.core.PcapAddress;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.Inet4NetworkAddress;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.List;

public class NetworkUtils {
    public static PcapNetworkInterface getNetworkInterface(String ip) throws Exception {
        InetAddress addr = InetAddress.getByName(ip);
        PcapNetworkInterface networkInterface = Pcaps.getDevByAddress(addr);
        return networkInterface;
    }

    public static PcapNetworkInterface getHardwareNetworkInterface() throws Exception {
        List<PcapNetworkInterface> interfaces = Pcaps.findAllDevs();
        PcapNetworkInterface result = null;
        outer : for (PcapNetworkInterface networkInterface : interfaces) {
            List<PcapAddress> addresses = networkInterface.getAddresses();
            for (PcapAddress address : addresses) {
                if (address.getAddress() instanceof Inet4Address) {
                    result = networkInterface;
                    break outer;
                }
            }
        }
        if (result != null) {
            return result;
        }
        throw new RuntimeException("not find hardware network interface");
    }

    public static void main(String[] args) throws Exception {
        PcapNetworkInterface networkInterface = getNetworkInterface("192.168.0.107");
        System.out.println(networkInterface);

        networkInterface = getHardwareNetworkInterface();
        System.out.println(networkInterface);
    }
}
