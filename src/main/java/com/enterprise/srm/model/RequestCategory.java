package com.enterprise.srm.model;

public enum RequestCategory {
    IT_SUPPORT("IT Support"),
    FACILITY("Facility Issue"),
    HR_DOCUMENT("HR Document"),
    OFFICE_SUPPLIES("Office Supplies"),
    ACCESS_PERMISSION("Access / Permission"),
    OTHER("Other");

    private final String displayName;

    RequestCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
