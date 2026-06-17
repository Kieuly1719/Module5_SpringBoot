package com.codegym.shoppingcart.controller;

import com.codegym.shoppingcart.model.CartItem;
import com.codegym.shoppingcart.model.Product;
import com.codegym.shoppingcart.service.ProductService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;
    private List<CartItem> getCart(HttpSession session){
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null){
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("total", calculateTotal(cart));
        return "cart/view";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findById(id).orElseThrow();
        List<CartItem> cart = getCart(session);

        boolean existed = false;

        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                existed = true;
                break;
            }
        }

        if (!existed) {
            cart.add(new CartItem(product, 1));
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                if (quantity <= 0) {
                    cart.remove(item);
                } else {
                    item.setQuantity(quantity);
                }
                break;
            }
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getProduct().getId().equals(id));

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);

        model.addAttribute("cart", cart);
        model.addAttribute("total", calculateTotal(cart));

        return "cart/checkout";
    }

    @PostMapping("/checkout")
    public String completeCheckout(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/products";
    }

    private double calculateTotal(List<CartItem> cart) {
        return cart.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
