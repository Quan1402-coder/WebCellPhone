package com.charles.website.controller;

import com.charles.website.entity.CartItem;
import com.charles.website.entity.Order;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.dto.CartItemDTO;
import com.charles.website.model.dto.OrderDTO;
import com.charles.website.model.request.CartItemRequest;
import com.charles.website.model.request.OrderRequest;
import com.charles.website.services.OrderService;
import com.charles.website.utils.Authen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> getAll() {
        Authen.check();

        List<Order> list = orderService.getList();
        List<OrderDTO> listDTO = list.stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getAllByUser() {
        Authen.check();

        List<Order> list = orderService.getListByUser();
        List<OrderDTO> listDTO = list.stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable("id")Long id) {
        Authen.check();

        Order order = orderService.getDetail(id);

        return ResponseEntity.ok(new OrderDTO(order));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest req) {
        Authen.check();
        orderService.add(req);
        return ResponseEntity.ok(new MessageResponse("Create Order is success"));
    }

    @PutMapping("/update-status")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> updateStatus(@RequestParam("id")Long id,
                                          @RequestParam("status")Integer status) {
        Authen.check();
        orderService.updateStatus(id, status);
        return ResponseEntity.ok(new MessageResponse("Update status Order is success"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> delete(@RequestParam("id")Long id) {
        Authen.check();
        orderService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Delete Order is success"));
    }
}
