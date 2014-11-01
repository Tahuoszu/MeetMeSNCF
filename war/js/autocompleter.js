//Autocomplétion pour la recherche de gares de départ et d'arrivée
/*
var options, a;
jQuery(function() {
	options = {
		serviceUrl : '/search',
		type : "POST",
		minChars : 2
	}
	a = $('#depart').autocomplete(options);
});
*/

$(document).ready(function() {
	$(function() {
		$("#depart").autocomplete({
			minLength : 2,
			source : function(request, response) {
				$.ajax({
					url : "search",
					type : "POST",
					data : {
						depart : request.term
					},
					dataType : "json",
					success : function(data) {
						response(data);
					}
				});
			},
			focus : function(event, ui) {
		        $("#depart").val(ui.item.value );
		        return false;
		    },
			select : function(event, ui) {
				$('#depart').val(ui.item.value);
                return false; 
	        },
		    change : function(event, ui) {
		    	$('#depart').val(ui.item.value);
                return false; 
		    }
		});
	});
});

$(document).ready(function() {
	$(function() {
		$("#arrivee").autocomplete({
			minLength : 2,
			source : function(request, response) {
				$.ajax({
					url : "search",
					type : "POST",
					data : {
						arrivee : request.term
					},
					dataType : "json",
					success : function(data) {
						response(data);
					}
				});
			},
			focus : function(event, ui) {
		        $("#arrivee").val(ui.item.value );
		        return false;
		    },
			select : function(event, ui) {
				$('#arrivee').val(ui.item.value);
                return false; 
	        },
		    change : function(event, ui) {
		    	$('#arrivee').val(ui.item.value);
                return false; 
		    }
		});
	});
});