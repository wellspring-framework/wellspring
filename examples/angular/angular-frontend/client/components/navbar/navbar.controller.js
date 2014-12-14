'use strict';

angular.module('angularFrontendApp').controller('NavbarCtrl',
		function($scope, $location) {
			$scope.menu = [ {
				'title' : 'Home',
				'link' : '/'
			}, {
				'title' : 'Users',
				'link' : '/users'
			}, {
				'title' : 'Products',
				'link' : '/products'
			} ];

			$scope.isCollapsed = true;

			$scope.isActive = function(route) {
				return route === $location.path();
			};
		});