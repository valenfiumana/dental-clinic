window.addEventListener('load', function () {
    (function(){
      const url = '/patients';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      console.log(data);
         for(patient of data){
            var table = document.getElementById("patientTable");
            var patientRow =table.insertRow();
            let tr_id = 'tr_' + patient.id;
            patientRow.id = tr_id;
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + patient.id + '\"' +
                                      ' type="button" onclick="deleteBy('+patient.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + patient.id + '\"' +
                                      ' type="button" onclick="findBy('+patient.id+')" class="btn btn-info btn_id">' +
                                      patient.id +
                                      '</button>';

            patientRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_lastname\">' + patient.lastname.toUpperCase() + '</td>' +
                    '<td class=\"td_name\">' + patient.name.toUpperCase() + '</td>' +
                    '<td class=\"td_idDocument\">' + patient.idDocument + '</td>' +
                    '<td class=\"td_email\">' + patient.email + '</td>' +
                    '<td class=\"td_birthday\">' + patient.birthday + '</td>' +
                    '<td class=\"td_street\">' + patient.residence.street + '</td>' +
                    '<td class=\"td_number\">' +  patient.residence.number + '</td>' +
                    '<td class=\"td_city\">' +  patient.residence.city + '</td>' +
                    '<td class=\"td_state\">' + patient.residence.state + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "pacienteList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })