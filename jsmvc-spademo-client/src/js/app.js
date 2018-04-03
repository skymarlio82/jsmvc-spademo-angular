'use strict';

[ 'nav', 'footer' ].forEach(function (c) {
	var mod = 'layout' + c.toUpperCase().substring(0, 1) + c.substring(1);
	angular.module(mod, []);
	angular.module(mod).component(mod, {
		templateUrl : "fragment/" + c + ".htm"
	});
});

var app = angular.module('jsmvcAngularjs1SpaApp', [
	'ui.router',
	'infrastructure',
	'layoutNav',
	'layoutFooter',
	'eventDetail'
]);

app.config([
	'$stateProvider',
	'$urlRouterProvider',
	'$locationProvider',
	'$httpProvider',
	function ($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {
		$httpProvider.defaults.headers.common["Cache-Control"] = 'no-cache';
		$httpProvider.interceptors.push('HttpErrorHandlingInterceptor');
		$locationProvider.hashPrefix('!');
		$urlRouterProvider.otherwise('/');
		$stateProvider.state('app', {
			abstract : true,
			url : '',
			template : '<ui-view></ui-view>'
		}).state('index', {
	        parent : 'app',
	        url : '/',
	        template : '<event-list-home></event-list-home>'
	    }).state('allEvents', {
	        parent : 'app',
	        url : '/events/all',
	        template : '<event-list-home></event-list-home>'
	    }).state('openingEvents', {
	        parent : 'app',
	        url : '/events/opening',
	        template : '<event-list-home></event-list-home>'
	    }).state('closedEvents', {
	        parent : 'app',
	        url : '/events/closed',
	        template : '<event-list-home></event-list-home>'
	    });
	}
]);

angular.module('jsmvcAngularjs1SpaApp').controller('EventListHomeController', [
	'$http',
	'$location',
	function ($http, $location) {
		var self = this;
		switch ($location.path()) {
			case '/':
				$http.get('api/events').then(function (resp) {
					self.events = resp.data;
				});
				break;
			case '/events/all':
				$http.get('api/events/by?category=all').then(function (resp) {
					self.events = resp.data;
				});
				break;
			case '/events/opening':
				$http.get('api/events/by?category=Opening').then(function (resp) {
					self.events = resp.data;
				});
				break;
			case '/events/closed':
				$http.get('api/events/by?category=Closed').then(function (resp) {
					self.events = resp.data;
				});
				break;
			default:
				break;
		}
		self.closeEvent = function (event) {
			$http.put('api/events/' + event.id + '/closed').then(function (resp) {
				$(self.events).each(function (index, elem) {
					if (elem.id == event.id) {
						self.events[index].status = 'Closed';
						return false;
					}
				});
			});
		}
		self.deleteEvent = function (event) {
			$http.delete('api/events/' + event.id + '/delete').then(function (resp) {
				$(self.events).each(function (index, elem) {
					if (elem.id == resp.data.id) {
						self.events.splice(index, 1);
						return false;
					}
				});
			});
		}
	}
]);

angular.module('jsmvcAngularjs1SpaApp').component('eventListHome', {
	templateUrl : 'fragment/event-list.template.htm',
	controller : 'EventListHomeController'
});
