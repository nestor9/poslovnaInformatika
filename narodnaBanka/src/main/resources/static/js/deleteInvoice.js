///DELETE 
var invoiceSelect = document.getElementById('delete-invoice-dropdown');
fetch("http://localhost:8080/salesystem/invoices/enterprise/" + localStorage.getItem("salesystem-enterprise"))
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
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

document.getElementById("delete-invoice-button").addEventListener('click',function (event)
        {
            var id = document.getElementById('delete-invoice-dropdown').value;
            fetch('http://localhost:8080/salesystem/invoices/' + id, {
            method: 'delete',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            }).then(res => {
                alert('Faktura je izbrisana!');
                window.location.replace("http://localhost:8080/index.html")
            }
                );
            event.preventDefault();
        }  
    ); 
