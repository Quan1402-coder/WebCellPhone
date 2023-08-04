package com.charles.website.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.charles.website.entity.CartItem;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.dto.CartItemDTO;
import com.charles.website.model.request.CartItemRequest;
import com.charles.website.services.CartItemService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("")
    public ResponseEntity<?> getAllItem() {
        Authen.check();

        List<CartItem> listCartItem = cartItemService.getList();
        List<CartItemDTO> listDTO = listCartItem.stream().map(item -> new CartItemDTO(item)).collect(Collectors.toList());

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemDetail(@PathVariable("id")Long id) {
        Authen.check();
        CartItem cartItem = cartItemService.getDetail(id);
        return ResponseEntity.ok(new CartItemDTO(cartItem));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody CartItemRequest req) {
        Authen.check();
        cartItemService.add(req);
        return ResponseEntity.ok(new MessageResponse("Create item is success"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateItem(@RequestParam("id") Long id,
                                        @RequestBody CartItemRequest req) {
        Authen.check();
        cartItemService.update(id, req);
        return ResponseEntity.ok(new MessageResponse("Update item is success"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestParam("id") Long id) {
        Authen.check();
        cartItemService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Delete item is success"));
    }
}
