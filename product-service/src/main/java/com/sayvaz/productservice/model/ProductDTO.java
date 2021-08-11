package com.sayvaz.productservice.model;

import java.io.Serializable;
import java.math.BigDecimal;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO implements Serializable{
	
	private Integer productId;
	private String name;
	private String description;
	private BigDecimal price;	
	
	private static final long serialVersionUID = 3108875703816424265L;

}
