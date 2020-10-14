package com.sybinal.shop.model;

import java.util.List;

public class ProductRelation extends Product {

		/* 一对多关系的Product Image */
	private List<ProductImage> productImageList;

	/**
	 * 一对多关系的Product Image
	 * 
	 * @return
	 */
	public List<ProductImage> getProductImageList() {
		return productImageList;
	}

	/**
	 * 一对多关系的Product Image
	 * 
	 * @return
	 */
	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
	}

	
}
