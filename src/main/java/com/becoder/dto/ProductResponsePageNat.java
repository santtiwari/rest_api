package com.becoder.dto;

import java.util.List;

public class ProductResponsePageNat {

	private List<ProductDto> products;
	private long totalElements;
	private int totalPages;
	private Boolean isFirst;
	private Boolean isLast;
	private int pageNo;
	private int pageSize;

	// Default constructor
	public ProductResponsePageNat() {}

	// Constructor for builder
	private ProductResponsePageNat(Builder builder) {
		this.products = builder.products;
		this.totalElements = builder.totalElements;
		this.totalPages = builder.totalPages;
		this.isFirst = builder.isFirst;
		this.isLast = builder.isLast;
		this.pageNo = builder.pageNo;
		this.pageSize = builder.pageSize;
	}

	// Getters and Setters
	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// Static inner Builder class
	public static class Builder {
		private List<ProductDto> products;
		private long totalElements;
		private int totalPages;
		private Boolean isFirst;
		private Boolean isLast;
		private int pageNo;
		private int pageSize;

		public Builder products(List<ProductDto> products) {
			this.products = products;
			return this;
		}

		public Builder totalElements(long totalElements) {
			this.totalElements = totalElements;
			return this;
		}

		public Builder totalPages(int totalPages) {
			this.totalPages = totalPages;
			return this;
		}

		public Builder isFirst(Boolean isFirst) {
			this.isFirst = isFirst;
			return this;
		}

		public Builder isLast(Boolean isLast) {
			this.isLast = isLast;
			return this;
		}

		public Builder pageNo(int pageNo) {
			this.pageNo = pageNo;
			return this;
		}

		public Builder pageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public ProductResponsePageNat build() {
			return new ProductResponsePageNat(this);
		}
	}
}
