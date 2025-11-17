package com.detech.gsrt.config;

public class Views {
    public static class Public {}
    public static class Internal extends Public {}
    public static class Private extends Internal {}
    public static class Creation extends Public {}
    public static class Secret {}
}
