'use strict';

angular.module('angularFrontendApp').config(function($stateProvider) {
	$stateProvider.state('products', {
		url : '/products',
		templateUrl : 'app/products/products.html',
		controller : 'ProductsCtrl'
	}).state('/products/new', {
		url : '/products/new',
		templateUrl : 'app/products/add.html',
		controller : 'ProductsCtrl'
	}).state('/products/:id/view', {
		url : '/products/:id/view',
		templateUrl : 'app/products/view.html',
		controller : 'ProductsCtrl'
	}).state('/products/:id/edit', {
		url : '/products/:id/edit',
		templateUrl : 'app/products/edit.html',
		controller : 'ProductsCtrl'
	});
});