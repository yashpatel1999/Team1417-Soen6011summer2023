const params = new URLSearchParams(window.location.search);
console.log(params);
var id = params.get("id");
console.log(id);
var data = new Object();
// console.log(data)
// data.id=id;
let url="http://localhost:9997/getJobs?id="+id;
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
        const jobSearch = document.querySelector(".jobs-list-container .job-search");
        let searchTerm = "";
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

            let deadline = jobPosting.deadline;
            let currentDate = new Date();
            let applyButton = document.createElement("a");
            if(compareDates(deadline,currentDate))
            {
                jobCard.style.backgroundColor = "lightpink";
            }

            applyButton.innerHTML = "Click Here";
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
            th:window.location.href = `http://localhost:9997/getSingleJobEmp?id=${jobId}&eid=`+id;
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
        jobSearch.addEventListener("input", (e) => {
            searchTerm = e.target.value;
            console.log(searchTerm);
            handleSearch(data1);
        });
        function handleSearch(jobPostings)
        {
            console.log("here in handle search")
            const filteredJobs = jobPostings.filter(job =>
                job.job_title.toLowerCase().includes(searchTerm));
            displayJobPostings(filteredJobs);
        }

        function compareDates(d1, d2){
            let deadline = new Date(d1).getTime();
            let currentdate = new Date(d2).getTime();

            if (deadline < currentdate) {
                return true;
            }
            return false;
        }
    });

