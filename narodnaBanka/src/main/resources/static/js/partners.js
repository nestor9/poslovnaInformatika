function getPartners(){
	readPartners();
	var partner = [];
	
	$(document).on("click",'tr',function(event){
		highlightRow(this);
		$('#dataTable').on('click','tr', function(event){ //ili izbrisati ovu liniju
			partner.length = 0;
			var selectedRow = $(this);
			var td = selectedRow.children('td');
			for (var i = 0; i < td.length; ++i) {
				partner.push(td.eq(i).text());
				
			}
		}); //i ovu
		console.log(partner);
	});
	
	$(document).on("click", '#add', function(event){
		readCities();
		readEnterprises();
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		addPartner();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		addPartnerToSelect();
		readCities2();
		readEnterprises2();
		$('#updateModalScrollable').modal('show');
		//updatePartner();
	});
	
	// $(document).on("click", '#doUpdate', function(event) {
	// 	updatePartner();
	// 	$('#updateModalScrollable').modal('hide');
	// });
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		addPartnerToSelect2();
		$('#deletePromptModal').modal('show');
	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		deletePartner();
		$('#deletePromptModal').modal('hide');
	});
}
var partnersArray = [];
function readPartners() {
	$.ajax({
		url : "http://localhost:8080/salesystem/partners/all" 
	}).then(
			function(data, status, request) {
				console.log(data);
				partnersArray = data;
				console.log('this is partners array '+partnersArray);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++) {
					console.log(data[i].partner_id)
					console.log(data[i].enterpriseDTO.nameEnterprise);
					newRow = 
					"<tr>" 
						+ "<td class=\"partner_name\">" + data[i].partner_name + "</td>"
						+ "<td class=\"address\">" + data[i].address + "</td>"
						+ "<td class=\"phone_number\">" + data[i].phone_number + "</td>"
						+ "<td class=\"fax\">" + data[i].fax + "</td>"
						+ "<td class=\"email\">" + data[i].email + "</td>"
						+ "<td class=\"cityDTO\">" + data[i].cityDTO.city_name + "</td>"
						+ "<td class=\"enterpriseDTO\">" + data[i].enterpriseDTO.nameEnterprise + "</td>" +
					"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
}

function addPartnerToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/partners/all"
	}).then(
		function(data) {
			$("#partnerEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#partnerEditSelect').append($('<option>', { 
			        value: item.partner_id,
			        text : item.partner_name
			    }));
			});	
		}
	);
}

function addPartnerToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/partners/all"
	}).then(
		function(data) {
			$("#partnerDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#partnerDeleteSelect').append($('<option>', { 
			        value: item.partner_id,
			        text : item.partner_name
			    }));
			});	
		}
	);
}

function readEnterprises(){
	$.ajax({
		url : "http://localhost:8080/salesystem/enterprises/all"
	}).then(
		function(data) {
			$("#preduzeceSelect").empty();
			$.each(data, function (i, item) {
			    $('#preduzeceSelect').append($('<option>', { 
			        value: item.enterprise_id,
			        text : item.nameEnterprise
			    }));
			});	
		}
	);
}

function readEnterprises2() {
	$.ajax({
		url : "http://localhost:8080/salesystem/enterprises/all"
	}).then(
		function(data) {
			$("#preduzeceIzmeniSelect").empty();
			$.each(data, function (i, item) {
			    $('#preduzeceIzmeniSelect').append($('<option>', { 
			        value: item.enterprise_id,
			        text : item.nameEnterprise
			    }));
			});	
		}
	);
}

function readCities(){
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all"
	}).then(
		function(data) {
			$("#gradSelect").empty();
			$.each(data, function (i, item) {
			    $('#gradSelect').append($('<option>', { 
			        value: item.city_id,
			        text : item.city_name
			    }));
			});	
		}
	);
}

function readCities2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/cities/all"
	}).then(
		function(data) {
			$("#gradIzmeniSelect").empty();
			$.each(data, function (i, item) {
			    $('#gradIzmeniSelect').append($('<option>', { 
			        value: item.city_id,
			        text : item.city_name
			    }));
			});	
		}
	);
}

function addPartner(){
		var nazivInput = $('#nazivInput');
		var adresaInput = $('#adresaInput');
		var telefonInput = $('#telefonInput');
		var faxInput = $('#faxInput');
		var emailInput = $('#emailInput');
		var gradSelect = $('#gradSelect');
		var preduzeceSelect = $('#preduzeceSelect');
	
		var partner_name = nazivInput.val();
		var address = adresaInput.val();
		var phone_number = telefonInput.val();
		var fax = faxInput.val();
		var email = emailInput.val();
		var cityDTO = gradSelect.val();
		var enterpriseDTO = preduzeceSelect.val();
		
		console.log('partner_name: ' + partner_name);
		console.log('address: ' + address);
		console.log('phone_number: ' + phone_number);
		console.log('fax: ' + fax);
		console.log('email: ' + email);
		console.log('cityDTO: ' + cityDTO);
		console.log('enterpriseDTO' + enterpriseDTO);
		
		if(partner_name == null || address == null || phone_number == null || fax == null || email == null || cityDTO == null || enterpriseDTO == null){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
				'partner_name': partner_name,
				'address' : address,
				'phone_number' : phone_number,
				'fax' : fax,
				'email' : email,
				'cityDTO': {
					'city_id' : cityDTO
				},
				'enterpriseDTO': {
					'enterprise_id' : enterpriseDTO
				}
		}
		
		$.ajax({
			url : 'http://localhost:8080/salesystem/partners/add',
			type:'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				console.log('...')
				alert('Dodat je nov partner')
				readPartners();
				nazivInput.val("");
				adresaInput.val("");
				telefonInput.val("");
				faxInput.val("");
				emailInput.val("");
				gradSelect.val("");
				preduzeceSelect.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

//EDIT : 

	//popunjavanje forme za edit, "izaberi" dugme
	$(document).on("click", '#fillOutPartnerFieldsEdit', function(event) {
		var id = document.getElementById("partnerEditSelect").value;
		if(!id) {
			alert("Morate izabrati partnera da biste izvrsili izmenu");
		}
		var chosenPartnerData = partnersArray.filter(partner => partner.partner_id == id);
		//za vanilla js:
		//var nazivIzmeniInput = document.getElementById("nazivIzmeniInput");
		// nazivIzmeniInput.innerText = ili .setValue() 
		console.log(chosenPartnerData[0].partner_name);
		$('#nazivIzmeniInput').val(chosenPartnerData[0].partner_name);
		$('#adresaIzmeniInput').val(chosenPartnerData[0].address);
		$('#telefonIzmeniInput').val(chosenPartnerData[0].phone_number);
		$('#faxIzmeniInput').val(chosenPartnerData[0].fax);
		$('#emailIzmeniInput').val(chosenPartnerData[0].email);	
		event.preventDefault();
	});

	//klik za izmenu
	$(document).on("click", '#doUpdate', function(event){
		if(!document.getElementById("partnerEditSelect").value){
			alert('Izaberite partnera za izmenu!');
			return;
		}
		var partner_name = $('#nazivIzmeniInput').val();
		var address = $('#adresaIzmeniInput').val();
		var phone_number = $('#telefonIzmeniInput').val();
		var fax = $('#faxIzmeniInput').val();
		var email = $('#emailIzmeniInput').val();
		var cityDTO = $('#gradIzmeniSelect').val();
		var enterpriseDTO = $('#preduzeceIzmeniSelect').val();
		var izabranPartner = $('#partnerEditSelect').val();
		var params = {
				'partner_id': izabranPartner,
				'partner_name': partner_name,
				'address': address,
				'phone_number': phone_number,
				'fax': fax,
				'email': email,
				'cityDTO': {
					'city_id' : cityDTO
				},
				'enterpriseDTO': {
					'enterprise_id' : enterpriseDTO
				}
		}
		$.ajax({
			url:"http://localhost:8080/salesystem/partners/" + izabranPartner,
			type:"PUT",
			data: JSON.stringify(params),
			contentType:'application/json',
			dataType:"json",
			success:function(data){
				console.log(data);
				readPartners();
				$('#nazivIzmeniInput').val("");
				$('#adresaIzmeniInput').val("");
				$('#telefonIzmeniInput').val("");
				$('#faxIzmeniInput').val("");
				$('#emailIzmeniInput').val("");
				//gradIzmeniSelect.val("");
				//preduzeceIzmeniSelect.val("");
				$('#updateModalScrollable').modal('hide');
			}
		});
	});

//END EDIT

function deletePartner(){
	var izaberiPartnera = $('#partnerDeleteSelect');
	var izabranPartner = izaberiPartnera.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/partners/" + izabranPartner,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste partnera!');
    		readPartners();
        }
	});
}
