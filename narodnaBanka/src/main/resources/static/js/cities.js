function getCities(){
	readCities();
	var city = [];
	
	$(document).on("click",'tr',function(event){
		highlightRow(this);
		$('#dataTable').on('click','tr', function(event){ //ili izbrisati ovu liniju
			pricelist.length = 0;
			var selectedRow = $(this);
			var td = selectedRow.children('td');
			for (var i = 0; i < td.length; ++i) {
				city.push(td.eq(i).text());
				
			}
		}); //i ovu
		console.log(city);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		addCity();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		addCityToSelect();
		$('#updateModalScrollable').modal('show');
	});
	
//	$(document).on("click", "#doUpdate", function(event) {
//		updateCity();
//		$('#updateModalScrollable').modal('hide');
//	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		addCityToSelect2();
		$('#deletePromptModal').modal('show');
	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		deleteCity();
		$('#deletePromptModal').modal('hide');
	});
}
var citiesArray = [];
function readCities() {
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all" 
	}).then(
			function(data, status, request) {
				console.log(data);
				citiesArray = data;
				console.log('this is city array '+ citiesArray);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++) {
					console.log(data[i].city_id)
					newRow = 
					"<tr>" 
						+ "<td class=\"ptt\">" + data[i].ptt + "</td>"
						+ "<td class=\"city_name\">" + data[i].city_name + "</td>" +
					"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
}

function addCity(){
	var pttInput = $('#pttInput');
	var nazivInput = $('#nazivInput');
	
		var ptt = pttInput.val();
		var city_name = nazivInput.val();
		
		console.log('ptt: ' + ptt);
		console.log('city_name: ' + city_name);
		
		if(ptt == null || city_name == null){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
				'ptt': ptt,
				'city_name' : city_name,
		}
		
		$.ajax({
			url : 'http://localhost:8080/salesystem/cities/add',
			type:'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				console.log('...')
				alert('Dodat je nov grad')
				readCities();
				pttInput.val("");
				nazivInput.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

function addCityToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all"
	}).then(
		function(data) {
			$("#gradEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#gradEditSelect').append($('<option>', { 
			        value: item.city_id,
			        text : item.city_name
			    }));
			});	
		}
	);
}

function addCityToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all"
	}).then(
		function(data) {
			$("#gradDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#gradDeleteSelect').append($('<option>', { 
			        value: item.city_id,
			        text : item.city_name
			    }));
			});	
		}
	);
}
//EDIT

	$(document).on("click", '#fillOutCityFieldsEdit', function(event) {
		var id = document.getElementById("gradEditSelect").value;
		if(!id) {
			alert("Morate izabrati grad da biste izvrsili izmenu");
		}
		var chosenCityData = citiesArray.filter(city => city.city_id == id);
		console.log(chosenCityData[0].city_name);
		$('#pttIzmeniInput').val(chosenCityData[0].ptt);
		$('#nazivIzmeniInput').val(chosenCityData[0].city_name);
		event.preventDefault();
	});
	

	$(document).on("click", '#doUpdate', function(event){
		if(!document.getElementById("gradEditSelect").value){
			alert('Izaberite grad za izmenu');
			return;
		}
	var pttIzmeniInput = $('#pttIzmeniInput');
	var nazivIzmeniInput = $('#nazivIzmeniInput');
	var izaberiGrad = $('#gradEditSelect');

	var ptt = pttIzmeniInput.val();
	var city_name = nazivIzmeniInput.val();
	var izabranGrad = izaberiGrad.val();

	console.log('ptt: ' + ptt);
	console.log('city_name: ' + city_name);
	console.log('izabranGrad: ' + izabranGrad);
		
	var params = {
			'city_id': izabranGrad,
			'ptt': ptt,
			'city_name': city_name
	}
	
	$.ajax({
		url:"http://localhost:8080/salesystem/cities/" + izabranGrad,
		type:"PUT",
		data: JSON.stringify(params),
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(data){
			console.log(data);
			readCities();
			pttIzmeniInput.val("");
			nazivIzmeniInput.val("");
			$('#updateModalScrollable').modal('hide');
		}
	});
});

function deleteCity(){
	var izaberiGrad = $('#gradDeleteSelect');
	var izabranGrad = izaberiGrad.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/cities/" + izabranGrad,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste grad!');
    		readCities();
        }
	});
}