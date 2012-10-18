/*
 * Classe que trata as mensagens(Seta o valor, monstra a mensagem na tela oe apaga a mensagem da tela)
 *  
 */
;(function(window){
  
  var CONTAINER = $( $('div.alert').get(0) );
  var TIMEOUT = 10000;
  
  var Message = window.Message = function(){
  };
  Message.fn = Message.prototype;
  
  Message.set = function(isMessageError, content) {
    if ($.type(isMessageError) != "boolean" || $.type(content) != "string") return;
    
    Message.display(true);
    CONTAINER.html(content);
    if (isMessageError) {
      CONTAINER.addClass('alert-error');
      CONTAINER.removeClass('alert-success');
    }else{
      CONTAINER.addClass('alert-success');
      CONTAINER.removeClass('alert-error');
    }
    
  };
  
  var timeout = null;
  Message.display = function(isVisible) {
    if (isVisible) {
      
      CONTAINER.show();
      
      if (timeout) timeout.not_call = true;
      timeout = desappearMessage();
      setTimeout(timeout.callback, TIMEOUT);
      
    }else{
      CONTAINER.hide();
    }
  };

  var desappearMessage = function(){
    var obj = {
      not_call: false,
      callback: function(){
        if (obj.not_call) return;
        Message.display(false); 
      }
    };
    return obj;
  };
  
})(window);
