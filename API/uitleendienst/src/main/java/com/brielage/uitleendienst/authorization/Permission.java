package com.brielage.uitleendienst.authorization;

public enum Permission {
    ADMIN("Admins-WebApplication");

    public final String group;

    Permission (final String group) {this.group = group;}
}
