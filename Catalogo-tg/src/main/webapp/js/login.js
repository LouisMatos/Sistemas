$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});


function meuMouseOver()
{
    var div = document.getElementById('meulog');
 
    div.innerHTML = div.innerHTML + 'Você entrou no campo nome ';
}
 
function meuMouseOut()
{
    var div = document.getElementById('meulog');
 
    div.innerHTML = div.innerHTML + 'Você saiu do campo nome ';
}




function validarSenha(){
	   NovaSenha = document.getElementById('register-form:senha_cadastro').value;
	   CNovaSenha = document.getElementById('register-form:confirma_senha_cadastro').value;
	   if (NovaSenha != CNovaSenha) {
	      alert("SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS");
	      document.getElementById('register-form:confirma_senha_cadastro').value = "";
	   }else{
	      document.FormSenha.submit();
	   }
	}


/*função valida email*/
function isEmailUsuarioComun(){
	var x= document.getElementById('register-form:email_cadastro').value;
	var atpos=x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
		alert("Não é um endereço de e-mail válido");
		document.getElementById('register-form:email_cadastro').value = "";
	}else{
		return true
	}
}

function isEmailLogin(){
	var x= document.getElementById('login-form:email').value;
	var atpos=x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
		alert("Não é um endereço de e-mail válido");
		document.getElementById('login-form:email').value = "";
	}else{
		return true
	}
	
}
	

function limpa_campos_cad_usuario_comum(){
	document.getElementById("register-form:email_cadastro").value = "";
	document.getElementById("register-form:senha_cadastro").value = "";
	document.getElementById("register-form:confirma_senha_cadastro").value = "";
}

function limpa_campos_login(){
	document.getElementById("login-form:email").value = "";
	document.getElementById("login-form:senha").value = "";
}


