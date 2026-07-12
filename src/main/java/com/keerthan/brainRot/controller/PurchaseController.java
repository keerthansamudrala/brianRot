package com.keerthan.brainRot.controller;

import com.keerthan.brainRot.dto.purchase.PurchaseRequestDTO;
import com.keerthan.brainRot.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("posts/preview")
    public ResponseEntity<String> buyPost(@RequestBody PurchaseRequestDTO dto) {
        String result = purchaseService.buyPost(dto);
        if (result.contains("failed") || result.contains("not available") || result.contains("already bought")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
