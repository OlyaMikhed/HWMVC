package by.mikhed.HWMVC.controller;

import by.mikhed.HWMVC.entity.Product;
import by.mikhed.HWMVC.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping({"/", "/index"})
    public ModelAndView index(Model model) {
        model.addAttribute("message", this.message);
        return new ModelAndView("index");
    }

    @GetMapping("/all")
    public ModelAndView getAllProductsPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return new ModelAndView("productlist");
    }

    @GetMapping("/add")
    public ModelAndView getAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return new ModelAndView("addproduct");
    }

    @GetMapping("/edit")
    public ModelAndView getEditPage(Model model, Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return new ModelAndView("editproduct");
    }

    @PostMapping("/add")
    public ModelAndView add(Model model, @ModelAttribute("product") Product product) {
        String title = product.getTitle();
        String description = product.getDescription();
        if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(description)) {
            productService.save(new Product(title, description));
            List<Product> products = productService.findAll();
            model.addAttribute("products", products);
            return new ModelAndView("redirect:/product/all");
        }

        model.addAttribute("errorMessage", this.errorMessage);
        return new ModelAndView("addproduct");
    }

    @PostMapping("/edit")
    public ModelAndView edit(Model model, Product product) {
        if (!StringUtils.isEmpty(product.getDescription()) && !StringUtils.isEmpty(product.getTitle())) {
            productService.update(product);
            return new ModelAndView("redirect:/product/all");
        }

        model.addAttribute("errorMessage", this.errorMessage);
        return new ModelAndView("editproduct");
    }

    @PostMapping("/delete")
    public ModelAndView delete(Model model, Integer id) {
        productService.deleteById(id);
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return new ModelAndView("productlist");
    }
}
