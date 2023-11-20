package com.pblgllgs.producer.models;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picture {

    private String name;
    private String type;
    private String source;
    private long size;
}
