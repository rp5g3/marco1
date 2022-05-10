var overlay = {
	
	click_on_overlay_hide: true,

	show_loading_image: true,

	loading_image: "imgs/bigrotation2.gif",

	$: function(id){
		return document.getElementById(id);
	},

	init: function(){
		var ol_div = document.createElement("div");

		ol_div.id = "overlay";
		ol_div.style.display = "none";
		ol_div.onclick = (this.click_on_overlay_hide)? overlay.hide : null;

		if(this.show_loading_image){
			var l_img = document.createElement("img");

			l_img.src = this.loading_image;
			l_img.style.position = "absolute";
			l_img.style.top = "250px";
			l_img.style.left = "450px";

			ol_div.appendChild(l_img);
		}

		document.body.appendChild(ol_div);
	},

	show: function(){
		if(this.$("overlay")){
			this.$("overlay").style.display = "";
		}
	},

	hide: function(){
		if(overlay.$("overlay")){
			overlay.$("overlay").style.display = "none";
		}
	}

}

function ajaxdoPost3(formName,urlName, divId) {
	var kw = {
	     form: formName,
	        url: urlName,
	        handleAs:"text",
	        load: function(response){
	        	//overlay.show();
	        	node = dijit.byId(divId);	        		        	
	        	//node.setContent(response);	
			node.attr('content', response); 
	        	//overlay.hide();
	        },
	        error: function(data){
	        	if (data.number == 500){
	        		alert("Um erro inseperado ocorreu!"+
	        		"\nNão foi possível abrir a url "+ urlName +
	        		"\nPor Favor, nos reporte do problema enviando um email para suporte@adapit.com.br"+
	        		"\nCódigo do Erro:" +data);
	        	}
	            else alert("Um erro inesperado ocorreu! : Não foi possível efetivar a operação.\nCódigo do erro:" +data);
	        },
	        timeout: 600000
	 };
	//dojo.xhrGet(kw);  //Servlet get argement with doGet

	dojo.xhrPost(kw);  //Servlet get argement with doPost

}	

function ajaxdoPost(formName,urlName, divId) {
	var kw = {
	     form: dojo.byId(formName),
	        url: urlName,
	        handleAs:"text",
	        load: function(response){
	        	//overlay.show();
	        	node = dijit.byId(divId);	        		        	
	        	//node.setContent(response);	
			node.attr('content', response); 
	        	//overlay.hide();
	        },
	        error: function(data){
	        	if (data.number == 500){
	        		alert("Um erro inseperado ocorreu!"+
	        		"\nNão foi possível abrir a url "+ urlName +
	        		"\nPor Favor, nos reporte do problema enviando um email para suporte@adapit.com.br"+
	        		"\nCódigo do Erro:" +data);
	        	}
	            else alert("Um erro inesperado ocorreu! : Não foi possível efetivar a operação.\nCódigo do erro:" +data);
	        },
	        timeout: 600000
	 };
	//dojo.xhrGet(kw);  //Servlet get argement with doGet

	dojo.xhrPost(kw);  //Servlet get argement with doPost

}	

function ajaxdoGet(formName,urlName, divId) {
	var kw = {
	     form: dojo.byId(formName),
	        url: urlName,
	        handleAs:"text",
	        load: function(response){
	        	overlay.show();
	        	node = dijit.byId(divId);	        		        	
	        	//node.setContent(response);
			node.attr('content', response);	
	        	overlay.hide();
	        },
	        error: function(data){
	            alert("Um erro ocorreu! : Não foi possível abrir a página solicitada. " + data);
	        },
	        timeout: 600000
	 };
	dojo.xhrGet(kw);  //Servlet get argement with doGet

	//dojo.xhrPost(kw);  //Servlet get argement with doPost
	
}				

function openContent(url){
	window.location.replace ( url );
}



function adapitPost(urlName, divId) {
	overlay.show();
	openPost(urlName,divId);		
}

function openPost(urlName, divId) {
	var kw = {
			form: null,
	        url: urlName,
	        handleAs:"text",
	        load: function(response){         
	            	        	
	        	node = dijit.byId(divId);	        		        	
	        	//node.setContent(response);
			node.attr('content', response);	
	        		        	      	        	
	        },
	        error: function(data){
	            alert("Ocorreu algum erro com o servidor!");	 
	            overlay.hide();           
	        },
	        timeout: 600000
	 };		
	 dojo.xhrPost(kw);
	
}

function open(urlName, divId) {	
	var kw = {
			form: null,
	        url: urlName,
	        handleAs:"text",
	        load: function(response){         
	            	        	
	        	node = dijit.byId(divId);	        		        	
	        	//node.setContent(response);	
			node.attr('content', response);
	        		        	      	        	
	        },
	        error: function(data){
	            alert("Ocorreu algum erro com o servidor!");	 
	            overlay.hide();           
	        },
	        timeout: 600000
	 };	
	 dojo.xhrGet(kw);
	 	 
}

function adapitOpen(urlName, divId) {	
	overlay.show();
	open(urlName,divId);		 	 
}

function adapitOpenNoReport(urlName, divId) {	
	open(urlName,divId);		 	 
}

function adapitPut(urlName) {	
	var kw = {
			form: null,
	        url: urlName,
	        handleAs:"text",
	        load: function(response){	        	
	        	        	      	        	
	        },
	        error: function(data){
	            	            
	        },
	        timeout: 60000
	 };	
	 dojo.xhrGet(kw);	 	 
}

  
    
	function paginate(pageId,galleryId){
			var gallery=new virtualpaginate(pageId, 1);
			gallery.buildpagination(galleryId);
	}
	
	function modalImageWin(urlName,titulo) {
			if (window.showModalDialog) {
				window.showModalDialog(urlName,titulo,"dialogWidth:600px;dialogHeight:600px");
			} else {
				window.open(urlName,titulo,'height=500,width=600,toolbar=no,directories=no,status=no,continued from previous linemenubar=no,scrollbars=no,resizable=no ,modal=yes');
			}
	}
