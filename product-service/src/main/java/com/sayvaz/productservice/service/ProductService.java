package com.sayvaz.productservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.sayvaz.productservice.entity.Product;
import com.sayvaz.productservice.exception.ResourceNotFoundException;
import com.sayvaz.productservice.model.ProductDTO;
import com.sayvaz.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MessageSource messageSource;

	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	public ProductDTO retrieveProductById(Integer productId) {
		ProductDTO productDTO = null;
		
		Optional<Product> productOpt = productRepo.findByProductId(productId);
	
		if(productOpt.isPresent()) {
			productDTO  = modelMapper.map(productOpt.get(), ProductDTO.class);
		} else {
			throw new ResourceNotFoundException(messageSource.getMessage("exception.product.notfound", new Object[]{productId}, LocaleContextHolder.getLocale()));
		}
		
		return productDTO;
	}
	
}
