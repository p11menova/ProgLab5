package org.example.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;


public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
    public static java.util.List<String> getNamesArray(){
        return Arrays.asList(getNames(TicketType.class));
    }
}
