function highlightRow(row){
	if(!$(row).hasClass("header")){
		$(".highlighted").removeClass("highlighted");
		$(row).addClass("highlighted");
	}
}

function getIdOfSelectedEntityPricelist(){
	var row = $(".highlighted");
    var id = row.find(".pricelist_id").html();
    if(id==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return id;
    }  
}

function getNameOfSelectedEntityPricelist(){
	var row = $(".highlighted");
    var name = row.find(".date_from").html();
    if(name==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return name;
    }  
}