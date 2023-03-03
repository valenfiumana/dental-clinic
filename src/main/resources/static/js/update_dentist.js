window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_dentist_form');

    formulario.addEventListener('submit', function (event) {
        let dentistId = document.querySelector('#dentist_id').value;

        const formData = {
            id: document.querySelector('#dentist_id').value,
            name: document.querySelector('#name').value,
            lastname: document.querySelector('#lastname').value,
            licensure: document.querySelector('#licensure').value,

        };

        const url = '/dentists';
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
          const url = '/dentists'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let dentist = data;
              document.querySelector('#odentist_id').value = dentist.id;
              document.querySelector('#name').value = dentist.name;
              document.querySelector('#lastname').value = dentist.lastname;
              document.querySelector('#licensure').value = dentist.licensure;
              document.querySelector('#div_dentist_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }