package org.wellspring.crud.persistence.specification;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.wellspring.crud.persistence.domain.User;

@StaticMetamodel(User.class)
public class User_ {

	public static volatile SingularAttribute<User, Integer> id;

	public static volatile SingularAttribute<User, String> name;

	public static volatile SingularAttribute<User, String> lastName;

	public static volatile SingularAttribute<User, String> email;

	// public static volatile SetAttribute<Order, Item> items;

	// public static volatile SingularAttribute<Order, BigDecimal> totalCost;

}