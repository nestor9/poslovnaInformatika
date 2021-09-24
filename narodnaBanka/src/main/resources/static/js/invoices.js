//get services
var serviceSelect = document.getElementById('service-dropdown');
var invoiceItems = [];

    fetch("http://localhost:8080/salesystem/services")
    .then((resp) => resp.json())
    .then(function(data) {
        var values = data;
        console.log(data);
        for (const val of values) {
            var option = document.createElement("option");
            option.value = val.services_id;
            option.text = val.name;
            serviceSelect.appendChild(option);
          }
      })
    .catch(function(error) {
        console.log(error);
    });
    //get partner for invoice
    var partnerSelect = document.getElementById('partner-dropdown');

    fetch("http://localhost:8080/salesystem/partners/all")
    .then((resp) => resp.json())
    .then(function(data) {
        var values = data;
        for (const val of values) {
            var option = document.createElement("option");
            option.value = val.partner_id;
            option.text = val.partner_name;
            partnerSelect.appendChild(option);
          }
      })
    .catch(function(error) {
        console.log(error);
    });
    
var datumFakture = document.getElementById('datumFakture');
datumFakture.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
var datumValute = document.getElementById('datumValute');
datumValute.max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];

    // add invoice 
    document.getElementById("saveInvoice").addEventListener('click',function (event)
    {
        
        if(invoiceItems.length==0){
            alert("Morate dodati bar jednu stavku!");
            return;
        }
        if(document.getElementById('quantity').value<=0){
            //less equal
            alert("Kolicina mora biti veca od 0!");
            return;
        }
        if(document.getElementById('discount').value<=0){
            alert("Popust ne sme biti negativan!");
            return;
        }
        if(document.getElementById('unitPrice').value<=0){
            alert("Cena ne sme biti negativna!");
            return;
        }
        if(document.getElementById('datumFakture').value > document.getElementById('datumValute').value){
            alert("Datum valute ne sme biti manji od datuma fakture!");
            return;
        }
    	//on save show all items(can be edited) and than add to db
        var invoice = {
            quantity: document.getElementById('quantity').value,
            discount: document.getElementById('discount').value,
            unitPrice: document.getElementById('unitPrice').value,
            pdvBase: document.getElementById('pdvBase').value,
           // pdvAmount: document.getElementById('pdvAmount').value,
            //totalAmount: document.getElementById('totalAmount').value, //total amount needs to be calculated
            service_id: document.getElementById('service-dropdown').value,
            partner_id: document.getElementById('partner-dropdown').value,
            date_invoice: document.getElementById('datumFakture').value,
            date_currency: document.getElementById('datumValute').value,
            enterprise_id : localStorage.getItem('salesystem-enterprise')
            }
        invoiceItems = [...invoiceItems,invoice]
        console.log(invoiceItems);
        fetch('http://localhost:8080/salesystem/invoiceItems', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(invoiceItems)
        }).then(res => res.json())
        .then(res => 
            window.location.replace("http://localhost:8080/ReportInvoiceItem.html")
        );
        event.preventDefault();
    }  ); 

document.getElementById("addInvoiceItem").addEventListener('click',function (event)
    {
        var invoice = {
            quantity: document.getElementById('quantity').value,
            discount: document.getElementById('discount').value,
            unitPrice: document.getElementById('unitPrice').value,
            pdvBase: document.getElementById('pdvBase').value,
            // pdvAmount: document.getElementById('pdvAmount').value,
            // totalAmount: document.getElementById('totalAmount').value, //total amount needs to be calculated
            service_id: document.getElementById('service-dropdown').value,
            partner_id: document.getElementById('partner-dropdown').value,
            date_invoice: document.getElementById('datumFakture').value,
            date_currency: document.getElementById('datumValute').value,
            enterprise_id : localStorage.getItem('salesystem-enterprise')
            
            }
        invoiceItems = [...invoiceItems,invoice]
        console.log(invoiceItems);
        document.getElementById('quantity').value=0;
         document.getElementById('discount').value=0;
         document.getElementById('unitPrice').value=0;
         document.getElementById('pdvBase').value=0;
    }  ); 
