function nu(campo){
	var digits="0123456789"
	var campo_temp 
	for (var i=0;i<campo.value.length;i++){
		campo_temp=campo.value.substring(i,i+1) 
		if (digits.indexOf(campo_temp)==-1){
			campo.value = campo.value.substring(0,i);
			break;
		}
	}
}

function ValRG(numero){
	 /*
	 ##  Igor Carvalho de Escobar
	 ##    www.webtutoriais.com
	 ##   Java Script Developer
	 */
	 var numero = numero.split("");
	 tamanho = numero.length;
	 vetor = new Array(tamanho);
	
	if(tamanho>=1){
		vetor[0] = parseInt(numero[0]) * 2; 
	}
	if(tamanho>=2){
		vetor[1] = parseInt(numero[1]) * 3; 
	}
	if(tamanho>=3){
		 vetor[2] = parseInt(numero[2]) * 4; 
	}
	if(tamanho>=4){
	 	vetor[3] = parseInt(numero[3]) * 5; 
	}
	if(tamanho>=5){
	 	vetor[4] = parseInt(numero[4]) * 6; 
	}
	if(tamanho>=6){
	 	vetor[5] = parseInt(numero[5]) * 7; 
	}
	if(tamanho>=7){
	 	vetor[6] = parseInt(numero[6]) * 8; 
	}
	if(tamanho>=8){
	 	vetor[7] = parseInt(numero[7]) * 9; 
	}
	if(tamanho>=9){
	 	vetor[8] = parseInt(numero[8]) * 100; 
	}
	
	 total = 0;
	
	if(tamanho>=1){
	 	total += vetor[0];
	}
	if(tamanho>=2){
	 	total += vetor[1]; 
	}
	if(tamanho>=3){
	 	total += vetor[2]; 
	}
	if(tamanho>=4){
	 	total += vetor[3]; 
	}
	if(tamanho>=5){
	 	total += vetor[4]; 
	}
	if(tamanho>=6){
	 total += vetor[5]; 
	}
	if(tamanho>=7){
	 	total += vetor[6];
	}
	if(tamanho>=8){
	 	total += vetor[7]; 
	}
	if(tamanho>=9){
	 	total += vetor[8]; 
	}
	
	
	 resto = total % 11;
	if(resto!=0){		
		document.getElementById('rg').innerHTML="<font face=verdana size=2 color=red>RG Inv�lido!</font><br><br>";
		alert('O RG n�o est� preencido corretamente');
	}
	else{
		document.getElementById('rg').innerHTML="<font face=verdana size=2 color=forestgreen>RG V�lido!</font><br><br>";
	}
}

function validarRg(){

	if(document.getElementById('rg').value != ''){
		return ValRG(document.getElementById('rg').value);
	}else{
		alert('RG � um campo obrigat�rio');
		return false;
	}
	

}
