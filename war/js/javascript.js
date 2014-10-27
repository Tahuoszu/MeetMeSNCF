// Validation cote client du formulaire dinscription
$(document).ready(function() {
    $('#registerForm').bootstrapValidator({
        fields: {
            login: {
                validators: {
                    notEmpty: {
                        message: "Vous n'avez pas saisi de login."
                    },
                    remote: {
    	                message: "Ce login est déjà utilisé.",
    	                url: '/registerValidation',
    	                data: {
                            type: 'login'
                        }
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: "Vous n'avez pas saisi de mot de passe."
                    },
                    identical: {
                        field: 'confirmation',
                        message: "Le mot de passe et sa confirmation diffèrent."
                    }
                }
            },
            confirmation: {
                validators: {
                    notEmpty: {
                        message: "Vous n'avez pas saisi de confirmation."
                    },
                    identical: {
                        field: 'password',
                        message: "Le mot de passe et sa confirmation diffèrent."
                    }
                }
            },
            email: {
                validators: {
                	notEmpty: {
                        message: "Vous n'avez pas saisi d'adresse e-mail."
                    },
                    emailAddress: {
                        message: "Veuillez fournir une adresse email valide."
                    },
                    remote: {
    	                message: "Cette adresse email est déjà utilisée.",
    	                url: '/registerValidation',
    	                data: {
                            type: 'email'
                        }
                    }
                }
            },
            presentation: {
                validators: {
                	notEmpty: {
                        message: "Vous n'avez pas saisi de présentation."
                    }
                }
            },
            sexe: {
                validators: {
                	notEmpty: {
                        message: "Veuillez indiquer votre sexe."
                    }
                }
            }
            
        }
    })
    
    // On n'affiche rien si c'est un succès
    .on('success.field.bv', function(e, data) {
        var $parent = data.element.parents('.form-group');
        // Remove the has-success class
        $parent.removeClass('has-success');
   })
    
    // Enabling the submit button all the time
    .on('status.field.bv', function (e, data) {
         data.bv.disableSubmitButtons(false);
    });
    
    $('#loginForm').bootstrapValidator({
        fields: {
            login: {
                validators: {
                    notEmpty: {
                        message: "Vous n'avez pas saisi de login."
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: "Vous n'avez pas saisi de mot de passe."
                    }
                }
            }
        }
    }) 

    // On n'affiche rien si c'est un succès
    .on('success.field.bv', function(e, data) {
        var $parent = data.element.parents('.form-group');
        // Remove the has-success class
        $parent.removeClass('has-success');
    })
    
    // Enabling the submit button all the time
    .on('status.field.bv', function (e, data) {
         data.bv.disableSubmitButtons(false);
    });
});

// Autocompletion pour la recherche de gares
var options, a;
jQuery(function(){
    options = { serviceUrl:'/gareSuggestions' };
    a = $('#depart').autocomplete(options);
});

