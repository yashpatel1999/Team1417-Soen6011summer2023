document.addEventListener("DOMContentLoaded", function (event) {
    const params = new URLSearchParams(window.location.search);
    console.log(params);
    var cid = params.get("id");
    console.log(cid);
    let data = new Object();
    let url = "http://localhost:9997/postCandidateResumeData?cid=" + cid;
    console.log(url);
// $(".empty-screen").html = "";
    fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
    }).then((response) => response.json())
        .then((data) => {
            console.log(data);
            var data1 = data.can_details;
            var details = data1[0];

            document.getElementById("email-id12").value = details.email_id;
            document.getElementById("fname").value = details.fname;
            document.getElementById("lname").value = details.lname;
            // document.getElementById("dob").value = details.date_of_birth;

            const editButton = document.getElementById("edit");
            editButton.addEventListener("click", () => {
                removeDisabled()
            });

            function removeDisabled() {
                console.log("here");
                var inputs = document.getElementsByClassName("editable-field");
                console.log(inputs);
                for (var i = 0; i < inputs.length; i++) {
                    inputs[i].disabled = false;
                }
            }


            // var resumeRegistrationForm = document.querySelector("#resumeBuilder .register-button");
            // resumeRegistrationForm.addEventListener('click', function (event) {
            //     event.preventDefault();
            //     var form = this.parentElement;
            //     console.log("-->"+form.value);
            //     var fname = form.querySelector("#fname");
            //     var lname = form.querySelector("#lname");
            //     var email = form.querySelector("#email-id12");
            //     var dob = form.querySelector("#dob");
            //     var project = form.querySelector("#projects");
            //     var experience = form.querySelector("#workExperience");
            //     var skills = form.querySelector("#skills");
            //     var contact = form.querySelector("#contactNumber");
            //     var education = form.querySelector("#education");
            //     console.log(fname + " " + lname + " " + email + " " + dob + " " + project);
            //     var validationArr = [fname, lname, email, dob, project, experience, skills, contact, education];
            //     console.log(validationArr);
            //     var shouldReturn = false;
            //     for (let index = 0; index < validationArr.length; index++) {
            //         const element = validationArr[index];
            //         console.log(element)
            //         if (element.value.trim().length == 0) {
            //             element.parentElement.classList.add("aleart");
            //             shouldReturn = true;
            //         } else {
            //             element.parentElement.classList.remove("aleart");
            //         }
            //     }
            //     if (shouldReturn) {
            //         return false;
            //     }
            //     ;
            //     resumeRegistrationForm.submit();
            // });


        });
});