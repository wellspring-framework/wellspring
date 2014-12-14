package org.wellspring.crud.to;

import java.io.Serializable;

public class GenericTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {

		return java.util.Objects.toString(this);
	}

}