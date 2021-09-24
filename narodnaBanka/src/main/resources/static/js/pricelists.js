function getPricelists(){
	readPricelists();
	var pricelist = [];

	$(document).on("click",'tr',function(event){
		highlightRow(this);
		$('#dataTable').on('click','tr', function(event){ //ili izbrisati ovu liniju
			pricelist.length = 0;
			var selectedRow = $(this);
			var td = selectedRow.children('td');
			for (var i = 0; i < td.length; ++i) {
				pricelist.push(td.eq(i).text());
				
			}
		}); //i ovu
		console.log(pricelist);
	});
	
	$(document).on("click", '#add', function(event){
		readEnterprises();
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		addPricelist();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		addPricelistToSelect();
		readEnterprises2();
		$('#updateModalScrollable').modal('show');
	});
	
//	$(document).on("click", "#doUpdate", function(event) {
//		updatePricelist();
//		$('#updateModalScrollable').modal('hide');
//	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		addPricelistToSelect2();
		$('#deletePromptModal').modal('show');
	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		deletePricelist();
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#copy', function(event){
		addPricelistToSelectCopy();
		$('#copyModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doCopy', function(event){
		copyPricelist();
		$('#copyModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.copyModalClose', function(event){
		$('#copyModalScrollable').modal('hide');
	});
}
var pricelistsArray = [];
function readPricelists() {
		$.ajax({
			url : "http://localhost:8080/salesystem/pricelists/all" 
		}).then(
				function(data, status, request) {
					console.log(data);
					pricelistsArray = data;
					console.log('this is pricelists array '+pricelistsArray);
					$("#dataTableBody").empty();
					for (i = 0; i < data.length; i++) {
						console.log(data[i].pricelist_id)
						newRow = 
						"<tr>" 
							+ "<td class=\"dateFrom\">" + data[i].date_from + "</td>"
							+ "<td class=\"enterprice\">" + data[i].enterpriseDTO.nameEnterprise + "</td>" +
						"</tr>"
						$("#dataTableBody").append(newRow);
					}
				});
}

function addPricelistToSelectCopy(){
	console.log('test');
	$.ajax({
		url : "http://localhost:8080/salesystem/pricelists/all"
	}).then(
			function(data){
				console.log('Data' + data);
				$("#cenovnikSelect").empty();
				
				$.each(data, function(i, item){
					$('#cenovnikSelect').append($('<option>', {
					value : item.pricelist_id,
					text : item.date_from
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

function addPricelistToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/pricelists/all"
	}).then(
		function(data) {
			$("#cenovnikEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#cenovnikEditSelect').append($('<option>', { 
			        value: item.pricelist_id,
			        text : item.date_from
			    }));
			});	
		}
	);
}

function addPricelistToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/pricelists/all"
	}).then(
		function(data) {
			$("#cenovnikDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#cenovnikDeleteSelect').append($('<option>', { 
			        value: item.pricelist_id,
			        text : item.date_from
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

function addPricelist(){
	var datumInput = $('#datumInput');
	var preduzeceSelect = $('#preduzeceSelect');
	var procenatCenovnikInput = $('#procenatCenovnikInput');
	
		var date_from = datumInput.val();
		var enterpriseDTO = preduzeceSelect.val();
		var percentage = procenatCenovnikInput.val();

		
		console.log('date_from: ' + date_from);
		console.log('enterpriseDTO: ' + enterpriseDTO);
		console.log('percentage' + percentage);
		
		if(date_from == null || enterpriseDTO == null){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
				'date_from': date_from,
				'percentage' : percentage,
				'enterpriseDTO': {
					'enterprise_id' : enterpriseDTO
				}
		}
		

		
		$.ajax({
			url : 'http://localhost:8080/salesystem/pricelists/add',
			type:'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				console.log('...')
				alert('Dodat je novi cenovnik')
				readPricelists();
				datumInput.val("");
				preduzeceSelect.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

function copyPricelist(){
	var cenovnikSelect = $('#cenovnikSelect');
	var procenatInput =$('#procenatInput')
	
		var pricelist_id = cenovnikSelect.val();
		var percentage = procenatInput.val();
		console.log('pricelist_id' + pricelist_id);
		
		if(pricelist_id == null || percentage == null){
			alert('Morate izabrati cenovnik')
		}
		
		var params = {
				'pricelists' : {
					'pricelist_id' : pricelist_id,
					'percentage' : percentage
				}
				
		}
		
		console.log(params);
		$.ajax({
			url : 'http://localhost:8080/salesystem/pricelists/copy/' + pricelist_id + '/' + percentage,
			type : 'POST',
			data: JSON.stringify(params),
			contentType:'application/json; charset=utf-8',
			dataType:'json',
			success : function(data){
				alert('Cenovnik je kopiran');
				cenovnikSelect.val("");
				readPricelists();
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

	$(document).on("click", '#fillOutPricelistFieldsEdit', function(event){
		var id = document.getElementById("cenovnikEditSelect").value;
		if(!id) {
			alert("Morate izabrati cenovnik da biste izvrsili izmenu");
		}
		var chosenPricelistData = pricelistsArray.filter(pricelist => pricelist.pricelist_id == id);
		console.log(chosenPricelistData[0].date_from);
		$('#datumIzmeniInput').val(chosenPricelistData[0].date_from);
		event.preventDefault();
	});

		$(document).on("click", '#doUpdate', function(event){
			if(!document.getElementById("cenovnikEditSelect").value){
				alert('Izaberite cenovnik za izmenu');
				return;
			}
		var datumIzmeniInput = $('#datumIzmeniInput');
		var izaberiCenovnik = $('#cenovnikEditSelect');
		var preduzeceIzmeniSelect = $('#preduzeceIzmeniSelect');
		
		var datum_vazenja = datumIzmeniInput.val();
		var preduzece = preduzeceIzmeniSelect.val();
		var izabranCenovnik = izaberiCenovnik.val();

		console.log('datum_vazenja: ' + datum_vazenja);
		console.log('izabran cenovnik: ' + izabranCenovnik);
		console.log('preduzece: ' + preduzece);
			
		var params = {
				'pricelist_id': izabranCenovnik,
				'date_from': datum_vazenja,
				'enterpriseDTO': {
					'enterprise_id' : preduzece
				}
		}
		
		$.ajax({
			url:"http://localhost:8080/salesystem/pricelists/" + izabranCenovnik,
			type:"PUT",
			data: JSON.stringify(params),
			contentType:"application/json; charset=utf-8",
			dataType:"json",
			success:function(data){
				console.log(data);
				readPricelists();
				datumIzmeniInput.val("");
				//preduzeceIzmeniSelect.val("");
				$('#updateModalScrollable').modal('hide');
			}
		});
});

function deletePricelist(){
	var izaberiCenovnik = $('#cenovnikDeleteSelect');
	var izabranCenovnik = izaberiCenovnik.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/pricelists/" + izabranCenovnik,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste cenovnik!');
    		readPricelists();
        }
	});
}