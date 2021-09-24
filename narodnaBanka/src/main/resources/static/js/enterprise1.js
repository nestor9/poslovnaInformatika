function getRates(){
	readRates();
	var rate = [];
	
	$(document).on("click",'tr',function(event){
		highlightRow(this);
		$('#dataTable').on('click','tr', function(event){ //ili izbrisati ovu liniju
			rate.length = 0;
			var selectedRow = $(this);
			var td = selectedRow.children('td');
			for (var i = 0; i < td.length; ++i) {
				rate.push(td.eq(i).text());
			}
		}); //i ovu
		console.log(rate);
	});
	
	$(document).on("click", '#add', function(event){
		readCategories();
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		addRate();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
a();
		readCategories2();
		$('#updateModalScrollable').modal('show');
	});
	
//	$(document).on("click", "#doUpdate", function(event) {
//		updateRate();
//		$('#updateModalScrollable').modal('hide');
//	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		addRateToSelect2();
		$('#deletePromptModal').modal('show');
	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		deleteP();
		$('#deletePromptModal').modal('hide');
	});
}
var ratesArray = [];
function a(){
	$.ajax({
		url : "http://localhost:8080/salesystem/enterprises/all"
	}).then(
		function(data) {
			$("#stopaEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#stopaEditSelect').append($('<option>', { 
			        value: item.enterprise_id,
			        text : item.nameEnterprise
			    }));
			});	
		}
	);
}
function readCategories2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all"
	}).then(
		function(data) {
			$("#kategorijaIzmeniSelect").empty();		
			$.each(data, function (i, item) {
			    $('#kategorijaIzmeniSelect').append($('<option>', { 
			        value: item.city_id,
			        text : item.city_name
			    }));
			});	
		}
	);
}

function readCategories(){
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all"
	}).then(
		function(data) {
			$("#kategorijaSelect").empty();			
			$.each(data, function (i, item) {
			    $('#kategorijaSelect').append($('<option>', { 
			        value: item.city_id,
			        text : item.city_name

			    }));
			});	
		}
	);
}
function readRates() {
	$.ajax({
		url : "http://localhost:8080/salesystem/enterprises/all" 
	}).then(
			function(data, status, request) {
				console.log(data);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++) {
					console.log(data[i].enterprise_id)
					newRow = 
					"<tr>" 
						+ "<td class=\"name\">" + data[i].nameEnterprise + "</td>"
						+ "<td class=\"city_name\">" + data[i].address + "</td>"
                        + "<td class=\"city_name\">" + data[i].phone + "</td>" 
                        + "<td class=\"city_name\">" + data[i].fax + "</td>"
                        + "<td class=\"cityes\">" + data[i].cityDTO.city_name + "</td>"  +
					"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
}

function addRateToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/enterprises/all"
	}).then(
		function(data) {
			$("#stopaEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#stopaEditSelect').append($('<option>', { 
			        value: item.enterprise_id,
			        text : item.nameEnterprise
			    }));
			});	
		}
	);
}

function addRateToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/enterprises/all"
	}).then(
		function(data) {
			$("#stopaDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#stopaDeleteSelect').append($('<option>', { 
			        value: item.enterprise_id,
			        text : item.nameEnterprise
			    }));
			});	
		}
	);
}




function addRate(){
		var nazivInput = $('#nazivInput');
		var adresaInput = $('#adresaInput');
		var telefonInput = $('#telefonInput');
		var faxInput = $('#faxInput');
		var kategorijaSelect = $('#kategorijaSelect');
	
		var nameEnterprise = nazivInput.val();
		var address = adresaInput.val();
		var phone = telefonInput.val();
		var fax = faxInput.val();
		var cityDTO = kategorijaSelect.val();

		
		console.log('nameEnterprise: ' + nameEnterprise);
		console.log('address: ' + address);
		console.log('phone: ' + phone);
		console.log('fax: ' + fax);
		console.log('cityDTO' + cityDTO);
		
		if(nameEnterprise == null || address == null || phone == null){
			alert('Niste uneli potrebne podatke')
		}
		var params = {
			'nameEnterprise' : nameEnterprise,
			'address': address,
			'phone' : phone,
			'fax': fax,
			'cityDTO': {
				'city_id' : cityDTO
			}
	}
		
		$.ajax({
			url : 'http://localhost:8080/salesystem/enterprises/add',
			type:'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				console.log('...')
				alert('Dodata je novo preduzece')
				readRates();
				nazivInput.val("");
				adresaInput.val("");
				telefonInput.val("");
				faxInput.val("");
				kategorijaSelect.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

//EDIT

	$(document).on("click", '#fillOutRateFieldsEdit', function(event){
	//ovde bio id samo
		var id = document.getElementById("stopaEditSelect").value;
		if(!id) {
			alert("Morate izabrati preduzece da biste izvrsili izmenu");
		}
		var chosenRateData = ratesArray.filter(enterprise => enterprise.enterprise_id == id);
		console.log(chosenRateData[0].nameEnterpise);
		$('#nazivIzmeniInput').val(chosenRateData[0].nameEnterprise);
		/*$('#adresaIzmeniInput').val(chosenRateData[0].address);
		$('#telefonIzmeniInput').val(chosenRateData[0].phone);
		$('#faxIzmeniInput').val(chosenRateData[0].fax);*/
		event.preventDefault();
	});

	$(document).on("click", '#doUpdate', function(event){
		if(!document.getElementById("stopaEditSelect").value){
			alert('Izaberite preduzece za izmenu');
			return;
		}
	
	var nazivIzmeniInput = $('#nazivIzmeniInput');
	var adresaIzmeniInput = $('#datumIzmeniInput');
	var telefonIzmeniInput = $('#telefonIzmeniInput');
	var faxIzmeniInput = $('#faxIzmeniInput');
	var kategorijaIzmeniSelect = $('#kategorijaIzmeniSelect');
	var izaberiStopu = $('#stopaEditSelect');

	var nameEnterprise = nazivIzmeniInput.val();
	var address = adresaIzmeniInput.val();
	var phone = telefonIzmeniInput.val();
	var fax = faxIzmeniInput.val();

	var cityDTO = kategorijaIzmeniSelect.val();
	var izabranaStopa = izaberiStopu.val();

	//console.log('percentage: ' + percentage);
	//console.log('date: ' + date);
	//console.log('pdvCategoryDTO: ' + pdvCategoryDTO);
	//console.log('izabranaStopa: ' + izabranaStopa);
		
	var params = {
			'enterprise_id': izabranaStopa,
			'nameEnterprise' : nameEnterprise,
			'address': address,
			'phone' : phone,
			'fax': fax,
			'cityDTO': {
				'city_id' : cityDTO
			}
	}
	
	$.ajax({
		url:"http://localhost:8080/salesystem/enterprises/" + izabranaStopa,
		type:"PUT",
		data: JSON.stringify(params),
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(data){
			console.log(data);
			readRates();
			nazivIzmeniInput.val("");
			adresaIzmeniInput.val("");
			telefonIzmeniInput.val("");
			faxIzmeniInput.val("");
		//	kategorijaIzmeniSelect.val("");
			$('#updateModalScrollable').modal('hide');
		}
	});
});
	function deleteP(){
		var izaberiStopu = $('#stopaDeleteSelect');
		var izabranaStopa = izaberiStopu.val();
		$.ajax({
	    	url: "http://localhost:8080/salesystem/enterprises/" + izabranaStopa,
	    	type: "DELETE",
	    	success: function(){
	    		alert('Izbrisali ste preduzece!');
	    		readRates();
	        }
		});
	}


