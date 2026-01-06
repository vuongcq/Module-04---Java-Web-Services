package com.example.session11.controller;

import com.example.session11.model.dto.request.ProductDTO;
import com.example.session11.model.dto.response.DataResponse;
import com.example.session11.model.entity.Product;
import com.example.session11.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<Page<Product>>> findAllAndSearch(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "searchName",required = false) String searchName) {
        return productService.findAllAndSearch(PageRequest.of(page, size), searchName);
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<?>> addProduct(@Valid @ModelAttribute ProductDTO productDTO , BindingResult bindingResult) {
        ResponseEntity<DataResponse<?>> response =  productService.addProduct(productDTO,bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(
                    DataResponse
                            .<Map<String, String>>builder()
                            .message("Add product failed")
                            .data(errorMap)  // Trả về Map chứa các thông điệp lỗi
                            .status(400)
                            .build()
            );
        }
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DataResponse<?>> updateProduct(@Valid @ModelAttribute ProductDTO productDTO, @PathVariable long id) {
        return productService.updateProduct(productDTO,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResponse<String>> deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/productSuggestions")
    public ResponseEntity<DataResponse<List<Product>>> getProductSuggestions() {
        return productService.getProductSuggestions();
    }


}
