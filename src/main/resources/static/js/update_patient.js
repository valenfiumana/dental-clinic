window.addEventListener('load', function () {

    const form = document.querySelector('#update_patient_form');

    form.addEventListener('submit', function (event) {
        let patientId = document.querySelector('#patient_id').value;

        const formData = {
            id: document.querySelector('#patient_id').value,
            lastname: document.querySelector('#lastname').value,
            name: document.querySelector('#name').value,
            idDocument: document.querySelector('#idDocument').value,
            email: document.querySelector('#email').value,
            birthday: document.querySelector('#birthday').value,
            residence: {
                            street: document.querySelector('#street').value,
                            number: document.querySelector('#number').value,
                            city: document.querySelector('#city').value,
                            state: document.querySelector('#state').value,
                        }


        };
        console.log(formData);
        const url = '/patients';
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
          const url = '/patients'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let patient = data;
              document.querySelector('#patient_id').value = patient.id;
              document.querySelector('#name').value = patient.name;
              document.querySelector('#lastname').value = patient.lastname;
              document.querySelector('#idDocument').value = patient.idDocument;
              document.querySelector('#email').value = patient.email;
              document.querySelector('#birthday').value = patient.birthday;
              document.querySelector('#street').value = patient.residence.street;
              document.querySelector('#number').value = patient.residence.number;
              document.querySelector('#city').value = patient.residence.city;
              document.querySelector('#state').value = patient.residence.state;
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }