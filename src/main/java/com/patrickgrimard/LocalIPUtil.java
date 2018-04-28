package com.patrickgrimard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * ip工具类
 * 
 * @author chanceli
 * @since 2018/2/7
 */
public class LocalIPUtil {
	private static Logger log = LoggerFactory.getLogger(LocalIPUtil.class);

	private LocalIPUtil() {
	}

	/**
	 * 获取本地ip地址
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static List<String> getLocalAddr() {
		List<String> results = new ArrayList<>();
		Enumeration<NetworkInterface> allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();

			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						results.add(ip.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
			log.error("getLocalAddr error "+e.getMessage());
		}
		return results;
	}
}
