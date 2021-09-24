//get invoices
    var invoiceSelect = document.getElementById('edit-invoice-dropdown');
    var invoiceItems = [];
    fetch("http://localhost:8080/salesystem/invoices/enterprise/" + localStorage.getItem("salesystem-enterprise"))
    .then((resp) => resp.json())
    .then(function(data) {
        var values = data;
        invoiceItems = data;
        console.log(data);
        for (const val of values) {
            var option = document.createElement("option");
            option.value = val.id;
            option.text = "Broj:"+val.invoice_number + ", Datum:" + new Date(val.date_invoice).toLocaleDateString();
            invoiceSelect.appendChild(option);
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


document.getElementById("fillOutDataInvoice").addEventListener('click',function (event){
    var selectedId = document.getElementById('edit-invoice-dropdown').value;
    console.log('invoice selected ' + selectedId);
    if(!selectedId){
        alert("Morate izabrati fakturu za izmenu!");
        return;
    }
    var chosenData = invoiceItems.filter(invoice => invoice.id == selectedId);
    document.getElementById('status').value = chosenData[0].status;
    event.preventDefault();
});

    // edit invoice 
    document.getElementById("saveInvoice").addEventListener('click',function (event)
    {
        if(document.getElementById('status').value==""){
            alert("Status ne sme biti prazan!");
            return;
        }
        if(document.getElementById('datumFakture').value > document.getElementById('datumValute').value){
            alert("Datum valute ne sme biti manji od datuma fakture!");
            return;
        }
        var invoice = {
            status: document.getElementById('status').value,
            partner_id: document.getElementById('partner-dropdown').value,
            date_invoice: document.getElementById('datumFakture').value,
            date_currency: document.getElementById('datumValute').value
            }
            console.log('id after click '+ document.getElementById('edit-invoice-dropdown').value);
            var id = document.getElementById('edit-invoice-dropdown').value;
        fetch('http://localhost:8080/salesystem/invoices/' + id, {
        method: 'put',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(invoice)
        })
        .then(res => {
            alert("Faktura je izmenjena");
            window.location.replace("http://localhost:8080/index.html");
        }
        );
        event.preventDefault();
    }  ); 

