'use strict';

angular.module('angularFrontendApp').config(function($stateProvider) {
	$stateProvider.state('users', {
		url : '/users',
		templateUrl : 'app/users/users.html',
		controller : 'UsersCtrl'
	}).state('/users/new', {
		url : '/users/new',
		templateUrl : 'app/users/add.html',
		controller : 'UsersCtrl'
	}).state('/users/:id/view', {
		url : '/users/:id/view',
		templateUrl : 'app/users/view.html',
		controller : 'UsersCtrl'
	}).state('/users/:id/edit', {
		url : '/users/:id/edit',
		templateUrl : 'app/users/edit.html',
		controller : 'UsersCtrl'
	});
});