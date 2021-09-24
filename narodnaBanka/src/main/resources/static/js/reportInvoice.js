function getInvoiceItem(){
	readInvoice();

$(document).on("click", '#add', function(event){
	$('#addModalScrollable').modal('show');
});

$(document).on("click", '#doAdd', function(event){
	addPricelistItem();
	$('#addModalScrollable').modal('hide');				
});

$(document).on("click", '.addModalClose', function(event){
	$('#addModalScrollable').modal('hide');
});
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

function readInvoice() {
	$.ajax({
		url : "http://localhost:8080/salesystem/invoiceItems"
	}).then(
			function(data, status, request){
				console.log(data);
				$("#dataTableBody").empty();
				for (i = 0; i < data.length; i++){
					console.log('this is data' + data);
					console.log(data[i].id)
					newRow = 
						"<tr>"
							+'<td><a href="displayReport.html?id=' + data[i].id + '">' + data[i].quantity + '</a></td>' 
							+ "<td class=\"invoiceItems\">" + data[i].discount + "</td>"
							+ "<td class=\"invoiceItems\">" + data[i].unitPrice + "</td>"
							+ "<td class=\"invoiceItems\">" + data[i].pdvBase + "</td>"
							+ "<td class=\"invoiceItems\">" + data[i].pdvAmount + "</td>"
							+ "<td class=\"invoiceItems\">" + data[i].totalAmount + "</td>"
							+ "<td class=\"invoiceItems\">" +
								"Broj:" + data[i].invoiceDTO.invoice_number + "<br>" + 
								" Datum:" + new Date(data[i].invoiceDTO.date_invoice).toLocaleDateString() +  "<br>" +
								" Pdv:" + data[i].invoiceDTO.total_pdv + "<br>" +
								" Iznos:" + data[i].invoiceDTO.total_amount + "<br>" +
								" Osnovica:" + data[i].invoiceDTO.total_base + 
							 "</td>" + 
							"<td>"+ new Date(data[i].invoiceDTO.bussinesYearDTO.dateFrom).toLocaleDateString() +
							 "-" +new Date(data[i].invoiceDTO.bussinesYearDTO.dateTo).toLocaleDateString()+ "</td>"+ 
						 "<td class=\"invoiceItems\">" + data[i].invoiceDTO.enterpriseName + "</td>" +
						"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
}
function fakturaIzvestaj(){
    var quantity = $('#inputKolicina').val();
    window.open('http://localhost:8080/salesystem/invoiceItems/report/'+quantity);
    location.reload();
}

function prikaziOdredjenIzvestaj(id){

	
    console.log("Funckija: "+id);
    $.ajax({
        url: 'http://localhost:8080/salesystem/invoiceItems/'+id,
    }).then(
    		
    		function(data,status,request){
    		console.log(data);
            var idI = $("#idIzvestaja");
            var kolicinaI = $("#kolicina");
            var popustI = $("#popust");
            var cenaI = $("#cena");
            var osnovicaI = $("#osnovica");
            var pdvI = $("#pdv");
            var ukI = $("#ukupaniznos");
          
            idI.val(data[i].id);
            kolicinaI.val(data[i].quantity);
            popustI.val(data[i].discount);
            cenaI.val(data[i].unitPrice);
            osnovicaI.val(data[i].pdvBase);
            pdvI.val(data[i].pdvAmount);
            ukI.val(data[i].totalAmount);
            
            
           

    		});
    	
}
function addRateToSelect2(){
	$.ajax({
		url : "http://localhost:8080/salesystem/invoiceItems/all"
	}).then(
		function(data) {
			$("#stopaDeleteSelect").empty();
			$.each(data, function (i, item) {
			    $('#stopaDeleteSelect').append($('<option>', { 
			        value: item.id,
			        text : item.quantity
			    }));
			});	
		}
	);
}
function deleteRate(){
	var izaberiStopu = $('#stopaDeleteSelect');
	var izabranaStopa = izaberiStopu.val();
	$.ajax({
    	url: "http://localhost:8080/salesystem/invoiceItems/" + izabranaStopa,
    	type: "DELETE",
    	success: function(){
    		alert('Izbrisali ste stavku izvestaja!');
    		readInvoice();
        }
	});
}



