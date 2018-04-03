'use strict';

angular.module('eventDetail').component('eventDetail', {
	templateUrl : 'fragment/event-detail.template.htm',
	controller : 'EventDetailController'
});

angular.module('eventDetail').component('eventClose', {
	templateUrl : 'fragment/event-list.template.htm',
	controller : 'EventCloseController'
});

angular.module('eventDetail').component('eventDelete', {
	templateUrl : 'fragment/event-list.template.htm',
	controller : 'EventDeleteController'
});

angular.module('eventDetail').component('eventCreate', {
	templateUrl : 'fragment/event-form.template.htm',
	controller : 'EventDetailController'
});
