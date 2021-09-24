function getCategory(){
	readCategory();
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
		readCategory();
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
		addRateToSelect();
		// mozda fali
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
		deleteRate();
		$('#deletePromptModal').modal('hide');
	});
}
var ratesArray = [];
function readCategory() {
	$.ajax({
		url : "http://localhost:8080/salesystem/pdvCategories/all" 
	}).then(
			function(data, status, request) {
				console.log(data);
				ratesArray = data;
				console.log('this is pdv category '+ratesArray);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++) {
					console.log(data[i].id)
					newRow = 
					"<tr>" 
						+ "<td class=\"percentage\">" + data[i].id + "</td>"
						+ "<td class=\"date\">" + data[i].name + "</td>"+
						"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
	
}

function addRateToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/pdvCategories/all"
	}).then(
		function(data) {
			$("#stopaEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#stopaEditSelect').append($('<option>', { 
			        value: item.id,
			        text : item.name
			    }));
			});	
		}
	);
}

function addRateToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/pdvCategories/all"
	}).then(
		function(data) {
			$("#stopaDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#stopaDeleteSelect').append($('<option>', { 
			        value: item.id,
			        text : item.name
			    }));
			});	
		}
	);
}



function addRate(){
		var procenatInput = $('#procenatInput');
	
		var name = procenatInput.val();

		
		console.log('name: ' + name);
		
		if(name == null ){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
				'name' : name
			
		}
		
		$.ajax({
			url : 'http://localhost:8080/salesystem/pdvCategories/add',
			type:'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				console.log('...')
				alert('Dodata je nova kategorija pdv-a')
				readCategory();
				procenatInput.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

//EDIT

$(document).on("click", '#fillOutRateFieldsEdit', function(event){
	var id = document.getElementById("stopaEditSelect").value;
	if(!id) {
		alert("Morate izabrati kategoriju da biste izvrsili izmenu");
	}
	var chosenRateData = ratesArray.filter(category => category.id == id);
	console.log(chosenRateData[0].percentage);
	$('#procenatIzmeniInput').val(chosenRateData[0].name);
	event.preventDefault();
});

$(document).on("click", '#doUpdate', function(event){
	if(!document.getElementById("stopaEditSelect").value){
		alert('Izaberite kategoriju za izmenu');
		return;
	}

var procenatIzmeniInput = $('#procenatIzmeniInput');
var izaberiStopu = $('#stopaEditSelect');

var name = procenatIzmeniInput.val();
var izabranaStopa = izaberiStopu.val();

console.log('name: ' + name);
console.log('izabranaStopa: ' + izabranaStopa);
	
var params = {
		'id': izabranaStopa,
		'name' : name
		
}

$.ajax({
	url:"http://localhost:8080/salesystem/pdvCategories/" + izabranaStopa,
	type:"PUT",
	data: JSON.stringify(params),
	contentType:"application/json; charset=utf-8",
	dataType:"json",
	success:function(data){
		console.log(data);
		readCategory();
		procenatIzmeniInput.val("");
		$('#updateModalScrollable').modal('hide');
	}
});
});


function deleteRate(){
	var izaberiStopu = $('#stopaDeleteSelect');
	var izabranaStopa = izaberiStopu.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/pdvCategories/" + izabranaStopa,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste kategoriju!');
    		readRates();
        }
	});
}