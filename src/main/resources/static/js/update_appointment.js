window.addEventListener('load', function () {

    const form = document.querySelector('#update_appointment_form');

    form.addEventListener('submit', function (event) {
        let appointmentId = document.querySelector('#appointment_id').value;

        const formData = {
            id: document.querySelector('#appointment_id').value,
            dentist_id: document.querySelector('#dentist_id').value,
            patient_id: document.querySelector('#patient_id').value,
            date: document.querySelector('#date').value,

        };

        const url = '/appointments';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    function findBy(id) {
          const url = '/appointments'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
          console.log(data);
              let turno = data;
              document.querySelector('#appointment_id').value = appointment.id;
              document.querySelector('#dentist_id').value = appointment.dentist_id;
              document.querySelector('#patient_id').value = appointment.patient_id;
              document.querySelector('#date').value = appointment.date;
              document.querySelector('#div_appointment_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }