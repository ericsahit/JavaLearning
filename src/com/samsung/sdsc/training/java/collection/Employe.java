package com.samsung.sdsc.training.java.collection;

public class Employe {
	private int id;
	private String name;

	public Employe(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employe) {
			Employe e = (Employe) obj;
//			if (this.id == e.id
//					&& (this.name == e.name || (this.name != null && this.name
//							.equals(e.name)))) {
			if (this.id == e.id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
//		return this.id + this.name.hashCode();
		return this.id;
	}

	@Override
	public String toString() {
		return "[id=" + this.id + ", name=" + this.name + "]";
	}
}
