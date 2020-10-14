package com.sybinal.shop.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sybinal.shop.common.ApiResponseEnum;
import com.sybinal.shop.common.ApiResponseObject;
import com.sybinal.shop.model.Product;
import com.sybinal.shop.service.catalog.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductApiController extends AbstractApiController {

	@Autowired
	ProductService productService;

	/**
	 * 获取分类商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject searchProductByCategory(@RequestBody Map<String,Object> reqMap) {
		
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), productService.getProductByCategory(reqMap));
	}

	/**
	 * 查询商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/search/product", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject searchProduct(@RequestBody Map<String,Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), productService.getProductRelation(reqMap));
	}
	
	/**
	 * 查询商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product/hot", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject searchProductByHot(@RequestBody Map<String,Object> reqMap) {
		List<Product> products=productService.getProduct(reqMap);
		if(products.size()==0){
			return this.reponseJSON(ApiResponseEnum.FAIL.getCode(), ApiResponseEnum.FAIL.getName(), null);
		}else{
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), products);
		}
	}
	
	/**
	 * 商品详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "catalog/product/details", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject productDetails(@RequestBody Map<String,Object> reqMap) {
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(), productService.getProductDetailsById(reqMap));
	}


	
}