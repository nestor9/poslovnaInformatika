function getDisplayID(){
	readID();
	readDInvoice();
}
function readID(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(id);
}
function fakturaIzvestaj(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
    window.location.replace("http://localhost:8080/salesystem/invoiceItems/reportInvoiceItem/"+id);
    //location.reload();
}
function readDInvoice() {
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(id);
	params = {'id':id};	
	var tabela = $('#tabelaIzvestaj');
	$.ajax({
		url : "http://localhost:8080/salesystem/invoiceItems/" + id,
	}).then(
			function(data, status, request, params){
				console.log(data);
				
					/*var idI = $("#idIzvestaja");
		            var kolicinaI = $("#kolicina");
		            var popustI = $("#popust");
		            var cenaI = $("#cena");
		            var osnovicaI = $("#osnovica");
		            var pdvI = $("#pdv");
		            var ukI = $("#ukupaniznos");*/
		        		  tabela.append(
									 '<tr>'+
								      '<th scope="col">Kolicina</th> ' + 	
										'<td>' +  data.quantity + '</td>' + 
							    	  '</tr>' +
									  '<tr>'+
								      '<th scope="col">Popust</th> ' + 	
										'<td>' +  data.discount + '</td>' + 
							    	  '</tr>' +
									  '<tr>'+
								      '<th scope="col">Cena</th> ' + 	
										'<td>'  +  data.unitPrice + '</td>' + 
							    	  '</tr>' +
									  '<tr>'+
								      '<th scope="col">Osnovica</th> ' + 	
										'<td>' +  data.pdvBase + '</td>' + 
							    	  '</tr>' +
									  '<tr>'+
								      '<th scope="col">Pdv</th> ' + 	
										'<td>' +  data.pdvAmount + '</td>' + 
							    	  '</tr>' +
									  '<tr>'+
								      '<th scope="col">Ukupan iznos</th> ' + 	
										'<td>' +  data.totalAmount + '</td>' + 
							    	  '</tr>' +
										'</td>'+
							    	  '</tr>'	   
								);
		          /*  idI.val(data.id);
		            kolicinaI.val(data.quantity);
		            popustI.val(data.discount);
		            cenaI.val(data.unitPrice);
		            osnovicaI.val(data.pdvBase);
		            pdvI.val(data.pdvAmount);
		            ukI.val(data.totalAmount);
		           */
				
			
			});
}
