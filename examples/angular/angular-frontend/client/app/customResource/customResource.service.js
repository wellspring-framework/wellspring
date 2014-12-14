'use strict';

angular.module('angularFrontendApp')
  .factory('CustomResource', function($resource){
	  return function (url, params, resourceType){
		    return $resource(
		      url, 
		      params,
		      {'get':    {method:'GET'},
		       //'query':  {method:'GET', isArray:false,url: url + '?page=' + page + '&size=' + size + '&sort=' + sort},
		       'query':  {method:'GET', isArray:false},
		       'update':   {method:'PUT'},
		       'delete':   {method:'DELETE'},
		       'validate':   {method:'POST'},
		      }
		    );
		  };
		});

