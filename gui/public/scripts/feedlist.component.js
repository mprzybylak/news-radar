angular
    .module('newsRadar')
    .component('feedList',{
        templateUrl: 'assets/news-radar-gui/scripts/feedlist.template.html',
        controller: function FeedListController($http, $rootScope) {
            var self = this;
            $http({
                method: 'GET',
                url: jsRoutes.controllers.FeedListController.load().url
            })
            .then(function successCallback(response){
                self.feeds = response.data
            });

            $rootScope.$on('feedAddEvent', function(event, feed){
                self.feeds.push(feed);
            });
        }
    });

