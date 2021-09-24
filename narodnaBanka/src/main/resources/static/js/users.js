function getUsers(){
	readUsers();
	var user = [];
	
	$(document).on("click",'tr',function(event){
		highlightRow(this);
		$('#dataTable').on('click','tr', function(event){ //ili izbrisati ovu liniju
			user.length = 0;
			var selectedRow = $(this);
			var td = selectedRow.children('td');
			for (var i = 0; i < td.length; ++i) {
				user.push(td.eq(i).text());
				
			}
		}); //i ovu
		console.log(user);
	});
	
	$(document).on("click", '#add', function(event){
		readEnterprises();
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		addUser();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		addUserToSelect();
		readEnterprises2();
		$('#updateModalScrollable').modal('show');
	});
	
//	$(document).on("click", "#doUpdate", function(event) {
//		updateUser();
//		$('#updateModalScrollable').modal('hide');
//	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		addUserToSelect2();
		$('#deletePromptModal').modal('show');
	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		deleteUser();
		$('#deletePromptModal').modal('hide');
	});
}
var usersArray = [];
function readUsers(){
	$.ajax({
		url:"http://localhost:8080/salesystem/users/all"
	}).then(
			function(data, status, request) {
				console.log(data);
				usersArray = data;
				console.log('this is users array '+usersArray);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++) {
					console.log(data[i].user_id)
					newRow = 
					"<tr>" 
						+ "<td class=\"username\">" + data[i].username + "</td>" 
						// + "<td class=\"password\">" + data[i].password + "</td>" 
						+ "<td class=\"enterprice\">" + data[i].enterpriseDTO.nameEnterprise + "</td>" +
					"</tr>"
					$("#dataTableBody").append(newRow);
				}	
			});
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

function addUser(){
	var korisnickoImeInput = $('#korisnickoImeInput');
	var lozinkaInput = $('#lozinkaInput');
	var preduzeceSelect = $('#preduzeceSelect');
	
		var username = korisnickoImeInput.val();
		var password = lozinkaInput.val();
		var enterpriseDTO = preduzeceSelect.val();
		
		console.log('username: ' + username);
		console.log('password: ' + password);
		console.log('enterpriseDTO: ' + enterpriseDTO);
		
		if(username == null || password == null || enterpriseDTO == null){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
				'username': username,
				'password' : password,
				'enterpriseDTO': {
					'enterprise_id' : enterpriseDTO
				}
		}
		
		$.ajax({
			url : 'http://localhost:8080/salesystem/users/add',
			type:'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				console.log('...')
				alert('Registrovan je novi korisnik')
				readUsers();
				korisnickoImeInput.val("");
				lozinkaInput.val("");
				preduzeceSelect.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
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

function addUserToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/users/all"
	}).then(
		function(data) {
			$("#korisnikEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#korisnikEditSelect').append($('<option>', { 
			        value: item.user_id,
			        text : item.username
			    }));
			});	
		}
	);
}

function addUserToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/users/all"
	}).then(
		function(data) {
			$("#korisnikDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#korisnikDeleteSelect').append($('<option>', { 
			        value: item.user_id,
			        text : item.username
			    }));
			});	
		}
	);
}

//EDIT
	$(document).on("click", '#fillOutUserFieldsEdit', function(event){
		var id = document.getElementById("korisnikEditSelect").value;
		if(!id){
			alert("Morate izabrati korisnika da biste izvrsili izmenu");
		}
		var chosenUserData = usersArray.filter(user => user.user_id == id);
		console.log(chosenUserData[0].username);
		$('#korisnickoImeIzmeniInput').val(chosenUserData[0].username);
		$('#lozinkaIzmeniInput').val(chosenUserData[0].password);
		event.preventDefault();
	});

	$(document).on("click", '#doUpdate', function(event){
		if(!document.getElementById("korisnikEditSelect").value){
			alert('Izaberite korisnika za izmenu');
			return;
		}	
	var korisnickoImeIzmeniInput = $('#korisnickoImeIzmeniInput');
	var lozinkaIzmeniInput = $('#lozinkaIzmeniInput');
	var izaberiKorisnika = $('#korisnikEditSelect');
	var preduzeceIzmeniSelect = $('#preduzeceIzmeniSelect');
	
	var username = korisnickoImeIzmeniInput.val();
	var password = lozinkaIzmeniInput.val();
	var preduzece = preduzeceIzmeniSelect.val();
	var izabranKorisnik = izaberiKorisnika.val();

	console.log('username: ' + username);
	console.log('password: ' + password);
	console.log('izabran korisnik: ' + izabranKorisnik);
	console.log('preduzece: ' + preduzece);
		
	var params = {
			'user_id': izabranKorisnik,
			'username': username,
			'password': password,
			'enterpriseDTO': {
				'enterprise_id' : preduzece
			}
	}
	
	$.ajax({
		url:"http://localhost:8080/salesystem/users/" + izabranKorisnik,
		type:"PUT",
		data: JSON.stringify(params),
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(data){
			console.log(data);
			readUsers();
			korisnickoImeIzmeniInput.val("");
			lozinkaIzmeniInput.val("");
			preduzeceIzmeniSelect.val("");
			$('#updateModalScrollable').modal('hide');
		}
	});
});

function deleteUser(){
	var izaberiKorisnika = $('#korisnikDeleteSelect');
	var izabranKorisnik = izaberiKorisnika.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/users/" + izabranKorisnik,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste korisnika!');
    		readUsers();
        }
	});
}
