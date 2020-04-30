package com.uca.tarea.labo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Product;

@Controller
public class ProductController {
	
	private List<Product> productos = new ArrayList<Product>();
	
	@GetMapping("/compraProducto")
	public ModelAndView compraProducto() {
		ModelAndView mav = new ModelAndView();
		
		productos.add(new Product(0, "GTA5", 60));
		productos.add(new Product(1, "MINECRAFT", 20));
		productos.add(new Product(2, "BATTLEFIELD", 10));
		productos.add(new Product(3, "COD", 8));
		productos.add(new Product(4, "ASPHALT9", 20));
		
		mav.setViewName("index");
		mav.addObject("product", new Product());
		mav.addObject("producto", productos);
		return mav;
	}
	
	@PostMapping("/validar")
	@ResponseBody
	public ModelAndView validar(Product product) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resultado");
		if(productos.get(product.getId()).getCantidad() >= product.getCantidad()) {
			mav.addObject("resultado", "Has comprado el juego " + productos.get(product.getId()).getNombre() + " con exito!");
			return mav;
		}
		mav.addObject("resultado", "Se han acabado las existencias del juego. \n"
				+ "Solo existen " + productos.get(product.getId()).getCantidad() + " copias."
				);
		return mav;
	}
	
}