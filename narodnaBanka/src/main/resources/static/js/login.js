const user = localStorage.getItem("salesystem-user");
if(user!=null || user!=""){
  document.getElementById('menu').style.display="block";
  document.getElementById('logout').style.visibility = 'visible';
  document.getElementById('loginForm').style.display="none";
}else{
   document.getElementById('loginForm').style.display="visible";
  document.getElementById('logout').style.visibility = 'hidden';
}

document.getElementById("login").addEventListener('click',function (event)
{
    console.log('asdddd');
    if(document.getElementById('username').value == "" || document.getElementById('password').value ==""){
        alert("Molimo Vas, popunite sve podatke!");
        return;
    }
    var user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        }
    fetch('http://localhost:8080/salesystem/users', {
    method: 'post',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
    }).then(res => res.json())
    .then(res => 
        {
            if(res!=null){
                console.log('this is response after login');
                document.getElementById('loginForm').style.display="none";
                document.getElementById('menu').style.display="block";
                localStorage.setItem("salesystem-user", document.getElementById('username').value);
                localStorage.setItem("salesystem-enterprise", res.enterpriseDTO.enterprise_id);
            }else{
                alert("Podaci nisu ispravni, pokusajte ponovo!");
            }
        }
    );
    event.preventDefault();
}  ); 

document.getElementById("logout").addEventListener('click',function (event){
    localStorage.removeItem("salesystem-user");
    localStorage.removeItem("salesystem-enterprise");
    document.getElementById('menu').style.display="none";
    document.getElementById('logout').style.visibility = 'hidden';
    document.getElementById('loginForm').style.display="block"; 
    event.preventDefault();     
})
