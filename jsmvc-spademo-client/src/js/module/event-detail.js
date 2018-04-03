'use strict';

angular.module('eventDetail', [ 'ui.router' ]).config([
	'$stateProvider',
	function ($stateProvider) {
		$stateProvider.state('eventDetail', {
			parent : 'app',
			url : '/events/:eventId',
			template : '<event-detail></event-detail>'
		}).state('createEvent', {
			parent : 'app',
			url : '/events/create',
			template : '<event-create></event-create>'
		});
	}
]);
