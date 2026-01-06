package com.example.session11.service;

import com.example.session11.model.dto.request.ProductDTO;
import com.example.session11.model.dto.response.DataResponse;
import com.example.session11.model.entity.Product;
import com.example.session11.repository.ProductRepository;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<DataResponse<?>> addProduct(ProductDTO productDTO, BindingResult bindingResult) {
        if (productDTO.getImage() == null || productDTO.getImage().isEmpty()) {
            bindingResult.rejectValue("image", "error.image","Image can not null");
            logger.error("Error : image file null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            Product product = convertProductDTOToProduct(productDTO);
            String imageUrl = cloudinaryService.upload(productDTO.getImage());
            product.setImage(imageUrl);
           Product newProduct = productRepository.save(product);
              DataResponse<Product> dataResponse = DataResponse
                     .<Product>builder()
                     .message("Add product successful")
                     .data(newProduct)
                     .status(201)
                     .build();
           logger.info("Add product successful");
           return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
        }
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    public ResponseEntity<DataResponse<?>> updateProduct(ProductDTO productDTO,long id) {
        Product oldProduct = findById(id);
        if(oldProduct != null){
            Product product = convertProductDTOToProduct(productDTO);
            product.setId(id);
            if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
                String imageUrl = cloudinaryService.upload(productDTO.getImage());
                product.setImage(imageUrl);
            }else {
                product.setImage(oldProduct.getImage());
            }
            try {
                logger.info("Product before update: " + oldProduct);
                Product updatedProduct = productRepository.save(product);
                DataResponse<Product> dataResponse = DataResponse
                        .<Product>builder()
                        .message("Update product successful")
                        .data(updatedProduct)
                        .status(200)
                        .build();
                logger.info("Product after update: " + updatedProduct);

                return new ResponseEntity<>(dataResponse, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Update product failed : " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            logger.error("Product not found with id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<DataResponse<String>> deleteProduct(long id) {
        Product product = findById(id);
        if (product != null) {
            try {
                productRepository.delete(product);
                DataResponse<String> dataResponse = DataResponse
                        .<String>builder()
                        .message("Delete product successful")
                        .data("Product with id " + id + " has been deleted.")
                        .status(200)
                        .build();
                logger.info("Delete product successful");
                return new ResponseEntity<>(dataResponse, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Delete product failed : " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            DataResponse<String> dataResponse = DataResponse
                    .<String>builder()
                    .message("Product not found")
                    .data("Product with id " + id + " not found.")
                    .status(404)
                    .build();
            logger.error("Product not found with id: " + id);
            return new ResponseEntity<>(dataResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<DataResponse<Page<Product>>> findAllAndSearch(Pageable pageable , String searchName) {
        if (searchName != null && !searchName.isEmpty()) {
            logger.info("Search Product :" + searchName);
        }
            Page<Product> page = productRepository.findAllAndSearchName(searchName, pageable);
            DataResponse<Page<Product>> dataResponse = DataResponse
                    .<Page<Product>>builder()
                    .message("List products successful")
                    .data(page)
                    .status(200)
                    .build();
            logger.info("Find all and search products successful : " + page.getContent().size() + " product !" );
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);

    }

    public ResponseEntity<DataResponse<List<Product>>> getProductSuggestions() {
        String lastSearchLog = findLastSearchProductLog();
        if (lastSearchLog == null || lastSearchLog.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    DataResponse.<List<Product>>builder()
                            .message("No search log found")
                            .data(Collections.emptyList())
                            .status(400)
                            .build()
            );
        }

        List<Product> products = productRepository.getProductSuggestions(lastSearchLog, PageRequest.of(0, 10)).getContent();
        DataResponse<List<Product>> dataResponse = DataResponse
                .<List<Product>>builder()
                .message("List products successful")
                .data(products)
                .status(200)
                .build();
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    public static String findLastSearchProductLog() {
        File file = new File("app.log");
        if (!file.exists()) {
            System.err.println("Log file does not exist.");
            return null;
        }

        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lineTrimmed = line.substring(22);
                if (lineTrimmed.startsWith("Search Product :")) {
                    return lineTrimmed.substring("Search Product :".length()).trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
            return null;
        }
        return null;
    }

    public Product convertProductDTOToProduct(ProductDTO productDTO) {
        return Product.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
    }

}
