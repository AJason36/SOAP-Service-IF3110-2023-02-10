package com.soap.util;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPContextUtils {
    public static String extractIPAddress(SOAPMessageContext ctx) {
        try {
            Class<?> clazz = ctx.get("com.sun.xml.internal.ws.http.exchange").getClass();
            Method method = clazz.getDeclaredMethod("getRemoteAddress");
            method.setAccessible(true);
            InetSocketAddress addr = (InetSocketAddress) method.invoke(ctx.get("com.sun.xml.internal.ws.http.exchange"));
            return addr.getAddress().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String extractURLEndpoint(SOAPMessageContext ctx) {
        try {
            Class<?> clazz = ctx.get("com.sun.xml.internal.ws.http.exchange").getClass();
            Method method = clazz.getDeclaredMethod("getRequestURI");
            method.setAccessible(true);
            return ((java.net.URI) method.invoke(ctx.get("com.sun.xml.internal.ws.http.exchange"))).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String extractArgs(SOAPMessageContext ctx) {
        try {
            String soapBody = ctx.getMessage().getSOAPBody().getTextContent();
            String cleaned = soapBody.trim().replaceAll("\\s+", " ").replaceAll("\\n", ",");
            return String.join(", ", cleaned.split(" "));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
