function limpaCampos(){
				document.getElementById("form_aluno:registro_aluno").value = "";
				document.getElementById("form_aluno:nome_aluno").value = "";
			}



function validarCampos(){
	
	if(document.getElementById("form_aluno:registro_aluno").value == "" || document.getElementById("form_aluno:nome_aluno").value == "" ){
		alert("Preencha os campos para continuar!");
	}
	
}