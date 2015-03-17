package com.samsung.sdsc.training.java.wanghaihua;

public class Product {
	
	private String id;
	
	public Product(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String value) {
		if (id == null || !id.equals(value)) {
			id = value;
		}
	}

	@Override
	public String toString() {
		return "Product [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
