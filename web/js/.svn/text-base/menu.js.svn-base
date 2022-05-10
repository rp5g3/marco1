function toggleMenu(event){
	

	var link = $(event.target)
	var buttonId = event.target.parentNode.id;
	
	if (typeof(link.attr('href')) == 'undefined' && $('>ul', event.target.parentNode).length > 0){
		event.stopPropagation();
		event.preventDefault();
	}
	else if(typeof(link.attr('href')) == 'string' &&  link.attr('href').length == 0){
		event.stopPropagation();
		event.preventDefault();
	}
	else{
		return;
	}
	var childUl = $($('>ul', event.target.parentNode).get(0))
	if ( link.is(".expanded") || link.is(".collapsed") ){
		link.toggleClass("expanded");
		link.toggleClass("collapsed");
	}
	if (childUl.is(":hidden")){
	    $('#'+buttonId).attr("class", "MainMenuNodeExpanded");
	    childUl.slideDown("fast");
	} else {
		$('#'+buttonId).attr("class", "MainMenuNode");
		childUl.slideUp("fast");
	}
	closeAllExpandedMenu(buttonId);
}

function closeAllExpandedMenu(exceptThis){
	if(exceptThis != "projects"){
		$("#projects").attr("class", "MainMenuNode");
		$("#projects").children("ul").slideUp("fast");
	}
	if(exceptThis != "billing"){
		$("#billing").attr("class", "MainMenuNode");	
		$("#billing").children("ul").slideUp("fast");
	}
	if(exceptThis != "general_cruds"){
		$("#general_cruds").attr("class", "MainMenuNode");
		$("#general_cruds").children("ul").slideUp("fast");
	}
}

$(document).ready(function (){$(".MainMenuNode>a").click(toggleMenu)})
$(document).ready(function (){$(".ExpandNode>a").click(toggleMenu)})
