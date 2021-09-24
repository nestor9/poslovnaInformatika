function getPricelistItem(){
	readItems();
	var item = [];
	
	$(document).on("click",'tr',function(event){
		highlightRow(this);
		$('#dataTable').on('click','tr', function(event){ //ili izbrisati ovu liniju
			item.length = 0;
			var selectedRow = $(this);
			var td = selectedRow.children('td');
			for (var i = 0; i < td.length; ++i) {
				item.push(td.eq(i).text());
				
			}
		}); //i ovu
		console.log(item);
	});

	$(document).on("click", '#add', function(event){
		readPricelists();
		readServices();
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		addPricelistItem();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});

	$(document).on("click", '#edit', function(event){
		addItemToSelect();
		readPricelist2();
		readServices2();;
		$('#updateModalScrollable').modal('show');
	});
	
//	$(document).on("click", "#doUpdate", function(event) {
//		updateItem();
//		$('#updateModalScrollable').modal('hide');
//	});

	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		addItemToSelect2();
		$('#deletePromptModal').modal('show');
	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		deleteItem();
		$('#deletePromptModal').modal('hide');
	});
}
var itemsArray = [];
function readItems() {
	$.ajax({
		url : "http://localhost:8080/salesystem/priceListItems"
	}).then(
			function(data, status, request){
				console.log(data);
				itemsArray = data;
				console.log('this is items array '+itemsArray);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++){
					console.log(data[i].price_list_item_id)
					newRow = 
						"<tr>" 
							+ "<td class=\"price\">" + data[i].price + "</td>"
							+ "<td class=\"pricelist\">" + data[i].pricelist.date_from + "</td>"
							+ "<td class=\"services\">" + data[i].services.name + "</td>" +
						"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
}

function readPricelists(){
	$.ajax({
		url:"http://localhost:8080/salesystem/pricelists/all"
	}).then(
			function(data){
				$("#cenovnikSelect").empty();
				$.each(data, function(i,item){
					$('#cenovnikSelect').append($('<option>',{
						value : item.pricelist_id,
						text : item.date_from
					}));
				});
			});
}

function readPricelist2(){
	$.ajax({
		url:"http://localhost:8080/salesystem/pricelists/all"
	}).then(
			function(data){
				$("#cenovnikIzmeniSelect").empty();
				$.each(data, function(i,item){
					$('#cenovnikIzmeniSelect').append($('<option>',{
						value : item.pricelist_id,
						text : item.date_from
					}));
				});
			});
}

function readServices(){
	$.ajax({
		url : "http://localhost:8080/salesystem/services/"
	}).then(function(data){
		$("#robaSelect").empty();
		$.each(data, function(i,item){
			$('#robaSelect').append($('<option>',{
				value : item.services_id,
				text : item.name
			}));
		});
	});
}

function readServices2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/services/"
	}).then(function(data){
		$("#robaIzmeniSelect").empty();
		$.each(data, function(i,item){
			$('#robaIzmeniSelect').append($('<option>',{
				value : item.services_id,
				text : item.name
			}));
		});
	});
}

function addItemToSelect(){
	$.ajax({
		url : "http://localhost:8080/salesystem/priceListItems"
	}).then(
		function(data) {
			$("#stavkaEditSelect").empty();
			$.each(data, function (i, item) {
			    $('#stavkaEditSelect').append($('<option>', { 
			        value: item.price_list_item_id,
			        text : item.price
			    }));
			});	
		}
	);
}

function addItemToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/priceListItems"
	}).then(
		function(data) {
			$("#stavkaDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#stavkaDeleteSelect').append($('<option>', { 
			        value: item.price_list_item_id,
			        text : item.price
			    }));
			});	
		}
	);
}

function addPricelistItem(){
		var cenaInput = $('#cenaInput');
		var cenovnikSelect = $('#cenovnikSelect')
		var robaSelect = $('#robaSelect')
	
		var price = $('#cenaInput').val();
		var pricelist = $('#cenovnikSelect').val();
		var services =  $('#robaSelect').val();
		
		console.log('price' + price);
		console.log('pricelist' + pricelist);
		console.log('services' + services);
		
		if(price == null || pricelist == null || services == null){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
				'price' : price,
				'pricelist' : {
					'pricelist_id' : pricelist
				},
				'services' : {
					'services_id' : services
				}
		}
		
		console.log(params);
		
		$.ajax({
			url : 'http://localhost:8080/salesystem/priceListItems/add',
			type : 'POST',
			data : JSON.stringify(params),
			contentType : 'application/json; charset=utf-8',
			dataType:'json',
			success:function(data){
				alert('Dodata je nova stavka cenovnika')
				readItems();
				cenaInput.val("");
				cenovnikSelect.val("");
				robaSelect.val("");
			}
		})
		console.log('slanje poruke');
		event.preventDefault();
		return false;
}

//EDIT

	$(document).on("click", '#fillOutItemFieldsEdit', function(event){
		var id = document.getElementById("stavkaEditSelect").value;
		if(!id){
			alert("Morate odabrati stavku da biste izvrsili izmenu");
		}
		var chosenItemData = itemsArray.filter(item => item.price_list_item_id == id);
		console.log(chosenItemData[0].price);
		$('#cenaIzmeniInput').val(chosenItemData[0].price);
		event.preventDefault();
	});

	$(document).on("click", '#doUpdate', function(event){
		if(!document.getElementById("stavkaEditSelect").value){
			alert('Izaberite stavku za izmenu');
			return;
		}

	var cenaIzmeniInput = $('#cenaIzmeniInput');
	var cenovnikIzmeniSelect = $('#cenovnikIzmeniSelect');
	var robaIzmeniSelect = $('#robaIzmeniSelect');
	var izaberiStavku = $('#stavkaEditSelect');
	
	var price = cenaIzmeniInput.val();
	var pricelist = cenovnikIzmeniSelect.val();
	var services = robaIzmeniSelect.val();
	var izabranaStavka = izaberiStavku.val();

	console.log('price: ' + price);
	console.log('pricelist: ' + pricelist);
	console.log('services: ' + services);
	console.log('izabranaStavka: ' + izabranaStavka);
		
	var params = {
			'price_list_item_id': izabranaStavka,
			'price': price,
			'pricelist': {
				'pricelist_id' : pricelist
			},
			'services': {
				'services_id' : services
			}
	}
	
	$.ajax({
		url:"http://localhost:8080/salesystem/priceListItems/" + izabranaStavka,
		type:"PUT",
		data: JSON.stringify(params),
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success:function(data){
			console.log(data);
			readItems();
			cenaIzmeniInput.val("");
			//cenovnikIzmeniSelect.val("");
			//robaIzmeniSelect.val("");
			$('#updateModalScrollable').modal('hide');
		}
	});
});

function deleteItem(){
	var izaberiStavku = $('#stavkaDeleteSelect');
	var izabranaStavka = izaberiStavku.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/priceListItems/" + izabranaStavka,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste stavku cenovnika!');
    		readItems();
        }
	});
}
