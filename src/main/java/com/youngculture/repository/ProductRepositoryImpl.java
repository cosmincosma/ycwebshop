package com.youngculture.repository;

import com.youngculture.model.Category;
import com.youngculture.model.Product;
import com.youngculture.utils.CategoryUtil;
import com.youngculture.utils.FileReaderUtil;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> getAllProducts(InputStream inputStream) {
        return FileReaderUtil.readAllProducts(inputStream);
    }

    @Override
    public List<Product> getAllProductsFromASpecificCategory(InputStream inputStream, String categoryString) {
        Category category = CategoryUtil.processCategory(categoryString);

        List<Product> allProducts = FileReaderUtil.readAllProducts(inputStream);

        return allProducts.stream().
                filter(product -> product.getCategory() == category).
                collect(Collectors.toList());
    }

    @Override
    public Integer countProducts(InputStream inputStream) {
        List<Product> allProducts = FileReaderUtil.readAllProducts(inputStream);
        return allProducts.size();
    }

    @Override
    public Integer countProductsFromASpecificCategory(InputStream inputStream, String categoryString) {
        Category category = CategoryUtil.processCategory(categoryString);

        List<Product> allProducts = FileReaderUtil.readAllProducts(inputStream);

        return Math.toIntExact(allProducts.stream().
                filter(product -> product.getCategory() == category)
                .count());
    }

}
