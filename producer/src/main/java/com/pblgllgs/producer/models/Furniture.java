package com.pblgllgs.producer.models;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Furniture {
    private String color;
    private String material;
    private String name;
    private int price;
}
