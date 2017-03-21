angular
    .module('newsRadar')
    .component('feedAdd',{
        templateUrl: 'assets/news-radar-gui/scripts/feedadd.template.html',
        controller: function FeedAddController($http, $rootScope) {

            this.addFeed = function(feed) {

                $http({
                    method: 'POST',
                    url: jsRoutes.controllers.FeedListController.add().url,
                    data: feed
                }).then(function successCallback(response){
                    $rootScope.$broadcast('feedAddEvent', {
                        id: response.data,
                        name: feed.feedName,
                        address: feed.feedAddress
                    });
                });
            };
        }
    });

