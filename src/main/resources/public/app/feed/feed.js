'use strict';

angular.module('myApp.feed', ['ngRoute'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/feed', {
      templateUrl: 'feed/feed.html',
      controller: 'feedCtrl'
    });
  }])

  .controller('feedCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.messages = [
      {name:'Loading',message:'Messages Coming Soon!'}
    ];

    $scope.inputBox = 'two way binding is awesome!';

    $scope.submitInput = function() {
      var message = $scope.inputBox;
      console.log("Submitting: " + message);

      $http({
        method: 'POST',
        url: '/api/v1/messages',
        data: {
          name: 'User',
          message: message
        }
      }).then(function(response) {
        $scope.messages = response.data;
        $scope.inputBox = '';
      })
    };

    $http({
      method: 'GET',
      url: '/api/v1/messages'
    }).then(function(response) {
      $scope.messages = response.data;
    })


  }]);