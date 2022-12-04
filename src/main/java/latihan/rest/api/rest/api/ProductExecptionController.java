/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.rest.api.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author asus
 */

//class untuk menampilkan pesan not found yang di panggil di kelas server controller
 @ControllerAdvice
public class ProductExecptionController  {
   @ExceptionHandler(value = ProductNotfoundException.class)
   public ResponseEntity<Object> exception(ProductNotfoundException exception) {
   return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
   }
}



