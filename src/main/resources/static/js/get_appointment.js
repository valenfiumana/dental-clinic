window.addEventListener('load', function () {
    (function(){
      const url = '/appointments';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      console.log(data);
         for(appointment of data){
            var table = document.getElementById("appointmentTable");
            var appointmentRow =table.insertRow();
            let tr_id = 'tr_' + appointment.id;
            appointmentRow.id = tr_id;
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + appointment.id + '\"' +
                                      ' type="button" onclick="deleteBy('+appointment.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + appointment.id + '\"' +
                                      ' type="button" onclick="findBy('+appointment.id+')" class="btn btn-info btn_id">' +
                                      appointment.id +
                                      '</button>';

            appointmentRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_dentist_id\">' + appointment.dentist_id + '</td>' +
                    '<td class=\"td_patient_id\">' + appointment.patient_id + '</td>' +
                    '<td class=\"td_date\">' + appointment.date + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/appointmentList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })