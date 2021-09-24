//read
readUnits();

var unitsOfMeasureArray = [];

function readUnits(){

    fetch("http://localhost:8080/salesystem/unitOfMeasures")
    .then((resp) => resp.json())
    .then(function(data) {
        var values = data;
        unitsOfMeasureArray = data;
        console.log(values);
        var table = document.getElementById("units-table");
        for (const val of values) {
            var newRow = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            td1.innerText= val.name;
            td2.innerText = val.short_name;
            newRow.appendChild(td1);
            newRow.appendChild(td2);
            table.append(newRow);
        }
    })
    .catch(function(error) {
        alert(error);
    });
}


//add

document.getElementById("add-unit").addEventListener('click',function (event){
    document.getElementById("add-unit-window").style.visibility = "visible";
});

document.getElementById("add-unit-button").addEventListener('click',function (event)
    {
        if(document.getElementById('name').value =="" || document.getElementById('short-name').value == ""){
            alert("Popunite sva polja!");
            return;
        }
        var unitOfMeasure = {
            name: document.getElementById('name').value,
            short_name: document.getElementById('short-name').value,
            }
        fetch('http://localhost:8080/salesystem/unitOfMeasures', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(unitOfMeasure)
        }).then(res => res.json())
        .then(res => { 
            document.getElementById("add-unit-window").style.visibility = "hidden";
            alert('Jedinica mere je kreirana!');
            window.location.reload();
        }
            );
        event.preventDefault();
    }  ); 

    document.getElementById("cancel-add").addEventListener('click',function (event){
        document.getElementById("add-unit-window").style.visibility = "hidden";
    });
    

//delete
var deleteUnitSelect = document.getElementById('delete-unit-dropdown');
fetch("http://localhost:8080/salesystem/unitOfMeasures")
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
    for (const val of values) {
        var option = document.createElement("option");
        option.value = val.id;
        option.text = val.name + " - " + val.short_name;
        deleteUnitSelect.appendChild(option);
      }
  })
.catch(function(error) {
    console.log(error);
});

document.getElementById("delete-unit").addEventListener('click',function (event){
        document.getElementById("delete-unit-window").style.visibility = "visible";
    });
    
    document.getElementById("delete-unit-button").addEventListener('click',function (event)
        {
            //find selected item from checkbox
            var unitSelected = document.getElementById('delete-unit-dropdown').value;
            console.log(unitSelected);
            fetch('http://localhost:8080/salesystem/unitOfMeasures/' + unitSelected, {
            method: 'DELETE',
            })
            .then(res => {
                document.getElementById("delete-unit-window").style.visibility = "hidden"
                alert('Jedinica mere je izbrisana!');
                window.location.reload();
            }
                );
            event.preventDefault();
        }  ); 

//fill in the table
document.getElementById("cancel-delete").addEventListener('click',function (event){
    document.getElementById("delete-unit-window").style.visibility = "hidden";
});

//edit 
//fillOutUnitData
var editUnitSelect = document.getElementById('edit-unit-dropdown');
fetch("http://localhost:8080/salesystem/unitOfMeasures")
.then((resp) => resp.json())
.then(function(data) {
    var values = data;
    for (const val of values) {
        var option = document.createElement("option");
        option.value = val.id;
        option.text = val.name + " - " + val.short_name;
        editUnitSelect.appendChild(option);
      }
  })
.catch(function(error) {
    console.log(error);
});
document.getElementById("edit-unit").addEventListener('click',function (event){
    document.getElementById("edit-unit-window").style.visibility = "visible";
});

//novi deo za popunjenu formu : 

document.getElementById("fillOutUnitData").addEventListener('click',function (event){
    var selectedId = document.getElementById('edit-unit-dropdown').value;
    if(!selectedId){
        alert("Morate izabrati jedinicu mere za izmenu!");
        return;
    }
    var chosenUnitData = unitsOfMeasureArray.filter(unit => unit.id == selectedId);
    document.getElementById('edit-name').value = chosenUnitData[0].name;
    document.getElementById('edit-short-name').value = chosenUnitData[0].short_name;
    event.preventDefault();
});


document.getElementById("edit-unit-button").addEventListener('click',function (event)
    {
        //find selected item from checkbox
        var unitSelected = document.getElementById('edit-unit-dropdown').value;
        if(!unitSelected){
            alert("Niste izabrali jedinicu mere za izmenu!");
            return;
        }
        var unitOfMeasure = {
            name: document.getElementById('edit-name').value,
            short_name: document.getElementById('edit-short-name').value,
            }
        fetch('http://localhost:8080/salesystem/unitOfMeasures/' + unitSelected, {
        method: 'put',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(unitOfMeasure)
        }).then(res => res.json())
        .then(res => { 
            document.getElementById("edit-unit-window").style.visibility = "hidden";
            alert("Jedinica mere je izmenjena!");
            window.location.reload();
        }
            );
        event.preventDefault();
    }  ); 

document.getElementById("cancel-edit").addEventListener('click',function (event){
    document.getElementById("edit-unit-window").style.visibility = "hidden";
});

