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

//Chat
var alreadyTalk = {};

function majChat(receiver) {
//  Si cest deja une personne avec laquelle on a une discussion en cours
    if(alreadyTalk[receiver] === undefined) {
        var menu = $('.dropdown-menu');

        // On ajoute le destinataire choisi dans le menu de Discussions
        $(menu).append(
                '<li><a href="#'+receiver+'">' + 
                receiver + 
        "</a></li>");
        alreadyTalk[receiver] = true;

        var a = $(menu).find("a").last();
        var receiverId = a.text();

        // On cache tous les divs de chat
        $(".chat").hide();
        // et on affiche celle correspondant au destinataire choisi
        $("div.chat[id='"+receiverId+"']").show();

        afficherDiscussionOnClick(a);
    }
}

function connectChat() {
    
    $.ajax({
        url: '/chat',
        method: 'GET',
        dataType: 'text',
        success: function(chatToken) {
            var channel = new goog.appengine.Channel(chatToken);
            var socket = channel.open();
            socket.onmessage = onMessage;
            socket.onerror = onError;
            socket.onclose = onClose;
        }   
    });
    
    onMessage = function(message) {
        var messageText = message.data;
        var array = messageText.split("/");
        var receiver = array[0];
        var chaine = '#'+receiver+' > div.center';
        var text = "<span class='receiver'>" + receiver + "</span> : " + array[1] +"<br />";
        
        // Lors de la reception dun message, on affiche celui-ci dans la discussion
        // correspondante, avec le nom de la personne qui la envoye
        $(chaine).append(text);
        
        $(".chat").hide();
        $("div.chat[id='"+receiver+"']").show();
        
        majChat(receiver);
        
    };
    
    onError =  function(err) {
        //alert("Error => " + err.description);
    };
    
    onClose =  function() {
        //alert("channel closed");
    };
}



// Lorsque lutilisateur clique sur le bouton Chatter (et ce faisant choisi un interlocuteur)
$(".talk").click( function() {
    
    var div = $(this).parent();
    var receiver = $(div).find('.destinataire').attr('value');

    majChat(receiver);
});

// Lorsque lutilisateur clique sur une personne dans la liste des
// discussion, on affiche la discussion correspondante.
function afficherDiscussionOnClick(a) {
    a.click(function() {
        var receiverId = $(this).text();
        $(".chat").hide();
        $("div.chat[id='"+receiverId+"']").show();
    });
}

function generateChats() {
    var inputs = $('.chooseReceiver .destinataire');
    inputs.each(function() {
        var chat = generateChat($(this).val());
        $("#chats").append(chat);
    });
}

// Genere le code html correspondant a une discussion avec une personne
function generateChat(receiver) {
    var chat = '<div class="chat" id="'+ receiver +'">'
            + '  <div class="center"></div>'
            + '  <div class="form-group">'
            + '    <textarea rows="3" cols="6" class="form-control message"></textarea>'
            + "  </div>"
            + '  <div class="form-group">'
            + '    <button type="button" class="btn btn-primary btn-xs send">Envoyer un message</button>'
            + "  </div>"
            + "</div>";
    return $(chat);
}

generateChats();

// Lorsque lutilisateur clique sur le bouton pour envoyer un message de chat
$('.send').click(function() {
    var button = $(this);
    var chat = button.parents(".chat");
    
    // On recupere le message, le login de lutilisateur 
    // et le login du destinataire (channelKey).
    var message = chat.find('.message').val();
    var login = chat.parent().find('.login').val();
    var channelKey = chat.attr('id');
        
    // On affiche le message que lutilisateur envoie.
    chat.find('.center').append("<span class='sender'>" + login + "</span> : " + message + "<br />");

    // Envoi du message au serveur via une requete POST
    $.ajax({
        url: '/chat',
        type: 'POST',
        data:{
            message:message,
            sender:login,
            channelKey:channelKey,
        },
        success: function(data) {},
        complete:function() {}      
    });
    
    // On vide les textareas
    $('.message').val('');
});

$(".chat").hide();


