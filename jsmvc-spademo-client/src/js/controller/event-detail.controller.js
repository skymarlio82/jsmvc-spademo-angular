'use strict';

angular.module('eventDetail').controller('EventDetailController', [
	'$http',
	'$state',
	'$stateParams',
	function ($http, $state, $stateParams) {
		var self = this;
		if ($stateParams.eventId !== undefined) {
			$http.get('api/events/' + $stateParams.eventId).then(function (resp) {
				self.event = resp.data;
			});
		} else {
			self.event = {id : 0, title : '', description : '', owner : '', status : '', formEditable : false, startStr : '', endStr : ''};
		}
		$(".datePicker").datepicker({ autoclose : true });
		self.back2Home = function () {
			$state.go('index');
		}
		self.activeEditable = function () {
			self.event.formEditable = true;
		}
		self.updateEventDetail = function () {
			var id = self.event.id;
			$http.put('api/events/' + id, self.event).then(function () {
				$state.go('index');
			});
		}
		self.submitEventDetail = function () {
			$http.post('api/events', self.event).then(function () {
				$state.go('index');
			});
		}
	}
]);

angular.module('eventDetail').controller('EventCloseController', [
	'$http',
	'$state',
	'$stateParams',
	function ($http, $state, $stateParams) {
		$http.put('api/events/' + $stateParams.eventId + '/closed').then(function (resp) {
			console.log('api/events/' + $stateParams.eventId + '/closed', resp.data);
			$state.go('index');
		});
	}
]);

angular.module('eventDetail').controller('EventDeleteController', [
	'$http',
	'$state',
	'$stateParams',
	function ($http, $state, $stateParams) {
		$http.delete('api/events/' + $stateParams.eventId + '/delete').then(function (resp) {
			console.log('api/events/' + $stateParams.eventId + '/delete', resp.data);
			$state.go('index');
		});
	}
]);
