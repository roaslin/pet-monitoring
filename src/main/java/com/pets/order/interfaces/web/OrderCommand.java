package com.pets.order.interfaces.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
@ToString
public class OrderCommand {

    private final String id;
    private final String description;

}
