/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.rest.api.rest.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController
public class ProductServiceController {
    //method untuk menambahkan data secara manual
    private static Map<String, Product> productRepo = new HashMap<>();
   static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        honey.setPrice(20000);
        honey.setNumber(2);
        honey.setTotal();
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        almond.setPrice(20000);
        almond.setNumber(3);
        almond.setTotal();
        productRepo.put(almond.getId(), honey);
            
  
   }
   
   //method untuk delete data
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
       //kondisi dimana ketika ID tidak di temukan ketika delete
      if(!productRepo.containsKey(id))throw new ProductNotfoundException();
      productRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   //method untuk update product
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
       //kondisi dimana ketika update id tidak ditemukan 
      if(!productRepo.containsKey(id))throw new ProductNotfoundException();
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
   }
   
   //method untuk create product
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) 
   {
      //kondisi dimana jika ketika create data baru namun ID telah ada atau tidak boleh id sama
      if(productRepo.containsKey(product.getId())){ 
            return new ResponseEntity<>("Id Product is already exist", HttpStatus.OK); 
        }
      //kondisi jika ketika create data ID tidak di isi atau kosong
      else if(productRepo.containsKey(product.getId().equals("''"))){ 
            return new ResponseEntity<>("Id Product should be fill", HttpStatus.OK); 
        } 
      //kondisi jika success/berhasil meng create data
      else{
            productRepo.put(product.getId(), product);
            product.setTotal();
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }
   }
   //method untuk membaca atau menampilkan product
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
       
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}
