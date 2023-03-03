window.addEventListener('load', function () {

    const form = document.querySelector('#add_new_appointment');

    form.addEventListener('submit', function (event) {

        const formData = {
            dentist_id: document.querySelector('#dentist_id').value,
            patient_id: document.querySelector('#patient_id').value,
            date: document.querySelector('#date').value,

        };
        const url = '/appointments';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Appointment added </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> There was an error, try again later</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#dentist_id').value = "";
        document.querySelector('#patient_id').value = "";
        document.querySelector('#date').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/appointmentList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});