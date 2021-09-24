//read
var servicesArray = [];
readServices();

function readServices() {

    fetch("http://localhost:8080/salesystem/services")
    .then((resp) => resp.json())
    .then(function(data) {
        servicesArray = data;
        console.log('this is array from read' + servicesArray);
        var values = data;
        console.log(values);
        var table = document.getElementById("services-table");
        for (const val of values) {
            var newRow = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var td4 = document.createElement("td");
            var td5 = document.createElement("td");
            td1.innerText= val.name;
            td2.innerText = val.description;
            if(val.goods){
                td3.innerText = "da";
            }else{
                td3.innerText = "ne";
            }
            td4.innerText = val.serviceGroup.name;
            td5.innerText = val.unitOfMeasure.short_name;
            newRow.appendChild(td1);
            newRow.appendChild(td2);
            newRow.appendChild(td3);
            newRow.appendChild(td4);
            newRow.appendChild(td5);
            table.append(newRow);
        }
    })
    .catch(function(error) {
        console.log(error);
    });
}

//add
var GroupSelect = document.getElementById('service-group-dropdown');
var editGroupSelect = document.getElementById('edit-service-group-dropdown');
fetch("http://localhost:8080/salesystem/serviceGroups")
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
    for (const val of values) {
        var option = document.createElement("option");
        var option2 = document.createElement("option");
        option.value = val.id;
        option.text = val.name;
        option2.value = val.id;
        option2.text = val.name;
        GroupSelect.appendChild(option);
        editGroupSelect.appendChild(option2);
      }
  })
.catch(function(error) {
    console.log(error);
});
var UnitSelect = document.getElementById('unit-dropdown');
var editUnitSelect = document.getElementById('edit-unit-dropdown');
fetch("http://localhost:8080/salesystem/unitOfMeasures")
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
    for (const val of values) {
        var option = document.createElement("option");
        var option2 = document.createElement("option");
        option.value = val.id;
        option.text = val.name + " - " + val.short_name;
        option2.value = val.id;
        option2.text = val.name + " - " + val.short_name;
        UnitSelect.appendChild(option);
        editUnitSelect.appendChild(option2);
      }
  })
.catch(function(error) {
    console.log(error);
});

document.getElementById("add-service").addEventListener('click',function (event){
    document.getElementById("add-service-window").style.visibility = "visible";
});

document.getElementById("add-service-button").addEventListener('click',function (event)
    {
        if(document.getElementById('name').value =="" || document.getElementById('description').value == ""
        || document.getElementById('goods').value =="" || document.getElementById('service-group-dropdown').value == null || 
        document.getElementById('unit-dropdown').value == null ){
            alert("Popunite sva polja!");
            return;
        }
        var service = {
            name: document.getElementById('name').value,
            description: document.getElementById('description').value,
            goods: document.getElementById('goods').checked,
            serviceGroup: {
                id: document.getElementById('service-group-dropdown').value
            },
            unitOfMeasure: {
                id: document.getElementById('unit-dropdown').value
            }
            }
        fetch('http://localhost:8080/salesystem/services', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(service)
        }).then(res => res.json())
        .then(res => {
            document.getElementById("add-service-window").style.visibility = "hidden"
            alert('Roba je kreirana!');
            window.location.reload();
        }
            );
        event.preventDefault();
    }  ); 
document.getElementById("cancel-add").addEventListener('click',function (event){
        document.getElementById("add-service-window").style.visibility = "hidden";
    });
    

//delete
var deleteServiceSelect = document.getElementById('delete-service-dropdown');
fetch("http://localhost:8080/salesystem/services")
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
    for (const val of values) {
        var option = document.createElement("option");
        option.value = val.services_id;
        option.text = val.name;
        deleteServiceSelect.appendChild(option);
      }
  })
.catch(function(error) {
    console.log(error);
});
document.getElementById("delete-service").addEventListener('click',function (event){
        document.getElementById("delete-service-window").style.visibility = "visible";
    });
    
    document.getElementById("delete-service-button").addEventListener('click',function (event)
        {
            var id = document.getElementById('delete-service-dropdown').value;
            fetch('http://localhost:8080/salesystem/services/' + id, {
            method: 'delete',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            })
            .then(res => {
                document.getElementById("delete-service-window").style.visibility = "hidden"
                alert('Roba je izbrisana!');
                window.location.reload();
            }
                );
            event.preventDefault();
        }  
    ); 
 document.getElementById("cancel-delete").addEventListener('click',function (event){
  document.getElementById("delete-service-window").style.visibility = "hidden";
        });
        
//edit 
    
var editServiceSelect = document.getElementById('edit-service-dropdown');
fetch("http://localhost:8080/salesystem/services")
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
    for (const val of values) {
        var option = document.createElement("option");
        option.value = val.services_id;
        option.text = val.name;
        editServiceSelect.appendChild(option);
      }
  })
.catch(function(error) {
    console.log(error);
});

document.getElementById("edit-service").addEventListener('click',function (event){
    document.getElementById("edit-service-window").style.visibility = "visible";
});

document.getElementById("fillOutServiceData").addEventListener('click',function (event){
    var selectedId = document.getElementById('edit-service-dropdown').value;
    if(!selectedId){
        alert("Morate izabrati robu za izmenu!");
        return;
    }
    var chosenData = servicesArray.filter(service => service.services_id == selectedId);
    document.getElementById('edit-name').value = chosenData[0].name;
    document.getElementById('edit-description').value = chosenData[0].description;
    event.preventDefault();
});

document.getElementById("edit-service-button").addEventListener('click',function (event)
    {
        //find selected item from checkbox
        var serviceSelected = document.getElementById('edit-service-dropdown').value;
        //fill in the fields from selected item
        var service = {
            name: document.getElementById('edit-name').value,
            description: document.getElementById('edit-description').value,
            goods: document.getElementById('edit-goods').checked,
            serviceGroup: {
                id: document.getElementById('edit-service-group-dropdown').value
            },
            unitOfMeasure: {
                id: document.getElementById('edit-unit-dropdown').value
            }
            }
        fetch('http://localhost:8080/salesystem/services/' + serviceSelected, {
        method: 'put',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(service)
        })
        .then(res => {
            document.getElementById("edit-service-window").style.visibility = "hidden"
            alert('Roba je izmenjena!');
            window.location.reload();
        }
            );
        event.preventDefault();
    }  ); 

document.getElementById("cancel-edit").addEventListener('click',function (event){
    document.getElementById("edit-service-window").style.visibility = "hidden";
});


