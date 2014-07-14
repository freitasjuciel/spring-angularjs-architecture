"use strict";

angular.module("jees", ['ui.router'])
	.config( function( $stateProvider, $urlRouterProvider ) {
		 
		// For any unmatched url, redirect to /state1
		$urlRouterProvider.otherwise("/");
		
		// Now set up the states
		$stateProvider.state('post', {
            url : "/",
            templateUrl : "site/view/post/post-view.html",
            controller : PostController
        })
        .state('post.insert', {
        	url : "novo"
        })
        .state('post.edit', {
        	url : ":id"
        });
		
        $stateProvider.state('usuario', {
            url : "/usuario/",
            templateUrl : "site/view/usuario/usuario-view.html",
            controller : UsuarioController
        })
        .state('usuario.insert', {
            url: "cadastro"
        })
        .state('usuario.confirm', {
            url: "confirmar/:id"
        });
        
	}).run( function( $rootScope, $http ){
		
		$http.get('account/authenticated')
	    	.success( function( result ){
	    		$rootScope.authenticatedUser = result;
	    	});
		
	});
