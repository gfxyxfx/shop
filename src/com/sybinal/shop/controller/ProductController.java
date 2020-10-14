package com.sybinal.shop.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sybinal.shop.common.PageInformation;
import com.sybinal.shop.model.Product;
import com.sybinal.shop.model.ProductCategory;
import com.sybinal.shop.model.ProductRelation;
import com.sybinal.shop.service.catalog.ProductCategoryService;
import com.sybinal.shop.service.catalog.ProductService;
import com.sybinal.shop.service.user.UserService;
import com.sybinal.shop.utils.UserUtils;

@Controller
public class ProductController {

	@Autowired
	UserService userService;

	@Autowired
	ProductCategoryService productCategoryService;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/admin/productList", method = RequestMethod.GET)
	public ModelAndView productList(@ModelAttribute Product product) {
		ModelAndView model = new ModelAndView();
		Map<Integer, String> categoryMap = new TreeMap<Integer, String>();
		//取得商品分类信息
		List<ProductCategory> productCategoryList = productCategoryService.getProductCategory();
		if (productCategoryList != null && productCategoryList.size() > 0) {
			categoryMap.put(0, "全部");
			for (ProductCategory productCategory : productCategoryList) {
				categoryMap.put(productCategory.getId(), productCategory.getName());
			}
		}
		model.addObject("categoryMap", categoryMap);
		model.setViewName("product/productInfoMain");
		return model;
	}

	@RequestMapping(value = "/admin/product/addEdit", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute ProductRelation product, Integer productId, HttpServletRequest request)
			throws IOException {
		ModelAndView model = new ModelAndView();
		Map<Integer, String> categoryMap = new TreeMap<Integer, String>();
		//取得商品分类信息
		List<ProductCategory> productCategoryList = productCategoryService.getProductCategory();
		if (productCategoryList != null && productCategoryList.size() > 0) {
			for (ProductCategory productCategory : productCategoryList) {
				categoryMap.put(productCategory.getId(), productCategory.getName());
			}
		}
		model.addObject("categoryMap", categoryMap);

		if (productId != null) {
			product = productService.getProductInfoById(productId);	
		}else{
			product.setHot(1);//默认为热销商品
			product.setInventoryFlag(0);//默认为更新库存
		}
		model.addObject("product", product);
		model.setViewName("product/productInfoEdit");
		return model;
	}

	@RequestMapping(value = "/admin/product/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveProduct(@RequestBody ProductRelation product)
			throws IllegalAccessException, InvocationTargetException {
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			product.setUpdateUserName(UserUtils.getUserName());
			i = productService.saveProductInfo(product);
			if (i > 0) {
				map.put("error", "0"); // 成功
				map.put("data", product); // 数据
			} else {
				map.put("error", "-1"); // 失败
			}
		} catch (Exception e) {
			map.put("error", "-1"); // 失败
		}
		return map;
	}

	@RequestMapping(value = "/admin/productInfoDataTable", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> productInfoDataTable(PageInformation pageInfo, Product product) {
		return productService.getProductInfoByCondition(pageInfo, product);
	}
}
