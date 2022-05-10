	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, November 2005
	
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	
	
	Terms of use:
	You are free to use this script as long as the copyright message is kept intact. However, you may not
	redistribute, sell or repost it without our permission.
	
	Thank you!
	
	www.dhtmlgoodies.com
	Alf Magne Kalleland
	
	************************************************************************************************************/


	
	var arrowImageHeight = 35;	// Height of arrow image in pixels;
	var displayWaitMessage=true;	// Display a please wait message while images are loading?
	
	var previewImage = false;
	var previewImageParent = false;
	var slideSpeed = 0;
	var previewImagePane = false;
	var slideEndMarker = false;
	var galleryContainer = false;
	var imageGalleryCaptions = new Array();	


	function getTopPos(inputObj){	
	  var returnValue = inputObj.offsetTop;
	  while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetTop;
	  return returnValue;
	}
	
	function getLeftPos(inputObj){
	  var returnValue = inputObj.offsetLeft;
	  while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetLeft;
	  return returnValue;
	}
		
	
	//,,,largeImageCaption
	function showPreview(newSrc,imageIndex,prevPane,largeImageCaption, waitMsg){
		if(!previewImage){
			var images = document.getElementById(prevPane).getElementsByTagName('IMG');
			if(images.length>0){
				this.previewImage = images[0];
			}else{
				this.previewImage = document.createElement('IMG');
				document.getElementById(prevPane).appendChild(this.previewImage);	
			}
			
			
		}
		if(displayWaitMessage){
			document.getElementById(waitMsg).style.display='inline';
		}
		document.getElementById(largeImageCaption).style.display='none';
		this.previewImage.onload = function() {
			hideWaitMessageAndShowCaption(imageIndex-1,largeImageCaption,waitMsg);
		};				
		this.previewImage.src = newSrc;
		
	}

	function hideWaitMessageAndShowCaption(imageIndex, largeImageCaption, waitMsg){
		document.getElementById(waitMsg).style.display='none';	
		document.getElementById(largeImageCaption).innerHTML = imageGalleryCaptions[imageIndex];
		document.getElementById(largeImageCaption).style.display='block';
		
	}

	
	SlideShow.prototype.hideWaitMessageAndShowCaption2 = function(imageIndex, largeImageCaption, waitMsg){
		document.getElementById(waitMsg).style.display='none';	
		document.getElementById(largeImageCaption).innerHTML = this.imageGalleryCaptions[imageIndex];
		document.getElementById(largeImageCaption).style.display='block';
		
	}
	

	function initSlide(e){
		if(document.all)e = event;
		
		if(this.src.indexOf('over')<0)this.src = this.src.replace('.gif','-over.gif');
		
		slideSpeed = e.clientY + Math.max(document.body.scrollTop,document.documentElement.scrollTop) - getTopPos(this);
		if(this.src.indexOf('down')>=0){
			slideSpeed = (slideSpeed)*-1;	
		}else{
			slideSpeed = arrowImageHeight - slideSpeed;
		}
		slideSpeed = Math.round(slideSpeed * 10 / arrowImageHeight);
	}
	
	function stopSlide(){
		slideSpeed = 0;
		this.src = this.src.replace('-over','');
	}
	
	function slidePreviewPane(){
		if(slideSpeed!=0){
			var topPos = previewImagePane.style.top.replace(/[^\-0-9]/g,'')/1;	
		
			if(slideSpeed<0 && slideEndMarker.offsetTop<(previewImageParent.offsetHeight - topPos)){
				slideSpeed=0;			
			}
			topPos = topPos + slideSpeed;
			if(topPos>0)topPos=0;

		 	previewImagePane.style.top = topPos + 'px';
	 	
		}
	 	setTimeout('slidePreviewPane()',30);		
	}
	
	function revealThumbnail(){
		this.style.filter = 'alpha(opacity=100)';
		this.style.opacity = 1;
	}
	
	function hideThumbnail(){
		this.style.filter = 'alpha(opacity=95)';
		this.style.opacity = 0.5;
	}
		
	//container,theimages,slideend,imagecaption,arrow_up_image,arrow_down_image
	function initGalleryScript(gc,ti,se,ic,up,dw){
		previewImageParent = document.getElementById(ti);
		previewImagePane = document.getElementById(ti).getElementsByTagName('DIV')[0];
		previewImagePane.style.top = '0px';
		galleryContainer  = document.getElementById(gc);
		var images = this.previewImagePane.getElementsByTagName('IMG');
		for(var no=0;no<images.length;no++){
			images[no].onmouseover = revealThumbnail;
			images[no].onmouseout = hideThumbnail;
		}	
		this.slideEndMarker = document.getElementById(se);
		
		document.getElementById(up).onmousemove = initSlide;
		document.getElementById(up).onmouseout = stopSlide;
		
		document.getElementById(dw).onmousemove = initSlide;
		document.getElementById(dw).onmouseout = stopSlide;
		var divs = previewImageParent.getElementsByTagName('DIV');
		for(var no=0;no<divs.length;no++){
			if(divs[no].className==ic)imageGalleryCaptions[imageGalleryCaptions.length] = divs[no].innerHTML;
		}		
		slidePreviewPane();		
	}
	
