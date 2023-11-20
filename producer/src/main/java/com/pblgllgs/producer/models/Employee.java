package com.pblgllgs.producer.models;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {

    @JsonProperty("employee_id")
    private String employeeId;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
