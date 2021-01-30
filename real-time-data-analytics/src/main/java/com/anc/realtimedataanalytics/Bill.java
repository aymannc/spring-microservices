package com.anc.realtimedataanalytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    private Long id;
    private String customerName;
    private Float total;
}
