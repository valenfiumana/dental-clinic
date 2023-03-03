window.addEventListener('load', function () {

    const form = document.querySelector('#add_new_patient');

    form.addEventListener('submit', function (event) {

        const formData = {
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
        const url = '/patients';
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
                     '<strong></strong> Patient added </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> There was an error. Try again /strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#lastname').value = "";
        document.querySelector('#name').value = "";
         document.querySelector('#idDocument').value = "";
         document.querySelector('#email').value = "";
         document.querySelector('#birthday').value = "";
         document.querySelector('#street').value = "";
         document.querySelector('#number').value = "";
         document.querySelector('#city').value = "";
         document.querySelector('#state').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/patientList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});