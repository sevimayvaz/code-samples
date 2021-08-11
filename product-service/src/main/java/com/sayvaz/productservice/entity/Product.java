package com.sayvaz.productservice.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

	private Integer productId;
	private String name;
	private String description;
	private BigDecimal price;
}
