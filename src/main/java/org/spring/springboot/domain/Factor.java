package org.spring.springboot.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Factor {
    private Long id;
    private Long factorId;
    private Long factorName;
}
