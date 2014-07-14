'use strict';


/**
 *
 * @param $scope
 * @param $log
 */
function PostController( $scope, $state, $http ){

	$scope.LIST_STATE = "post";
	$scope.INSERT_STATE = "post.insert";
	$scope.EDIT_STATE = "post.edit";
	
	$scope.currentPost = {};
	$scope.posts = null;
	$scope.currentState = null;
	$scope.currentPage = {
		page : 0,
		size : 5
	};
	
    /*-------------------------------------------------------------------
	 * 		 				 	  NAVIGATIONS
	 *-------------------------------------------------------------------*/
    /**
     * 
     */
    $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
    	$scope.initialize(toState, toParams, fromState, fromParams);
    });
    
    /**
     * 
     */
    $scope.initialize = function( toState, toParams, fromState, fromParams )
    {
    	var state = $state.current.name;
    	
    	switch (state) {

            case $scope.LIST_STATE: {
                $scope.changeToList();
			}
			break;
			case $scope.INSERT_STATE: {
				$scope.changeToInsert();
			}
			break;
			case $scope.EDIT_STATE: {
				$scope.changeToEdit( toParams.id );
			}
			break;
			default : {
				$state.go( $scope.LIST_STATE );
			}
		}
    };
    
    $scope.changeToList = function(){
    	
    	$scope.currentPage.page = 0;
    	listPosts( $scope.currentPage );
    	
    	$scope.currentState = $scope.LIST_STATE;
    	$state.go( $scope.LIST_STATE );
    };
    
    $scope.changeToInsert = function(){
    	
    	$scope.currentPost = {};
    	
    	$scope.currentState = $scope.INSERT_STATE;
    	$state.go( $scope.INSERT_STATE );
    };
    
    $scope.changeToEdit = function( id ){
    	
    	$http.get('post/' + id )
			.success( function( result ){
				$scope.currentPost = result;
				
				$scope.currentState = $scope.EDIT_STATE;
				$state.go( $scope.EDIT_STATE );
			});
    };
    
    /*-------------------------------------------------------------------
	 * 		 				 	  BEHAVIORS
	 *-------------------------------------------------------------------*/
    
    $scope.insert = function(){
    	
    	$http.post('post', $scope.currentPost )
    		.success( function( result ){
    			$state.go( $scope.LIST_STATE );
    		});
    };
    
    $scope.update = function(){
    	
    	$http.put('post', $scope.currentPost )
    		.success( function( result ){
    			$state.go( $scope.LIST_STATE );
    		});
    };
    
    $scope.like = function( post ){
    	
    	$http.put('post/' + post.id + '/like' )
    		.success( function( result ){
    			post.likes = result.likes;
    		});
    };
    
    $scope.remove = function( idPost ){
    	
    	$http.delete('post/' + idPost )
    		.success( function( result ){
    			
    			for ( var i = 0; i < $scope.posts.length; i++ ){
    				
    				if ( $scope.posts[i].id == idPost ){
    					
    					$scope.posts.splice(i, 1);
    				}
    			}
    			
    		});
    };
    
    function listPosts( page ){
    	
    	$http.get('post?page=' + page.page + '&size=' + page.size )
	    	.success( function( result ){
	    		$scope.posts = result.content;
	    		$scope.currentPage.page = result.number + 1;
	    	});
    };
};