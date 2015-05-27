package com.ivocosme.gsonenhancingvolley.requests.objects;

public class JSONResponseObject {
    private int exampleId;
    private String exampleString;
    private boolean exampleBoolean;

    @Override
    public String toString() {
        return "JSONResponseObject{" +
                "exampleId=" + exampleId +
                ", exampleString='" + exampleString + '\'' +
                ", exampleBoolean=" + exampleBoolean +
                '}';
    }
}
