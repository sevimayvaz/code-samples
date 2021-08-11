package com.sayvaz.productservice.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sayvaz.productservice.entity.Product;
import com.sayvaz.productservice.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Product> findAll() {
		String query = "SELECT product_id, name, description, price FROM Product";
		return jdbcTemplate.query(query, new ProductExtractor());
	}

	@Override
	public Optional<Product> findByProductId(Integer productId) {
		String query = "SELECT product_id, name, description, price FROM Product WHERE product_id = ?";
		return jdbcTemplate.query(query, new ProductExtractor(), productId).stream().findAny();
	}

	@Override
	public Product save(Product product) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String query = "INSERT INTO Product (name, description, price) VALUES (?, ?, ?)";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, product.getName());
				ps.setString(2, product.getDescription());
				ps.setBigDecimal(3, product.getPrice());
				
				return ps;
			}
		}, keyHolder);
		
		product.setProductId(keyHolder.getKey().intValue());
		return product;
	}

	@Override
	public void delete(Integer productId) {
		String query = "DELETE FROM Product WHERE product_id = ?";
		
		jdbcTemplate.update(query, productId);
		
	}

	@Override
	public void update(Product product) {
		String query = "UPDATE Product SET name = ?, description = ?, price = ? WHERE product_id = ?";
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, product.getName());
				ps.setString(2, product.getDescription());
				ps.setBigDecimal(3, product.getPrice());
				ps.setInt(4, product.getProductId());				
				return ps;
			}
		});
		
	}
	
	private class ProductExtractor implements ResultSetExtractor<List<Product>> {

		@Override
		public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {

			List<Product> resultList = new ArrayList<>();
			Product product = null;
			
			while(rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getBigDecimal("price"));
				resultList.add(product);
			}
			return resultList;
		}
		
	}

}
