angular.module('app', [])
	.controller('BeanController', [ '$scope', '$http', function($scope, $http) {
		$scope.url = null;
		$scope.mensaje = "";
		
		$scope.inicializar = function(url) {			
			
			return $http.post(url + "?modo=1", {}, {headers: {'Content-Type': 'application/json'}
			}).then(function(response) {				
				$scope.bean = response.data;
			}, function(response) {
				debugger;
			});
		};
		
		$scope.enviar = function(url) {			
			var json = angular.toJson($scope.bean, false);
			return $http.post(url + "?modo=2", json, {headers: {'Content-Type': 'application/json'}
			}).then(function(response) {				
				$scope.mensaje = response.data.mensaje;
			}, function(response) {
				debugger;
			});
		};				
	} ]);