const params = new URLSearchParams(window.location.search);
console.log(params);
var id = params.get("id");
console.log(id);
let data = new Object();
// console.log(data)
// data.id=id;
let url = "http://localhost:9997/getAllJobs?id=" + id;
console.log(url);
// $(".empty-screen").html = "";
fetch(url, {
    method: 'POST',
    body: JSON.stringify(data),
}).then((response) => response.json())
    .then((data) => {
        console.log(data)
        var data1 = data.Job_details;
        const jobsHeading = document.querySelector(".jobs-list-container h2");
        // const jobsContainer = document.querySelector(".jobs-list-container .jobs");
        // const jobSearch = document.querySelector(".jobs-list-container .job-search");
        console.log(data1);

        if(data1.length>0)
        {
            jobsHeading.innerHTML = `${data1.length} Jobs`;
        }
        function createJobPostingCard(jobPosting) {
            let jobCard = document.createElement("div");
            jobCard.classList.add("job");

            let image = document.createElement("img");
            image.src = "images/software-engineer.svg";

            let title = document.createElement("h3");
            title.innerHTML = jobPosting.job_title;
            title.classList.add("job-title");

            let company_name = document.createElement("div");
            // console.log(job.company_name);
            company_name.innerHTML = jobPosting.company_name;
            company_name.classList.add("cName");

            let company_location = document.createElement("h4");
            company_location.innerHTML = jobPosting.company_location;
            company_location.classList.add("cLoc");

            let details = document.createElement("div");
            details.innerHTML = jobPosting.job_desc;
            details.classList.add("details");

            let applyButton = document.createElement("a");
            applyButton.innerHTML = "Apply Here";
            applyButton.classList.add("details-btn");
            applyButton.addEventListener("click", () => {
                redirectToJobPage(jobPosting.job_id);
            });

            jobCard.appendChild(image);
            jobCard.appendChild(company_name);
            jobCard.appendChild(company_location);
            jobCard.appendChild(title);
            jobCard.appendChild(details);
            jobCard.appendChild(applyButton);

            return jobCard;
        }

        function redirectToJobPage(jobId) {
            th:window.location.href = `http://localhost:9997/getSingleJob?id=${jobId}&cid=`+id;
            console.log(jobId);

        }

        function displayJobPostings(jobPostings) {
            const jobPostingsContainer = document.querySelector(".jobs-list-container .jobs");
            jobPostingsContainer.innerHTML = "";

            jobPostings.forEach((jobPosting) => {
                const jobCard = createJobPostingCard(jobPosting);
                jobPostingsContainer.appendChild(jobCard);
            });
        }

        displayJobPostings(data1);

    });














// $(document).ready(function () {
//     const params = new URLSearchParams(window.location.search);
//     console.log(params);
//     var id = params.get("id");
//     console.log(id);
//     var data = new Object();
//     // console.log(data)
//     // data.id=id;
//     let url="http://localhost:9997/getAllJobs?id="+id;
//     console.log(url);
//     // $(".empty-screen").html = "";
//     fetch(url, {
//         method: 'POST',
//         body: JSON.stringify(data),
//     }).then((response) => response.json())
//         .then((data) => {
//             console.log(data)
//             var data1=data.Job_details;
//             // const jobs=data1[0];
//             // var job_id=jobs.job_id;
//             // console.log(data1.length);
//             // console.log(job_id);
//             //
//             const jobsHeading = document.querySelector(".jobs-list-container h2");
//             const jobsContainer = document.querySelector(".jobs-list-container .jobs");
//             const jobSearch = document.querySelector(".jobs-list-container .job-search");
//
//             let searchTerm = "";
//
//             var data1=data.Job_details;
//             var data2=data.emp_details;
//             var j_ids=[];
//             if(data1.length>0)
//             {
//                 jobsHeading.innerHTML = `${data1.length} Jobs`;
//             }
//
//             const createJobListingCards = () => {
//                 jobsContainer.innerHTML = "";
//                 for(var i=0;i<data1.length;i++)
//                 {
//                     let jobCard = document.createElement("div");
//                     jobCard.classList.add("job");
//
//                     let image = document.createElement("img");
//                     image.src="images/software-engineer.svg";
//
//                     let title = document.createElement("h3");
//                     title.innerHTML = data1[i].job_title;
//                     title.classList.add("job-title");
//
//                     let company_name = document.createElement("div");
//                     // console.log(job.company_name);
//                     company_name.innerHTML= data1[i].company_name;
//                     company_name.classList.add("cName");
//
//                     let company_location = document.createElement("h4");
//                     company_location.innerHTML= data1[i].company_location;
//                     company_location.classList.add("cLoc");
//
//                     let details = document.createElement("div");
//                     details.innerHTML = data1[i].job_desc;
//                     details.classList.add("details");
//
//                     // let postedBy = document.createElement("div");
//
//
//
//                     let detailsBtn = document.createElement("button");
//                     detailsBtn.classList.add("details-btn");
//                     // detailsBtn.id = 'btn-id_'+i;
//                     // $(detailsBtn).addClass("details-btn");
//                     detailsBtn.innerHTML = "Apply Here";
//                     detailsBtn.id = data1[i].job_id;
//                     j_ids.push(detailsBtn.id);
//
//                     // detailsBtn.addEventListener('click',function (){
//                     //    // detailsBtn.id = data1[i].job_id;
//                     //    applyForJob(data1[i].job_id);
//                     // });
//
//                     // $(".details-btn").click(function (){
//                     //     console.log("clicked");
//                     //     $("add-application").submit();
//                     //     console.log("after submit");
//                     // });
//
//                     console.log(detailsBtn.id);
//
//
//
//                     let name1 = document.createElement("div");
//                     let fname = data2[i].fname;
//                     let lname = data2[i].lname;
//                     name1.innerHTML = "posted by: "+fname+" "+lname;
//                     console.log(name1);
//                     name1.classList.add("nameEmp");
//
//                     jobCard.appendChild(image);
//                     jobCard.appendChild(company_name);
//                     jobCard.appendChild(company_location);
//                     jobCard.appendChild(title);
//                     jobCard.appendChild(details);
//                     jobCard.appendChild(detailsBtn);
//                     jobCard.appendChild(name1);
//                     jobsContainer.appendChild(jobCard);
//
//
//                     // console.log(data1[1].job_id)
//                     // document.getElementById('job_id').value =data1[i].job_id;
//                 }
//
//                 console.log(j_ids);
//             };
//
//             // function applyForJob(jobId)
//             // {
//             //     // var form = document.getElementById("add-application");
//             //     // document.getElementById(jobId).addEventListener("click", function () {
//             //     //     console.log(jobId);
//             //     //     console.log("clicked");
//             //     //     document.getElementById("j_id").value = jobId;
//             //     //     document.getElementById("c_id").value = id;
//             //     //     form.submit();
//             //     // });
//             //     console.log(`applying for job ${jobId}`);
//             // }
//
//
//             createJobListingCards();
//
//
//             jobSearch.addEventListener("input",(e) => {
//                 searchTerm= e.target.value;
//                 createJobListingCards();
//             });
//                 for (var j = 0; j < j_ids.length; j++) {
//                     var form = document.getElementById("add-application");
//                     document.getElementById(j_ids[j]).addEventListener("click", function () {
//                         // let job_id = data1[j].job_id;
//                         // console.log(job_id);
//                         console.log("clicked");
//                         document.getElementById("j_id").value = j_ids[j];
//                         document.getElementById("c_id").value = id;
//                         form.submit();
//                     });
//                 }
//
//             // let job_id = data1[0].job_id;
//             //     var form = document.getElementById("add-application");
//             //     document.getElementById("btn-id_2").addEventListener("click", function () {
//             //
//             //                 console.log(job_id);
//             //                 console.log("clicked");
//             //                 document.getElementById("j_id").value = job_id;
//             //                 document.getElementById("c_id").value = id;
//             //                 form.submit();
//             //             });
//         });
// });
//
//
//
//
//
