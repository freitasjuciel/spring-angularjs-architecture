'use strict';


/**
 *
 * @param $scope
 * @param $log
 */
function UsuarioController( $scope, $state, $http ){

	$scope.LIST_STATE = "usuario";
	
	$scope.INSERT_STATE = "usuario.insert";
	
	$scope.CONFIRM_STATE = "usuario.confirm";
	
	$scope.currentUsuario = {};
	$scope.currentState = null;
	$scope.usuarios = null;
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
			case $scope.CONFIRM_STATE: {
				$scope.changeToConfirm( toParams.id );
			}
			break;
			default : {
				$state.go( $scope.LIST_STATE );
			}
		}
    };
    
    $scope.changeToList = function(){
    	
    	$scope.currentPage.page = 0;
    	listUsuarios( $scope.currentPage );
    	
    	$scope.currentState = $scope.LIST_STATE;
    	$state.go( $scope.LIST_STATE );
    };
    
    $scope.changeToInsert = function(){
    	
    	$scope.currentUsuario = {};
    	$scope.currentState = $scope.INSERT_STATE;
    	$state.go( $scope.INSERT_STATE );
    };
    
    $scope.changeToConfirm = function( id ){
    	
    	$http.post('account/' + id + '/confirm', id).success( $state.go( "post" ) );
    };
    
    /*-------------------------------------------------------------------
	 * 		 				 	  BEHAVIORS
	 *-------------------------------------------------------------------*/
    
    $scope.insert = function(){
    	
    	$http.post('account', $scope.currentUsuario).success( $state.go( $scope.LIST_STATE ) );
    };
    
    function listUsuarios( page ){
    	
    	$http.get('account')
	    	.success( function( result ){
	    		$scope.usuarios = result.content;
	    		$scope.currentPage.page = result.number + 1;
	    	});
    };
};